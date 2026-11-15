# COMP 3005 Assignment 3 Q1

## Description
This project is a Java application that connects to a PostgreSQL database using JDBC. It allows users to manage student records through a simple menu in the console.

## Project Structure
- `database/schema.sql`: Contains the SQL commands to create the students table and insert the initial student records.
- `app/pom.xml`: Contains the Maven configuration and project dependencies, including the PostgreSQL JDBC driver.
- `app/src/main/java/Main.java`: Contains the Java application code, including the database connection and CRUD functions.

## Requirements
- Java JDK installed
- PostgreSQL installed and running
- pgAdmin 4 installed
- IntelliJ IDEA (used to open and run the project)

## Database Setup
1. Create the database: open pgAdmin 4 and create a new database called `a3_students`.
2. Open the Query Tool for `a3_students`, open `database/schema.sql`, and run it.

## Configuration
Open `Main.java` and update the database connection details to match your PostgreSQL setup:

```java
static final String URL = "jdbc:postgresql://localhost:5432/a3_students";
static final String USER = "postgres";
static final String PASSWORD = "your_password";
```

Replace the username and password with your own PostgreSQL login information.

## How to Run
1. Open the `app` folder in IntelliJ.
2. Wait for Maven to load and download the project dependencies.
3. Open `Main.java` and click the green Run button.

Once the application starts, a menu will appear with the following options:
- **View all students** (`getAllStudents()`): Displays all records currently stored in the students table.
- **Add student** (`addStudent()`): Prompts the user to enter the student's first name, last name, email, and enrollment date.
- **Update student email** (`updateStudentEmail()`): Asks for a student ID and the new email address.
- **Delete student** (`deleteStudent()`): Asks for the ID of the student to be deleted.
- **Exit**: Closes the application.

## Video Demonstration
https://drive.google.com/file/d/1cFA0TA-YSSz0z1Ks5nzLqspvhQqkUUoD/view?usp=sharing

## AI Use
I used Claude to explain lecture concepts and check my reasoning while studying. All code and written content are my own work.
