package algorithms.DIPLOMA;

import java.util.ArrayList;

public class Teacher {

    private String name;
    private int lesson;


    public Teacher(String name, int lesson) {
        this.name = name;
        this.lesson = lesson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLesson() {
        return lesson;
    }

    public void setLesson(int lessons) {
        this.lesson = lessons;
    }
}
