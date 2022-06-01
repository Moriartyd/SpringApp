package ru.galeev.springapp.utils.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.galeev.springapp.persistence.domain.Calculation;
import ru.galeev.springapp.persistence.domain.ContractRequest;
import ru.galeev.springapp.persistence.domain.user.User;
import ru.galeev.springapp.persistence.repository.ContractRequestRepository;
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
    ContractRequestRepository contractRequestRepository;

    private static final int K_R = 5; // Количество рекомендованных мероприятий
    private static final int K = 3; // Количество мероприятий для начала работы коллаборативной фильтрации

    public LinkedHashMap<Calculation, Double> getRecommends(User user) {
        LinkedHashMap<Calculation, Double> map = getRecommendsMap(user);
        for (Map.Entry<Calculation, Double> mE : map.entrySet()) {
            ContractRequest m = contractRequestRepository.findByMatrixPK(new MatrixPK(user, mE.getKey()));
            m.setFilteredScore(mE.getValue());
            contractRequestRepository.saveAndFlush(m);
        }
        return map;
    }

    public LinkedHashMap<Calculation, Double> getRecommendsMap(User user) {

        List<ContractRequest> mList = contractRequestRepository.findByUser(user);
        if (mList == null) {
            return null;
        }
        int flag = isNoScored(mList);

        LinkedHashMap<Calculation, Double> cbMap = cbFilter.getRecommendedList(user);
        LinkedHashMap<Calculation, Double> ibMap = flag < K ? null : ibFilter.getRecommendedList(user);

        if (flag < K) {
            Iterator<Map.Entry<Calculation, Double>> itr = cbMap.entrySet().iterator();
            for (int i = 0; i < K_R && itr.hasNext(); i++) {
                Map.Entry<Calculation, Double> obj = itr.next();
                ContractRequest m = contractRequestRepository.findByMatrixPK(new MatrixPK(user, obj.getKey()));
                m.setFilteredScore(obj.getValue());
            }
            return cbMap;
        } else {
            LinkedHashMap<Calculation, Double> resultMap = new LinkedHashMap<>();
            Iterator<Map.Entry<Calculation, Double>> cbItr = cbMap.entrySet().iterator();
            Iterator<Map.Entry<Calculation, Double>> ibItr = ibMap.entrySet().iterator();
            int i = 0;
            while (i <= K_R) {
                if (cbItr.hasNext()) {
                    Map.Entry<Calculation, Double> cb = cbItr.next();

                    if (ibItr.hasNext()) {
                        Map.Entry<Calculation, Double> ib = ibItr.next();
//                        resultMap.put(ib.getKey(), ib.getValue());

                        if (ib.getKey().equals(cb.getKey())) {
                            resultMap.put(ib.getKey(), ib.getValue());
                        } else {
                            resultMap.put(ib.getKey(), ib.getValue());
                        }
                    } else {
                        Iterator<Map.Entry<Calculation, Double>> itr = cbMap.entrySet().iterator();
                        while (i <= K_R && itr.hasNext()) {
                            Map.Entry<Calculation, Double> n = itr.next();
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

    private int isNoScored(List<ContractRequest> mList) {
        int i = 0;
        for (ContractRequest m : mList) {
            if (m.getScore() != 0) {
                i++;
            }
        }
        return i;
    }

}
