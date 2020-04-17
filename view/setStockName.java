package view;

import listeners.setStock;
import model.Folio;

import javax.swing.*;
import java.awt.*;

public class setStockName {
    private JTextField field;
    private JFrame frame;
    public setStockName(Folio folio, String tickerSymbol, int numberOfShares, double value){


        frame = new JFrame();
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Stock Name");
        field = new JTextField(10);
        JButton button = new JButton("OK");
        button.addActionListener(new setStock(folio,tickerSymbol,numberOfShares,value ,this));

        panel.setLayout(new GridLayout(1, 1));

        panel.add(label);
        panel.add(field);

        panel.add(button);
        frame.add(panel);
        frame.setVisible(true);

    }

    public JFrame getFrame(){

        return frame;
    }
    public String getStockName(){
        return field.getText();
    }
}
