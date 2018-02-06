package algorithms.DIPLOMA;

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

        initialGenes.add(new Teacher("Russian", 1));
        initialGenes.add(new Teacher("Russian", 2));
        initialGenes.add(new Teacher("Russian", 3));
        initialGenes.add(new Teacher("Russian", 4));
        initialGenes.add(new Teacher("Russian", 5));
        initialGenes.add(new Teacher("Math", 2));
        initialGenes.add(new Teacher("Math", 4));
        initialGenes.add(new Teacher("Math", 5));
        initialGenes.add(new Teacher("English", 3));
        initialGenes.add(new Teacher("English", 1));
        initialGenes.add(new Teacher("Litra", 1));
        initialGenes.add(new Teacher("Litra", 2));
        initialGenes.add(new Teacher("Litra", 3));
        initialGenes.add(new Teacher("Phys", 2));

        return initialGenes;
    }
}
