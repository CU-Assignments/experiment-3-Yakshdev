import java.util.HashMap;
import java.util.Scanner;

class CourseFullException extends Exception {
    public CourseFullException(String message) {
        super(message);
    }
}

class PrerequisiteNotMetException extends Exception {
    public PrerequisiteNotMetException(String message) {
        super(message);
    }
}

class Course {
    private String name;
    private int maxCapacity;
    private int enrolledStudents;
    private String prerequisite;

    public Course(String name, int maxCapacity, String prerequisite) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.enrolledStudents = 0;
        this.prerequisite = prerequisite;
    }

    public String getName() {
        return name;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public boolean isFull() {
        return enrolledStudents >= maxCapacity;
    }

    public void enrollStudent() throws CourseFullException {
        if (isFull()) {
            throw new CourseFullException("Error: CourseFullException - " + name + " is full.");
        }
        enrolledStudents++;
        System.out.println("Enrollment Successful in " + name);
    }
}

public class UniversityEnrollmentSystem {
    private static HashMap<String, Boolean> completedCourses = new HashMap<>();
    private static HashMap<String, Course> availableCourses = new HashMap<>();

    public static void enroll(String courseName) throws CourseFullException, PrerequisiteNotMetException {
        if (!availableCourses.containsKey(courseName)) {
            System.out.println("Error: Course not found.");
            return;
        }

        Course course = availableCourses.get(courseName);
        String prerequisite = course.getPrerequisite();

        if (!prerequisite.isEmpty() && !completedCourses.getOrDefault(prerequisite, false)) {
            throw new PrerequisiteNotMetException("Error: PrerequisiteNotMetException - Complete " + prerequisite + " before enrolling in " + courseName + ".");
        }

        course.enrollStudent();
        completedCourses.put(courseName, true); // Mark as completed after enrollment
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Initialize courses
        availableCourses.put("Core Java", new Course("Core Java", 3, ""));
        availableCourses.put("Advanced Java", new Course("Advanced Java", 2, "Core Java"));
        availableCourses.put("Data Structures", new Course("Data Structures", 2, "Core Java"));

        try {
            System.out.print("Enroll in Course: ");
            String courseName = sc.nextLine();

            enroll(courseName);
        } catch (CourseFullException | PrerequisiteNotMetException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Invalid input.");
        } finally {
            sc.close();
        }
    }
}
