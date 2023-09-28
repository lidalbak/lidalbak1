import yfinance as yf
import pandas as pd
import matplotlib.pyplot as p
import plotly.graph_objects as go

from datetime import datetime, timedelta

tickers = ["NSKOG.OL"]
# print(yf.Ticker("NSKOG.OL").info)

endDate = datetime.today()
startDate = endDate-timedelta(days=1000)
closedDf = pd.DataFrame()
earningdates = pd.DataFrame()
for k, v in yf.Ticker("AAPL").info.items():
    print(k, v)

for ticker in tickers:
    data = yf.download(ticker, start=startDate, end=endDate)
    earningdates = yf.Ticker(ticker).earnings_dates
    volume = yf.Ticker(ticker).info["averageVolume"]
    marketcap = yf.Ticker(ticker).info["marketCap"]
    priceToBook = yf.Ticker(ticker).info["priceToBook"]

    # print(data)
    closedDf[ticker] = data["Close"]

# print(earningdates["EPS Estimate"])
ed = earningdates["EPS Estimate"]
print(ed)

# closedDf.to_csv("LiveStockInfo.csv")
# p.plot(data)
# p.show()
# print(data)
# print(closedDf)
#
fig = go.Figure(data=[go.Candlestick(open=data["Open"],
                high=data["High"], low=data["Low"], close=data["Close"])])
# fig2=go.Figure(data=[go.Line(open=data["Open"],close=data["Close"])])4
fig.update_layout(xaxis_rangeslider_visible=False)
fig.update_layout(template="plotly_dark")
fig.update_layout(xaxis_title="Date", yaxis_title="dollar")
fig.update_layout(title="Nasdaq")
# fig.show()
# fig2.show()
