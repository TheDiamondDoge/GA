package algorithms.DIPLOMA.model;


public class Teacher {

    private String name;
    private int lesson;
    private int dayOfTheWeek;
    private int teacherId;
    //TODO in case of multithreading THIS id will be a problem cuz non-atomic
    private static int TEACHERS_AMOUNT = 0;


    public Teacher(String name, int lesson, int dayOfTheWeek) {
        teacherId = generateTeacherId();
        this.name = name;
        this.lesson = lesson;
        this.dayOfTheWeek = dayOfTheWeek;
    }

    private int generateTeacherId(){
        increaseTeachersAmount();
        return TEACHERS_AMOUNT;
    }

    private void increaseTeachersAmount(){
        TEACHERS_AMOUNT += 1;
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

    public int getTeacherId() {
        return teacherId;
    }

    @Override
    public String toString() {
        return name + "-" + lesson;
    }
}
