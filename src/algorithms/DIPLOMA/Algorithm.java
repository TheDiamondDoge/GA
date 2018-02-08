package algorithms.DIPLOMA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Algorithm {

    private static final int POPULATION_SIZE = 100000;
    public static final double ELITE_RATE = 0.1;
    public static final double SURVIVE_RATE = 0.5;
    public static final double MUTATION_RATE = 0.2;
    public static final double MAX_ITER = POPULATION_SIZE;
    public static final int TARGET_SIZE = 5;

    private ArrayList<Teacher> GENES_POOL = Genes.generate();


    public void init(List<Genome> population) {
        int tsize = TARGET_SIZE;


        for (int i = 0; i < POPULATION_SIZE; i++) {
            Genome genome = new Genome();
            for (int j = 0; j < tsize; j++) {
                genome.addTeacherToDay(GENES_POOL.get((int) (Math.random() * 14)));
            }

            genome.calcFitness();
            population.add(genome);
        }
    }

    private Genome arrayModify(Genome g1, Genome g2, int spos) {
        Genome child = g1;

        ArrayList<Teacher> teachers = child.getDay();

        for (int i = spos; i < g1.getDay().size(); i++) {
            teachers.set(i, g2.getDay().get(i));
        }

        child.setDay(teachers);

        return child;
    }

    public void selectElite(List<Genome> population, List<Genome> children, int esize) {
        for (int i = 0; i < esize; i++) {
            children.add(population.get(i));
        }
    }

    public Genome mutate(Genome genome) {
        int tsize = TARGET_SIZE;
        int ipos = (int) (Math.random() * tsize);
        Teacher delta = GENES_POOL.get((int) (Math.random() * 14));
        genome.getDay().set(ipos, delta);

        return genome;
    }


    public List<Genome> mate(List<Genome> population) {
        int esize = (int) (POPULATION_SIZE * ELITE_RATE);
        int tsize = TARGET_SIZE;
        List<Genome> children = new ArrayList<>();

        selectElite(population, children, esize);

        for (int i = esize; i < POPULATION_SIZE; i++) {
            int i1 = (int) (Math.random() * POPULATION_SIZE * SURVIVE_RATE);
            int i2 = (int) (Math.random() * POPULATION_SIZE * SURVIVE_RATE);
            int spos = (int) (Math.random() * tsize);

            /*String str = population.get(i1).str.substring(0, spos) +
                    population.get(i2).str.substring(spos);*/

            Genome genome = arrayModify(population.get(i1), population.get(i2), spos);

//            children.add(arrayModify(population.get(i1), population.get(i2), spos));

            if (Math.random() < MUTATION_RATE) {
                mutate(genome);
            }

            children.add(genome);


        }
        return children;
    }

    public void go() {
        List<Genome> population = new ArrayList<Genome>();
        init(population);

        for (int i = 0; i < MAX_ITER; i++) {
            Collections.sort(population);
            System.out.print(i + " > ");

            ArrayList<Teacher> teachers = population.get(i).getDay();

            for (int j = 0; j < teachers.size(); j++) {
                System.out.print(teachers.get(j).getName() + "-" + teachers.get(j).getLesson() + " => ");
            }

            System.out.println();

            if (population.get(0).getFitness() == 0) {
                break;
            }

            population = mate(population);
        }

    }
}
