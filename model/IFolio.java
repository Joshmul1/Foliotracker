package model;

import java.util.List;
import java.util.Observable;

public interface IFolio  {


    String getFolioName();

    double getTotalHoldings();

    List<Stock> getStocks();

    boolean createStock(String ticker, String name, int shares, double pricePerShare);


}
