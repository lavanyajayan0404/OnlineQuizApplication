package Application;
import java.util.*;
class Question {
    int questionNumber;
    String questionText;
    String[] options;
    char correctAnswer;
    String complexity;

    
    
    public Question(int questionNumber, String questionText, String[] options, char correctAnswer, String complexity) {
        this.questionNumber = questionNumber;
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
         this.complexity=complexity;  
    }
}
