# PartnerCenter Java SDK
[![Build Status](https://chironeng.visualstudio.com/_apis/public/build/definitions/d03521b6-3f75-4cda-8e94-fcf0e02fd9f1/1/badge)]

The Partner Center Java SDK, provides an SDK to interact with the Microsoft's Partner Center Service. This enables the Partners to perform the Partner Center operations programmatically or through their own portals. The Java SDK is the latest addition to existing portfolio of REST APIs and the C# SDK. This is currently tested and supports Java versions of Java 7 and above. 

This repo includes the Java SDK code as well as a sample-application that demonstrates how to make calls and consume the Java SDK.

For documentation, refer to the C# SDK documentation here:
https://msdn.microsoft.com/library/partnercenter/mt635943.aspx 
Java SDK is similar to C# SDK and please refer to the Sample app for more details specific to Java SDK.

## Capabilities of the Java SDK

* Customer Operations

* Commerce operations (Order & Subscription Management)

* Azure Spending 

* Audit 

* Customer User Management

* License Assignment 

* Rate Card  

* Azure Subscription Utilization Records

## Here are the steps you can follow to run the SDK and Sample-App code locally:

1. Install Java, Maven and Eclipse (or another IDE) if you don't already have these installed

2. Setup the JAVA_HOME and JRE_HOME environment variables.

3. Setup the MAVEN_HOME environment variable.

4. Download the SDK and Samples projects from github.

5. Import the downloaded projects into the IDE as maven projects.

6. Replace the placeholders values for the App-Id, Username, Password etc in the SamplesConfiguration.json file, which is located in resources in the Samples project.

7. Run the Program.java file in the com.microsoft.store.partnercenter.samples package in the Samples project.

8. This will show a Menu which guides through running the different scenarios supported by the SDK.
