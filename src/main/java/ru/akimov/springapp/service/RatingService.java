package ru.akimov.springapp.service;

import org.springframework.stereotype.Service;
import ru.akimov.springapp.persistence.domain.Calculation;
import ru.akimov.springapp.persistence.domain.user.Contractor;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RatingService {

    private static final float FIRST_CATEGORY_WEIGHT = 0.4f;
    private static final float SECOND_CATEGORY_WEIGHT = 0.4f;
    private static final float THIRD_CATEGORY_WEIGHT = 0.15f;
    private static final float OTHER_CATEGORY_WEIGHT = 0.2f;
    private static final float USER_RATING_WEIGHT = 0.2f;
    private static final float CALCULATED_RATING_WEIGHT = 0.8f;

    public ArrayList<Contractor> getRangedList(List<Contractor> contractors, Calculation calc) {
        return new ArrayList<>(getMap(contractors, calc).keySet());
    }

    private Map<Contractor, Float> getMap(List<Contractor> contractors, Calculation calc) {
        Map<Contractor, Float> result = new HashMap<>();

        for (Contractor con : contractors) {
            float calculatedRating = getContractorsRating(con, calc);
            float usersRating  = (float) ((con.getRating() / con.getRatingNum()));
            float resultRating = (USER_RATING_WEIGHT * usersRating) + (CALCULATED_RATING_WEIGHT * calculatedRating);
            result.put(con, resultRating);
        }
        return result.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (oldVal, newVal) -> oldVal, LinkedHashMap::new));
    }

    private float getContractorsRating(Contractor con, Calculation calc) {
        float result =
                get1stCriteriaRating(con, calc) + get2ndCriteriaRating(con, calc) +
                        get3rdCriteriaRating(con, calc) + getOtherCriteriaRating(con, calc);

        return result * 0.8f;
    }

    private static float get1stCriteriaRating(Contractor con, Calculation calc) {

        int rating = 0;

        if (con.getTechFoundation().contains(calc.getTechFoundation().getWord())) {
            rating++;
        }
        if (con.getTechCarrierWall().contains(calc.getTechCarrierWall().getWord())) {
            rating++;
        }
        if (con.getTechOverlaps().contains(calc.getTechOverlaps().getWord())) {
            rating++;
        }
        if (con.getTechRoof().contains(calc.getTechRoof().getWord())) {
            rating++;
        }
        return rating * FIRST_CATEGORY_WEIGHT;
    }

    private static float get2ndCriteriaRating(Contractor con, Calculation calc) {

        int rating = 0;

        if (con.getMinWidth() <= calc.getWidth() && con.getMaxWidth() >= calc.getWidth()) {
            rating++;
        }
        if (con.getMinFloors() <= calc.getFloors() && con.getMaxFloors() >= calc.getFloors()) {
            rating++;
        }
        if (con.getMinLength() <= calc.getLength() && con.getMaxLength() >= calc.getLength()) {
            rating++;
        }
        if (con.getMinHeight() <= calc.getHeight() && con.getMaxHeight() >= calc.getHeight()) {
            rating++;
        }
        return rating * SECOND_CATEGORY_WEIGHT;
    }

    private static float get3rdCriteriaRating(Contractor con, Calculation calc) {

        int rating = 0;

        if (con.getTechPartitions().equals(calc.getTechPartitions())) {
            rating++;
        }
        if (con.getTechExterior().contains(calc.getTechExterior().getWord())) {
            rating++;
        }
        if (con.getTechInterior().contains(calc.getTechInterior().getWord())) {
            rating++;
        }
        if (con.getTechLadder().contains(calc.getTechLadder().getWord())) {
            rating++;
        }
        return rating * THIRD_CATEGORY_WEIGHT;
    }

    private static float getOtherCriteriaRating(Contractor con, Calculation calc) {
        int rating = 0;
        if (con.getTechWindow().equals(calc.getTechWindow())) {
            rating++;
        }
        return rating * OTHER_CATEGORY_WEIGHT;
    }
}
