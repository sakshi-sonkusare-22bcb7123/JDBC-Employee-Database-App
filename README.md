# 🗄️ Java JDBC Employee Database App – Task 7 (August 2025)

In this task, I built a **desktop CRUD app** that manages employees using **Java + JDBC** connected to a **MySQL** database. The app demonstrates reliable **DB connectivity**, clean **PreparedStatement** usage, and a simple **GUI (Swing)** that performs **Add, View, Update, and Delete** operations.

---

## 👓 Goals

* Connect a Java application to **MySQL** using the **MySQL Connector/J** driver.
* Implement **CRUD** using `Connection`, `PreparedStatement`, and `ResultSet`.
* Build a **desktop GUI** (Swing) for a friendlier user experience.
* Package a repeatable setup so it runs on any Windows machine with JDK + MySQL.

---

## ♨️ What’s Inside

* **Employee management**: Add, list, update salary/department, delete
* **Robust JDBC code**: uses `PreparedStatement` everywhere (safe & fast)
* **Error handling**: user-friendly messages + console logs
* **Modular structure**: `DBConnection` helper + main app

---

## 🧰 Tech Stack & Libraries

| Tool / Library                                                   | Purpose                                        |
| ---------------------------------------------------------------- | ---------------------------------------------- |
| **Java (JDK 17+)**                                               | Programming language & runtime                 |
| **MySQL Server 8.x**                                             | Relational database                            |
| **MySQL Connector/J 8.0.24** (`mysql-connector-java-8.0.24.jar`) | JDBC driver to talk to MySQL                   |
| **JDBC API** (`java.sql`)                                        | `Connection`, `PreparedStatement`, `ResultSet` |
| **Java Swing & AWT** (`javax.swing`, `java.awt`)                 | GUI for CRUD forms & tables                    |
| **VS Code**                                                      | Editor & build/run                             |
| **Terminal / PowerShell**                                        | Compile & run with classpath                   |

> Note: If you don’t need GUI, the app also works from the console (menu-driven).

---

## 🗃️ Database Schema

```sql
CREATE DATABASE IF NOT EXISTS employee_db;
USE employee_db;

CREATE TABLE IF NOT EXISTS employees (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  department VARCHAR(50),
  salary DOUBLE
);
```

---

## 🏗️ Project Structure (simple)

```
Elevate Labs Internship/
├─ EmployeeApp.java           # main app (GUI or console)
├─ DBConnection.java          # JDBC connection helper
├─ lib/
│  └─ mysql-connector-java-8.0.24.jar
└─ README.md
```

---

## 🔌 JDBC Connection Snippet (MySQL)

```java
// DBConnection.java
import java.sql.*;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/employee_db";
    private static final String USER = "root";      // change if different
    private static final String PASSWORD = "password"; // change to your MySQL password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
```

---

## 🪜 Step-by-Step Setup & Run

### 1) Install prerequisites

* **JDK 17+** (verify with `java -version`)
* **MySQL 8.x** (remember your root password)
* **VS Code** (optional, but handy)

### 2) Create DB & Table

Run the SQL from **Database Schema** in MySQL Workbench or the MySQL CLI.

### 3) Add the JDBC Driver (Windows)

* Download **MySQL Connector/J 8.0.24** (jar) and place it at:

  ```
  Elevate Labs Internship\lib\mysql-connector-java-8.0.24.jar
  ```

### 4) Compile (PowerShell from project folder)

```powershell
javac -cp ".;lib/mysql-connector-java-8.0.24.jar" EmployeeApp.java DBConnection.java
```

### 5) Run (PowerShell)

```powershell
java -cp ".;lib/mysql-connector-java-8.0.24.jar" EmployeeApp
```

> Classpath separator on **Windows** is `;` (already shown above). On **macOS/Linux**, use `:` instead of `;` and forward slashes.

---

## 🖥️ GUI Walkthrough (Swing)

* **Add Employee**: enter Name, Department, Salary → **Add**
* **View Employees**: table view refreshes after each change
* **Update**: select a row or enter ID → change salary/department → **Update**
* **Delete**: select a row or enter ID → **Delete**

> If you’re using the console version, a menu like this appears:
>
> ```
> 1. Add Employee  2. View Employees  3. Update Employee  4. Delete Employee  5. Exit
> ```

---

## 🧪 Test Cases (Manual)

* Add: `Sakshi, CSBS, 200000` → appears in table/list
* Update: change Sakshi’s salary to `210000` → confirm refresh shows new value
* Delete: remove by ID → verify row disappears
* Edge: add with empty name → app shows an error & aborts insert

---

## 🧿 Key Code Ideas Covered

* Use of `PreparedStatement` for **parameterized queries** (prevents SQL injection)
* Try-with-resources to **auto-close** `Connection`, `PreparedStatement`, `ResultSet`
* Clear separation of concerns: **DBConnection** vs **UI/logic**
* Basic **validation** before executing queries

---

## 🧯 Troubleshooting

**Error:** `java.sql.SQLException: No suitable driver found for jdbc:mysql://localhost:3306/employee_db`

* Cause: Driver JAR not on **runtime classpath**
* Fix: Ensure `-cp ".;lib/mysql-connector-java-8.0.24.jar"` is used for both `javac` and `java`

**Error:** `Access denied for user 'root'@'localhost'`

* Cause: Wrong password/user or privileges
* Fix: Verify credentials in `DBConnection.java`; ensure MySQL user has rights

**Error:** `Communications link failure`

* Cause: MySQL not running or port blocked
* Fix: Start MySQL service; confirm `localhost:3306` is correct

**Warning:** Timezone/SSL messages

* Optional: append params

  ```
  jdbc:mysql://localhost:3306/employee_db?useSSL=false&serverTimezone=UTC
  ```

---

## 🐧 What I Learned

* How to **connect Java apps to MySQL** with JDBC
* Writing safe, maintainable queries with **PreparedStatement**
* Building a simple **desktop GUI** in **Swing** for CRUD workflows
* Diagnosing classpath issues and common JDBC errors

---

## 🥤 Author

**Sakshi Bhojraj Sonkusare**
Aspiring software developer focusing on practical, user-friendly applications.

* LinkedIn: [https://www.linkedin.com/in/sakshi-sonkusare-381362354/](https://www.linkedin.com/in/sakshi-sonkusare-381362354/)
* Portfolio: Coming soon

---

## ❤️ Contributions

Suggestions, bug reports, and feature requests are welcome! Feel free to open an issue or a pull request.
