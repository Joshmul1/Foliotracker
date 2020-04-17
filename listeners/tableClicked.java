package listeners;

import model.*;
import view.StockView;
import view.editStock;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class tableClicked implements MouseListener {

   StockView view;
   IFolioTracker portfolio;
   IFolio clickedFolio;
   IStock clickedStock;

    public  tableClicked(StockView view, IFolioTracker portfolio){
        this.view = view;
        this.portfolio = portfolio;
        clickedFolio = null;
        clickedStock = null;
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        JTabbedPane tabbedPane = view.getTabbedPane();

        JSplitPane splitPane  = (JSplitPane) tabbedPane.getComponentAt(tabbedPane.getSelectedIndex());
        JScrollPane scrollPane = (JScrollPane) splitPane.getLeftComponent();
        JViewport viewport = scrollPane.getViewport();
        JTable table = (JTable) viewport.getComponent(0);
       // JViewport tablePaneComp =  scrollPaneComp.getViewport();
        //JTable table = (JTable) tablePaneComp.getComponent(0);
       // final int selectedRowIndex = table.rowAtPoint(mouseEvent.getPoint());

        int row = table.getSelectedRow();
        String folioName = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
        String tickerSymbol = table.getValueAt(row,0).toString();


        List<Folio> folioList = portfolio.getAllFolio();

        for(Folio f : folioList){
            if (f.getFolioName().equals(folioName)){
                clickedFolio = f;
                break;
            }

        }

        List<Stock> stocks = clickedFolio.getStocks();

        for (Stock s : stocks){
            if (s.getTickerSymbol().equals(tickerSymbol)){
                clickedStock = s;
                break;
            }
        }

        if (clickedStock!=null){

            new editStock(clickedFolio, clickedStock);
        }

//        DefaultTableModel model = (DefaultTableModel) table.getModel();






    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
