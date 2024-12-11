import java.util.*;

class StudentStack {
    private Stack<Student> stack;

    // Constructor để khởi tạo Stack
    public StudentStack() {
        stack = new Stack<>();
    }

    // Thêm sinh viên vào Stack
    public void pushStudent(Student student) {
        stack.push(student);
    }

    // Lấy sinh viên ra khỏi Stack
    public Student popStudent() {
        if (!stack.isEmpty()) {
            return stack.pop();
        } else {
            System.out.println("The stack is empty.");
            return null;
        }
    }

    // Xem sinh viên ở đỉnh của Stack mà không lấy ra
    public Student peekStudent() {
        if (!stack.isEmpty()) {
            return stack.peek();
        } else {
            System.out.println("The stack is empty.");
            return null;
        }
    }

    // Kiểm tra Stack có rỗng không
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    // In danh sách sinh viên trong Stack
    public void printStack() {
        for (Student student : stack) {
            System.out.println(student);
        }
    }

    // Tạo bản sao của Stack
    public StudentStack cloneStack() {
        StudentStack clonedStack = new StudentStack();
        Stack<Student> tempStack = (Stack<Student>) stack.clone();
        while (!tempStack.isEmpty()) {
            clonedStack.pushStudent(tempStack.pop());
        }
        return clonedStack;
    }
}

public class StudentInformationSystemIT {
    private Student[] students;
    private int size;

    public StudentInformationSystemIT(int capacity) {
        students = new Student[capacity];
        size = 0;
    }

    public void addStudent(Student student) {
        if (size < students.length) {
            students[size++] = student;
        } else {
            System.out.println("The student array is full.");
        }
    }

    public void addRandomStudents(int num) {
        Random rand = new Random();
        String[] sampleNames = {"John Doe", "Alice Smith", "Bob Brown", "Charlie Lee", "David Williams", "Emma Johnson", "Frank Miller", "Grace Lee"};

        for (int i = 0; i < num; i++) {
            String id = "S" + String.format("%03d", rand.nextInt(999) + 1);
            String name = sampleNames[rand.nextInt(sampleNames.length)];
            double marks = rand.nextDouble() * 10; // Điểm trong khoảng [0.0, 10.0]
            addStudent(new Student(id, name, marks));
        }
    }


    public void sortStudentsWithBubbleSortClone() {
        long startTime = System.nanoTime();

        // Tạo bản sao của danh sách sinh viên
        Student[] clonedStudents = Arrays.copyOf(students, size);

        // Sắp xếp danh sách sinh viên đã sao chép bằng thuật toán Bubble Sort
        for (int i = 0; i < clonedStudents.length - 1; i++) {
            for (int j = 0; j < clonedStudents.length - i - 1; j++) {
                if (clonedStudents[j].getMarks() > clonedStudents[j + 1].getMarks()) {
                    Student temp = clonedStudents[j];
                    clonedStudents[j] = clonedStudents[j + 1];
                    clonedStudents[j + 1] = temp;
                }
            }
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Bubble Sort on cloned list took " + duration + " nanoseconds");
    }

    // Sắp xếp với Merge Sort trên bản sao và hiển thị thời gian
    public void sortStudentsWithMergeSortClone() {
        long startTime = System.nanoTime();

        // Tạo bản sao của danh sách sinh viên
        Student[] clonedStudents = Arrays.copyOf(students, size);

        // Sắp xếp danh sách sinh viên đã sao chép bằng thuật toán Merge Sort
        mergeSort(clonedStudents, 0, clonedStudents.length - 1);

        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Merge Sort on cloned list took " + duration + " nanoseconds");
    }


    private void mergeSort(Student[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private void merge(Student[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Student[] leftArray = new Student[n1];
        Student[] rightArray = new Student[n2];

        System.arraycopy(arr, left, leftArray, 0, n1);
        System.arraycopy(arr, mid + 1, rightArray, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i].getMarks() <= rightArray[j].getMarks()) {
                arr[k++] = leftArray[i++];
            } else {
                arr[k++] = rightArray[j++];
            }
        }

        while (i < n1) {
            arr[k++] = leftArray[i++];
        }

        while (j < n2) {
            arr[k++] = rightArray[j++];
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập số lượng sinh viên từ người dùng
        System.out.print("Enter the number of students to add: ");
        int numStudents = scanner.nextInt();

        StudentInformationSystemIT system = new StudentInformationSystemIT(numStudents);

        // Thêm sinh viên ngẫu nhiên
        system.addRandomStudents(numStudents);

        // In số lượng sinh viên
        System.out.println("Number of students added: " + numStudents);

        // Tạo Stack và thêm sinh viên vào Stack
        StudentStack studentStack = new StudentStack();
        System.out.println("\nAdding students to the stack...");
        for (int i = 0; i < numStudents; i++) {
            studentStack.pushStudent(system.students[i]);
        }

        // In danh sách sinh viên trong Stack
        System.out.println("\nStudent stack:");
        studentStack.printStack();

        // Tạo bản sao của Stack
        StudentStack clonedStack = studentStack.cloneStack();
        System.out.println("\nCloned student stack:");
        clonedStack.printStack();

        // Lấy sinh viên ra khỏi Stack
        System.out.println("\nPopping a student from the stack: ");
        System.out.println(studentStack.popStudent());

        // Kiểm tra sinh viên ở đỉnh Stack
        System.out.println("\nPeeking at the top student in the stack: ");
        System.out.println(studentStack.peekStudent());

        // Sắp xếp với Bubble Sort và hiển thị thời gian
        System.out.println("\nBubble Sort:");
        system.sortStudentsWithBubbleSortClone();

        // Sắp xếp với Merge Sort và hiển thị thời gian
        System.out.println("\nMerge Sort:");
        system.sortStudentsWithMergeSortClone();
    }
}
