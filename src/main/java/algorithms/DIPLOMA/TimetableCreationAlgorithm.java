package algorithms.DIPLOMA;

import algorithms.DIPLOMA.model.Genome;
import algorithms.DIPLOMA.util.Population;
import algorithms.DIPLOMA.util.printers.DayPrinter;

import java.util.*;

public class TimetableCreationAlgorithm {

    private static final double MAX_ITER = 1000;

    private Population pop;
    private Map<String, ArrayList<Genome>> result;

    public void initTeachersPool(int dayOfTheWeek){
        pop = new Population();
        pop.initPool(dayOfTheWeek);
    }

    public void start() {
        if (result == null) {
            result = new HashMap<>();
        }

        List<Genome> population = pop.init();

        for (int i = 0; i < MAX_ITER; i++) {
            Collections.sort(population);

            if (population.get(0).getFitness() == 0) {
                addToMap(population.get(0));

                System.out.print(i + " > ");
                System.out.println(population.get(0));

                pop.deleteGenesFromPool(population.get(0));
                break;
            }

            population = pop.mergeRandomGenomes(population);
        }
    }

    private void addToMap(Genome genome){
        if (genome.getDay().size() == 0)
            return;

        DayPrinter dayPrinter = new DayPrinter();

        int dayOfTheWeekNumeric = genome.getDay().get(0).getDayOfTheWeek();
        String dayOfTheWeek = dayPrinter.dayOfTheWeekFromNumber(dayOfTheWeekNumeric);

        if (!result.containsKey(dayOfTheWeek)) {
            result.put(dayOfTheWeek, new ArrayList<>());
        }

        result.get(dayOfTheWeek).add(genome);
    }

    public Map<String, ArrayList<Genome>> getResult() {
        return result;
    }
}
