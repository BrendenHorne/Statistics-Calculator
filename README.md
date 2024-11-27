# KSU SWE 3643 Software Testing and Quality Assurance Semester Project: Web-Based Calculator
This repository contains my semester project for SWE 3643 Software Testing and Quality Assurance, a web based statistics calculator. The repository contains everything you need to run the application.

## Table of Contents
- [Team Members](#team-members)
- [Architecture](#architecture)
- [Environment](#environment)
- [Executing the Web Application](#executing-the-web-application)
- [Executing Unit Tests](#executing-unit-tests)
- [Reviewing Unit Test Coverage](#reviewing-unit-test-coverage)
- [Executing End To End Tests](#executing-end-to-end-tests)
- [Final Video Presentation](#final-video-presentation)

  ## Team Members
  Brenden Horne

  ## Architecture
  ![Screenshot 2024-11-24 164137](https://github.com/user-attachments/assets/adbc984b-6949-4c43-9aec-7a3f7f49c2ac)

  ## Environment
  This is a cross-platform application and should work in Windows 10+, Mac OSx Ventura+, and Linux environments. Note that the application has only been carefully tested in Windows 11.\
  
  ### To prepare your environment to execute this application:
  You can install java [here](https://www.java.com/download/ie_manual.jsp)\
  After installing java you can run the "java --version" command in the command terminal to check the version and ensure proper installation.\
  The output you see should be similar to the following:\
  "java 21.0.4 2024-07-16 LTS\
Java(TM) SE Runtime Environment (build 21.0.4+8-LTS-274)\
Java HotSpot(TM) 64-Bit Server VM (build 21.0.4+8-LTS-274, mixed mode, sharing)"\

## Executing The Web Application
After downloading and extracting the files locate the StatCalcProject folder in your file explorer. Left click on the folder to select it. Then, right click and select Copy as Path.\
Next open the command terminal and run the command "cd path\to\StatCalcProject". Replace path\to\StatCalcProject with the path you copied.\
Next, run the command "runtime\bin\java -jar runtime\StatisticsCalculator.jar". On Mac replace back slashes "\" with foward slashes "/" 
Finally, open a browser and go to "http://localhost:8080"\
To stop the application press ctrl + c in the command terminal.

## Executing Unit Tests
To run unit tests first run the command "cd path\to\StatCalcProject"
Then, run the command "runtime\bin\java -jar runtime\unitTests.jar"
On Mac replace back slashes "\" with foward slashes "/"

## Reviewing Unit Test Coverage
![Screenshot 2024-11-27 133925](https://github.com/user-attachments/assets/2b59345d-c593-4a53-beb9-ecf08e7a1f49)\
Screenshot of 100% coverage with unit tests.

## Executing End-To-End Tests
To run end to end tests first run the command "cd path\to\StatCalcProject"
Then, run the command "runtime\bin\java -jar runtime\e2eTests.jar"
On Mac replace back slashes "\" with foward slashes "/"

## Final Video Presentation
