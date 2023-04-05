package cme;

import java.math.BigDecimal;
import java.util.ArrayList;

public class StudentRateCalculationStrategy implements RateCalculationStrategy {
    @Override
    public BigDecimal calculateRate(Period periodStay, BigDecimal hourlyNormalRate, BigDecimal hourlyReducedRate, ArrayList<Period> normalPeriods, ArrayList<Period> reducedPeriods) {
        int normalRateHours = periodStay.occurences(normalPeriods);
        int reducedRateHours = periodStay.occurences(reducedPeriods);
        BigDecimal calculatedCost = (hourlyNormalRate.multiply(BigDecimal.valueOf(normalRateHours))).add(
                hourlyReducedRate.multiply(BigDecimal.valueOf(reducedRateHours)));

        BigDecimal threshold = BigDecimal.valueOf(5.50);
        if (calculatedCost.compareTo(threshold) > 0) {
            BigDecimal discountAmount = calculatedCost.subtract(threshold);
            BigDecimal discount = discountAmount.multiply(BigDecimal.valueOf(0.33));
            calculatedCost = threshold.add(discountAmount.subtract(discount));
        }
        return calculatedCost;
    }
}
