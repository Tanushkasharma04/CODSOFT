import java.io.*;
import java.util.*;

public class StudentManagementSystem {
    
    // Student class to represent individual students
    public static class Student {
        private String name;
        private String rollNumber;
        private String grade;

        public Student(String name, String rollNumber, String grade) {
            this.name = name;
            this.rollNumber = rollNumber;
            this.grade = grade;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRollNumber() {
            return rollNumber;
        }

        public void setRollNumber(String rollNumber) {
            this.rollNumber = rollNumber;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        @Override
        public String toString() {
            return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
        }
    }

    // Class to manage the student collection
    public static class ManagementSystem {
        private List<Student> students;
        private static final String FILE_PATH = "student_data.txt";

        public ManagementSystem() {
            students = new ArrayList<>();
            loadData();
        }

        // Add a new student
        public void addStudent(Student student) {
            students.add(student);
            saveData();
        }

        // Remove a student by roll number
        public boolean removeStudent(String rollNumber) {
            for (Student student : students) {
                if (student.getRollNumber().equals(rollNumber)) {
                    students.remove(student);
                    saveData();
                    return true;
                }
            }
            return false;
        }

        // Search for a student by roll number
        public Student searchStudent(String rollNumber) {
            for (Student student : students) {
                if (student.getRollNumber().equals(rollNumber)) {
                    return student;
                }
            }
            return null;
        }

        // Display all students
        public void displayAllStudents() {
            if (students.isEmpty()) {
                System.out.println("No students available.");
            } else {
                for (Student student : students) {
                    System.out.println(student);
                }
            }
        }

        // Load data from file
        private void loadData() {
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] details = line.split(",");
                    String name = details[0].trim();
                    String rollNumber = details[1].trim();
                    String grade = details[2].trim();
                    students.add(new Student(name, rollNumber, grade));
                }
            } catch (IOException e) {
                System.out.println("No previous data found.");
            }
        }

        // Save data to file
        private void saveData() {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                for (Student student : students) {
                    writer.write(student.getName() + ", " + student.getRollNumber() + ", " + student.getGrade());
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("Error while saving data.");
            }
        }

        // Input validation for student data
        public static boolean isValidInput(String name, String rollNumber, String grade) {
            return !name.isEmpty() && !rollNumber.isEmpty() && !grade.isEmpty();
        }
    }

    // Console-based interface to interact with the student management system
    public static class StudentManagementApp {
        private static Scanner scanner = new Scanner(System.in);
        private static ManagementSystem system = new ManagementSystem();

        public static void main(String[] args) {
            while (true) {
                System.out.println("\nSTUDENT MANAGEMENT SYSTEM");
                System.out.println("1. Add Student");
                System.out.println("2. Remove Student");
                System.out.println("3. Search Student");
                System.out.println("4. Display All Students");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        removeStudent();
                        break;
                    case 3:
                        searchStudent();
                        break;
                    case 4:
                        system.displayAllStudents();
                        break;
                    case 5:
                        System.out.println("Exiting the system.");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            }
        }

        private static void addStudent() {
            System.out.print("Enter student's name: ");
            String name = scanner.nextLine();

            System.out.print("Enter student's roll number: ");
            String rollNumber = scanner.nextLine();

            System.out.print("Enter student's grade: ");
            String grade = scanner.nextLine();

            if (ManagementSystem.isValidInput(name, rollNumber, grade)) {
                Student student = new Student(name, rollNumber, grade);
                system.addStudent(student);
                System.out.println("Student added successfully.");
            } else {
                System.out.println("Invalid input! All fields must be filled.");
            }
        }

        private static void removeStudent() {
            System.out.print("Enter the roll number of the student to remove: ");
            String rollNumber = scanner.nextLine();

            if (system.removeStudent(rollNumber)) {
                System.out.println("Student removed successfully.");
            } else {
                System.out.println("Student not found.");
            }
        }

        private static void searchStudent() {
            System.out.print("Enter the roll number of the student to search: ");
            String rollNumber = scanner.nextLine();

            Student student = system.searchStudent(rollNumber);
            if (student != null) {
                System.out.println(student);
            } else {
                System.out.println("Student not found.");
            }
        }
    }
}
