import java.sql.*;
import java.util.Scanner;

public class ConferenceAttendeeManagementSystem {

    // Database Connection
    public static Connection connect() {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Connect to the database
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/conference_db", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Add Attendee
    public static void addAttendee(String fullName, String email, String contactNumber, String country) {
        try (Connection conn = connect()) {
            String query = "INSERT INTO attendees (full_name, email, contact_number, country) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, fullName);
            stmt.setString(2, email);
            stmt.setString(3, contactNumber);
            stmt.setString(4, country);
            stmt.executeUpdate();
            System.out.println("Attendee added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Edit Attendee
    public static void editAttendee(int id, String email, String contactNumber) {
        try (Connection conn = connect()) {
            String query = "UPDATE attendees SET email = ?, contact_number = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, contactNumber);
            stmt.setInt(3, id);
            stmt.executeUpdate();
            System.out.println("Attendee updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete Attendee
    public static void deleteAttendee(int id) {
        try (Connection conn = connect()) {
            String query = "DELETE FROM attendees WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Attendee deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Search Attendee
    public static void searchAttendee(String searchTerm) {
        try (Connection conn = connect()) {
            String query = "SELECT * FROM attendees WHERE full_name LIKE ? OR country LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + searchTerm + "%");
            stmt.setString(2, "%" + searchTerm + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("full_name") +
                        ", Email: " + rs.getString("email") + ", Contact: " + rs.getString("contact_number") +
                        ", Country: " + rs.getString("country"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Generate Attendee Statistics
    public static void generateAttendeeStatistics() {
        try (Connection conn = connect()) {
            CallableStatement stmt = conn.prepareCall("{call get_attendee_statistics()}");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Country: " + rs.getString("country") + ", Attendees: " + rs.getInt("attendee_count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Simple menu for user interaction
        while (true) {
            System.out.println("\nConference Attendee Management System");
            System.out.println("1. Add Attendee");
            System.out.println("2. Edit Attendee");
            System.out.println("3. Delete Attendee");
            System.out.println("4. Search Attendee");
            System.out.println("5. Generate Attendee Statistics");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter Full Name:");
                    String fullName = scanner.nextLine();
                    System.out.println("Enter Email:");
                    String email = scanner.nextLine();
                    System.out.println("Enter Contact Number:");
                    String contactNumber = scanner.nextLine();
                    System.out.println("Enter Country:");
                    String country = scanner.nextLine();
                    addAttendee(fullName, email, contactNumber, country);
                    break;
                case 2:
                    System.out.println("Enter Attendee ID to Edit:");
                    int editId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.println("Enter New Email:");
                    String newEmail = scanner.nextLine();
                    System.out.println("Enter New Contact Number:");
                    String newContact = scanner.nextLine();
                    editAttendee(editId, newEmail, newContact);
                    break;
                case 3:
                    System.out.println("Enter Attendee ID to Delete:");
                    int deleteId = scanner.nextInt();
                    deleteAttendee(deleteId);
                    break;
                case 4:
                    System.out.println("Enter Name or Country to Search:");
                    String searchTerm = scanner.nextLine();
                    searchAttendee(searchTerm);
                    break;
                case 5:
                    generateAttendeeStatistics();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}