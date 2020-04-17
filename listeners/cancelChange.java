package listeners;

import view.editStock;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class cancelChange implements ActionListener {
    private editStock gui;


    public cancelChange(editStock gui){
        this.gui = gui;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        gui.getFrame().dispose();
    }
}
