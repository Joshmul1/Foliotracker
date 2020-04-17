package model;

public interface IStock {

    String getTickerSymbol();

    String getStockName();

    boolean setStockName(String stockName);


    int getNumberOfShares();


    void setNumberOfShares(int numberOfShares);


    void addShares(int numberOfShares);

    boolean removeShares(int numberOfShares);


    double getValueOfHolding();

    double getPricePerShare();

    void setPricePerShare(double pricePerShare);


}
