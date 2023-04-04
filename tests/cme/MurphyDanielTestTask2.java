package cme;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MurphyDanielTestTask2 {

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
        Rate rate = new Rate(normalRate, reducedRate, kind, normalPeriods, reducedPeriods);
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
        Rate rate = new Rate(normalRate, reducedRate, kind, normalPeriods, reducedPeriods);
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
        Rate rate = new Rate(normalRate, reducedRate, kind, normalPeriods, reducedPeriods);
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
        Rate rate = new Rate(normalRate, reducedRate, kind, reducedPeriods, normalPeriods);
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
            new Rate(normalRate, reducedRate, kind, normalPeriods, reducedPeriods);
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
            new Rate(normalRate, reducedRate, kind, normalPeriods, reducedPeriods);
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
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(normalRate, reducedRate, kind, normalPeriods, reducedPeriods);
        });
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
        Rate rate = new Rate(normalRate, reducedRate, kind, reducedPeriods, normalPeriods);
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
        Rate rate = new Rate(normalRate, reducedRate, kind, reducedPeriods, normalPeriods);
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
            new Rate(normalRate, reducedRate, kind, normalPeriods, reducedPeriods);
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
            new Rate(normalRate, reducedRate, kind, normalPeriods, reducedPeriods);
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
            new Rate(normalRate, reducedRate, kind, normalPeriods, reducedPeriods);
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
            new Rate(normalRate, reducedRate, kind, normalPeriods, reducedPeriods);
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
            new Rate(normalRate, reducedRate, kind, normalPeriods, reducedPeriods);
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
            new Rate(normalRate, reducedRate, kind, normalPeriods, reducedPeriods);
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
            new Rate(null, reducedRate, kind, normalPeriods, reducedPeriods);
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
            new Rate(normalRate, null, kind, normalPeriods, reducedPeriods);
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
            new Rate(normalRate, reducedRate, kind, normalPeriods, reducedPeriods);
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
            new Rate(normalRate, reducedRate, kind, normalPeriods, null);
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
            new Rate(normalRate, reducedRate, kind, normalPeriods, reducedPeriods);
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
        Rate rate = new Rate(normalRate, reducedRate, kind, normalPeriods, reducedPeriods);
        BigDecimal actualValue = rate.calculate(new Period(1, 6));
        BigDecimal expectedValue = BigDecimal.valueOf(215);
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
        Rate rate = new Rate(normalRate, reducedRate, kind, normalPeriods, reducedPeriods);
        BigDecimal actualValue = rate.calculate(new Period(0, 8));
        BigDecimal expectedValue = BigDecimal.valueOf(600);
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
        Rate rate = new Rate(normalRate, reducedRate, kind, reducedPeriods, normalPeriods);
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
        Rate rate = new Rate(normalRate, reducedRate, kind, reducedPeriods, normalPeriods);
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
        Rate rate = new Rate(normalRate, reducedRate, kind, reducedPeriods, normalPeriods);
        BigDecimal actualValue = rate.calculate(new Period(0, 24));
        BigDecimal expectedValue = BigDecimal.valueOf(30);
        assertEquals(expectedValue, actualValue);
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
        Rate rate = new Rate(normalRate, reducedRate, kind, reducedPeriods, normalPeriods);
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
        Period periodStay = new Period(4, 6);
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(normalRate, reducedRate, kind, normalPeriods, reducedPeriods);
        });
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
        Rate rate = new Rate(normalRate, reducedRate, kind, reducedPeriods, normalPeriods);
        BigDecimal actualValue = rate.calculate(new Period(3, 9));
        BigDecimal expectedValue = BigDecimal.valueOf(50);
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
        Rate rate = new Rate(normalRate, reducedRate, kind, reducedPeriods, normalPeriods);
        BigDecimal actualValue = rate.calculate(new Period(0, 8));
        BigDecimal expectedValue = BigDecimal.valueOf(55);
        assertEquals(expectedValue, actualValue);
    }
}