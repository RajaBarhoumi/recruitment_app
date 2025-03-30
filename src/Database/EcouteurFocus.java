package Database;


import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class EcouteurFocus implements FocusListener {
    private GestionEtudiant gestionEtudiant;

    public EcouteurFocus(GestionEtudiant gestionEtudiant) {
        this.gestionEtudiant = gestionEtudiant;
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
        if (tf == gestionEtudiant.tf_first_name) {
            return "Entrer votre nom";
        } else if (tf == gestionEtudiant.tf_last_name) {
            return "Entrer votre prenom";
        } else if (tf == gestionEtudiant.tf_cin) {
            return "Entrer votre CIN";
        } else if (tf == gestionEtudiant.tf_moyenne) {
            return "Entrer votre moyenne";
        }
        return "";
    }
}
