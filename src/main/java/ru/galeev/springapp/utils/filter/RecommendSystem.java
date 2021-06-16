package ru.galeev.springapp.utils.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.galeev.springapp.persistence.domain.Event;
import ru.galeev.springapp.persistence.domain.Matrix;
import ru.galeev.springapp.persistence.domain.MatrixPK;
import ru.galeev.springapp.persistence.domain.User;
import ru.galeev.springapp.persistence.repository.MatrixRepository;
import ru.galeev.springapp.utils.filter.contentbased.ContentBasedFilter;
import ru.galeev.springapp.utils.filter.itembased.ItemBasedFilter;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecommendSystem {

    @Autowired
    ContentBasedFilter cbFilter;
    @Autowired
    ItemBasedFilter ibFilter;
    @Autowired
    MatrixRepository matrixRepository;

    private static final int K_R = 5; // Количество рекомендованных мероприятий
    private static final int K = 3; // Количество мероприятий для начала работы коллаборативной фильтрации

    public LinkedHashMap<Event, Double> getRecommends(User user) {
        LinkedHashMap<Event, Double> map = getRecommendsMap(user);
        for (Map.Entry<Event, Double> mE : map.entrySet()) {
            Matrix m = matrixRepository.findByMatrixPK(new MatrixPK(user, mE.getKey()));
            m.setFilteredScore(mE.getValue());
            matrixRepository.saveAndFlush(m);
        }
        return map;
    }

    public LinkedHashMap<Event, Double> getRecommendsMap(User user) {

        List<Matrix> mList = matrixRepository.findByUser(user);
        if (mList == null) {
            return null;
        }
        int flag = isNoScored(mList);

        LinkedHashMap<Event, Double> cbMap = cbFilter.getRecommendedList(user);
        LinkedHashMap<Event, Double> ibMap = flag < K ? null : ibFilter.getRecommendedList(user);

        if (flag < K) {
            Iterator<Map.Entry<Event, Double>> itr = cbMap.entrySet().iterator();
            for (int i = 0; i < K_R && itr.hasNext(); i++) {
                Map.Entry<Event, Double> obj = itr.next();
                Matrix m = matrixRepository.findByMatrixPK(new MatrixPK(user, obj.getKey()));
                m.setFilteredScore(obj.getValue());
            }
            return cbMap;
        } else {
            LinkedHashMap<Event, Double> resultMap = new LinkedHashMap<>();
            Iterator<Map.Entry<Event, Double>> cbItr = cbMap.entrySet().iterator();
            Iterator<Map.Entry<Event, Double>> ibItr = ibMap.entrySet().iterator();
            int i = 0;
            while (i <= K_R) {
                if (cbItr.hasNext()) {
                    Map.Entry<Event, Double> cb = cbItr.next();

                    if (ibItr.hasNext()) {
                        Map.Entry<Event, Double> ib = ibItr.next();
//                        resultMap.put(ib.getKey(), ib.getValue());

                        if (ib.getKey().equals(cb.getKey())) {
                            resultMap.put(ib.getKey(), ib.getValue());
                        } else {
                            resultMap.put(ib.getKey(), ib.getValue());
                        }
                    } else {
                        Iterator<Map.Entry<Event, Double>> itr = cbMap.entrySet().iterator();
                        while (i <= K_R && itr.hasNext()) {
                            Map.Entry<Event, Double> n = itr.next();
                            if (!resultMap.containsKey(n.getKey())) {
                                resultMap.put(n.getKey(), n.getValue());
                                i++;
                            }
                        }
                    }
                }
                i++;
            }
            return resultMap;
        }
    }

    private int isNoScored(List<Matrix> mList) {
        int i = 0;
        for (Matrix m : mList) {
            if (m.getScore() != 0) {
                i++;
            }
        }
        return i;
    }

}
