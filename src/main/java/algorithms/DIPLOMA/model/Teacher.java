package algorithms.DIPLOMA.model;


public class Teacher {
    private String name;
    private int lesson;
    private int dayOfTheWeek;
    private String grade;


    public Teacher(String name, int lesson, int dayOfTheWeek, String grade) {
        this.name = name;
        this.lesson = lesson;
        this.dayOfTheWeek = dayOfTheWeek;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getLesson() {
        return lesson;
    }

    public int getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return name + "-" + lesson;
    }
}
