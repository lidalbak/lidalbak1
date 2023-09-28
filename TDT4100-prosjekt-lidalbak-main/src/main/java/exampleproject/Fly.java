package exampleproject;


import java.util.ArrayList;
import java.util.Collection;

public class Fly implements InterfaceFly {
    int seter; 
    int avaibleSeats; 
    String dato; 
    String hvor;
    String id;  
    static Collection<String> hovedsteder; 
    static Collection<String> sasflyr; 

    
    public Fly(int seter, String dato, String hvor) {
        dato d=new dato(dato);// SJEKKER BARE DATO
        if (seter<0 || seter>200){
            throw new IllegalArgumentException("For få/mange seter"); 
        }
        this.seter = seter;
        this.avaibleSeats=seter; 
        this.dato = dato;
        hovedsteder=new ArrayList<>(); 
        hovedsteder.add("Oslo"); 
        hovedsteder.add("New York"); 
        hovedsteder.add("London"); 
        hovedsteder.add("Berlin"); 
        hovedsteder.add("Madrid"); 
        hovedsteder.add("Dubai"); 
        hovedsteder.add("Bejing"); 
        hovedsteder.add("Tokyo");
        hovedsteder.add("Washington");
        hovedsteder.add("New Mexico");
        hovedsteder.add("Madeira");
        hovedsteder.add("Toronto");
        hovedsteder.add("Milano");
        hovedsteder.add("Bali");
        hovedsteder.add("Phillipinene");
        hovedsteder.add("Maldivene");
        hovedsteder.add("Paris");
        hovedsteder.add("Barcelona");
        hovedsteder.add("Lisboa");
        hovedsteder.add("Riga");
        sasflyr=new ArrayList<>(); 
        sasflyr.add("New York");
        sasflyr.add("Dubai");
        sasflyr.add("Bejing");
        sasflyr.add("Tokyo");
        sasflyr.add("Washington");
        sasflyr.add("New Mexico");
        sasflyr.add("Madeira");
        sasflyr.add("Toronto");
        sasflyr.add("Milano");
        sasflyr.add("Bali");
        sasflyr.add("Phillipinene");
        sasflyr.add("Maldivene");
        
        
        
        if (!hovedsteder.contains(hvor)){
            throw new IllegalArgumentException("Ingen flyvninger går dit"); 
        }
        this.hvor=hvor; 
        this.id= dato+hvor; 


    }


    @Override
    public int getAvaibleSeats() {
        return avaibleSeats; 
    }

    @Override
    public void addOrder(int antallseter, Person person) {
        if (antallseter<=0){
            throw new IllegalArgumentException("Kan ikke bestille 0 eller - antall seter"); 
        }
        if (avaibleSeats<antallseter){
            throw new IllegalStateException("Ikke nok seter på flyet");  
        }
        avaibleSeats-=antallseter; 

        
        
    }


    @Override
    public String toString() {
        return " [seter=" + seter + ", avaibleSeats=" + avaibleSeats + "]";
    }
    
}
