package com.example.calculator.logic;

import com.example.calculator.logic.calculationResult;

import java.util.ArrayList;
import java.util.Arrays;

public class linearRegression {
    // preq-LOGIC-7

    public static calculationResult computeRegressionFormula(ArrayList<Double> separateValues) {
        calculationResult result = new calculationResult();
        // Separate the values into x and y arrays
        ArrayList<Double> xValues = new ArrayList<>();
        ArrayList<Double> yValues = new ArrayList<>();
        // Separate every other value into xValues and yValues
        for (int i = 0; i < separateValues.size(); i++) {
            if (i % 2 == 0) {
                xValues.add(separateValues.get(i));
            } else {
                yValues.add(separateValues.get(i));
            }
        }
        // Check if we have equal numbers of x and y values
        if (xValues.size() != yValues.size() || separateValues.size() < 4) {
            result.setIsSuccess(false);
            result.setError("Invalid input one ordered pair per line");
            return result;
        }
        // Calculate means of x and y values
        double xMean = Statistics.computeMean(xValues).getResult();
        double yMean = Statistics.computeMean(yValues).getResult();

        // Calculate sum of products for covariance and variance
        double sumXY = 0.0;
        double sumXSquared = 0.0;

        for (int i = 0; i < xValues.size(); i++) {
            double xDiff = xValues.get(i) - xMean;
            double yDiff = yValues.get(i) - yMean;
            sumXY += xDiff * yDiff;
            sumXSquared += xDiff * xDiff;
        }

        // Calculate slope (m) and intercept (b)
        double slope = sumXSquared != 0 ? sumXY / sumXSquared : 0.0;
        double intercept = yMean - (slope * xMean);



        // Set operation result
        result.setOperation("y = " + slope + "x + " + intercept);
        result.setIsSuccess(true);
        return result;
    }


    // preq-LOGIC-8
    public static calculationResult predictYValue(ArrayList<Double> list) {
        calculationResult result = new calculationResult();
        if (list.size() != 3){
            result.setIsSuccess(false);
            result.setError("Format is: \"x,m,b\"");
            return result;
        }
        double x = list.get(0);
        double m = list.get(1);
        double b = list.get(2);
        double y = (m * x) + b;
        result.setResult(y);
        result.setIsSuccess(true);
        return result;
    }
}

