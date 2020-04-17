package view;

import listeners.createFolio;
import listeners.setFolio;
import listeners.setStock;
import model.Folio;
import model.FolioTracker;
import model.IFolioTracker;

import javax.swing.*;
import java.awt.*;

public class addFolio {
    private JTextField field;
    private JFrame frame;
    private FolioTracker folioTracker;
    private StockView stockView;

    public addFolio(FolioTracker folioTracker , StockView stockView){
        this.folioTracker = folioTracker;
        this.stockView = stockView;

        frame = new JFrame();
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Folio Name");
        field = new JTextField(10);
        JButton button = new JButton("OK");
        button.addActionListener(new setFolio(this, folioTracker, stockView));

        panel.setLayout(new GridLayout(1, 1));

        panel.add(label);
        panel.add(field);

        // JPanel buttonPanel = new JPanel();
        //buttonPanel.add(button);
        panel.add(button);
        frame.add(panel);
        frame.setVisible(true);

    }

    public JFrame getFrame(){

        return frame;
    }
    public String getFolioName(){
        return field.getText();
    }
}
