package listeners;

import model.Folio;
import model.FolioTracker;
import view.StockView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class deleteFolio implements ActionListener {
    private FolioTracker folioTracker;
    private StockView stockView;

    public deleteFolio(FolioTracker folioTracker , StockView stockView){
        this.folioTracker = folioTracker;
        this.stockView = stockView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JTabbedPane tabbedPane = stockView.getTabbedPane();
        String folioName =tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
        List<Folio> allFolios = folioTracker.getAllFolio();
        for (Folio f : allFolios){
            if (f.getFolioName().equals(folioName)){
                folioTracker.deleteFolio(f);
                return;
            }
        }

    }
}
