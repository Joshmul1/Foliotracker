package model;

import java.text.DecimalFormat;
import java.util.Observable;

// Model knows nothing of Views or Controller
// Only knows of Observers - sends them update when anything changes
public class Stock extends Observable  implements IStock{


   private String tickerSymbol;
   private String stockName;
   private int numberOfShares;
   private double pricePerShare;

    public Stock(String tickerSymbol, String stockName, int numberOfShares, double pricePerShare) {

        this.tickerSymbol = tickerSymbol;
        this.stockName = stockName;
        this.numberOfShares = numberOfShares;
        this.pricePerShare = pricePerShare;
        
       


    }
    public String getTickerSymbol(){
        assert tickerSymbol!= null;
        return tickerSymbol;
    }

    public String getStockName(){
        assert stockName!= null;
        return stockName;
    }


    public boolean setStockName(String name){

        if(name.isEmpty()){
            return false;
        }
        else {
            stockName = name;
            setChanged();
            notifyObservers();
            return true;
        }
    }
    public int getNumberOfShares(){
        return numberOfShares;
    }

    public double getPricePerShare(){
        return pricePerShare;
    }

    public void setPricePerShare(double pricePerShare) {
        this.pricePerShare = pricePerShare;
        setChanged();
        notifyObservers();
    }

    public void setNumberOfShares(int numberOfShares){
        this.numberOfShares= numberOfShares;

    }

    public void addShares(int numberOfShares) {
    	if (numberOfShares < 1)
    		return;
    		else
        this.numberOfShares += numberOfShares;
    }

    public boolean removeShares(int numberOfShares) {
        if (this.numberOfShares - numberOfShares >=0){
            this.numberOfShares -= numberOfShares;
            return true;
        }
        return false;
    }

    public double getValueOfHolding() {
        DecimalFormat df = new DecimalFormat("0.00");
        return Double.valueOf(df.format(numberOfShares*pricePerShare));
    }




}