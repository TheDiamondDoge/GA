package algorithms.DIPLOMA.model;

import java.util.ArrayList;

public class Genome implements Comparable<Genome>{
    private ArrayList<Teacher> day;
    private int fitness;


    public Genome(ArrayList<Teacher> day) {
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
        }
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
        StringBuffer stringBuffer = new StringBuffer();

        for(Teacher t : day){
            stringBuffer.append(t.toString() + " ");
        }

        return stringBuffer.toString() + " " + fitness;
    }

    @Override
    public int compareTo(Genome o) {
        return fitness - o.fitness;
    }
}
