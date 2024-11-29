package com.example.calculator.logic;

import java.util.ArrayList;
public class Statistics {
    // preq-LOGIC-3
    public static calculationResult computeSampleStandardDeviation(ArrayList<Double> list) {
        calculationResult result = new calculationResult();
        double sampleMean = computeMean(list).getResult();
        double num = 0.0;
        if (list.size() == 1){
            result.setResult(0);
            result.setIsSuccess(true);
            return result;
        }
        for (double value : list) {
            num += Math.pow((value - sampleMean), 2);
        }
        double ssd = Math.sqrt(num / (list.size() - 1) );

        if (list.isEmpty()){
            result.setIsSuccess(false);
            result.setError("Invalid Input please Enter Values");
            return result;
        }
        result.setResult(ssd);
        result.setIsSuccess(true);

        return result;
    }

    // preq-LOGIC-4
    public static calculationResult computePopulationStandardDeviation(ArrayList<Double> list) {
        calculationResult result = new calculationResult();
        double popMean = computeMean(list).getResult();
        double num = 0.0;
        for (double value : list) {
            num += Math.pow((value - popMean), 2);
        }
        double psd = Math.sqrt(num / list.size());
        result.setResult(psd);
        result.setIsSuccess(true);

        if (list.isEmpty() || list.size() < 2){
            result.setIsSuccess(false);
            result.setError("Invalid Input please Enter at Least Two Values");
        }
        return result;
    }

    // preq-LOGIC-5
    public static calculationResult computeMean(ArrayList<Double> list) {
        calculationResult result = new calculationResult();
        double total = 0;
        for (double value : list) {
            total += value;
        }
        double mean = total / list.size();
        result.setResult(mean);
        result.setIsSuccess(true);

        if (list.isEmpty()){
            result.setIsSuccess(false);
            result.setError("Invalid Input please Enter Values");
        }
        return result;
    }

    // preq-LOGIC-6
    public static calculationResult computeZScore(ArrayList<Double> list){
        calculationResult result = new calculationResult();
        if (list.size() != 3){
            result.setIsSuccess(false);
            result.setError("Format is: \"value,mean,standard deviation\"");
            return result;
        }
        double value = list.get(0);
        double mean = list.get(1);
        double sd = list.get(2);
        if (list.get(2) == 0){
            result.setIsSuccess(false);
            result.setError("Divide by zero error");
            return result;
        }
        double z = (value - mean) / sd;
        result.setResult(z);
        result.setIsSuccess(true);
        return result;
    }
}


