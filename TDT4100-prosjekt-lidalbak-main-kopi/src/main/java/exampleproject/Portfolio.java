package exampleproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;


public class Portfolio {
    HashMap<String, Stock> tickerToStock; 
    List<Stock> portfolio;

    public Portfolio() {
        this.portfolio = new ArrayList<>(); 
        this.tickerToStock=new HashMap<String, Stock>(); 

    } 
    
    public void addStock(Stock stock) {
        if (!portfolio.contains(stock)){
            throw new IllegalArgumentException("Aksjen er ikke en del i portoføljen"); 
        }
        portfolio.add(stock); 
        tickerToStock.put(stock.getTicker(), stock);
        
    }

    public void removeStock(Stock stock) {
        if (!portfolio.contains(stock)){
            throw new IllegalArgumentException("Aksjen er ikke en del i portoføljen"); 
        }
        portfolio.remove(stock); 
        
    }
    public double calculatePortfolioValue(){
        double result=0; 
         for (Stock stock : portfolio) {
            result+=stock.getMarketValue(); 
         }
         return result; 
    }

    public double stockWeight(Stock stock) {
        return stock.getMarketValue()/calculatePortfolioValue(); 
    }

    public void buyStock(String ticker, double shares) {
        double prisPåAksje=ReadCSVfile.tickerPris.get(ticker);
        if (!portfolio.contains(ticker)){
            Stock tmp=new Stock(prisPåAksje,prisPåAksje, ticker, shares); //hente pris fra fil   
            portfolio.add(tmp);     
        }//contains stock/ticker
        else{
            Stock tmp=tickerToStock.get(ticker); 
            
        }

        
    }

    public void ProfitLoss(String ticker){
        if (!portfolio.contains(ticker)){
            throw new IllegalArgumentException("Du eier ikke denne aksjen");
        }//contains stock/ticker

        double prisPåAksje=ReadCSVfile.tickerPris.get(ticker);
        
    }

    private void kallPython() throws IOException, InterruptedException{
        
        String command = "python3 /Users/larsdalbakk/Desktop/TDT4100-prosjekt-lidalbak-main-kopi/readExcel.py";

        Process proc = Runtime.getRuntime().exec(command);

        // Read the output

        BufferedReader reader =  new BufferedReader(new InputStreamReader(proc.getInputStream()));

        String line = "";
        while((line = reader.readLine()) != null) {
            System.out.print(line + "\n");
        }

        proc.waitFor();   

    
        
        
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        Portfolio p=new Portfolio(); 
        p.kallPython();

    }
}
