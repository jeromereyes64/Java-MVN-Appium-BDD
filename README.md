# 📌 General Store App - Mobile Automation Framework

This project is a **mobile automation testing framework** built for automating test scenarios in the **General Store App** using **Appium**, **Java**, **Cucumber (BDD)**, and **TestNG**.

It follows a **Page Object Model (POM)** design pattern and supports both functional and UI validations, including toast/snackbar message verification.

---

## ✅ Tech Stack

- **Language:** Java 17 or above
- **Automation Tool:** Appium (v2.x)
- **Build Tool:** Maven
- **Testing Framework:** TestNG + Cucumber BDD
- **Design Pattern:** Page Object Model (POM)
- **Device:** Android real device/emulator

---

## 📂 Project Structure

```
src
 └── main
     └── java
         └── appiumJava.testqa
             ├── pages               # Page classes for screen elements and actions
             │    └── GeneralStorePage.java
             │
             ├── stepdefinitions     # Step definitions for Cucumber feature files
             │    └── GeneralStoreAppSteps.java
             │
             └── utils               # Utility classes
                  ├── DriverManager.java
                  ├── CommonActions.java
                  └── Hooks.java
             
 └── test
     └── resources
         └── features               # Cucumber Feature files
              └── generalStore.feature
              
app/                                 # APK files
 └── General-Store.apk
```

---

## ⚙️ Setup Instructions

### 1️⃣ Prerequisites

✔ Install **Java 17+**  
✔ Install **Appium 2.x** and **Appium Inspector**  
✔ Install **Android Studio** with SDK + Platform Tools  
✔ Enable **Developer Options** and **USB Debugging** on the device

### 2️⃣ Clone the Repository

```bash
git clone <your-repo-url>
cd <project-folder>
```

### 3️⃣ Install Maven Dependencies

```bash
mvn clean install
```

### 4️⃣ Configure Device & App

Update your device details inside **DriverManager.java**:

```java
options.setDeviceName("RZ8R1178SHH");          // Change to your device/emulator name
options.setPlatformVersion("13.0");           // Change to your Android version
options.setApp(System.getProperty("user.dir") + "/app/General-Store.apk");
```

### 5️⃣ Start Appium Server

```bash
appium
```

### 6️⃣ Run Tests

Using Maven:

```bash
mvn clean test
```

or directly through TestNG runner.

---

## 📝 Feature File Example

**generalStore.feature**

```gherkin
Feature: Shopping preferences selection in General Store app

  @generalStore @CountrySelection
  Scenario Outline: User selects country and proceeds to shop
    Given the General Store app is launched
    When I select "<country>" from the country dropdown
    And I enter my name as "<name>"
    And I select gender "<gender>"
    And I tap on the "Let's Shop" button
    Then I should be navigated to the shopping page

    Examples:
      | country   | name   | gender |
      | Andorra   | Jerome | Male   |
      | Argentina | Maria  | Female |
      | Australia | John   | Male   |

  @generalStore @NameValidation
  Scenario: User tries to proceed without entering a name
    Given the General Store app is launched
    When I select "Andorra" from the country dropdown
    And I leave the name field empty
    And I select gender "Male"
    And I tap on the "Let's Shop" button
    Then I should see an error message "Please enter your name"
```

---

## 🔥 Key Features

✅ **Page Object Model (POM)** – Clean separation of locators & actions  
✅ **Reusable Common Actions** – click, sendKeys, scroll, drag & drop, toast/snackbar handling  
✅ **Toast/Snackbar Verification** – Validates short-lived messages dynamically  
✅ **BDD-Style Reporting** – Human-readable test cases via Gherkin syntax  
✅ **Device Agnostic** – Works on any Android device or emulator

---

## 📌 Sample Toast Verification

```java
@Then("I should see an error message {string}")
public void i_should_see_an_error_message(String expectedMessage) {
    String actualToast = actions.getToastMessage(); // Captures dynamically
    Assert.assertEquals(actualToast, expectedMessage, "Toast message mismatch!");
    System.out.println("✅ Toast message verified: " + expectedMessage);
}
```

---

## 📸 Screenshots & Logs

After execution, you can find detailed logs in the console.  
(Screenshots can be added later for failed scenarios.)
