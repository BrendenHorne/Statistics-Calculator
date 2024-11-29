package com.example.calculator.logic;

import java.util.ArrayList;
import java.util.Arrays;

public class calculationResult {
        private double result = 0.0;
        private boolean isSuccess;
        private String operation;
        private String error;

        public double getResult() {
            return result;
        }

        public void setResult(double result) {
            this.result = result;
        }

        public boolean getIsSuccess() {
            return isSuccess;
        }

        public void setIsSuccess(boolean isSuccess) {
            this.isSuccess = isSuccess;
        }

        public String getOperation() {
            return operation;
        }

        // for example, "1.25 + 3.8 ="
        public void setOperation(String operation) {
            this.operation = operation;
        }

        public String getError() {
            return error;
        }

        // for example, "" or "Not A Number"
        public void setError(String error) {
            this.error = error;
        }

        public static ArrayList<Double> splitByComma(ArrayList<String> list){
            ArrayList<Double> newList = new ArrayList<>();
            for (String x : list) {
                String[] numbers = x.split(",");
                for (String num : numbers) {
                    newList.add(Double.valueOf(num.trim()));
                }
            }
            return newList;
        }

        public static ArrayList<Double> splitByNewLine(ArrayList<String> list){
            ArrayList<Double> newList = new ArrayList<>();
            for (String x : list) {
                String[] numbers = x.split("\n");
                for (String num : numbers) {
                    newList.add(Double.valueOf(num.trim()));
                }
            }
            return newList;
        }

        public static ArrayList<String> splitByNewString(ArrayList<String> list){
            ArrayList<String> newList = new ArrayList<>();
            for (String x : list) {
                String[] numbers = x.split("\n");
                newList.addAll(Arrays.asList(numbers));
            }
            return newList;
    }
    }
