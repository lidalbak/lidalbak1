package exampleproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javafx.scene.paint.Stop;

public class oversiktOverDato {
    HashMap<String,Fly> flyvninger=new HashMap<>(); 
    
    String a; 
    
    public oversiktOverDato(){

    }

    public void addFlyvning(String dato,String hvor, Person person,int antallseter){
        this.a="";
        dato sjekk=new dato(dato); //TESTER BARE OM DATOEN ER VALID
        Fly sjek1=new Fly(1, "21.11.2025", "Oslo");//Danner bare en liste over hvor flyvninger går til
        if (!Fly.hovedsteder.contains(hvor)){
            throw new IllegalArgumentException("Ingen flyvninger går dit");
        }
        String id=dato+hvor; 

        if (flyvninger.containsKey(id)){
            Fly tmp=flyvninger.get(id);
            tmp.addOrder(antallseter, person); 
            
        }
        else{
            if(Fly.sasflyr.contains(hvor)){
                Fly nytt=new SAS(200, dato, hvor);
                nytt.addOrder(antallseter, person);
                flyvninger.put(id, nytt);
                
            }
            else{
                Fly w=new Wideroe(100, dato, hvor); 
                w.addOrder(antallseter, person);
                flyvninger.put(id, w);
                
            
            }
             
        }

    }
    private void iterateUsingForEach(HashMap<String, Fly> map) {
        for (HashMap.Entry<String, Fly> entry : flyvninger.entrySet()) {
            String key = entry.getKey();
            String tmp=key.substring(0, 10); 
            String tmp2=key.substring(10); 
            Fly value = entry.getValue();
            this.a+= tmp+" "+tmp2+": "+value+"\n"; 
        }
    }
    
    @Override
    public String toString() {
        this.a="";
        iterateUsingForEach(flyvninger);
        return a; 
        
    }



  

}
