package exampleproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystemLoopException;
import java.util.Scanner;

public class Filskriving {
    oversiktOverDato skrivestilfil=new oversiktOverDato();
    public Filskriving(){
        // this.skrivestilfil=ny; 

    }
    public void readStateFromFile(String path) throws FileNotFoundException{
        Scanner scanner=new Scanner(new File(path)); 
        oversiktOverDato ny=new oversiktOverDato(); 
        while (scanner.hasNextLine()){
            String line=scanner.nextLine(); 
            if (line.equals("")){
                throw new IllegalArgumentException(" Tom fil "); 
            }
            String[] infoOnLine=line.split(":");
            String dato=infoOnLine[0].substring(0, 10);
            String hvor=infoOnLine[0].substring(11); 
            String id= dato+hvor; 

            //lage ny klasse og legge til flyvninger
            String[] infoomfly=infoOnLine[1].split("\\[");
            String flytype=infoomfly[0]; 
            String[] aviableseats=infoomfly[1].substring(12).split("=");

            String ledigeseter=aviableseats[1].substring(0, aviableseats[1].length()-1);

            
            
            if (flytype.equals(" SAS ")){
                // vet at SAS har 200 passasjerer limit
                Fly nyfly=new SAS(200, dato, hvor); 
                nyfly.avaibleSeats=Integer.parseInt(ledigeseter); 
                ny.flyvninger.put(id, nyfly); 


            } 
            else{
                // vet at widerøe har limit på 100 passasjerer
                Fly nyfly=new Wideroe(100, dato, hvor); 
                nyfly.avaibleSeats=Integer.parseInt(ledigeseter); 
                ny.flyvninger.put(id, nyfly); 
            }
            


            


        }
        skrivestilfil.flyvninger=ny.flyvninger; 
        

    }

}
