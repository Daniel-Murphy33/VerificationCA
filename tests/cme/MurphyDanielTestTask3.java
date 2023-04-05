package cme;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MurphyDanielTestTask3 {

    public static final int MAX_INT = 200;


    /*
     * Rate Constructor Test Cases
     */

    @Test
    public void testValidRates() {
        BigDecimal normalRate = BigDecimal.valueOf(10);
        BigDecimal reducedRate = BigDecimal.valueOf(5);
        CarParkKind kind = CarParkKind.STUDENT;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(2, 5));
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(1, 2));
        Rate rate = new Rate(normalRate, reducedRate, kind, normalPeriods, reducedPeriods, new StudentRateCalculationStrategy());
        assertNotNull(rate);
    }

    @Test
    public void testNonOverlappingPeriods() {
        BigDecimal normalRate = BigDecimal.valueOf(10);
        BigDecimal reducedRate = BigDecimal.valueOf(5);
        CarParkKind kind = CarParkKind.STUDENT;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(2, 5));
        normalPeriods.add(new Period(8, 10));
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(1, 2));
        reducedPeriods.add(new Period(6, 7));
        Rate rate = new Rate(normalRate, reducedRate, kind, normalPeriods, reducedPeriods, new StudentRateCalculationStrategy());
        assertNotNull(rate);
    }

    @Test
    public void testPeriodOutsideReducedPeriods() {
        BigDecimal normalRate = BigDecimal.valueOf(10);
        BigDecimal reducedRate = BigDecimal.valueOf(5);
        CarParkKind kind = CarParkKind.STUDENT;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(2, 5));
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(6, 7));
        Rate rate = new Rate(normalRate, reducedRate, kind, normalPeriods, reducedPeriods, new StudentRateCalculationStrategy());
        assertNotNull(rate);
    }

    @Test
    public void testPeriodOutsideNormalPeriods() {
        BigDecimal normalRate = BigDecimal.valueOf(10);
        BigDecimal reducedRate = BigDecimal.valueOf(5);
        CarParkKind kind = CarParkKind.STUDENT;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(6, 7));
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(2, 5));
        Rate rate = new Rate(normalRate, reducedRate, kind, reducedPeriods, normalPeriods, new StudentRateCalculationStrategy());
        assertNotNull(rate);
    }

    @Test
    public void testPeriodOverlappingWithOneReducedAndOneNormalPeriod() {
        BigDecimal normalRate = BigDecimal.valueOf(10);
        BigDecimal reducedRate = BigDecimal.valueOf(5);
        CarParkKind kind = CarParkKind.STUDENT;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(2, 5));
        normalPeriods.add(new Period(8, 10));
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(4, 7));
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(normalRate, reducedRate, kind, normalPeriods, reducedPeriods, new StudentRateCalculationStrategy());
        });
    }

    @Test
    public void testPeriodOverlappingWithMultipleReducedAndNormalPeriods() {
        BigDecimal normalRate = BigDecimal.valueOf(10);
        BigDecimal reducedRate = BigDecimal.valueOf(5);
        CarParkKind kind = CarParkKind.STUDENT;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(2, 5));
        normalPeriods.add(new Period(8, 10));
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(1, 2));
        reducedPeriods.add(new Period(4, 6));
        reducedPeriods.add(new Period(11, 12));
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(normalRate, reducedRate, kind, normalPeriods, reducedPeriods, new StudentRateCalculationStrategy());
        });
    }

    @Test
    public void testLowestValueNormalRate() {
        BigDecimal normalRate = BigDecimal.valueOf(0);
        BigDecimal reducedRate = BigDecimal.valueOf(0);
        CarParkKind kind = CarParkKind.STUDENT;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(2, 5));
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(1, 2));
        Rate rate = new Rate(normalRate, reducedRate, kind, reducedPeriods, normalPeriods, new StudentRateCalculationStrategy());
        assertNotNull(rate);
        ;
    }

    @Test
    public void testLowestValueReducedRate() {
        BigDecimal normalRate = BigDecimal.valueOf(10);
        BigDecimal reducedRate = BigDecimal.valueOf(0);
        CarParkKind kind = CarParkKind.STAFF;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(2, 5));
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(1, 2));
        Rate rate = new Rate(normalRate, reducedRate, kind, reducedPeriods, normalPeriods, new StaffRateCalculationStrategy());
        assertNotNull(rate);
    }

    @Test
    public void testHighRateValue() {

        BigDecimal normalRate = BigDecimal.valueOf(MAX_INT);
        BigDecimal reducedRate = BigDecimal.valueOf(MAX_INT - 1);
        CarParkKind kind = CarParkKind.STUDENT;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(2, 5));
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(1, 2));
        Rate rate = new Rate(normalRate, reducedRate, kind, reducedPeriods, normalPeriods, new StudentRateCalculationStrategy());
        assertNotNull(rate);
    }

    @Test
    public void testNegativeInvalidNormalRate() {
        BigDecimal normalRate = BigDecimal.valueOf(-1);
        BigDecimal reducedRate = BigDecimal.valueOf(2);
        CarParkKind kind = CarParkKind.STAFF;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(2, 5));
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(1, 2));
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(normalRate, reducedRate, kind, normalPeriods, reducedPeriods, new StaffRateCalculationStrategy());
        });
    }

    @Test
    public void testNegativeInvalidReducedRate() {
        BigDecimal normalRate = BigDecimal.valueOf(10);
        BigDecimal reducedRate = BigDecimal.valueOf(-2);
        CarParkKind kind = CarParkKind.STUDENT;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(2, 5));
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(1, 2));
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(normalRate, reducedRate, kind, normalPeriods, reducedPeriods, new StudentRateCalculationStrategy());
        });
    }

    @Test
    public void testInvalidConstructorReducedRateGreaterThanNormalRate() {
        BigDecimal normalRate = BigDecimal.valueOf(2);
        BigDecimal reducedRate = BigDecimal.valueOf(5);
        CarParkKind kind = CarParkKind.VISITOR;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(10, 11));
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(1, 2));
        reducedPeriods.add(new Period(5, 9));
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(normalRate, reducedRate, kind, normalPeriods, reducedPeriods, new VisitorRateCalculationStrategy());
        });
    }

    @Test
    public void testOverlappingNormalPeriods() {
        BigDecimal normalRate = BigDecimal.valueOf(10);
        BigDecimal reducedRate = BigDecimal.valueOf(3);
        CarParkKind kind = CarParkKind.VISITOR;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(2, 5));
        normalPeriods.add(new Period(10, 11));
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(7, 10));
        reducedPeriods.add(new Period(8, 12));
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(normalRate, reducedRate, kind, normalPeriods, reducedPeriods, new VisitorRateCalculationStrategy());
        });
    }

    @Test
    public void testOverlappingReducedPeriods() {
        BigDecimal normalRate = BigDecimal.valueOf(5.5);
        BigDecimal reducedRate = BigDecimal.valueOf(3);
        CarParkKind kind = CarParkKind.MANAGEMENT;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(2, 5));
        normalPeriods.add(new Period(10, 11));
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(7, 10));
        reducedPeriods.add(new Period(8, 12));
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(normalRate, reducedRate, kind, normalPeriods, reducedPeriods, new ManagementRateCalculationStrategy());
        });
    }

    @Test
    public void testOverlappingNormalAndReducedPeriods() {
        BigDecimal normalRate = BigDecimal.valueOf(7);
        BigDecimal reducedRate = BigDecimal.valueOf(4);
        CarParkKind kind = CarParkKind.STUDENT;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(2, 8));
        normalPeriods.add(new Period(6, 10));
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(1, 5));
        reducedPeriods.add(new Period(9, 11));
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(normalRate, reducedRate, kind, normalPeriods, reducedPeriods, new StudentRateCalculationStrategy());
        });
    }

    @Test
    public void testNullNormalRate() {
        BigDecimal reducedRate = BigDecimal.valueOf(10);
        CarParkKind kind = CarParkKind.STAFF;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(5, 7));
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(0, 2));
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(null, reducedRate, kind, normalPeriods, reducedPeriods, new StaffRateCalculationStrategy());
        });
    }

    @Test
    public void testNullReducedRate() {
        BigDecimal normalRate = BigDecimal.valueOf(5);
        CarParkKind kind = CarParkKind.STUDENT;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(2, 5));
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(1, 2));
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(normalRate, null, kind, normalPeriods, reducedPeriods, new StudentRateCalculationStrategy());
        });
    }

    /*
     * Added Test Cases to get 100% branch coverage
     */
    @Test
    public void testNullNormalPeriods() {
        BigDecimal normalRate = BigDecimal.valueOf(10);
        BigDecimal reducedRate = BigDecimal.valueOf(5);
        CarParkKind kind = CarParkKind.STUDENT;
        ArrayList<Period> normalPeriods = null;
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(1, 2));
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(normalRate, reducedRate, kind, normalPeriods, reducedPeriods, new StudentRateCalculationStrategy());
        });
    }

    @Test
    public void testNullReducedPeriods() {
        BigDecimal normalRate = BigDecimal.valueOf(10);
        BigDecimal reducedRate = BigDecimal.valueOf(5);
        CarParkKind kind = CarParkKind.STUDENT;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(1, 2));
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(normalRate, reducedRate, kind, normalPeriods, null, new StudentRateCalculationStrategy());
        });
    }

    @Test
    public void testMultipleOverlappingReducedPeriods() {
        BigDecimal normalRate = BigDecimal.valueOf(10);
        BigDecimal reducedRate = BigDecimal.valueOf(5);
        CarParkKind kind = CarParkKind.STUDENT;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(2, 5));
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(6, 8));
        reducedPeriods.add(new Period(7, 10));
        reducedPeriods.add(new Period(9, 11));
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(normalRate, reducedRate, kind, normalPeriods, reducedPeriods, new StudentRateCalculationStrategy());
        });
    }

    /*
     * Calculate method Test Cases
     */

    @Test
    public void testHighValidNormalRate() {
        BigDecimal normalRate = BigDecimal.valueOf(MAX_INT);
        BigDecimal reducedRate = BigDecimal.valueOf(5);
        CarParkKind kind = CarParkKind.STUDENT;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(2, 5));
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(1, 2));
        Rate rate = new Rate(normalRate, reducedRate, kind, normalPeriods, reducedPeriods, new StudentRateCalculationStrategy());
        BigDecimal actualValue = rate.calculate(new Period(1, 6)).setScale(2, RoundingMode.HALF_UP);
        BigDecimal expectedValue = BigDecimal.valueOf(145.87);
        assertEquals(expectedValue, actualValue);
    }


    @Test
    public void testHighValidReducedRate() {
        BigDecimal normalRate = BigDecimal.valueOf(MAX_INT);
        BigDecimal reducedRate = BigDecimal.valueOf(MAX_INT - 100);
        CarParkKind kind = CarParkKind.STAFF;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(5, 7));
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(0, 2));
        Rate rate = new Rate(normalRate, reducedRate, kind, normalPeriods, reducedPeriods, new StaffRateCalculationStrategy());
        BigDecimal actualValue = rate.calculate(new Period(0, 8));
        BigDecimal expectedValue = BigDecimal.valueOf(10); // max value for staff is 10
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testNullPeriodStay() {
        BigDecimal normalRate = BigDecimal.valueOf(10);
        BigDecimal reducedRate = BigDecimal.valueOf(5);
        CarParkKind kind = CarParkKind.STAFF;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(2, 5));
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(1, 2));
        Rate rate = new Rate(normalRate, reducedRate, kind, reducedPeriods, normalPeriods, new StaffRateCalculationStrategy());
        assertThrows(NullPointerException.class, () -> {
            rate.calculate(null);
        });
    }

    @Test
    public void testPeriodStayStartEndTimeEqual() {
        BigDecimal normalRate = BigDecimal.valueOf(10);
        BigDecimal reducedRate = BigDecimal.valueOf(5);
        CarParkKind kind = CarParkKind.STUDENT;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(2, 5));
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(1, 2));
        Rate rate = new Rate(normalRate, reducedRate, kind, reducedPeriods, normalPeriods, new StudentRateCalculationStrategy());
        assertThrows(IllegalArgumentException.class, () -> {
            rate.calculate(new Period(3, 3));
        });
    }


    @Test
    public void testMaxStayPeriod() {
        BigDecimal normalRate = BigDecimal.valueOf(10);
        BigDecimal reducedRate = BigDecimal.valueOf(5);
        CarParkKind kind = CarParkKind.STUDENT;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(5, 7));
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(0, 2));
        Rate rate = new Rate(normalRate, reducedRate, kind, reducedPeriods, normalPeriods, new StudentRateCalculationStrategy());
        BigDecimal actualValue = rate.calculate(new Period(0, 24));
        BigDecimal expectedValue = BigDecimal.valueOf(21.92).setScale(2, RoundingMode.HALF_UP);
        assertEquals(expectedValue, actualValue.setScale(2, RoundingMode.HALF_UP));
    }


    @Test
    public void testCalculateMinStayPeriod() {
        BigDecimal normalRate = BigDecimal.valueOf(10);
        BigDecimal reducedRate = BigDecimal.valueOf(5);
        CarParkKind kind = CarParkKind.MANAGEMENT;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(3, 7));
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(0, 2));
        Rate rate = new Rate(normalRate, reducedRate, kind, reducedPeriods, normalPeriods, new ManagementRateCalculationStrategy());
        BigDecimal actualValue = rate.calculate(new Period(3, 4));
        BigDecimal expectedValue = BigDecimal.valueOf(10);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testCalculateLowestRate() {
        BigDecimal normalRate = BigDecimal.valueOf(0);
        BigDecimal reducedRate = BigDecimal.valueOf(0);
        CarParkKind kind = CarParkKind.VISITOR;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(2, 5));
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(1, 2));
        Rate rate = new Rate(normalRate, reducedRate, kind, normalPeriods, reducedPeriods, new VisitorRateCalculationStrategy());
        Period periodStay = new Period(4, 6);
        BigDecimal actualValue = rate.calculate(periodStay);
        BigDecimal expectedValue = BigDecimal.valueOf(0);
        assertEquals(expectedValue, actualValue);
    }


    /*
     * Added Tests to help test multiple reduced and normal periods in the calculate method
     * */

    @Test
    public void testMultipleNormalPeriods() {
        BigDecimal normalRate = BigDecimal.valueOf(10);
        BigDecimal reducedRate = BigDecimal.valueOf(5);
        CarParkKind kind = CarParkKind.STUDENT;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(2, 5));
        normalPeriods.add(new Period(7, 10));
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(1, 2));
        reducedPeriods.add(new Period(5, 7));
        Rate rate = new Rate(normalRate, reducedRate, kind, reducedPeriods, normalPeriods, new StudentRateCalculationStrategy());
        BigDecimal actualValue = rate.calculate(new Period(3, 9)).setScale(2, RoundingMode.HALF_UP);
        BigDecimal expectedValue = BigDecimal.valueOf(35.32);
        assertEquals(expectedValue, actualValue);
    }


    @Test
    public void testMultipleReducedPeriods() {
        BigDecimal normalRate = BigDecimal.valueOf(10);
        BigDecimal reducedRate = BigDecimal.valueOf(5);
        CarParkKind kind = CarParkKind.STAFF;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(2, 5));
        normalPeriods.add(new Period(7, 10));
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(1, 2));
        reducedPeriods.add(new Period(5, 7));
        Rate rate = new Rate(normalRate, reducedRate, kind, reducedPeriods, normalPeriods, new StaffRateCalculationStrategy());
        BigDecimal actualValue = rate.calculate(new Period(0, 8));
        BigDecimal expectedValue = BigDecimal.valueOf(10); //max value for staff is 10
        assertEquals(expectedValue, actualValue);
    }

    /*
     * Added Tests for Task 3 - "Red Phase" of the TDD
     * */

    // Test for VISITOR CarParkKind
    @Test
    public void testVisitorRateCalculation() {
        BigDecimal normalRate = BigDecimal.valueOf(10);
        BigDecimal reducedRate = BigDecimal.valueOf(5);
        CarParkKind kind = CarParkKind.VISITOR;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(7, 17));
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(0, 7));
        Rate rate = new Rate(normalRate, reducedRate, kind, reducedPeriods, normalPeriods, new VisitorRateCalculationStrategy());
        //Rounding mode is used as decimal places are making the test fail, 5 != 5.0
        BigDecimal actualValue = rate.calculate(new Period(8, 10)).setScale(0, RoundingMode.HALF_UP);
        BigDecimal expectedValue = BigDecimal.valueOf(5).setScale(0, RoundingMode.HALF_UP);
        assertEquals(expectedValue, actualValue);
    }


    // Test for MANAGEMENT CarParkKind - under 5.00
    @Test
    public void testManagementCalculation() {
        BigDecimal normalRate = BigDecimal.valueOf(2);
        BigDecimal reducedRate = BigDecimal.valueOf(1);
        CarParkKind kind = CarParkKind.MANAGEMENT;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(7, 17));
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(0, 7));
        Rate rate = new Rate(normalRate, reducedRate, kind, reducedPeriods, normalPeriods, new ManagementRateCalculationStrategy());
        assertThrows(IllegalArgumentException.class, () -> {
            rate.calculate(new Period(6, 7)); // 1 hour in the reduced period
        });
    }

    // Test for STUDENT CarParkKind
    @Test
    public void testStudentRateCalculation() {
        BigDecimal normalRate = BigDecimal.valueOf(10);
        BigDecimal reducedRate = BigDecimal.valueOf(5);
        CarParkKind kind = CarParkKind.STUDENT;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(7, 17));
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(0, 7));
        Rate rate = new Rate(normalRate, reducedRate, kind, reducedPeriods, normalPeriods, new StudentRateCalculationStrategy());
        BigDecimal actualValue = rate.calculate(new Period(8, 10));

        // Round the actual value to 2 decimal places - causing errors
        actualValue = actualValue.setScale(2, RoundingMode.HALF_UP);

        // Expected value is calculated as follows:
        // - The full rate for the period is 20
        // - The amount above 5.50 is 20 - 5.50 = 14.50
        // - 33% of 14.50 is approximately 4.785, take away form price = 15.215 - round up
        BigDecimal expectedValue = BigDecimal.valueOf(15.22);
        assertEquals(expectedValue, actualValue);
    }

    // Test for STAFF CarParkKind
    @Test
    public void testStaffRateCalculation() {
        BigDecimal normalRate = BigDecimal.valueOf(10);
        BigDecimal reducedRate = BigDecimal.valueOf(5);
        CarParkKind kind = CarParkKind.STAFF;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(7, 17));
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(0, 7));
        Rate rate = new Rate(normalRate, reducedRate, kind, reducedPeriods, normalPeriods, new StaffRateCalculationStrategy());
        BigDecimal actualValue = rate.calculate(new Period(8, 20));

        // Expected value is 10 since it's the maximum payable amount
        BigDecimal expectedValue = BigDecimal.valueOf(10);
        assertEquals(expectedValue, actualValue);
    }

}