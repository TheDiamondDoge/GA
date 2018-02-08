package algorithms.DIPLOMA;

import java.util.ArrayList;

public class Genome implements Comparable<Genome>{

    private ArrayList<Teacher> day;
    private int fitness;


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
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public void calcFitness(){
        for(int i = 0; i < day.size(); i++){
            fitness += Math.abs(day.get(i).getLesson() - i - 1);
        }
    }

    @Override
    public int compareTo(Genome o) {
        return fitness - o.fitness;
    }
}
