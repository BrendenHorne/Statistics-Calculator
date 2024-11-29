package com.example.calculator;

import com.example.calculator.logic.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/calculate")
public class calculatorController {

    @PostMapping
    public ResponseEntity<?> calculate(@RequestBody Map<String, String> request) {
        String operation = request.get("operation");
        String rawInput = request.get("valuesInput");

        if (rawInput == null || rawInput.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "error", "Input cannot be empty."));
        }

        calculationResult result = null;

        try {
            // Determine which parsing method to use based on the operation
            ArrayList<Double> parsedValues = null;
            if (operation.equals("zScore") || operation.equals("predictYValue")) {
                ArrayList<String> stringValues = new ArrayList<>(List.of(rawInput));
                parsedValues = calculationResult.splitByComma(stringValues);
            }
            else if (operation.equals("sampleStdDeviation") || operation.equals("populationStdDeviation") || operation.equals("mean")){
                ArrayList<String> values = new ArrayList<>(Arrays.asList(rawInput.split("\n")));
                parsedValues = calculationResult.splitByNewLine(values);
            }
            else if (operation.equals("computeRegressionFormula")) {
                ArrayList<String> values = new ArrayList<>(Arrays.asList(rawInput.split("\n")));
                ArrayList<String> newValues = calculationResult.splitByNewString(values);
                parsedValues = calculationResult.splitByComma(newValues);
            }

            // Handle operations
            switch (operation) {
                case "sampleStdDeviation":
                    result = Statistics.computeSampleStandardDeviation(parsedValues);
                    break;
                case "populationStdDeviation":
                    result = Statistics.computePopulationStandardDeviation(parsedValues);
                    break;
                case "mean":
                    result = Statistics.computeMean(parsedValues);
                    break;
                case "zScore":
                    result = Statistics.computeZScore(parsedValues);
                    break;
                case "computeRegressionFormula":
                    result = linearRegression.computeRegressionFormula(parsedValues);
                    break;
                case "predictYValue":
                    result = linearRegression.predictYValue(parsedValues);
                    break;
            }

            if (operation.equals("computeRegressionFormula")){
                return ResponseEntity.ok(Map.of("success", true, "result", result.getOperation()));
            }

            if (result.getIsSuccess()) {
                return ResponseEntity.ok(Map.of("success", true, "result", result.getResult()));
            }
            else {
                return ResponseEntity.badRequest().body(Map.of("success", false, "error", result.getError()));
            }

        } catch (NumberFormatException e) {
            // Handle invalid numeric inputs
            return ResponseEntity.badRequest().body(Map.of("success", false, "error", result.getError()));
        } catch (Exception e) {
            // Handle unexpected errors
            return ResponseEntity.status(500).body(Map.of("success", false, "error", result.getError()));
        }
    }
}