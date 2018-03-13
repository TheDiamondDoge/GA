package algorithms.DIPLOMA;

import algorithms.DIPLOMA.model.Genome;
import algorithms.DIPLOMA.util.Population;
import algorithms.DIPLOMA.util.read_write.XLSParser;

import java.util.Collections;
import java.util.List;

public class Algorithm {

    private static final double MAX_ITER = 1000;

    private Population pop;

    public void init(int dayOfTheWeek){
        pop = new Population();
        pop.initPool(dayOfTheWeek);
    }

    public void go() {
        List<Genome> population = pop.init();

        for (int i = 0; i < MAX_ITER; i++) {
            Collections.sort(population);

            if (population.get(0).getFitness() == 0) {
                System.out.print(i + " > ");
                System.out.println(population.get(0));
                pop.deleteGenesFromPool(population.get(0));
                break;
            }

            population = pop.mate(population);
        }
    }
}
