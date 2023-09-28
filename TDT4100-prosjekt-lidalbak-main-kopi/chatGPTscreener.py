import pandas as pd
import yfinance as yf
from datetime import datetime,timedelta
import csv
from yahoo_fin.stock_info import get_data


endDate=datetime.today()
startDate=endDate-timedelta(days=3)
closedDF=pd.DataFrame()


def screen_stocks1():
    result=[]
    tickers=["EQNR.OL","NSKOG.OL","AAPL"]
    for ticker in tickers:
        data=yf.download(ticker,start=startDate,end=endDate)
        closedDF[ticker]=data["Close"]

     # Beregn P/E-forholdet for hvert selskap
    for ticker in tickers:
        
        price = closedDF[ticker].iloc[-1]
        earnings = yf.Ticker(ticker).info['trailingEps']
        pe_ratio = price / earnings
        #pe_ratio = yf.Ticker(ticker).info['forwardPE']
        
        result.append((ticker, pe_ratio))

    return result

def priceToBook():
    tickers=["EQNR.OL","NSKOG.OL","AAPL","TELNY","KOMPL.OL"]
    result=[]
    
    for ticker in tickers:
        
        PB=yf.Ticker(ticker).info["priceToBook"]
        result.append((ticker,PB))

    return result
        






screening_result = screen_stocks1()
print(screening_result)


PEDF=[]
with open('PEstockInfo.txt', 'w') as f:
    for ticker, pe_ratio in screening_result:
        f.writelines(ticker+","+str(pe_ratio)+"\n")
    
    f.close
with open('PBstockInfo.txt', 'w') as f:
    for ticker, PB in priceToBook():
        f.writelines(ticker+","+str(PB)+"\n")
    
    f.close

#print(yf.Ticker("AAPL").history(interval="1d",start=startDate,end=endDate))
#print(yf.Ticker("AAPL").info)
dic=yf.Ticker("AAPL").info
#for key,val in dic.items():
    #print(key," : ",val)





