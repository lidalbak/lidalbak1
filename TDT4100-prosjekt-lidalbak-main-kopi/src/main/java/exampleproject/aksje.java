package exampleproject;

public class aksje {
    private double PE; 
    private String ticker;


    
    public aksje(double pE, String ticker) {
        PE = pE;
        this.ticker = ticker;
    }
    @Override
    public String toString() {
        return ticker+" PE: "+PE; 
    }
    public double getPE() {
        return PE;
    }
    public void setPE(float pE) {
        PE = pE;
    }
    public String getTicker() {
        return ticker;
    }
    public void setTicker(String ticker) {
        this.ticker = ticker;
    } 
    
    
}
