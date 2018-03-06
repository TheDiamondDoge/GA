package algorithms.DIPLOMA.util;

import algorithms.DIPLOMA.model.Genome;
import algorithms.DIPLOMA.model.Teacher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Population {

    private static final int POPULATION_SIZE = 10;
    public static final double ELITE_RATE = 0.1;
    public static final double SURVIVE_RATE = 0.5;
    public static final double MUTATION_RATE = 0.2;
    public static final int TARGET_SIZE = 5;

    private ArrayList<Teacher> GENES_POOL;


    public Population(ArrayList<Teacher> GENES_POOL) {
        this.GENES_POOL = GENES_POOL;
    }

    public List init(int dayOfTheWeek) {
        List<Genome> population = new ArrayList<>();

        for (int i = 0; i < POPULATION_SIZE; i++) {
            ArrayList<Teacher> teachers = new ArrayList<>();

            for (int j = 0; j < TARGET_SIZE; j++) {
                Teacher teacher = randomTeacher();
                while (teacher.getDayOfTheWeek() != dayOfTheWeek) {
                    teacher = randomTeacher();
                }
                teachers.add(teacher);
            }

            population.add(new Genome(teachers));
        }

        return population;
    }

    private Teacher randomTeacher(){
        return GENES_POOL.get((int) (Math.random() * GENES_POOL.size() - 1));
    }

    private Genome arrayModify(Genome g1, Genome g2, int spos) {
        ArrayList<Teacher> teachers = g1.getDay();

        for (int i = spos; i < teachers.size(); i++) {
            teachers.set(i, g2.getDay().get(i));
        }

        return new Genome(teachers);
    }

    public List selectElite(List<Genome> population, int esize) {
        List<Genome> children = new ArrayList<>();

        for (int i = 0; i < esize; i++) {
            children.add(new Genome(population.get(i)));
        }

        return children;
    }

    public Genome mutate(Genome genome) {
        int ipos = (int) (Math.random() * TARGET_SIZE);
        Teacher delta = randomTeacher();

        ArrayList<Teacher> teachers = genome.getDay();
        teachers.set(ipos, delta);
        genome.calcFitness();

        return new Genome(teachers);
    }

    public List<Genome> mate(List<Genome> population) {
        int esize = (int) (POPULATION_SIZE * ELITE_RATE);
        List<Genome> children = selectElite(population, esize);

        for (int i = esize; i < POPULATION_SIZE; i++) {
            int i1 = (int) (Math.random() * POPULATION_SIZE * SURVIVE_RATE);
            int i2 = (int) (Math.random() * POPULATION_SIZE * SURVIVE_RATE);
            int spos = (int) (Math.random() * TARGET_SIZE);

            Genome genome = arrayModify(population.get(i1), population.get(i2), spos);

            if (Math.random() < MUTATION_RATE) {
                genome = mutate(genome);
            }

            children.add(genome);
        }
        return children;
    }

    public void deleteGenesOfFitPopulationFromPool(Genome genome){
        genome.getDay().forEach(x -> deleteTeacherFromGenesPool(x.getTeacherId()));
    }

    private void deleteTeacherFromGenesPool(int teacherId){
        GENES_POOL.removeIf(x -> x.getTeacherId() == teacherId);
    }
}
