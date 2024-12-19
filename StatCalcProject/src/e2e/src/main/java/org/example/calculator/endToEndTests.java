package org.example.calculator;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;

import static org.testng.AssertJUnit.assertEquals;

public class endToEndTests {

    private static Page page;
    private static Browser browser;
    static Process serverProcess;

    // Set up the browser and server before all tests
    @BeforeClass
    public static void setupClass() throws IOException {
        // Start the server
        serverProcess = new ProcessBuilder("java", "-jar", "StatisticsCalculator.jar").start();

        // Optionally, wait for the server to initialize
        try { Thread.sleep(7000); } catch (InterruptedException ignored) {}

        // Initialize Playwright and browser
        Playwright playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();

        // Navigate to the application's URL
        page.navigate("http://localhost:8080"); // Replace with your app URL
    }

    // Cleanup by stopping the server and closing the browser
    @AfterClass
    public static void tearDownClass() {
        // Stop the server and close the browser
        serverProcess.destroy();
        browser.close();
    }

    // preq-E2E-TEST-5
    @Test
    public void CalculatorWebUi_PageTitle_IsCalculator() {
        String expectedTitle = "Calculator";
        assertEquals(expectedTitle, page.title());
    }

    // preq-E2E-TEST-6 at com.microsoft.playwright.impl.WaitableResult.get(WaitableResult.java:54)
    @Test
    public void CalculatorWebUi_ComputeSampleStandardDeviation_ReturnsCorrectValue() {
        String inputData = "9,2,5,4,12,7,8,11,9,3,7,4,12,5,4,10,9,6,9,4";
        String expectedResult = "3.060787652326";

        page.fill("#valuesInput", inputData); // Fill input field with data
        page.click("#compute-sample-std-dev"); // Click the compute sample std deviation button

        String result = page.textContent("#resultMessage"); // Get the result
        assertEquals("Result: " + expectedResult, result.trim());
    }

    // preq-E2E-TEST-7
    @Test
    public void CalculatorWebUi_EmptyInput_ShowsErrorMessage() {
        page.click("#compute-sample-std-dev"); // Click compute button without entering data

        String errorMessage = page.textContent("#resultMessage"); // Get error message
        assertEquals("Error: Input cannot be empty.", errorMessage.trim());
    }

    // preq-E2E-TEST-8
    @Test
    public void CalculatorWebUi_ComputeSampleStandardDeviationWithSingleValue_ShowsErrorMessage() {
        String inputData = "5";  // Single value for standard deviation
        String expectedErrorMessage = "At least two values are required to compute the sample standard deviation.";

        page.fill("#valuesInput", inputData); // Fill input with a single value
        page.click("#compute-sample-std-dev"); // Trigger computation

        String errorMessage = page.textContent("#resultMessage"); // Get the error message
        assertEquals("Error: " + expectedErrorMessage, errorMessage.trim());
    }

    // preq-E2E-TEST-9
    @Test
    public void CalculatorWebUi_ComputeMean_ReturnsCorrectValue() {
        String inputData = "9,2,5,4,12,7,8,11,9,3,7,4,12,5,4,10,9,6,9,4";
        String expectedResult = "7";

        page.fill("#valuesInput", inputData); // Use the selector for the input field
        page.click("#compute-mean"); // Click the compute mean button

        String result = page.textContent("#resultMessage"); // Get the result from the resultMessage div
        assertEquals("Result: " + expectedResult, result.trim()); // Verify the result
    }

    // preq-E2E-TEST-10
    @Test
    public void CalculatorWebUi_ComputeZScore_ReturnsCorrectValue() {
        page.fill("#valuesInput", "7,3,3"); // Fill input for zScore
        page.click("#compute-z-score"); // Click compute z-score button

        String result = page.textContent("#resultMessage"); // Get the result
        assertEquals("Result: -0.49007", result.trim());
    }

    // preq-E2E-TEST-11
    @Test
    public void CalculatorWebUi_ComputeLinearRegression_ReturnsCorrectFormula() {
        String inputData = "5,3;2,1;7.5,-3;4.5,3;3,17;4,3;6,42;4,4;12,17;3,1";
        String expectedResult = "y = -0.04596x + 6.9336";

        page.fill("#valuesInput", inputData); // Fill input for regression
        page.click("#compute-regression-formula"); // Click compute regression button

        String result = page.textContent("#resultMessage"); // Get the result
        assertEquals("Result: " + expectedResult, result.trim());
    }

    // preq-E2E-TEST-12
    @Test
    public void CalculatorWebUi_PredictYValueForLinearRegression_ReturnsCorrectValue() {
        // Fill in the values for x, m, and b (linear regression)
        page.fill("#input-x-value", "6");  // Use the selector for the x-value input field
        page.fill("#input-m", "-0.04596"); // Use the selector for the m value (slope)
        page.fill("#input-b", "6.9336");   // Use the selector for the b value (intercept)

        // Trigger the prediction by clicking the "Predict Y" button
        page.click("button:has-text('Predict Y Value')");  // Use button text to identify the correct button

        // Get the result from the result div and check if it matches the expected value
        String result = page.textContent("#result");  // Get the result text from the result div
        assertEquals("6.65784", result.trim());  // Verify that the result matches the expected value
    }
}
