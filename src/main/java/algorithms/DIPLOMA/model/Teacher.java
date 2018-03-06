package algorithms.DIPLOMA.model;


public class Teacher {
    //TODO in case of multithreading THIS id will be a problem cuz non-atomic
    private static int teachersAmount = 0;

    private String name;
    private int lesson;
    private int dayOfTheWeek;
    private int teacherId;


    public Teacher(String name, int lesson, int dayOfTheWeek) {
        teacherId = getCurrentTeachersAmount();
        this.name = name;
        this.lesson = lesson;
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public Teacher(String name, int lesson) {
        teacherId++;
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

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    private int getCurrentTeachersAmount(){
        increaseTeachersAmount();
        return teachersAmount;
    }

    private void increaseTeachersAmount(){
        teachersAmount += 1;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", lesson=" + lesson +
                '}';
    }
}
