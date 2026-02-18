# Selenium Newsletter Tests

## Description

This project contains automated tests for newsletter signup functionality using Selenium WebDriver and JUnit 5. It demonstrates best practices for test automation with the Page Object Model pattern.

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher

## Setup

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd selenium-newsletter-tests
   ```

2. Install dependencies:
   ```bash
   mvn clean install
   ```

## Running Tests

To run the tests locally:

```bash
mvn test
```

The tests will automatically download the required browser drivers using WebDriverManager and run against Chrome browser.

## Project Structure

- `src/main/java/com/automation/pages/` - Page Object Model classes (BasePage.java, NewsletterPage.java)
- `src/test/java/com/automation/tests/` - Test classes (NewsletterSignupTest.java)
- `.github/workflows/ci.yml` - GitHub Actions CI pipeline for automated testing

## CI/CD

The project includes a GitHub Actions workflow that:
- Runs tests on Ubuntu with Java 11
- Installs Chrome browser
- Executes the test suite on every push and pull request to the main branch

## Dependencies

- Selenium WebDriver 4.18.1
- WebDriverManager 5.7.0 (for automatic browser driver management)
- JUnit 5.10.2

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Run tests: `mvn test`
5. Submit a pull request

## License

This project is licensed under the MIT License.