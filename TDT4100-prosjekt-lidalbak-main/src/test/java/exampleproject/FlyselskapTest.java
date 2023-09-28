package exampleproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FlyselskapTest {

    
    
    @Test
    public void createFlyTest() {
        Fly fly=new Fly(100, "21.11.2023", "Oslo"); 
        assertEquals(100, fly.getAvaibleSeats());
        assertEquals("21.11.2023", fly.dato);
        assertEquals("Oslo", fly.hvor);
    }

    
    
    @Test
    public void bilettTest(){
        bilett bilett=new bilett(true, true, 5); 
        assertEquals(true, bilett.isFrokost());
        assertEquals(true, bilett.isBagasje());
        assertEquals(24000, bilett.getPris());
    }

    @Test
    public void personTest() {
        bilett bilett=new bilett(true, true, 5);
        Person person=new Person("Lars", bilett); 
        assertEquals("Lars", person.name);
        
    }

    @Test
    public void addFlyvningTest(){
        bilett bilett=new bilett(true, true, 5);
        Person person=new Person("Lars", bilett);
        oversiktOverDato flyselskap=new oversiktOverDato(); 
        flyselskap.addFlyvning("21.11.2023", "Oslo", person,1);
        Fly tmp=flyselskap.flyvninger.get("21.11.2023Oslo");
        assertEquals(99,tmp.getAvaibleSeats());
        assertEquals("21.11.2023",tmp.dato);
        assertEquals("Oslo",tmp.hvor);


    }

    @Test
    public void feilDestinasjonTest() {
        bilett bilett=new bilett(true, true, 5);
        Person person=new Person("Lars", bilett);
        oversiktOverDato flyselskap=new oversiktOverDato(); 
        assertThrows(IllegalArgumentException.class, ()->{
            flyselskap.addFlyvning("21.11.2023", "Israel", person,1);
        }); 
    }

    @Test 
    public void feilDatoTest(){
        bilett bilett=new bilett(true, true, 5);
        Person person=new Person("Lars", bilett);
        oversiktOverDato flyselskap=new oversiktOverDato(); 
        assertThrows(IllegalArgumentException.class, ()->{
            flyselskap.addFlyvning("32.11.2023", "Oslo", person,1);
        }); 


    }

    @Test
    public void bestilltForMangeSeterTest() {
        bilett bilett=new bilett(true, true, 5);
        Person person=new Person("Lars", bilett);
        oversiktOverDato flyselskap=new oversiktOverDato(); 
        assertThrows(IllegalStateException.class, ()->{
            flyselskap.addFlyvning("21.11.2023", "Oslo", person,10);
            flyselskap.addFlyvning("21.11.2023", "Oslo", person,91);
        }); 

        
    }

    @Test
    public void filLesningTest() throws FileNotFoundException{
        bilett bilett=new bilett(true, true, 5);
        Person person=new Person("Lars", bilett);
        oversiktOverDato flyselskap=new oversiktOverDato(); 
        flyselskap.addFlyvning("21.11.2023", "Oslo", person,10);
        Fillagring lagre=new Fillagring(flyselskap);
        lagre.writeStateToFile("stateTest.txt");
        Filskriving lastinn=new Filskriving(); 
        lastinn.readStateFromFile("stateTest.txt");
        oversiktOverDato o=new oversiktOverDato(); 
        o=lastinn.skrivestilfil; 
        Fly tmp=o.flyvninger.get("21.11.2023Oslo");
        assertEquals(90,tmp.getAvaibleSeats());
        assertEquals("21.11.2023",tmp.dato);
        assertEquals("Oslo",tmp.hvor);

    }

    @Test
    public void filLesningsTomFilTest() throws FileNotFoundException{
        Filskriving lastinn=new Filskriving();
        oversiktOverDato tmp=lastinn.skrivestilfil;
        assertEquals(0, tmp.flyvninger.size());
    }




}
