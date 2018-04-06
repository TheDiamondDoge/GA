package algorithms.DIPLOMA.model;

import algorithms.DIPLOMA.data.GradeDataObject;

import java.util.ArrayList;
import java.util.List;

public class Genome implements Comparable<Genome>{
    private ArrayList<Teacher> day;
    private int fitness;


    public Genome() {
    }

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
    }

    private int getIntValueOfGrade(String grade){
        int result = 0;
        for (int i = 0; i < grade.length(); i++){
            result += Character.getNumericValue(grade.charAt(i));
        }
        return result;
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
