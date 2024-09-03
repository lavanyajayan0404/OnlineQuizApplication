package Application;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public List<Question> questions;
    public int totalMarks;
    public int obtainedMarks;

    public Test() {
        this.questions = new ArrayList<>();
        this.totalMarks = 0;
        this.obtainedMarks = 0;
    }
}
