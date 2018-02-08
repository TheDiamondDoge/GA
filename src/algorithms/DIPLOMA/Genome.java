package algorithms.DIPLOMA;

import java.util.ArrayList;

public class Genome implements Comparable<Genome>{

    private ArrayList<Teacher> day;
    private int fitness;


    public Genome() {
    }

    public Genome(Genome genome){
        this.copy(genome);
    }

    public Genome(ArrayList<Teacher> day) {
        this.setDay(day);
    }

    public ArrayList<Teacher> getDay() {
        return day;
    }

    public void addTeacherToDay(Teacher teacher){
        if(day == null){
            day = new ArrayList<>();
        }

        day.add(teacher);
    }

    public void setDay(ArrayList<Teacher> day) {
        this.day = day;
        this.calcFitness();
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    private void calcFitness(){
        fitness = 0;
        for(int i = 0; i < day.size(); i++){
            fitness += Math.abs(day.get(i).getLesson() - i - 1);
        }
    }

    public void copy(Genome genome){
        this.setDay(new ArrayList<>(genome.getDay()));
    }

    @Override
    public int compareTo(Genome o) {
        return fitness - o.fitness;
    }
}
