package listeners;

import model.IStock;
import view.StockView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Separate Controller (Listener) that know of both View and Model
public class StockController implements ActionListener {

    private IStock model;

    private StockView view;

    public StockController(IStock s, StockView v) {
        model = s;
        view = v;
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("close")) {
            JTabbedPane tabbedPane = view.getTabbedPane();
            if (tabbedPane.getSelectedIndex() < 0) {
                System.out.println("NO TAB TO CLOSE");

            } else {
                tabbedPane.removeTabAt(tabbedPane.getSelectedIndex());
             //   model.notifyObservers();
            }
        }
        else if (e.getActionCommand().equals("exit")){
            System.exit(0);

        }
    }

}