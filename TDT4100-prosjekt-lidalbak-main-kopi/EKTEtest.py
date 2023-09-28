import yfinance as yf
import pandas as pd 
import matplotlib.pyplot as p
import plotly.graph_objects as go

from datetime import datetime,timedelta

tickers=["NSKOG.OL"]
#print(yf.Ticker("NSKOG.OL").info)

endDate=datetime.today()
startDate=endDate-timedelta(days=1000)
closedDf=pd.DataFrame()
indicators={}
earningdates=pd.DataFrame()
for k,v in yf.Ticker("AAPL").info.items():
    print(k,v)

for ticker in tickers:
    data=yf.download(ticker,start=startDate,end=endDate)
    earningdates=yf.Ticker(ticker).earnings_dates
    volume=yf.Ticker(ticker).info["averageVolume"]
    marketcap=yf.Ticker(ticker).info["marketCap"]
    priceToBook=yf.Ticker(ticker).info["priceToBook"]
    indicators[ticker]=[marketcap,volume,priceToBook]

    
    
    #print(data)
    closedDf[ticker]=data["Close"]

ed=earningdates["EPS Estimate"].to_string()
Earningday=[]
for line in ed.splitlines():
    tmpLine=line[0:9]
    print(tmpLine)
    Earningday.append(tmpLine)
Earningday.pop(0)

print(indicators)
print(Earningday)
