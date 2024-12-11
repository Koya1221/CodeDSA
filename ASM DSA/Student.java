import java.util.*;

class Student {
    private String id;
    private String name;
    private double marks;

    public Student(String id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    // Setters to allow updating name and marks
    public void setName(String name) {
        this.name = name;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Marks: " + marks;
    }
}

class StudentManagementSystem {
    private static List<Student> studentList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = getValidChoice();
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    sortStudentsByMarks();
                    break;
                case 4:
                    searchStudentById();
                    break;
                case 5:
                    editStudent();
                    break;
                case 6:
                    deleteStudent();
                    break;
                case 7:
                    exitProgram();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("1. Add a new student");
        System.out.println("2. View all students");
        System.out.println("3. Sort students by marks");
        System.out.println("4. Search for a student by ID");
        System.out.println("5. Edit a student's details");
        System.out.println("6. Delete a student");
        System.out.println("7. Exit the program");
        System.out.print("Enter your choice: ");
    }

    private static int getValidChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a numeric value.");
            scanner.next(); // Consume the invalid input
        }
        return scanner.nextInt();
    }

    private static void addStudent() {
        System.out.print("Enter student ID: ");
        String id = scanner.next();  // ID should be a string
        scanner.nextLine();  // Consume newline
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student marks: ");
        double marks = scanner.nextDouble();

        Student newStudent = new Student(id, name, marks);
        studentList.add(newStudent);
        System.out.println("Student added successfully.");
    }

    private static void viewStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    private static void sortStudentsByMarks() {
        studentList.sort((s1, s2) -> Double.compare(s2.getMarks(), s1.getMarks()));
        System.out.println("Students sorted by marks:");
        viewStudents();
    }

    private static void searchStudentById() {
        System.out.print("Enter student ID to search: ");
        String id = scanner.next();
        Student student = searchStudentById(id);
        if (student != null) {
            System.out.println("Student found: " + student);
        } else {
            System.out.println("Student not found.");
        }
    }

    private static Student searchStudentById(String id) {
        for (Student student : studentList) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    private static void editStudent() {
        System.out.print("Enter student ID to edit: ");
        String id = scanner.next();
        Student student = searchStudentById(id);
        if (student != null) {
            System.out.print("Enter new name: ");
            scanner.nextLine();  // Consume newline
            String name = scanner.nextLine();
            System.out.print("Enter new marks: ");
            double marks = scanner.nextDouble();
            student.setName(name);
            student.setMarks(marks);
            System.out.println("Student details updated.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        String id = scanner.next();
        Student student = searchStudentById(id);
        if (student != null) {
            studentList.remove(student);
            System.out.println("Student deleted.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void exitProgram() {
        System.out.println("Exiting the program...");
    }
}
