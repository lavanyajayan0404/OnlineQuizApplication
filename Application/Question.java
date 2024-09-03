package Application;

public class Question {
    private int id;
    private String questionText;
    private String[] options;
    private char correctAnswer;
    private String complexity;

    public Question(int id, String questionText, String[] options, char correctAnswer, String complexity) {
        this.id = id;
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.complexity = complexity;
    }

    // Getters and setters
    public int getId() { return id; }
    public String getQuestionText() { return questionText; }
    public String[] getOptions() { return options; }
    public char getCorrectAnswer() { return correctAnswer; }
    public String getComplexity() { return complexity; }
}
