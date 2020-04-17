package listeners;

import model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

public class refreshStock implements ActionListener {
    private FolioTracker folioTracker;
    private StrathQuoteServer sqs;
    public refreshStock(FolioTracker folioTracker){
         sqs = new StrathQuoteServer();

        this.folioTracker = folioTracker;


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        List<Folio> allFolios = folioTracker.getAllFolio();
        try {
            for (Folio f : allFolios) {
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println("WORKING ON FOLIO :   "+ f.getFolioName());
                List<Stock> stocks = f.getStocks();

                for (Stock s : stocks) {
                    double currentPPS = s.getPricePerShare();

                    String pps = StrathQuoteServer.getLastValue(s.getTickerSymbol());
                    pps = pps.replace(",", "");
                    //double newValue = NumberFormat.getInstance().parse(pps).intValue();
                    double newValue = Double.valueOf(pps);
                    System.out.println("STOCK HAS PRICE: " + currentPPS);
                    System.out.println("NEW PRICE IS   : " + newValue);

                    if (currentPPS != newValue) {
                        s.setPricePerShare(newValue);
                    }
                }
            }
        }catch (WebsiteDataException e1){

        } catch (NoSuchTickerException e2){

        }
    }
}
