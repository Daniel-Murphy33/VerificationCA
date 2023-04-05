package cme;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface RateCalculationStrategy {
    BigDecimal calculateRate(Period periodStay, BigDecimal hourlyNormalRate, BigDecimal hourlyReducedRate, ArrayList<Period> normalPeriods, ArrayList<Period> reducedPeriods);
}
