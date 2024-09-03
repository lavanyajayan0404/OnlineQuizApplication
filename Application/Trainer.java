package Application;
import java.util.*;

class Trainer extends User {
    List<Test> tests;

    public Trainer(String username, String password) {
        super(username, password);
        this.tests = new ArrayList<>();
    }

    public Test setupTest(List<Question> questionBank, int easy, int medium, int hard, int excludeLastN) {
        Test test = new Test();
        List<Question> recentQuestions = new ArrayList<>();

        // Get recent questions to exclude
        int count = 0;
        for (int i = tests.size() - 1; i >= 0 && count < excludeLastN; i--, count++) {
            recentQuestions.addAll(tests.get(i).questions);
        }

        // Add questions based on criteria
        addQuestions(test, questionBank, recentQuestions, "easy", easy);
        addQuestions(test, questionBank, recentQuestions, "medium", medium);
        addQuestions(test, questionBank, recentQuestions, "hard", hard);

        tests.add(test);
        return test;
    }

    private void addQuestions(Test test, List<Question> questionBank, List<Question> recentQuestions, String complexity, int count) {
        Random random = new Random();
        List<Question> filteredQuestions = new ArrayList<>();
        for (Question q : questionBank) {
            if (q.complexity.equals(complexity) && !recentQuestions.contains(q)) {
                filteredQuestions.add(q);
            }
        }
        while (count > 0 && !filteredQuestions.isEmpty()) {
            Question q = filteredQuestions.remove(random.nextInt(filteredQuestions.size()));
            test.questions.add(q);
            count--;
        }
    }

    public void viewResult(Student student) {
        for (Test test : student.tests) {
            System.out.println("Test Results:");
            System.out.println("Total Marks: " + test.totalMarks);
            System.out.println("Obtained Marks: " + test.obtainedMarks);
        }
    }
}
