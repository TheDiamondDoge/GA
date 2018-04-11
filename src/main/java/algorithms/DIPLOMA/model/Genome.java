package algorithms.DIPLOMA.model;

import algorithms.DIPLOMA.data.GradeDataObject;
import algorithms.DIPLOMA.data.LessonLimitsWeekly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Genome implements Comparable<Genome>{
    //TODO Compare with weekly limits doesn`t work
    //TODO Write tests!
    private Map<String, Integer> lessonOnDay;
    private ArrayList<Teacher> day;
    private int fitness;


    public Genome(List<Teacher> day) {
        this.setDay(new ArrayList<>(day));
        this.calcFitness();
    }

    public Genome(Genome genome){
        this.copy(genome);
        this.calcFitness();
    }

    private void copy(Genome genome){
        this.setDay(new ArrayList<>(genome.getDay()));
    }

    public void calcFitness(){
        fitness = 0;
        for(int i = 0; i < day.size(); i++){
            fitness += Math.abs(day.get(i).getLesson() - i - 1);

            if (!day.get(i).getGrade().equalsIgnoreCase(GradeDataObject.GRADE)) {
                fitness += Math.abs(day.get(i).getGrade().hashCode() - GradeDataObject.GRADE.hashCode());
            }
        }
        fitness += weeklyLimitsInfluence();
    }

    private int weeklyLimitsInfluence(){
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
        LessonLimitsWeekly.adjustmentFroGrade(lessonOnDay);
    }

    public Map<String, Integer> getLessonOnDay() {
        return lessonOnDay;
    }

    @Override
    public int compareTo(Genome o) {
        return fitness - o.fitness;
    }
}
