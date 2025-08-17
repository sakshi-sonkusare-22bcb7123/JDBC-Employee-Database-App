import java.sql.*;
import java.util.Scanner;

public class EmployeeApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Employee Database ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> addEmployee(sc);
                case 2 -> viewEmployees();
                case 3 -> updateEmployee(sc);
                case 4 -> deleteEmployee(sc);
                case 5 -> { System.out.println("Thankyou for using Employee App. Goodbye!"); return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void addEmployee(Scanner sc) {
        System.out.print("Enter name: ");
        String name = sc.next();
        System.out.print("Enter department: ");
        String dept = sc.next();
        System.out.print("Enter salary: ");
        double salary = sc.nextDouble();

        String sql = "INSERT INTO employees (name, department, salary) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, dept);
            ps.setDouble(3, salary);
            ps.executeUpdate();
            System.out.println("Employee added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewEmployees() {
        String sql = "SELECT * FROM employees";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\nID\tName\tDepartment\tSalary");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                                   rs.getString("name") + "\t" +
                                   rs.getString("department") + "\t" +
                                   rs.getDouble("salary"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateEmployee(Scanner sc) {
        System.out.print("Enter Employee ID to update: ");
        int id = sc.nextInt();
        System.out.print("Enter new salary: ");
        double salary = sc.nextDouble();

        String sql = "UPDATE employees SET salary = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, salary);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Employee updated!");
            else System.out.println("Employee not found!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteEmployee(Scanner sc) {
        System.out.print("Enter Employee ID to delete: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM employees WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Employee deleted!");
            else System.out.println("Employee not found!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
