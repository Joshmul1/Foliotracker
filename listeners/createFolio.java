package listeners;

import model.Folio;
import model.FolioTracker;
import model.IFolio;
import model.IFolioTracker;
import view.StockView;
import view.addFolio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class createFolio  implements ActionListener {
private FolioTracker folioTracker;
private StockView stockView;


public createFolio(FolioTracker folioTracker, StockView stockView){


    this.stockView = stockView;
    this.folioTracker = folioTracker;






}


    @Override
    public void actionPerformed(ActionEvent e) {

    new addFolio(folioTracker , stockView);


    }
}
