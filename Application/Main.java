package Application;
import java.util.*;
public class Main {
    public static void main(String[] args) {
    	Scanner scanner=new Scanner(System.in);
        Admin admin = new Admin("admin", "admin1");
        Trainer trainer = new Trainer("trainer", "trainer1");
        Student student = new Student("student", "student1");

        //Adding questions to the question bank
        admin.addQuestion(new Question(1, "Capital of France?", new String[]{"Berlin", "Rome", "Paris", "Madrid"}, 'c', "easy"));
        admin.addQuestion(new Question(2, "Capital of Germany?", new String[]{"Berlin", "Rome", "Paris", "Madrid"}, 'a', "easy"));
        admin.addQuestion(new Question(3, "Capital of Italy?", new String[]{"Berlin", "Rome", "Paris", "Madrid"}, 'b', "easy"));
        
        admin.addQuestion(new Question(3, "Capital of France?", new String[]{"Berlin", "Rome", "Paris", "Madrid"}, 'c', "easy"));
        admin.addQuestion(new Question(4, "Capital of Germany?", new String[]{"Berlin", "Rome", "Paris", "Madrid"}, 'a', "easy"));
        admin.addQuestion(new Question(5, "Capital of Italy?", new String[]{"Berlin", "Rome", "Paris", "Madrid"}, 'b', "easy"));

        while (true) {
            System.out.println("1. Admin");
            System.out.println("2. Trainer");
            System.out.println("3. Student");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Admin Username: ");
                    String adminUsername = scanner.next();
                    System.out.print("Enter Admin Password: ");
                    String adminPassword = scanner.next();
                  System.out.println("Admin verified");
                        System.out.println("1.Add ");
                        System.out.println("2.Delete ");
                        System.out.println("3.View ");
                        int adminChoice=scanner.nextInt();
                        switch(adminChoice) {
                        case 1:
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
                             break;
                        case 2:
                            System.out.print("Enter question number to delete: ");
                            int delQNo = scanner.nextInt();
                            admin.deleteQuestion(delQNo); 
                            break;
                        case 3:
                            System.out.print("Enter question number to view: ");
                            int viewQNo = scanner.nextInt();
                            Question q = admin.viewQuestion(viewQNo);
                            if (q != null) {
                                System.out.println("Question: " + q.questionText);
                                for (int i = 0; i < q.options.length; i++) {
                                    System.out.println((char) ('a' + i) + ". " + q.options[i]);
                                }
                                System.out.println("Correct Answer: " + q.correctAnswer);
                                System.out.println("Complexity: " + q.complexity);
                            } else {
                                System.out.println("Question not found.");
                            }
                            break;
                      
                        }
                        
                        break;
                        
                case 2:
                	
                  System.out.print("Enter Trainer Username: ");
                   String trainerUsername = scanner.next();
                   System.out.print("Enter Trainer Password: ");
                   String trainerPassword = scanner.next();
                   System.out.println("Trainer verified"); {
                   System.out.println("1. Set up Test");
                   System.out.println("2. View Student Result");
                   System.out.print("Enter your choice: ");
                        int trainerChoice = scanner.nextInt();
                        switch (trainerChoice) {
                            case 1:
                                System.out.print("Enter number of easy questions: ");
                                int easy = scanner.nextInt();
                                System.out.print("Enter number of medium questions: ");
                                int medium = scanner.nextInt();
                                System.out.print("Enter number of hard questions: ");
                                int hard = scanner.nextInt();
                                
                                ;
                            case 2 :
                            	System.out.println("View the results");
                            	trainer.viewResult(student);
                            	break;
                        }
                   }
                        case 3:
                        {
                        	 System.out.print("Enter student Username: ");
                             String studentUsername = scanner.next();
                             System.out.print("Enter student Password: ");
                             String studentPassword = scanner.next();
                            System.out.println("student verified"); 
                            int studentChoice = scanner.nextInt();
                            switch (studentChoice) {
                                case 1:
                                {
                                	System.out.println("Take test");
                                	student.takeTest(null);
                                }
                                case 2:
                                {
                                	System.out.println("View results");
                                	student.viewResult();
                                }
                              
                              

                    }
                    }
            }
            
            	
            
            }
        }
}
    



                  
                        


