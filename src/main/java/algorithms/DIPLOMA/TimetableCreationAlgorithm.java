package algorithms.DIPLOMA;

import algorithms.DIPLOMA.data.GradeDataObject;
import algorithms.DIPLOMA.model.Genome;
import algorithms.DIPLOMA.model.Teacher;
import algorithms.DIPLOMA.util.Population;
import algorithms.DIPLOMA.util.printers.DayPrinter;

import java.util.*;

public class TimetableCreationAlgorithm {

    private static final double MAX_ITER = 1000;
    private static int GRADES_CREATED = 0;

    private Population pop;

    public void initTeachersPool(int dayOfTheWeek){
        pop = new Population();
        pop.setPopulationSize(1000);
        pop.initPool(dayOfTheWeek);
    }

    public Genome start() {
        List<Genome> population = pop.createInitialPopulation();

        for (int i = 0; i < MAX_ITER; i++) {
            population.stream().forEach(Genome::calcFitness);
            Collections.sort(population);

            if (population.get(0).getFitness() == 0) {
                //TODO NEED TO DELETE LESSONS FROM WEEKLY LIMIT HERE
                System.out.print(GradeDataObject.GRADE + " > ");
                System.out.println(population.get(0));

                pop.deleteGenesFromPool(population.get(0));
                GRADES_CREATED++;
                return population.get(0);
            }

            population = pop.mergeRandomGenomes(population);
        }
        return null;
    }

    public static void setGradesCreated(int gradesCreated) {
        GRADES_CREATED = gradesCreated;
    }

    public static int getGradesCreated() {
        return GRADES_CREATED;
    }

    public int[] getArray(){
        return pop.getAvailableTeachersPerLesson();
    }
}
