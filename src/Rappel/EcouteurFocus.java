package Rappel;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

public class EcouteurFocus implements FocusListener {
    private GestionProfil gestionProfil;

    public EcouteurFocus(GestionProfil gestionProfil) {
        this.gestionProfil = gestionProfil;
    }

    @Override
    public void focusGained(FocusEvent e) {
        JTextField tf = (JTextField) e.getSource();
        tf.setText("");
    }

    @Override
    public void focusLost(FocusEvent e) {

    }
}
