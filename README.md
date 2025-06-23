# Self Heal Demo (TestNG)

## Overview
This repository contains demo scripts and configuration for testing the self-healing feature on BrowserStack using Appium and TestNG. It includes two main flows:

### 1. Base App Flow
- Uses the **BaseApp.apk** for standard automation tests without self-healing.
- Located at: `android/testng-examples/BaseApp.apk`
- Demonstrates normal automation behavior and serves as a reference for comparison.

### 2. Self-Heal App Flow
- Uses the **SelfHealApp.apk** to showcase BrowserStack's self-heal capability.
- Located at: `android/testng-examples/SelfHealApp.apk`
- **Run sessions with and without self-heal enabled** to observe how the feature recovers from locator changes or UI modifications:
  - **Without self-heal:** Tests may fail if locators change.
  - **With self-heal:** Tests should pass even if some locators have changed, as self-heal attempts to find the correct elements automatically.

## How to Compare
- Run your test suite on the Self-Heal App twice: once with `selfHeal: false` and once with `selfHeal: true` in your `browserstack.yml`.
- Compare the results and logs to see the impact of self-healing.

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
