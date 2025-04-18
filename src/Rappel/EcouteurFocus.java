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
        if (tf.getText().equals(getPlaceholderText(tf))) {
            tf.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        JTextField tf = (JTextField) e.getSource();
        if (tf.getText().isEmpty()) {
            String placeholder = getPlaceholderText(tf);
            tf.setText(placeholder);
        }
    }

    private String getPlaceholderText(JTextField tf) {
        if (tf == gestionProfil.tf_first_name) {
            return "Entrer votre nom";
        } else if (tf == gestionProfil.tf_last_name) {
            return "Entrer votre prenom";
        } else if (tf == gestionProfil.tf_nickname) {
            return "Entrer votre pseudo";
        }
        return "";
    }
}
