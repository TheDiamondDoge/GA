package algorithms.DIPLOMA.model;


public class Teacher {
    //TODO Rewrite unit tests using MOCK
    //TODO Make writer for another sheet
    private String name;
    private int lesson;
    private int dayOfTheWeek;
    private String grade;
    private String subjectName;


    public Teacher(String name, String subjectName, int lesson, int dayOfTheWeek, String grade) {
        this.name = name;
        this.subjectName = subjectName;
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

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public String toString() {
        return name + "-" + grade + "-" + subjectName;
    }
}
