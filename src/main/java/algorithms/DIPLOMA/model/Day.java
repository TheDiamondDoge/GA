package algorithms.DIPLOMA.model;

import algorithms.DIPLOMA.data.GradeDataObject;
import algorithms.DIPLOMA.data.LessonLimitsWeekly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day implements Comparable<Day>{
    //TODO Compare with weekly limits doesn`t work
    //TODO Write tests!
    private Map<String, Integer> lessonOnDay;
    private ArrayList<Teacher> day;
    private int fitness;


    public Day(List<Teacher> day) {
        this.setDay(new ArrayList<>(day));
        this.calcFitness();
    }

    public Day(Day day){
        this.copy(day);
        this.calcFitness();
    }

    private void copy(Day day){
        this.setDay(new ArrayList<>(day.getDay()));
    }

    public void calcFitness(){
        fitness = 0;
        for(int i = 0; i < day.size(); i++){
            fitness += Math.abs(day.get(i).getLesson() - i - 1);

            if (!isCorrectGrade(i)) {
                fitness += Math.abs(day.get(i).getGrade().hashCode() - GradeDataObject.GRADE.hashCode());
            }
        }
        fitness += weeklyLimitsInfluence();
        fitness += duplicateLessons();
    }

    private boolean isCorrectGrade(int lessonNumber){
        return day.get(lessonNumber).getGrade().equalsIgnoreCase(GradeDataObject.GRADE);
    }

    public int weeklyLimitsInfluence(){
        int fitnessShift = 0;
        Map<String, Integer> limitsForGrade = LessonLimitsWeekly.getGradeWeeklyLimit(GradeDataObject.GRADE);
        lessonOnDay = new HashMap<>();

        for (Teacher teacher : day){
            String subjectName = teacher.getSubjectName();
            if (!lessonOnDay.containsKey(subjectName)){
                lessonOnDay.put(subjectName, 1);
            } else {
                int subjValue = lessonOnDay.get(subjectName) + 1;
                lessonOnDay.put(subjectName, subjValue);
            }
        }

        for (String subject : lessonOnDay.keySet()){
            int weeklyLimit = limitsForGrade.get(subject);
            int actual = lessonOnDay.get(subject);

            if (weeklyLimit < actual)
                fitnessShift += actual - weeklyLimit;
        }

        return fitnessShift;
    }

    private int duplicateLessons(){
        Map<String, Integer> tempMap = new HashMap<>();
        for (Teacher t : day){
            tempMap.put(t.getSubjectName(), 0);
        }

        if (tempMap.size() == day.size())
            return 0;
        else
            return day.size() - tempMap.size();
    }

    public ArrayList<Teacher> getDay() {
        return day;
    }

    private void setDay(ArrayList<Teacher> day) {
        this.day = day;
    }

    public int getFitness() {
        return fitness;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for(Teacher t : day){
            stringBuilder.append(t.toString()).append(" ");
        }

        return stringBuilder.toString() + " " + fitness;
    }

    public void weeklyLimitsAdjustment(){
        LessonLimitsWeekly.adjustmentFroGrade(lessonOnDay, getDay().get(0).getGrade());
    }

    @Override
    public int compareTo(Day o) {
        return fitness - o.fitness;
    }
}
