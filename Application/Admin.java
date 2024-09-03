package Application;
import java.util.*;
import java.util.HashMap;
import java.util.Map;
class Admin extends User {
    Map<Integer, Question> questionBank;

    public Admin(String username, String password) {
        super(username, password);
        this.questionBank = new HashMap<>();
    }

    public void addQuestion(Question question) {
        questionBank.put(question.questionNumber, question);
    }

    public void deleteQuestion(int questionNumber) {
        questionBank.remove(questionNumber);
    }

    public Question viewQuestion(int questionNumber) {
        return questionBank.get(questionNumber);
    }
}



