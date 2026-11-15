import java.sql.*;
import java.util.Scanner;

public class Main {
    // Database connection details
    static final String URL = "jdbc:postgresql://localhost:5432/a3_students";
    static final String USER = "postgres";
    static final String PASSWORD = "your_password";

    // Retrieves and displays all students currently in the database
    static void getAllStudents() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM students")) {
            // Loop through each row and get the student's information
            while (rs.next()) {
                int id = rs.getInt("student_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String email = rs.getString("email");
                Date date = rs.getDate("enrollment_date");
                System.out.println("Student id: " + id + ", First name: " + first_name + ", Last name: " + last_name + ", Email: " + email + ", Date: " + date);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving students: " + e.getMessage());
        }
    }

    // Adds a new student to the database
    static void addStudent(String firstName, String lastName, String email, String enrollmentDate) {
        String sql = "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Set the values for each placeholder in the INSERT statement
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setDate(4, Date.valueOf(enrollmentDate));


            int rows = pstmt.executeUpdate();
            System.out.println(rows + " student added.");
        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }
    // Updates the email address of a student based on their ID
    static void updateStudentEmail(int studentId, String newEmail) {
        String sql = "UPDATE students SET email = ? WHERE student_id = ?;";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // First placeholder is the new email, second is the student ID
            pstmt.setString(1, newEmail);
            pstmt.setInt(2, studentId);

            int rows = pstmt.executeUpdate();
            if (rows == 0) {
                System.out.println("No student found with id " + studentId);
            } else {
                System.out.println("Email updated for student " + studentId);
            }
        } catch (SQLException e) {
            System.out.println("Error updating email: " + e.getMessage());
        }
    }
    // Deletes a student from the database using their student ID
    static void deleteStudent(int studentId) {
        String sql = "DELETE FROM students WHERE student_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, studentId);
            // Execute the DELETE statement and check if a row was removed
            int rows = pstmt.executeUpdate();
            if (rows == 0) {
                System.out.println("No student found with id " + studentId);
            } else {
                System.out.println("Entry deleted for student with id: " + studentId);
            }
        } catch (SQLException e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }

    // Menu created for testing + video
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1) View all students");
            System.out.println("2) Add student");
            System.out.println("3) Update student email");
            System.out.println("4) Delete student");
            System.out.println("5) Exit");
            System.out.print("Choose: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    getAllStudents();
                    break;
                case "2":
                    System.out.print("First name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Last name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enrollment date (YYYY-MM-DD): ");
                    String enrollmentDate = scanner.nextLine();
                    addStudent(firstName, lastName, email, enrollmentDate);
                    break;
                case "3":
                    System.out.print("Student id: ");
                    int studentID = Integer.parseInt(scanner.nextLine());
                    System.out.print("New email: ");
                    String newEmail = scanner.nextLine();
                    updateStudentEmail(studentID, newEmail);
                    break;
                case "4":
                    System.out.print("Student id to delete: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    deleteStudent(id);
                    break;
                case "5":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
}