package exampleproject;

import java.util.Comparator;

public class PEcomparator implements Comparator<aksje>{

    @Override
    public int compare(aksje o1, aksje o2) {
        return (int) (o1.getPE()-o2.getPE()); 
    }
    



}
