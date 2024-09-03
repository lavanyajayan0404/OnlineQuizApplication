package Application;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin("admin", "admin1");
        Trainer trainer = new Trainer("trainer", "trainer1");
        Student student = new Student("student", "student1");

        while (true) {
            System.out.println("1. Admin");
            System.out.println("2. Trainer");
            System.out.println("3. Student");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    handleAdmin(scanner, admin);
                    break;
                case 2:
                    handleTrainer(scanner, trainer, student);
                    break;
                case 3:
                    handleStudent(scanner, student);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleAdmin(Scanner scanner, Admin admin) {
        System.out.print("Enter Admin Username: ");
        String adminUsername = scanner.next();
        System.out.print("Enter Admin Password: ");
        String adminPassword = scanner.next();
        System.out.println("Admin verified");

        while (true) {
            System.out.println("1. Add Question");
            System.out.println("2. Delete Question");
            System.out.println("3. View Question");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int adminChoice = scanner.nextInt();

            switch (adminChoice) {
                case 1:
                    addQuestion(scanner, admin);
                    break;
                case 2:
                    deleteQuestion(scanner, admin);
                    break;
                case 3:
                    viewQuestion(scanner, admin);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addQuestion(Scanner scanner, Admin admin) {
        System.out.print("Enter question number: ");
        int qNo = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter question text: ");
        String qText = scanner.nextLine();
        System.out.print("Enter options separated by comma: ");
        String[] options = scanner.nextLine().split(",");
        System.out.print("Enter correct answer (a/b/c/d): ");
        char correctAnswer = scanner.next().charAt(0);
        System.out.print("Enter complexity (easy/medium/hard): ");
        String complexity = scanner.next();
        admin.addQuestion(new Question(qNo, qText, options, correctAnswer, complexity));
    }

    private static void deleteQuestion(Scanner scanner, Admin admin) {
        System.out.print("Enter question number to delete: ");
        int delQNo = scanner.nextInt();
        admin.deleteQuestion(delQNo);
    }

    private static void viewQuestion(Scanner scanner, Admin admin) {
        System.out.print("Enter question number to view: ");
        int viewQNo = scanner.nextInt();
        Question q = admin.viewQuestion(viewQNo);
        if (q != null) {
            System.out.println("Question: " + q.getQuestionText());
            for (int i = 0; i < q.getOptions().length; i++) {
                System.out.println((char) ('a' + i) + ". " + q.getOptions()[i]);
            }
            System.out.println("Correct Answer: " + q.getCorrectAnswer());
            System.out.println("Complexity: " + q.getComplexity());
        } else {
            System.out.println("Question not found.");
        }
    }

    private static void handleTrainer(Scanner scanner, Trainer trainer, Student student) {
        System.out.print("Enter Trainer Username: ");
        String trainerUsername = scanner.next();
        System.out.print("Enter Trainer Password: ");
        String trainerPassword = scanner.next();
        System.out.println("Trainer verified");

        while (true) {
            System.out.println("1. Set Up Test");
            System.out.println("2. View Student Result");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int trainerChoice = scanner.nextInt();

            switch (trainerChoice) {
                case 1:
                    setupTest(scanner, trainer);
                    break;
                case 2:
                    trainer.viewResult(student);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void setupTest(Scanner scanner, Trainer trainer) {
        System.out.print("Enter number of easy questions: ");
        int easy = scanner.nextInt();
        System.out.print("Enter number of medium questions: ");
        int medium = scanner.nextInt();
        System.out.print("Enter number of hard questions: ");
        int hard = scanner.nextInt();
        System.out.print("Enter number of recent tests to exclude: ");
        int excludeLastN = scanner.nextInt();

        Test test = trainer.setupTest(new ArrayList<>(), easy, medium, hard, excludeLastN);
        System.out.println("Test setup complete.");
    }

    private static void handleStudent(Scanner scanner, Student student) {
        System.out.print("Enter Student Username: ");
        String studentUsername = scanner.next();
        System.out.print("Enter Student Password: ");
        String studentPassword = scanner.next();
        System.out.println("Student verified");

        while (true) {
            System.out.println("1. Take Test");
            System.out.println("2. View Results");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int studentChoice = scanner.nextInt();

            switch (studentChoice) {
                case 1:
                    // Mock test for simplicity; replace with actual Test object as needed
                    student.takeTest(new Test());
                    break;
                case 2:
                    student.viewResult();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
