package cme;

import java.math.BigDecimal;
import java.util.ArrayList;

public class StaffRateCalculationStrategy implements RateCalculationStrategy {
    @Override
    public BigDecimal calculateRate(Period periodStay, BigDecimal hourlyNormalRate, BigDecimal hourlyReducedRate, ArrayList<Period> normalPeriods, ArrayList<Period> reducedPeriods) {
        int normalRateHours = periodStay.occurences(normalPeriods);
        int reducedRateHours = periodStay.occurences(reducedPeriods);
        BigDecimal calculatedCost = (hourlyNormalRate.multiply(BigDecimal.valueOf(normalRateHours))).add(
                hourlyReducedRate.multiply(BigDecimal.valueOf(reducedRateHours)));

        BigDecimal maxPayable = BigDecimal.valueOf(10);
        if (calculatedCost.compareTo(maxPayable) > 0) {
            calculatedCost = maxPayable;
        }
        return calculatedCost;
    }
}
