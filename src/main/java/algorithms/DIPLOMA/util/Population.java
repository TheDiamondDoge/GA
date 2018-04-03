package algorithms.DIPLOMA.util;

import algorithms.DIPLOMA.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Population {
    private static final int POPULATION_SIZE = 100;
    private static final double ELITE_RATE = 0.1;
    private static final double SURVIVE_RATE = 0.5;
    private static final double MUTATION_RATE = 0.2;
    private List<Teacher> GENES_POOL;
    private int[] availableTeachersPerLesson = new int[10];
    private int TARGET_SIZE = 10;

    public Population() {
    }

    public void initPool(int dayOfTheWeek){
        GENES_POOL = TeachersCreator.getTeachersForDay(dayOfTheWeek);
        setAmountAvailableTeachersPerLesson();
    }

    private void setAmountAvailableTeachersPerLesson(){
        for (Teacher teacher : GENES_POOL){
            availableTeachersPerLesson[teacher.getLesson()]++;
        }
    }

    public List<Genome> createInitialPopulation() {
        TARGET_SIZE = calcTargetSize();
        return Stream.generate(() -> new Genome(getRandomListForGenome()))
                .parallel()
                .limit(POPULATION_SIZE)
                .collect(Collectors.toList());
    }

    private List<Teacher> getRandomListForGenome(){
        return Stream.generate(this::getRandomTeacher)
                .parallel()
                .limit(TARGET_SIZE)
                .collect(Collectors.toList());
    }

    private int calcTargetSize(){
        for (int lessonNum = 1; lessonNum < availableTeachersPerLesson.length; lessonNum++){
            if(isThereAnyAvailableTeacher(lessonNum)){
                return lessonNum - 1;
            }
        }
        return availableTeachersPerLesson.length - 1;
    }

    private boolean isThereAnyAvailableTeacher(int lessonNum){
        return availableTeachersPerLesson[lessonNum] == 0;
    }

    public Genome mutateGenome(Genome genome) {
        int extractionPosition = (int) (Math.random() * TARGET_SIZE);
        Teacher delta = getRandomTeacher();

        ArrayList<Teacher> teachers = genome.getDay();
        teachers.set(extractionPosition, delta);
        genome.calcFitness();

        return new Genome(teachers);
    }

    private Teacher getRandomTeacher(){
        return GENES_POOL.get((int) (Math.random() * GENES_POOL.size() - 1));
    }

    //mate
    public List<Genome> mergeRandomGenomes(List<Genome> population) {
        int eliteSize = (int) (POPULATION_SIZE * ELITE_RATE);
        List<Genome> children = selectElite(population, eliteSize);

        for (int i = eliteSize; i < POPULATION_SIZE; i++) {
            int firstRandomGenome = (int) (Math.random() * POPULATION_SIZE * SURVIVE_RATE);
            int secondRandomGenome = (int) (Math.random() * POPULATION_SIZE * SURVIVE_RATE);
            int pointOfMerge = (int) (Math.random() * TARGET_SIZE);

            Genome genome = mergeNewGenome(population.get(firstRandomGenome),
                    population.get(secondRandomGenome), pointOfMerge);

            if (Math.random() < MUTATION_RATE) {
                genome = mutateGenome(genome);
            }
            children.add(genome);
        }
        return children;
    }

    public List<Genome> selectElite(List<Genome> population, int eliteSize) {
        List<Genome> children = new ArrayList<>();

//        IntStream.range(0, eliteSize).map(i -> children.add(new Genome(population.get(i))));
        for (int i = 0; i < eliteSize; i++) {
            children.add(new Genome(population.get(i)));
        }

        return children;
//        return Stream.generate(() -> new Genome()).parallel().limit(eliteSize).collect(Collectors.toList());
    }

    private Genome mergeNewGenome(Genome g1, Genome g2, int pointOfMerge) {
        ArrayList<Teacher> teachers = g1.getDay();

        for (int i = pointOfMerge; i < teachers.size(); i++) {
            teachers.set(i, g2.getDay().get(i));
        }

        return new Genome(teachers);
    }

    public void deleteGenesFromPool(Genome genome){
        genome.getDay().forEach(x -> deleteTeacherFromGenesPool(x, x.getLesson()));
    }

    private void deleteTeacherFromGenesPool(Teacher teacher, int lesson){
        GENES_POOL.removeIf(x -> x.getName().equals(teacher.getName())
                              && x.getLesson() == teacher.getLesson()
                              && x.getDayOfTheWeek() == teacher.getDayOfTheWeek());

        availableTeachersPerLesson[lesson]--;
    }

    public List<Teacher> getGenesPool() {
        return GENES_POOL;
    }
}
