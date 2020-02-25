package sokoban;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Erik
 */
public class ActionEventListener implements ActionListener {
    private JTextField origin;
    private JTextArea destination;

    public ActionEventListener(JTextField origin, JTextArea destination) {
        this.origin = origin;
        this.destination = destination;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.destination.setText(this.origin.getText() + "\n" + this.destination.getText());
        this.origin.setText("");
    }
}
