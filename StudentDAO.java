import java.sql.*;
import java.util.*;

public class StudentDAO {
    private Connection conn;

    public StudentDAO() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "root", "password");
    }

    public void addStudent(Student student) throws SQLException {
        String query = "INSERT INTO students(name, email, course) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, student.getName());
        stmt.setString(2, student.getEmail());
        stmt.setString(3, student.getCourse());
        stmt.executeUpdate();
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> list = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM students");
        while (rs.next()) {
            Student s = new Student(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("course")
            );
            list.add(s);
        }
        return list;
    }

    public void updateStudent(Student student) throws SQLException {
        String query = "UPDATE students SET name=?, email=?, course=? WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, student.getName());
        stmt.setString(2, student.getEmail());
        stmt.setString(3, student.getCourse());
        stmt.setInt(4, student.getId());
        stmt.executeUpdate();
    }

    public void deleteStudent(int id) throws SQLException {
        String query = "DELETE FROM students WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}
