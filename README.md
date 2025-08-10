 miniProject - Selenium Automation Framework

This project demonstrates a modular and maintainable Selenium automation framework using Java 21, TestNG, and Maven. It automates signup flows and UI interactions for a sample Facebook-like web application, using a Page Object Model (POM) design and TestNG lifecycle annotations for structured execution and reporting. The framework supports both Chrome and Edge browsers with parameterized configuration, and is optimized for parallel test runs, validation logic, and scalable integration.

---

 Tech Stack

- Language: Java 21
- Build Tool: Maven
- Test Framework: TestNG
- Automation: Selenium WebDriver 4.27.0
- API Testing: TestNG 7.10.2
- IDE: IntelliJ IDEA

---

 Key Features

-  Modular design using Page Object Model (POM)
-  Cross-browser support (Chrome, Edge)
-  Maven-managed dependencies
-  TestNG-driven test execution and lifecycle management
-  Clean and readable code for future enhancements
-  **Screenshot capture for every method/function** — ensures visual traceability and debugging support

---

 Screenshot Logging

Every test method and key page interaction automatically captures a screenshot:
- Screenshots are saved with timestamped filenames
- Useful for debugging, reporting, and visual validation
- Integrated into `@AfterMethod` or custom utility class
- Can be extended to attach screenshots to TestNG reports or Allure

---

 Running Tests

1. Install dependencies:
   ```bash
   mvn clean install
