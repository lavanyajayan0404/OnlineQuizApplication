package Application;

import Application.DbConnection;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Admin extends User {
    private Map<Integer, Question> questionBank;

    public Admin(String username, String password) {
        super(username, password);
        this.questionBank = new HashMap<>();
        loadQuestionsFromDatabase();
    }

    public void addQuestion(Question question) {
        questionBank.put(question.getId(), question);
        saveQuestionToDatabase(question);
    }

    public void deleteQuestion(int questionId) {
        questionBank.remove(questionId);
        deleteQuestionFromDatabase(questionId);
    }

    public Question viewQuestion(int questionId) {
        return questionBank.get(questionId);
    }

    private void saveQuestionToDatabase(Question question) {
        String query = "INSERT INTO questions (id, question_text, options, correct_answer, complexity) VALUES (?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE question_text = ?, options = ?, correct_answer = ?, complexity = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, question.getId());
            stmt.setString(2, question.getQuestionText());
            stmt.setString(3, String.join(",", question.getOptions()));
            stmt.setString(4, String.valueOf(question.getCorrectAnswer()));
            stmt.setString(5, question.getComplexity());
            stmt.setString(6, question.getQuestionText());
            stmt.setString(7, String.join(",", question.getOptions()));
            stmt.setString(8, String.valueOf(question.getCorrectAnswer()));
            stmt.setString(9, question.getComplexity());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteQuestionFromDatabase(int questionId) {
        String query = "DELETE FROM questions WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, questionId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadQuestionsFromDatabase() {
        String query = "SELECT * FROM questions";
        try (Connection conn = DbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String questionText = rs.getString("question_text");
                String[] options = rs.getString("options").split(",");
                char correctAnswer = rs.getString("correct_answer").charAt(0);
                String complexity = rs.getString("complexity");
                Question question = new Question(id, questionText, options, correctAnswer, complexity);
                questionBank.put(id, question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
