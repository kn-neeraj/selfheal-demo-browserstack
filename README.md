# Self-Heal Demo (TestNG) for Web (BrowserStack)

## Demo Website
Try the self-heal demo web app here:  
**[https://browserstack.github.io/selfheal-demo-app/](https://browserstack.github.io/selfheal-demo-app/)**

## What Does This Repo Do?
This repository demonstrates BrowserStack's self-healing feature for web automation using TestNG. It contains two main demo scripts:

- **BStackDemoTest.java**: Runs a standard automation flow on the demo website.
- **BStackSelfHealDemoTest.java**: Runs the same flow, but toggles the "Self-Heal" mode on the website to simulate locator changes.

## Why Two Scripts, and Why Are They Identical?
The self-heal feature is designed to work **without any changes to your test scripts**. In real-world scenarios, your UI changes over time, but your automation scripts remain the same. Self-heal is valuable because it allows your tests to continue working even when locators change, without script updates.

- **BStackDemoTest.java**: Baseline test, runs with the default UI (no locator changes).
- **BStackSelfHealDemoTest.java**: Runs the same steps, but enables the toggle to simulate locator changes.

**The scripts are intentionally identical** (except for the toggle action) to show that self-heal works by adapting to UI changes, not by changing your test code.

## Why a Toggle Button?
In production, UI changes happen over time. For demo purposes, the website includes a toggle button to instantly simulate locator changes (IDs, XPaths, etc.). This allows you to:
- See how your tests behave when locators change, **without modifying your scripts**.
- Demonstrate self-heal: with the toggle ON and `selfHeal: true`, tests pass; with the toggle ON and `selfHeal: false`, tests fail.

## How It Works
1. **Base Flow:**
   - Runs the automation script against the demo website with standard locators (no changes).
   - Serves as a reference for normal automation behavior.
2. **Self-Heal Flow:**
   - The script toggles the self-heal mode on the website, which changes element locators (IDs, XPaths, etc.).
   - When `selfHeal: true` is set in `browserstack.yml`, BrowserStack's self-heal feature will attempt to find the correct elements even if locators have changed.
   - When `selfHeal: false`, the same script will fail at steps where locators have changed.

## How to Compare
- Run your test suite twice:
  1. With `selfHeal: false` in `browserstack.yml` (expect failures when locators change).
  2. With `selfHeal: true` in `browserstack.yml` (expect tests to pass as self-heal recovers from locator changes).
- Compare the results and logs to see the impact of self-healing.

## About the Self-Heal Feature
BrowserStack's self-heal feature automatically detects and recovers from locator changes in your web app's UI during automated tests. If a locator fails (e.g., due to a UI update), self-heal attempts to find the correct element using alternative strategies, allowing your tests to continue without manual intervention. This reduces test flakiness and maintenance effort, especially in agile environments with frequent UI changes.

To enable self-healing, set `selfHeal: true` in your `browserstack.yml` configuration file.

**Example browserstack.yml snippet:**
```yaml
selfHeal: true
```

## Running the Tests

### Using Maven

#### Run sample build

- Clone the repository
- Replace YOUR_USERNAME and YOUR_ACCESS_KEY with your BrowserStack access credentials in browserstack.yml.
- Install dependencies `mvn compile`
- To run the test suite having cross-platform with parallelization, run `mvn test -P sample-test`
- To run the `selfheal` test suite having cross-platform with parallelization, run `mvn test -P sample-selfheal-test`

Understand how many parallel sessions you need by using our [Parallel Test Calculator](https://www.browserstack.com/automate/parallel-calculator?ref=github)

#### Integrate your test suite

This repository uses the BrowserStack SDK to run tests on BrowserStack. Follow the steps below to install the SDK in your test suite and run tests on BrowserStack:

* Create sample browserstack.yml file with the browserstack related capabilities with your [BrowserStack Username and Access Key](https://www.browserstack.com/accounts/settings) and place it in your root folder.
* Add maven dependency of browserstack-java-sdk in your pom.xml file
```sh
<dependency>
    <groupId>com.browserstack</groupId>
    <artifactId>browserstack-java-sdk</artifactId>
    <version>LATEST</version>
    <scope>compile</scope>
</dependency>
```
* Modify your build plugin to run tests by adding argLine `-javaagent:${com.browserstack:browserstack-java-sdk:jar}` and `maven-dependency-plugin` for resolving dependencies in the profiles `sample-test` and `sample-selfheal-test`.
```
            <plugin>
               <artifactId>maven-dependency-plugin</artifactId>
                 <executions>
                   <execution>
                     <id>getClasspathFilenames</id>
                       <goals>
                         <goal>properties</goal>
                       </goals>
                   </execution>
                 </executions>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.0.0-M5</version>
                <configuration>
                    <suiteXmlFiles>
                        <suiteXmlFile>config/sample-selfheal-test.testng.xml</suiteXmlFile>
                    </suiteXmlFiles>
                    <argLine>
                        -javaagent:${com.browserstack:browserstack-java-sdk:jar}
                    </argLine>
                </configuration>
            </plugin>
```
* Install dependencies `mvn compile`

### Using Gradle

#### Prerequisites
- If using Gradle, Java v9+ is required.

#### Run sample build

- Clone the repository
- Install dependencies `gradle build`
- To run the test suite having cross-platform with parallelization, run `gradle sampleTest`
- To run the `selfheal` test suite having cross-platform with parallelization, run `gradle sampleSelfHealTest`

Understand how many parallel sessions you need by using our [Parallel Test Calculator](https://www.browserstack.com/automate/parallel-calculator?ref=github)

#### Integrate your test suite

This repository uses the BrowserStack SDK to run tests on BrowserStack. Follow the steps below to install the SDK in your test suite and run tests on BrowserStack:

* Following are the changes required in `gradle.build` -
    * Add `compileOnly 'com.browserstack:browserstack-java-sdk:latest.release'` in dependencies
    * Fetch Artifact Information and add `jvmArgs` property in tasks *SampleTest* and *SampleSelfHealTest* :
  ```
  def browserstackSDKArtifact = configurations.compileClasspath.resolvedConfiguration.resolvedArtifacts.find { it.name == 'browserstack-java-sdk' }
  
  task sampleTest(type: Test) {
    useTestNG() {
      dependsOn cleanTest
      useDefaultListeners = true
      suites "config/sample-test.testng.xml"
      jvmArgs "-javaagent:${browserstackSDKArtifact.file}"
    }
  }
  ```

* Install dependencies `gradle build`

## Notes
- You can view your test results on the [BrowserStack Automate dashboard](https://www.browserstack.com/automate)
- The self-heal toggle in the demo website is for demonstration purposes to simulate locator changes.
- Both scripts are intentionally similar to highlight the effect of self-healing with and without the feature enabled.
