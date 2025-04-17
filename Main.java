import java.util.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            StudentDAO dao = new StudentDAO();
            while (true) {
                System.out.println("\n1. Add Student\n2. View All Students\n3. Update Student\n4. Delete Student\n5. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter email: ");
                        String email = sc.nextLine();
                        System.out.print("Enter course: ");
                        String course = sc.nextLine();
                        dao.addStudent(new Student(name, email, course));
                        System.out.println("Student added.");
                        break;

                    case 2:
                        List<Student> list = dao.getAllStudents();
                        for (Student s : list) {
                            System.out.printf("ID: %d | Name: %s | Email: %s | Course: %s\n", s.getId(), s.getName(), s.getEmail(), s.getCourse());
                        }
                        break;

                    case 3:
                        System.out.print("Enter student ID to update: ");
                        int id = sc.nextInt(); sc.nextLine();
                        System.out.print("Enter new name: ");
                        name = sc.nextLine();
                        System.out.print("Enter new email: ");
                        email = sc.nextLine();
                        System.out.print("Enter new course: ");
                        course = sc.nextLine();
                        dao.updateStudent(new Student(id, name, email, course));
                        System.out.println("Student updated.");
                        break;

                    case 4:
                        System.out.print("Enter student ID to delete: ");
                        id = sc.nextInt();
                        dao.deleteStudent(id);
                        System.out.println("Student deleted.");
                        break;

                    case 5:
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid choice.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
