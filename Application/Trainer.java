package Application;

import Application.DbConnection;
import java.sql.*;
import java.util.*;

public class Trainer extends User {
    private static final String INSERT_TEST_QUERY = "INSERT INTO tests (trainer_id) VALUES (?)";
    private static final String INSERT_TEST_QUESTIONS_QUERY = "INSERT INTO test_questions (test_id, question_id) VALUES (?, ?)";
    private static final String SELECT_QUESTIONS_QUERY = "SELECT * FROM questions WHERE complexity = ?";

    private List<Test> tests;

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
        addQuestions(test, "easy", easy, recentQuestions);
        addQuestions(test, "medium", medium, recentQuestions);
        addQuestions(test, "hard", hard, recentQuestions);

        // Save the test to the database
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_TEST_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, this.getId()); // Assuming `getId()` returns the trainer's ID
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int testId = generatedKeys.getInt(1);
                    // Insert test questions
                    try (PreparedStatement insertQuestionsStmt = conn.prepareStatement(INSERT_TEST_QUESTIONS_QUERY)) {
                        for (Question q : test.questions) {
                            insertQuestionsStmt.setInt(1, testId);
                            insertQuestionsStmt.setInt(2, q.getId()); // Assuming `getId()` returns the question ID
                            insertQuestionsStmt.addBatch();
                        }
                        insertQuestionsStmt.executeBatch();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tests.add(test);
        return test;
    }

    private int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	private void addQuestions(Test test, String complexity, int count, List<Question> recentQuestions) {
        List<Question> filteredQuestions = new ArrayList<>();
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_QUESTIONS_QUERY)) {
            stmt.setString(1, complexity);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String questionText = rs.getString("question_text");
                    // Assume options are stored as a comma-separated string
                    String[] options = rs.getString("options").split(",");
                    char correctAnswer = rs.getString("correct_answer").charAt(0);
                    Question q = new Question(id, questionText, options, correctAnswer, complexity);
                    if (!recentQuestions.contains(q)) {
                        filteredQuestions.add(q);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Random random = new Random();
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
