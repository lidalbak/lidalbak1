import pandas_datareader as web
import pandas as pd
from yahoo_fin import stock_info as si
import datetime as dt

tickers=si.tickers_sp500()


start=dt.datetime.now()-dt.timedelta(days=365)
end=dt.datetime.now()

sp500_df=web.DataReader('^GSPC','yahoo',start,end)
sp500_df['Pct Change']=sp500_df['Adj Close'].pct_change()

