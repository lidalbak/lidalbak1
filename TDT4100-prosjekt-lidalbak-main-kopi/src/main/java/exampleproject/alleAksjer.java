package exampleproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class alleAksjer {
    private List<aksje> aksjer; 

    public alleAksjer(){
        aksjer=new ArrayList<>(); 
    }

    public void readPE() throws FileNotFoundException{
        Scanner scanner=new Scanner(new File("PEstockInfo.txt")); 
        while (scanner.hasNextLine()){
            String line=scanner.nextLine(); 

            String[] strings=line.split(","); 


            String ticker=strings[0]; 
            String PE=strings[1]; 
            aksje tmp=new aksje(Double.parseDouble(PE),ticker); 
            aksjer.add(tmp); 
        }
        scanner.close();
    }
    public List<aksje> getAksjer() {
        return aksjer;
    }

    public static void main(String[] args) throws FileNotFoundException {
        alleAksjer a=new alleAksjer(); 
        a.readPE();
        Collections.sort(a.getAksjer(), new PEcomparator());
        System.out.println(a.getAksjer());
    }




}


