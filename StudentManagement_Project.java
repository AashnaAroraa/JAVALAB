import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentManagement {
    // JDBC connection variables
    private static final String DB_URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private JFrame frame;
    private JTextField idField, nameField, ageField;
    private JTextArea displayArea;

    public StudentManagement() {
        // Initialize the GUI
        frame = new JFrame("Student Management System");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        panel.add(new JLabel("Student ID:"));
        idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Age:"));
        ageField = new JTextField();
        panel.add(ageField);

        JButton addButton = new JButton("Add Student");
        JButton viewButton = new JButton("View Students");
        JButton updateButton = new JButton("Update Student");
        JButton deleteButton = new JButton("Delete Student");

        panel.add(addButton);
        panel.add(viewButton);
        panel.add(updateButton);
        panel.add(deleteButton);

        displayArea = new JTextArea();
        displayArea.setEditable(false);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // Add action listeners
        addButton.addActionListener(e -> addStudent());
        viewButton.addActionListener(e -> viewStudents());
        updateButton.addActionListener(e -> updateStudent());
        deleteButton.addActionListener(e -> deleteStudent());

        frame.setVisible(true);
    }

    private void addStudent() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO students (id, name, age) VALUES (?, ?, ?)");) {
            stmt.setInt(1, Integer.parseInt(idField.getText()));
            stmt.setString(2, nameField.getText());
            stmt.setInt(3, Integer.parseInt(ageField.getText()));
            stmt.executeUpdate();
            displayArea.setText("Student added successfully!\n");
        } catch (Exception ex) {
            displayArea.setText("Error: " + ex.getMessage());
        }
    }

    private void viewStudents() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM students")) {
            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("ID: ").append(rs.getInt("id"))
                        .append(", Name: ").append(rs.getString("name"))
                        .append(", Age: ").append(rs.getInt("age"))
                        .append("\n");
            }
            displayArea.setText(sb.toString());
        } catch (Exception ex) {
            displayArea.setText("Error: " + ex.getMessage());
        }
    }

    private void updateStudent() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("UPDATE students SET name = ?, age = ? WHERE id = ?")) {
            stmt.setString(1, nameField.getText());
            stmt.setInt(2, Integer.parseInt(ageField.getText()));
            stmt.setInt(3, Integer.parseInt(idField.getText()));
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                displayArea.setText("Student updated successfully!\n");
            } else {
                displayArea.setText("Student not found!\n");
            }
        } catch (Exception ex) {
            displayArea.setText("Error: " + ex.getMessage());
        }
    }

    private void deleteStudent() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM students WHERE id = ?")) {
            stmt.setInt(1, Integer.parseInt(idField.getText()));
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                displayArea.setText("Student deleted successfully!\n");
            } else {
                displayArea.setText("Student not found!\n");
            }
        } catch (Exception ex) {
            displayArea.setText("Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        // Check JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            return;
        }

        // Create the database table if it doesn't exist
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS students (" +
                    "id INT PRIMARY KEY, " +
                    "name VARCHAR(100), " +
                    "age INT)";
            stmt.executeUpdate(createTableSQL);
        } catch (SQLException e) {
            System.out.println("Error creating database table: " + e.getMessage());
            return;
        }

        // Launch the application
        SwingUtilities.invokeLater(StudentManagement::new);
    }
}
