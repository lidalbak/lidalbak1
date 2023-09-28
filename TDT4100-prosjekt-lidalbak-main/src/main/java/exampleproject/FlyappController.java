package exampleproject;

import java.io.FileNotFoundException;
import java.io.PrintStream;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;


public class FlyappController {
    oversiktOverDato en=new oversiktOverDato(); 
    @FXML TextField customer; 
    @FXML TextField datoen; 
    @FXML TextField antall; 
    @FXML TextField hvor; 
    @FXML Label viseralt; 
    @FXML TextField navn; 
    @FXML CheckBox frokost; 
    @FXML CheckBox Bagasje; 
    @FXML Button bestill; 
    @FXML Label tilbakemelding; 
    @FXML Label datofeil; 
    @FXML Label reisefeil; 
    @FXML Button lagre; 
    @FXML Button lastinn; 
    @FXML Label bestillerror; 
    @FXML Label antallerror; 

    @FXML 
    public void datoOnAction() {
        bekreft(); //kaller bekreft først for å sjekke at alt er valid 
        if(antall.getText()=="" || datoen.getText()=="" || hvor.getText()=="" || navn.getText()==""){
            bestillerror.setText("Alle felter må være fylt ut");
        }
        else{
            bestillerror.setText("");
            try {
                bilett bilett=new bilett(Bagasje.isSelected(), frokost.isSelected(), Integer.parseInt(antall.getText()));
                Person person=new Person(navn.getText(),bilett);
                try {
                    en.addFlyvning(datoen.getText(), hvor.getText(), person, Integer.parseInt(antall.getText()));
                    viseralt.setText(en.toString());
                    tilbakemelding.setText("Bestilling bekreftet! Navn: "+ person.toString());
                } catch (IllegalStateException e) {
                    antallerror.setText("Ikke nok seter på flyet");
                }
                catch(IllegalArgumentException er){
                    //disse typene exceptions er allerede håndtert
                }
            } catch (Exception e) {
                //disse er også håndtert
            }
            
           
        }
    }

    @FXML
    public void bekreft(){
        Fly f=new Fly(5, "21.11.2023", "London");
        try {
            Integer.parseInt(antall.getText());
            antallerror.setText("");
            if (Integer.parseInt(antall.getText())<=0){
                antallerror.setText("Tallet må være positivt");

            }
        } catch (Exception e) {
            antallerror.setText("Ikke et gyldig antall");
        }
        if(antall.getText()=="" || datoen.getText()=="" || hvor.getText()==""){
            bestillerror.setText("Alle felter må være fylt ut");
        }
        else{
            bestillerror.setText("");
        }
        if (!Fly.sasflyr.contains(hvor.getText())){
            frokost.setSelected(false);
            frokost.setDisable(true);

        }
        else{
            frokost.setDisable(false);
        }
        if (!Fly.hovedsteder.contains(hvor.getText())){
            reisefeil.setText("Ingen flyvninger går dit");
        }
        else{
            reisefeil.setText("");
        }
        try {
            dato.validatedate(datoen.getText());
            datofeil.setText("");
        } catch (Exception e) {
            datofeil.setText("Denne datoen eksisterer ikke");
        }
        
    }
    @FXML 
    public void lagre(){
        
        Fillagring tmp=new Fillagring(en);
        tmp.writeStateToFile("state.txt");

    }
    @FXML
    public void lastinn() throws FileNotFoundException{
        Filskriving tmp=new Filskriving(); 
        try {
            
            tmp.readStateFromFile("state.txt");
            en.flyvninger=tmp.skrivestilfil.flyvninger; 
    
            viseralt.setText(en.toString());
        } catch (Exception e) {
            viseralt.setText("Kan ikke laste inn fra tom fil");
        }

    }

    
    


   


    
}
