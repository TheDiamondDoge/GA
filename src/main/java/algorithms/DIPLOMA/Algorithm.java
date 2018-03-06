package algorithms.DIPLOMA;

import algorithms.DIPLOMA.model.Genome;
import algorithms.DIPLOMA.util.Genes;
import algorithms.DIPLOMA.util.Population;

import java.util.Collections;
import java.util.List;

public class Algorithm {

    private static final double MAX_ITER = 1000;


    public void go(int dayOfTheWeek) {
        Population pop = new Population(Genes.getInitialPool());
        List<Genome> population = pop.init(dayOfTheWeek);

        for (int i = 0; i < MAX_ITER; i++) {
            Collections.sort(population);

            if (population.get(0).getFitness() == 0) {
                System.out.print(i + " > ");
                System.out.println(population.get(0));
                pop.deleteGenesOfFitPopulationFromPool(population.get(0));
                break;
            }

            population = pop.mate(population);
        }
    }
}
