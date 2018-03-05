package algorithms.DIPLOMA.model;


public class Teacher {

    private String name;
    private int lesson;
    private int dayOfTheWeek;


    public Teacher(String name, int lesson, int dayOfTheWeek) {
        this.name = name;
        this.lesson = lesson;
        this.dayOfTheWeek = dayOfTheWeek;
    }

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

    public int getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(int dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", lesson=" + lesson +
                '}';
    }
}
