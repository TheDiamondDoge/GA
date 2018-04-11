package algorithms.DIPLOMA.util;

import algorithms.DIPLOMA.data.GradeDataObject;
import algorithms.DIPLOMA.data.LessonLimitsDaily;
import algorithms.DIPLOMA.model.*;
import algorithms.DIPLOMA.util.creators.TeachersCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Population {
    private static final double ELITE_RATE = 0.1;
    private static final double SURVIVE_RATE = 0.5;
    private static final double MUTATION_RATE = 0.5;
    private int populationSize = 100;
    private List<Teacher> DAILY_GENES_POOL;
    private List<Teacher> GENES_POOL;
    private int[] availableTeachersPerLesson;
    private int TARGET_SIZE = 10;

    public Population() {
    }

    public void initPool(int dayOfTheWeek){
        GENES_POOL = TeachersCreator.getTeachersForDay(dayOfTheWeek);
    }

    private void setAmountAvailableTeachersPerLesson(){
        int dailyLessonLimit = LessonLimitsDaily.getLessonLimit(GradeDataObject.GRADE);
        availableTeachersPerLesson = new int[dailyLessonLimit + 1];
        for (Teacher teacher : DAILY_GENES_POOL){
            if (teacher.getLesson() <= dailyLessonLimit) {
                availableTeachersPerLesson[teacher.getLesson()]++;
            }
        }
    }

    public List<Genome> createInitialPopulation() {
        DAILY_GENES_POOL = GENES_POOL.stream()
                .filter((teacher -> teacher.getGrade().equals(GradeDataObject.GRADE)))
                .collect(Collectors.toList());

        setAmountAvailableTeachersPerLesson();
        TARGET_SIZE = calcTargetSize();
        return Stream.generate(() -> new Genome(getRandomListForGenome()))
                .parallel()
                .limit(populationSize)
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
        return DAILY_GENES_POOL.get((int) (Math.random() * DAILY_GENES_POOL.size() - 1));
    }

    //mate
    public List<Genome> mergeRandomGenomes(List<Genome> population) {
        int eliteSize = (int) (populationSize * ELITE_RATE);
        List<Genome> children = selectElite(population, eliteSize);

        for (int i = eliteSize; i < populationSize; i++) {
            int firstRandomGenome = (int) (Math.random() * populationSize * SURVIVE_RATE);
            int secondRandomGenome = (int) (Math.random() * populationSize * SURVIVE_RATE);
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
        for (int i = 0; i < eliteSize; i++) {
            children.add(new Genome(population.get(i)));
        }
        return children;
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
        int beforeSize = DAILY_GENES_POOL.size();
        DAILY_GENES_POOL.removeIf(x -> x.getName().equals(teacher.getName())
                              && x.getLesson() == teacher.getLesson()
                              && x.getDayOfTheWeek() == teacher.getDayOfTheWeek());

        availableTeachersPerLesson[lesson] =  availableTeachersPerLesson[lesson] - (beforeSize - DAILY_GENES_POOL.size());
    }

    public List<Teacher> getGenesPool() {
        return DAILY_GENES_POOL;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public int[] getAvailableTeachersPerLesson() {
        return availableTeachersPerLesson;
    }
}
