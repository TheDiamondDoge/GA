package algorithms.DIPLOMA;

import algorithms.DIPLOMA.model.Teacher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Algorithm {

    private static final int POPULATION_SIZE = 10;
    public static final double ELITE_RATE = 0.1;
    public static final double SURVIVE_RATE = 0.5;
    public static final double MUTATION_RATE = 0.2;
    public static final double MAX_ITER = 1000;
    public static final int TARGET_SIZE = 5;

    private ArrayList<Teacher> GENES_POOL = Genes.generate();


    public void init(List<Genome> population) {
        int tsize = TARGET_SIZE;

        for (int i = 0; i < POPULATION_SIZE; i++) {
            ArrayList<Teacher> teachers = new ArrayList<>();
            for (int j = 0; j < tsize; j++) {
                teachers.add(GENES_POOL.get((int) (Math.random() * 14)));
            }

            population.add(new Genome(teachers));
        }
    }

    private Genome arrayModify(Genome g1, Genome g2, int spos) {
        ArrayList<Teacher> teachers = g1.getDay();

        for (int i = spos; i < teachers.size(); i++) {
            teachers.set(i, g2.getDay().get(i));
        }

        return new Genome(teachers);
    }

    public void selectElite(List<Genome> population, List<Genome> children, int esize) {
        for (int i = 0; i < esize; i++) {
            children.add(new Genome(population.get(i)));
        }
    }

    public Genome mutate(Genome genome) {
        int tsize = TARGET_SIZE;
        int ipos = (int) (Math.random() * tsize);
        Teacher delta = GENES_POOL.get((int) (Math.random() * 14));

        /////////////////////////////////////////////////////////////////
        ArrayList<Teacher> teachers = genome.getDay();
        teachers.set(ipos, delta);
        genome.calcFitness();

        return new Genome(teachers);
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

            Genome genome = arrayModify(population.get(i1), population.get(i2), spos);

            if (Math.random() < MUTATION_RATE) {
                genome = mutate(genome);
            }

            children.add(genome);
        }
        return children;
    }

    public void go() {
        List<Genome> population = new ArrayList<>();
        init(population);

        for (int i = 0; i < MAX_ITER; i++) {
            Collections.sort(population);
            System.out.print(i + " > ");

            for (Teacher t : population.get(0).getDay()){
                System.out.print(t.getName() + "-" + t.getLesson() + "; ");
            }

            System.out.println(population.get(0).getFitness());

            if (population.get(0).getFitness() == 0) {
                break;
            }

            population = mate(population);
        }

    }
}
