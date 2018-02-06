package algorithms.DIPLOMA;

import java.util.ArrayList;

public class Genome {

    private ArrayList<Teacher> day;
    private int fitness;


    public ArrayList<Teacher> getDay() {
        return day;
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
}
