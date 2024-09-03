package Application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student extends User {
    List<Test> tests;

    public Student(String username, String password) {
        super(username, password);
        this.tests = new ArrayList<>();
    }

    public void takeTest(Test test) {
        Scanner scanner = new Scanner(System.in);
        int marks = 0;

        for (Question q : test.questions) {
            System.out.println(q.questionText);
            for (int i = 0; i < q.options.length; i++) {
                System.out.println((char) ('a' + i) + ". " + q.options[i]);
            }
            char answer = scanner.next().charAt(0);
            if (answer == q.correctAnswer) {
                marks++;
            }
        }

        test.totalMarks = test.questions.size();
        test.obtainedMarks = marks;
        tests.add(test);
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void viewResult() {
        for (Test test : tests) {
            System.out.println("Test Results:");
            System.out.println("Total Marks: " + test.totalMarks);
            System.out.println("Obtained Marks: " + test.obtainedMarks);
        }
    }
}
