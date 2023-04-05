package cme;

import java.math.BigDecimal;
import java.util.ArrayList;

public class VisitorRateCalculationStrategy implements RateCalculationStrategy {
    @Override
    public BigDecimal calculateRate(Period periodStay, BigDecimal hourlyNormalRate, BigDecimal hourlyReducedRate, ArrayList<Period> normalPeriods, ArrayList<Period> reducedPeriods) {
        int normalRateHours = periodStay.occurences(normalPeriods);
        int reducedRateHours = periodStay.occurences(reducedPeriods);
        BigDecimal calculatedCost = (hourlyNormalRate.multiply(BigDecimal.valueOf(normalRateHours))).add(
                hourlyReducedRate.multiply(BigDecimal.valueOf(reducedRateHours)));

        BigDecimal freeAmount = BigDecimal.valueOf(10);
        if (calculatedCost.compareTo(freeAmount) <= 0) {
            return BigDecimal.ZERO;
        } else {
            return calculatedCost.subtract(freeAmount).multiply(BigDecimal.valueOf(0.5));
        }
    }
}
