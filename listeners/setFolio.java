package listeners;

import model.Folio;
import model.FolioTracker;
import model.Stock;
import view.StockView;
import view.addFolio;
import view.errorBox;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class setFolio implements ActionListener {
    private FolioTracker folioTracker;
    private addFolio gui;
    private StockView stockView;
    private boolean isStillOpen;

    public setFolio(addFolio gui , FolioTracker folioTracker, StockView stockView){
        this.gui = gui;
        this.folioTracker = folioTracker;
        this.stockView = stockView;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        isStillOpen = false;
        int index=-1;
        if (!gui.getFolioName().isEmpty()) {

            Folio folio = new Folio(gui.getFolioName());


         if(!folioTracker.addFolio(folio)){
             new errorBox("Error : Folio with name " + folio.getFolioName()+ "already exists");

             JTabbedPane tabbedPane = stockView.getTabbedPane();

             for (int i=0 ;i <tabbedPane.getTabCount();i++){
                 if (tabbedPane.getTitleAt(i).equals(folio.getFolioName())){
                     isStillOpen = true;
                     break;
                 }
             }

             if (!isStillOpen){
                 List<Folio> allFolios = folioTracker.getAllFolio();
                 for (Folio f : allFolios){
                     if (f.getFolioName().equals(folio.getFolioName())){
                         index = allFolios.indexOf(f);
                         break;
                     }
                 }

                 List<Stock> stocks = allFolios.get(index).getStocks();
                 Folio newFolio =  new Folio(allFolios.get(index).getFolioName());
                 newFolio.setStocks(stocks);


                 folioTracker.removeFolio(index);
                 folioTracker.addFolio(newFolio);



             }


         }

         
        gui.getFrame().dispose();

        }
        else{
            new errorBox("Folio name cannot be empty");
        }


    }
}
