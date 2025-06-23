# Self Heal Demo (TestNG)

## Overview
This repository contains demo scripts and configuration for testing the self-healing feature on BrowserStack using Appium and TestNG. It includes two main components:

### 1. Base App (BaseApp.apk)
- The base Android app used for automation and self-healing tests.
- Located at: `android/testng-examples/BaseApp.apk`
- This app serves as the reference for normal (non-self-healing) test runs.

### 2. Self-Heal App (SelfHealApp.apk)
- The self-healing version of the app, built to demonstrate BrowserStack's self-heal capabilities.
- Located at: `android/testng-examples/SelfHealApp.apk`
- Used to validate how the self-heal feature recovers from locator changes or UI modifications.

## Test Scenarios Demonstrated
- This repository includes two different test suites:
  - **Base App Test Suite:** Runs against the base app to show standard automation behavior without self-healing.
  - **Self-Heal App Test Suite:** Runs against the self-heal app with self-healing enabled to demonstrate how tests recover from locator changes or UI modifications.
- The test names will differ between the two suites to clearly indicate which scenario is being executed (e.g., `BaseAppTest` vs `SelfHealAppTest`).
- By comparing the results of these two test suites, you can clearly observe the benefits of the self-heal feature.

## How to Check Differences
- Run your test suite with both the base app and the self-heal app.
- Compare the results and logs: with self-heal enabled, tests should pass even if some locators have changed, while they may fail without self-heal.
- You can also use diff tools to compare the APKs or source code if needed.

## About the Self-Heal Feature
- The self-heal feature in BrowserStack automatically detects and recovers from locator changes in your app's UI during automated tests.
- If a locator fails (e.g., due to a UI update), self-heal attempts to find the correct element using alternative strategies, allowing your tests to continue without manual intervention.
- This reduces test flakiness and maintenance effort, especially in agile environments with frequent UI changes.
- **To enable self-healing, set `selfHeal: true` in your `browserstack.yml` configuration file.**

#### Example `browserstack.yml` snippet:
```yaml
selfHeal: true
```

## Usage
- Build and upload the APKs to BrowserStack as needed.
- Run your automation scripts using the provided configuration files.
- Make sure `selfHeal: true` is set in your `browserstack.yml` to enable the self-heal feature.
- Review the test results and logs to observe self-healing in action.

### Running Tests with Maven or Gradle
- **For Maven:**
  - To run the Base App test suite:
    ```sh
    mvn test -P sampleBaseAppTest
    ```
  - To run the Self-Heal App test suite:
    ```sh
    mvn test -P sampleSelfHealAppTest
    ```
- **For Gradle:**
  - To run the Base App test suite:
    ```sh
    gradle clean sampleBaseAppTest
    ```
  - To run the Self-Heal App test suite:
    ```sh
    gradle clean sampleSelfHealAppTest
    ```

## Getting Help

If you are running into any issues or have any queries, please check [Browserstack Support page](https://www.browserstack.com/support/app-automate) or [get in touch with us](https://www.browserstack.com/contact?ref=help).
