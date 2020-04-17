package listeners;
import view.StockView;
import view.errorBox;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class closeTab implements ActionListener {


    private StockView view;
    public closeTab(StockView view){
        this.view = view;

    }

    public void actionPerformed(ActionEvent e) {
        JTabbedPane tabbedPane = view.getTabbedPane();
        if (tabbedPane.getSelectedIndex() < 0) {
           new errorBox("Error: There is no tab to close");


        } else {
            tabbedPane.removeTabAt(tabbedPane.getSelectedIndex());

        }
    }
}