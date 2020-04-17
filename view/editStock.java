package view;

import listeners.cancelChange;
import listeners.changeName;
import model.Folio;
import model.IFolio;
import model.IStock;

import javax.swing.*;
import java.awt.*;

public class editStock {
    IFolio folio;
    IStock stock;
    JTextField nameField;
    JFrame editStock;
    public editStock(IFolio folio , IStock stock){

        this.folio = folio;
        this.stock = stock;

        createGUI();


    }

    public void createGUI(){


        JLabel folioName = new JLabel(folio.getFolioName());
        JLabel tickerSymbol = new JLabel("Ticker Symbol : "+stock.getTickerSymbol());
        JLabel stockName = new JLabel("Stock Name : "+stock.getStockName());
        JLabel currentValue = new JLabel("Current Value : " + Double.toString(stock.getPricePerShare()));

        editStock = new JFrame();
        editStock.setVisible(true);
        editStock.setSize(400, 300);
        editStock.setMinimumSize(new Dimension(400,300));

        Container contentPane = editStock.getContentPane();

        JPanel mainPanel = new JPanel();
        JPanel label = new JPanel();
        JPanel stockDetails = new JPanel();
        JPanel editDetails = new JPanel();
        JPanel gainPanel = new JPanel();
        JPanel buttonPanel = new JPanel();


        JLabel stockNameLabel = new JLabel("Editing FolioTracker: " + folio.getFolioName());
        label.add(stockNameLabel);

        mainPanel.add(label);

        stockDetails.setLayout(new GridLayout(2,2,10,4));
        stockDetails.add(tickerSymbol);
        stockDetails.add(stockName);
        stockDetails.add(currentValue);


        mainPanel.add(stockDetails);

        editDetails.setLayout(new GridLayout(1,1,10,4));

        JLabel nameLabel = new JLabel("Change name of stock");
        editDetails.add(nameLabel);

        nameField = new JTextField();
        editDetails.add(nameField);



        mainPanel.add(editDetails);



        mainPanel.add(gainPanel);

        JButton save = new JButton("Save");
        buttonPanel.add(save);
        save.addActionListener(new changeName(this ,stock));
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(new cancelChange(this));
        buttonPanel.add(cancel);


        mainPanel.add(buttonPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));


        contentPane.add(mainPanel);

    }


    public String getName(){
        return nameField.getText();
    }

    public JFrame getFrame(){
        return editStock;
    }

}
