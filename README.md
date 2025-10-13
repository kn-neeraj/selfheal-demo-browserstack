# BrowserStack Self-Healing AI Agent Demo

## What is BrowserStack Self-Healing AI Agent?

BrowserStack's Self-Healing AI Agent automatically detects and repairs broken selectors during test execution. When the application's UI changes (for example, a button ID changes from #submit to #submit-btn), traditional tests fail with NoSuchElementException errors. The Self-Healing Agent dynamically identifies these selector failures, finds the correct element in real time, and allows the test to continue, thereby, reducing manual maintenance and improving build stability.

It works on the principle that the test script remains unchanged while DOM changes lead to selector failures. Therefore, the agent utilises past successful run as context to heal broken selectors during test execution.

## Introduction

This repository demonstrates BrowserStack's Self-Healing AI Agent for web and native apps.
- For Web demo, checkout branch `testng-automate` and follow the readme
- For Native App Demo, checkout branch `testng-appium-app` and follow the readme
