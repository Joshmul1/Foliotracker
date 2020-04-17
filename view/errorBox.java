package view;

import javax.swing.*;

public class errorBox {
    String message;

    public errorBox(String message){

        this.message = message;

        JFrame frame = new JFrame();
        JOptionPane pane = new JOptionPane();
        pane.showMessageDialog(frame, message);
        frame.add(pane);
    }
}
