package cme;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ManagementRateCalculationStrategy implements RateCalculationStrategy {
    @Override
    public BigDecimal calculateRate(Period periodStay, BigDecimal hourlyNormalRate, BigDecimal hourlyReducedRate, ArrayList<Period> normalPeriods, ArrayList<Period> reducedPeriods) {
        int normalRateHours = periodStay.occurences(normalPeriods);
        int reducedRateHours = periodStay.occurences(reducedPeriods);
        BigDecimal calculatedCost = (hourlyNormalRate.multiply(BigDecimal.valueOf(normalRateHours))).add(
                hourlyReducedRate.multiply(BigDecimal.valueOf(reducedRateHours)));

        BigDecimal minimumPayable = BigDecimal.valueOf(5);
        if (calculatedCost.compareTo(minimumPayable) < 0) {
            throw new IllegalArgumentException("Minimum payable is 5.00 for MANAGEMENT");
        }
        return calculatedCost;
    }
}
