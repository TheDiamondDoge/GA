package algorithms.DIPLOMA;

import algorithms.DIPLOMA.data.GradeDataObject;
import algorithms.DIPLOMA.model.Day;
import algorithms.DIPLOMA.util.Population;

import java.util.*;
import java.util.stream.Collectors;

public class DailyTimetableCreator {

    private static final int MAX_ITER = 2000;
    private static int GRADES_CREATED = 0;
    private Population pop;

    public void initTeachersPool(int dayOfTheWeek){
        pop = new Population();
        pop.setPopulationSize(1000);
        pop.initPool(dayOfTheWeek);
    }

    public Day start(int x) {
        List<Day> population = pop.createInitialPopulation(x);

        for (int i = 0; i < MAX_ITER; i++) {
            Collections.sort(population);

            if (population.get(0).getFitness() == 0) {

                List<Day> allChallengers = population.stream().filter(q -> q.getFitness() == 0).collect(Collectors.toList());
                int j = (int) (Math.random() * allChallengers.size());

                System.out.print(GradeDataObject.GRADE + " > ");
                System.out.println(allChallengers.get(j));

                pop.deleteGenesFromPool(allChallengers.get(j));
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
