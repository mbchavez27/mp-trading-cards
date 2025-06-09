# mp-trading-cards

Repository for CCPROG3 MP titled Trading Card Inventory System

# Developer Guide

Welcome to the **Trading Card Inventory System** development guide! This will help you get started quickly and maintain consistency across the project.

---

## 1. Clone the Repository

Clone the repo using HTTPS or SSH:

```bash
git clone https://github.com/yourusername/mp-trading-cards.git
# or using SSH
git clone git@github.com:yourusername/mp-trading-cards.git
```

---

## 2. Branching Strategy

- The **main** branch holds the stable production-ready code.
- Create a **feature branch** for each new feature or bugfix following this pattern:

```
feature/<feature-name>
fix/<issue-name>
```

Example:

```bash
git checkout -b feature/card-module
```

- Commit your changes with clear messages.
- Push your branch to the remote repo:

```bash
git push origin feature/card-module
```

- Open a Pull Request (PR) to merge your branch into **main** for code review.

---

## 3. Running the Application

This is a Java CLI app following the MVC pattern.

### Compile

Navigate to the project root and run:

```bash
javac -d out -sourcepath src src/com/tradingcards/Main.java
```

- `-d out` compiles the classes into the `out` directory.
- `-sourcepath src` tells the compiler where to find source files.

### Run

Run the compiled app with:

```bash
java -cp out com.tradingcards.Main
```

---

## 4. Project Structure & Naming Conventions

- Source code lives in `src/com/tradingcards/` organized by feature packages:
  - `binder/`
  - `card/`
  - `collection/`
  - `deck/`
- Each feature follows MVC pattern:

  - `FeatureController.java`
  - `FeatureModel.java`
  - `FeatureView.java`

- Main entry point is `Main.java` in `com.tradingcards`.

- Tests live under `test/com/tradingcards/` mirroring the source tree.
  - Test classes end with `Test.java`, e.g., `CardControllerTest.java`.

---

## 5. Coding Style

- **Package names:** all lowercase, e.g., `com.tradingcards.elements.card`
- **Class names:** PascalCase, e.g., `CardController`
- **Method and variable names:** camelCase, descriptive verbs for methods
- **Constants:** ALL_CAPS_WITH_UNDERSCORES
- Keep filenames matching the class names exactly (case-sensitive).

---

## 6. Running Tests

Tests are under the `test/` directory, following the same package structure. Use your preferred Java test runner (JUnit recommended).

Example command to run tests (if using JUnit):

```bash
# assuming junit jar is on classpath
java -cp "out;path/to/junit.jar" org.junit.runner.JUnitCore com.tradingcards.elements.card.CardControllerTest
```

---

## 7. Helpful Tips

- Always pull the latest `main` branch before starting work.
- Keep commits small and focused.
- Write tests for new features and bug fixes.
- Follow the MVC and feature-based modular structure for new code.
- Ask if unsure about any part of the architecture or conventions!

---

Feel free to reach out if you need any help getting set up or have questions about the project!
