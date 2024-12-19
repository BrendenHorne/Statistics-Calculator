package com.example.calculator;

import com.example.calculator.logic.linearRegression;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;
import com.example.calculator.logic.*;
import org.junit.platform.console.ConsoleLauncher;

public class unitTests {
    public static void main(String[] args) {
        ConsoleLauncher.main(new String[] { "--scan-classpath" });
    }
    //preq-UNIT-TEST-2
    @Test
    void testSampleStandardDeviation() {
        //Arrange
        ArrayList<Double> list1 = new ArrayList<Double>();
        ArrayList<Double> list2 = new ArrayList<Double>();
        ArrayList<Double> list3 = new ArrayList<Double>();

        Collections.addAll(list1, 1.0, 2.5, 7.0, 9.0, 12.2);
        Collections.addAll(list2, 0.0, 0.0, 0.0, 0.0);

        //Act
        double validList = Statistics.computeSampleStandardDeviation(list1).getResult();
        double zeroList = Statistics.computeSampleStandardDeviation(list2).getResult();
        String emptyList = Statistics.computeSampleStandardDeviation(list3).getError();

        boolean validListSuccess = Statistics.computeSampleStandardDeviation(list1).getIsSuccess();
        boolean zeroListSuccess = Statistics.computeSampleStandardDeviation(list2).getIsSuccess();
        boolean emptyListSuccess = Statistics.computeSampleStandardDeviation(list3).getIsSuccess();

        //Assert
        assertEquals(4.6128082552822, validList, 8);
        assertEquals(0, zeroList, 8);
        assertEquals("Invalid Input Please Enter Values", emptyList);

        assertTrue(validListSuccess);
        assertTrue(zeroListSuccess);
        assertFalse(emptyListSuccess);
    }

    //preq-UNIT-TEST-3
    @Test
    void testPopulationStandardDeviation() {
        //Arrange
        ArrayList<Double> list1 = new ArrayList<Double>();
        ArrayList<Double> list2 = new ArrayList<Double>();
        ArrayList<Double> list3 = new ArrayList<Double>();
        ArrayList<Double> list4 = new ArrayList<Double>();

        Collections.addAll(list1, 1.0, 2.5, 7.0, 9.0, 12.2);
        Collections.addAll(list2, 0.0, 0.0, 0.0, 0.0);
        Collections.addAll(list4, 2.4);

        //Act
        double validList = Statistics.computePopulationStandardDeviation(list1).getResult();
        double zeroList = Statistics.computePopulationStandardDeviation(list2).getResult();
        String emptyList = Statistics.computePopulationStandardDeviation(list3).getError();
        String missingList = Statistics.computePopulationStandardDeviation(list4).getError();

        boolean validListSuccess = Statistics.computePopulationStandardDeviation(list1).getIsSuccess();
        boolean zeroListSuccess = Statistics.computePopulationStandardDeviation(list2).getIsSuccess();
        boolean emptyListSuccess = Statistics.computePopulationStandardDeviation(list3).getIsSuccess();
        boolean missingListSuccess = Statistics.computePopulationStandardDeviation(list4).getIsSuccess();

        //Assert
        assertEquals(4.6128082552822, validList, 8);
        assertEquals(0, zeroList, 8);
        assertEquals("Invalid Input Please Enter At Least Two Values", emptyList);
        assertEquals("Invalid Input Please Enter At Least Two Values", missingList);

        assertTrue(validListSuccess);
        assertTrue(zeroListSuccess);
        assertFalse(emptyListSuccess);
        assertFalse(missingListSuccess);
    }

    //preq-UNIT-TEST-4
    @Test
    void testComputeMean() {
        //Arrange
        ArrayList<Double> list1 = new ArrayList<Double>();
        ArrayList<Double> list2 = new ArrayList<Double>();

        Collections.addAll(list1, 1.0, 2.5, 7.0, 9.0, 12.2);

        //Act
        double validList = Statistics.computeMean(list1).getResult();
        String emptyList = Statistics.computeMean(list2).getError();

        boolean validListSuccess = Statistics.computeMean(list1).getIsSuccess();
        boolean emptyListSuccess = Statistics.computeMean(list2).getIsSuccess();

        //Assert
        assertEquals(6.34, validList, 8);
        assertEquals("Invalid Input Please Enter Values", emptyList);

        assertTrue(validListSuccess);
        assertFalse(emptyListSuccess);
    }

    //preq-UNIT-TEST-5
    @Test
    void testZScore() {
        //Arrange
        ArrayList<Double> list1 = new ArrayList<Double>();
        ArrayList<Double> list2 = new ArrayList<Double>();
        ArrayList<Double> list3 = new ArrayList<Double>();

        Collections.addAll(list1, 1.0, 2.5, 7.0);
        Collections.addAll(list2, 12.0, 2.0);
        Collections.addAll(list3, 1.0, 7.0, 0.0);

        //Act
        double validList = Statistics.computeZScore(list1).getResult();
        String missingList = Statistics.computeZScore(list2).getError();
        String divideByZeroList = Statistics.computeZScore(list3).getError();

        boolean validListSuccess = Statistics.computeZScore(list1).getIsSuccess();
        boolean missingListSuccess = Statistics.computeZScore(list2).getIsSuccess();
        boolean divideByZeroListSuccess = Statistics.computeZScore(list3).getIsSuccess();

        //Assert
        assertEquals(-0.21429, validList, 8);
        assertEquals("Format is: \"value,mean,standard deviation\"", missingList);
        assertEquals("Divide by zero error", divideByZeroList);

        assertTrue(validListSuccess);
        assertFalse(missingListSuccess);
        assertFalse(divideByZeroListSuccess);

    }

    //preq-UNIT-TEST-6
    @Test
    void testLinearRegressionEquation() {
        //Arrange
        ArrayList<Double> list1 = new ArrayList<Double>();
        ArrayList<Double> list2 = new ArrayList<Double>();

        Collections.addAll(list1, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0);
        Collections.addAll(list2, 1.0, 2.0, 3.0, 4.0, 5.0);

        //Act
        String validList = linearRegression.computeRegressionFormula(list1).getOperation();
        String errorList = linearRegression.computeRegressionFormula(list2).getError();

        boolean validListSuccess = linearRegression.computeRegressionFormula(list1).getIsSuccess();
        boolean errorListSuccess = linearRegression.computeRegressionFormula(list2).getIsSuccess();

        //Assert
        assertEquals("y = 1.0x + 1.0", validList);
        assertEquals("Invalid input one ordered pair per line", errorList);

        assertTrue(validListSuccess);
        assertFalse(errorListSuccess);
    }

    //preq-UNIT-TEST-7
    @Test
    void testPredictY() {
        //Arrange
        ArrayList<Double> list1 = new ArrayList<Double>();
        ArrayList<Double> list2 = new ArrayList<Double>();

        Collections.addAll(list1, 1.0, 2.5, 7.0);
        Collections.addAll(list2, 1.0, 2.5);

        //Act
        double validList = linearRegression.predictYValue(list1).getResult();
        String errorList = linearRegression.predictYValue(list2).getError();

        boolean validListSuccess = linearRegression.predictYValue(list1).getIsSuccess();
        boolean errorListSuccess = linearRegression.predictYValue(list2).getIsSuccess();

        //Assert
        assertEquals(9.5, validList, 8);
        assertEquals("Format is: \"x,m,b\"", errorList);

        assertTrue(validListSuccess);
        assertFalse(errorListSuccess);
    }
}
