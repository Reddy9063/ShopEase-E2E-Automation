# E2E Test Automation Framework for Web & API

A comprehensive End-to-End (E2E) test automation framework for a demo e-commerce platform, covering both frontend UI and backend API workflows. Built with Java, Selenium WebDriver, REST Assured, TestNG, and integrated with GitHub Actions for CI/CD.

## Features
- Data-driven testing for flexibility
- Page Object Model (POM) for maintainability
- Utility functions for common actions
- Robust error handling and logging
- Configurable for multiple environments
- CI/CD integration with GitHub Actions

## Tech Stack
- Java
- Selenium WebDriver
- REST Assured
- TestNG
- Maven
- GitHub Actions

## Project Structure
```
|-- src
|   |-- main
|   |   |-- java
|   |   |   |-- config
|   |   |   |-- utils
|   |   |-- resources
|   |-- test
|       |-- java
|           |-- api
|           |-- ui
|           |-- data
|           |-- pages
|-- .github
|   |-- workflows
|-- pom.xml
|-- README.md
```

## Getting Started
1. Clone the repo
2. Install dependencies: `mvn clean install`
3. Run tests: `mvn test`

## CI/CD
Tests run automatically on every push via GitHub Actions.

---

Customize as needed for your actual application!
