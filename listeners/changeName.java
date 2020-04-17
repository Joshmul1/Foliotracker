package listeners;

import model.IStock;
import model.Stock;
import view.editStock;
import view.errorBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class changeName  implements ActionListener {

private editStock gui;
private IStock stock;


public changeName(editStock gui , IStock stock){
    this.gui = gui;
    this.stock = stock;
}


    @Override
    public void actionPerformed(ActionEvent e) {
        String input = gui.getName();

        if(stock.setStockName(input)){
            gui.getFrame().dispose();
        }
        else{
            new errorBox("Error : Name cannot be empty");
        }
    }
}
