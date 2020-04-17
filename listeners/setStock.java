package listeners;

import model.Folio;
import model.IFolio;
import model.IFolioTracker;
import view.StockView;
import view.setStockName;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class setStock implements ActionListener {
    private StockView view;
    private Folio folio;
    private String tickerSymbol;
    private int numberOfShares;
    private double value;
    private setStockName gui;

    public setStock(Folio folio, String tickerSymbol, int numberOfShares, double value , setStockName gui){
        this.view = view;
        this.folio = folio;
        this.tickerSymbol = tickerSymbol;
        this.numberOfShares = numberOfShares;
        this.value = value;
        this.gui = gui;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        folio.createStock(tickerSymbol,gui.getStockName(),numberOfShares, value);
        gui.getFrame().dispose();

    }
}
