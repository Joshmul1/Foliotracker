package model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Folio extends Observable implements IFolio,Cloneable {
    private String name;
    private List<Stock> stocks;



    public Folio(String name) {
        this.name=name;
        stocks = new ArrayList<>();
    }

    public String getFolioName(){
        return name;
    }
    public boolean createStock(String ticker, String name, int shares, double pricePerShare){
        DecimalFormat df = new DecimalFormat("0.00");
        pricePerShare = Double.valueOf(df.format(pricePerShare));

        Stock stock = new Stock(ticker , name , shares , pricePerShare);

        for (Stock s : stocks){
            if (s.getTickerSymbol().equals(stock.getTickerSymbol())){
                s.addShares(shares);
                setChanged();
                notifyObservers();
                return true;
            }
        }


       if (stocks.add(stock)) {
           System.out.println("STOCK WAS ADDED");
                setChanged();
                notifyObservers();
                return true;
            }

        return false;
    }


    public List<Stock> getStocks(){

        return stocks;
    }

    public double getTotalHoldings(){
        double sum = 0;
        for (Stock s : stocks){
            assert (s.getNumberOfShares() >=0 && s.getPricePerShare()> 0) : "The number of shares cannot be negative"
             + " and the price should be positive. The number of shares is "+ s.getNumberOfShares() + "and the price is " + s.getPricePerShare();
            sum+= s.getValueOfHolding();
        }
        return sum;
    }

    public boolean hasTicker(String tickerSymbol){

        for (Stock s : stocks){
            if (s.getTickerSymbol().equals(tickerSymbol)){
                return true;
            }
        }

        return false;
    }
    public void setStocks(List<Stock> newStocks){
        stocks = newStocks;
    }
}
