package exampleproject;

import java.util.ArrayList;
import java.util.Collection;

public class dato {
    String date; 
    private Collection<String> validmnd;
    private Collection<String> valid31mnd; 
    private Collection<String> valid30mnd; 
    private Collection<String> valid28mnd; 
    public dato(String date){ 
        validmnd=new ArrayList<>(); 
        valid31mnd=new ArrayList<>(); 
        valid30mnd=new ArrayList<>(); 
        valid28mnd=new ArrayList<>(); 
        for (int i = 1; i < 13; i++) {
            if (i<10){
                validmnd.add("0"+i);
            }
            else{
                validmnd.add(""+i);
            }
        }
        for (int i = 1; i < 32; i++) {
            if (i>=10 && i<29){
                valid28mnd.add(""+i);
            }
            if(i<10){
                valid30mnd.add("0"+i);
                valid31mnd.add("0"+i); 
                valid28mnd.add("0"+i); 
            }
            else if (i==31){
                valid31mnd.add(""+i);

            }
            else{
                valid31mnd.add(""+i);
                valid30mnd.add(""+i);
            }
        }
        validatedate(date);
        this.date=date; 
    }


    public static void validatedate(String date){
        Collection<String> validmnd=new ArrayList<>(); 
        Collection<String> valid31mnd=new ArrayList<>(); 
        Collection<String> valid30mnd=new ArrayList<>(); 
        Collection<String> valid28mnd=new ArrayList<>(); 
        for (int i = 1; i < 13; i++) {
            if (i<10){
                validmnd.add("0"+i);
            }
            else{
                validmnd.add(""+i);
            }
        }
        for (int i = 1; i < 32; i++) {
            if (i>=10 && i<29){
                valid28mnd.add(""+i);
            }
            if(i<10){
                valid30mnd.add("0"+i);
                valid31mnd.add("0"+i); 
                valid28mnd.add("0"+i); 
            }
            else if (i==31){
                valid31mnd.add(""+i);

            }
            else{
                valid31mnd.add(""+i);
                valid30mnd.add(""+i);
            }
        }
        if (!(date.length()==10)){
            throw new IllegalArgumentException("Feil i inntasting av dato"); 
        }
        String[] tmp=date.split("\\.");
        boolean a=false; 
        int dag=Integer.parseInt(tmp[0]);
        int mnd=Integer.parseInt(tmp[1]);
        int yr=Integer.parseInt(tmp[2]); 
        
        for (String string : validmnd) {
            if (string.equals(tmp[1])){
                a=true; 
            }
        }
        if (!a){
            throw new IllegalArgumentException("Feil i mnd");
        }
        a=false; 
        if (tmp[1].equals("01") || tmp[1].equals("03")|| tmp[1].equals("05")|| tmp[1].equals("07")|| tmp[1].equals("08")|| tmp[1].equals("10")|| tmp[1].equals("12")){
            if (!valid31mnd.contains(tmp[0])){
                throw new IllegalArgumentException("Feil i dag1");

            }

        }
        if (tmp[1].equals("02")){
            if (!valid28mnd.contains(tmp[0])){
                throw new IllegalArgumentException("Feil februar");
            }


        }
       
        if (tmp[1].equals("04")|| tmp[1].equals("06")|| tmp[1].equals("09")|| tmp[1].equals("11")){
            if (!valid30mnd.contains(tmp[0])){
                throw new IllegalArgumentException("Feil i dag2");

            }
            
        }


        if (dag<=0 || dag>31){
            throw new IllegalArgumentException("Dag feil");
        }

        if(!(yr==2023 || yr==2024 || yr==2025 ||yr==2026 ||yr==2027)){
            throw new IllegalArgumentException("Feil i år "); 
        }
        

    }
    @Override
    public String toString() {
        return date; 
    }
}


