package listeners;

import model.*;
import view.StockView;
import view.errorBox;
import view.setStockName;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;

public class addStock implements ActionListener {

    private StockView stockView;
    private IFolioTracker portfolio;
    private Folio folio;
    private String tickerSymbol;
    private String numberOfSharesString;
    private int numberOfShares;
    private String stockName;


    public addStock(StockView stockView, IFolioTracker portfolio) {

        this.stockView = stockView;
        this.portfolio = portfolio;
        folio = null;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tickerSymbol = stockView.getTickerValue();
        numberOfSharesString = stockView.getNumberOfShares();

        if (numberOfSharesString.isEmpty()){
            new errorBox("Number of Shares has to be populated");
            return;
        }
        if (numberOfSharesString.charAt(0) == '-') {

            new errorBox("Number of Shares has to be greater than 0");

        } else {
            if(!(numberOfSharesString.matches("[0-9]+") ) ){

               new errorBox("Number of Shares has to be a number");

                return;

            }


            numberOfShares = Integer.parseInt(stockView.getNumberOfShares().replaceAll("[\\D]", ""));


            JTabbedPane tabbedPane = stockView.getTabbedPane();

            String currentFolio = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());

            List<Folio> allFolio = portfolio.getAllFolio();

            for (Folio f : allFolio) {
                if (f.getFolioName().equals(currentFolio)) {
                    folio = f;
                    break;
                }
            }
            if (folio == null) {
                return;
            }

            String pps = null;
            try {
                pps = StrathQuoteServer.getLastValue(tickerSymbol);
                pps = pps.replace(",", "");
                //double value = NumberFormat.getInstance().parse(pps).intValue();
                DecimalFormat df = new DecimalFormat("0.00");

                double value = Double.valueOf(pps);
                value = Double.valueOf(df.format(value));




                if (!folio.hasTicker(tickerSymbol)){

                   new setStockName(folio , tickerSymbol , numberOfShares , value);




                }else {

                    folio.createStock(tickerSymbol, stockName, numberOfShares, value);
                }
            } catch (WebsiteDataException e1) {
                new errorBox("The ticker symbol entered does not exist");
                stockView.resetValues();
            } catch (NoSuchTickerException e1) {

            }


        }
    }
}
