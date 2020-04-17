import model.FolioTracker;
import view.StockView;


public class main {
    public static void main(String[] args) throws InterruptedException {
        FolioTracker folioTracker = new FolioTracker();
        StockView sv = new StockView(folioTracker);
        sv.createAndShowGUI();

    }
}
