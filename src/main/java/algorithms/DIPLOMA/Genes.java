package algorithms.DIPLOMA;

import algorithms.DIPLOMA.model.Teacher;

import java.util.ArrayList;

public class Genes {
    private static ArrayList<Teacher> initialGenes = new ArrayList<>();


    public void addGen(Teacher teacher){
        initialGenes.add(teacher);
    }

    public static ArrayList<Teacher> getInitialGenes(){
        return initialGenes;
    }

    public static ArrayList<Teacher> generate(){

//        initialGenes.add(new Teacher("Russian", 1));
//        initialGenes.add(new Teacher("Russian", 2));
//        initialGenes.add(new Teacher("Russian", 3));
//        initialGenes.add(new Teacher("Russian", 4));
//        initialGenes.add(new Teacher("Russian", 5));
//        initialGenes.add(new Teacher("Math", 2));
//        initialGenes.add(new Teacher("Math", 4));
//        initialGenes.add(new Teacher("Math", 5));
//        initialGenes.add(new Teacher("English", 3));
//        initialGenes.add(new Teacher("English", 1));
//        initialGenes.add(new Teacher("Litra", 1));
//        initialGenes.add(new Teacher("Litra", 2));
//        initialGenes.add(new Teacher("Litra", 3));
//        initialGenes.add(new Teacher("Phys", 2));
//        initialGenes.add(new Teacher("Phys", 5));


        initialGenes.add(new Teacher("Russian", 1, 1));
        initialGenes.add(new Teacher("Russian", 2, 1));
        initialGenes.add(new Teacher("Russian", 3, 1));
        initialGenes.add(new Teacher("Russian", 4, 1));
        initialGenes.add(new Teacher("Russian", 1, 2));
        initialGenes.add(new Teacher("Russian", 2, 2));
        initialGenes.add(new Teacher("Russian", 3, 2));
        initialGenes.add(new Teacher("Russian", 1, 3));
        initialGenes.add(new Teacher("Russian", 4, 3));
        initialGenes.add(new Teacher("Russian", 5, 3));
        initialGenes.add(new Teacher("Russian", 1, 5));

        initialGenes.add(new Teacher("Math", 2, 1));
        initialGenes.add(new Teacher("Math", 4, 1));
        initialGenes.add(new Teacher("Math", 5, 1));
        initialGenes.add(new Teacher("Math", 2, 2));
        initialGenes.add(new Teacher("Math", 4, 2));
        initialGenes.add(new Teacher("Math", 5, 2));
        initialGenes.add(new Teacher("Math", 2, 3));
        initialGenes.add(new Teacher("Math", 4, 3));
        initialGenes.add(new Teacher("Math", 5, 3));
        initialGenes.add(new Teacher("Math", 2, 4));
        initialGenes.add(new Teacher("Math", 4, 4));
        initialGenes.add(new Teacher("Math", 5, 4));
        initialGenes.add(new Teacher("Math", 2, 5));
        initialGenes.add(new Teacher("Math", 4, 5));
        initialGenes.add(new Teacher("Math", 5, 5));

        initialGenes.add(new Teacher("Litra", 1, 1));
        initialGenes.add(new Teacher("Litra", 2, 1));
        initialGenes.add(new Teacher("Litra", 4, 1));
        initialGenes.add(new Teacher("Litra", 1, 3));
        initialGenes.add(new Teacher("Litra", 2, 3));
        initialGenes.add(new Teacher("Litra", 4, 3));
        initialGenes.add(new Teacher("Litra", 4, 5));
        initialGenes.add(new Teacher("Litra", 4, 5));
        initialGenes.add(new Teacher("Litra", 4, 5));

        initialGenes.add(new Teacher("Phys", 2,1));
        initialGenes.add(new Teacher("Phys", 4,1));
        initialGenes.add(new Teacher("Phys", 5,1));
        initialGenes.add(new Teacher("Phys", 2,2));
        initialGenes.add(new Teacher("Phys", 4,2));
        initialGenes.add(new Teacher("Phys", 5,2));
        initialGenes.add(new Teacher("Phys", 5,4));
        initialGenes.add(new Teacher("Phys", 5,4));
        initialGenes.add(new Teacher("Phys", 5,4));

        return initialGenes;
    }
}
