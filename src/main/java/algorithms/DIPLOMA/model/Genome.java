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
                fitness += Math.abs(getIntValueOfGrade(day.get(i).getGrade()) - getIntValueOfGrade(GradeDataObject.GRADE));
            }
        }
        fitness += weeklyLimitsInfluens();
    }

    private int getIntValueOfGrade(String grade){
        int result = 0;
        for (int i = 0; i < grade.length(); i++){
            result += Character.getNumericValue(grade.charAt(i));
        }
        return result;
    }

    private int weeklyLimitsInfluens(){
        int fitnessShift = 0;
        Map<String, Integer> limitsForGrade = LessonLimitsWeekly.getGradeWeeklyLimit(GradeDataObject.GRADE);
        Map<String, Integer> tempMapForCompare = new HashMap<>();

        for (Teacher teacher : day){
            String subjectName = teacher.getSubjectName();
            if (!tempMapForCompare.containsKey(subjectName)){
                tempMapForCompare.put(subjectName, 1);
            } else {
                int subjValue = tempMapForCompare.get(subjectName) + 1;
                tempMapForCompare.put(subjectName, subjValue);
            }
        }

        for (String subject : tempMapForCompare.keySet()){
            int weeklyLimit = limitsForGrade.get(subject);
            int actual = tempMapForCompare.get(subject);

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

    @Override
    public int compareTo(Genome o) {
        return fitness - o.fitness;
    }
}
