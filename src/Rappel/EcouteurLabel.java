package Rappel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EcouteurLabel extends MouseAdapter {
    private Color originalColor;
    private JLabel label;
    private GestionProfil gestionProfil;

    public EcouteurLabel(GestionProfil gestionProfil) {
        this.gestionProfil = gestionProfil;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() instanceof JLabel) {
            label = (JLabel) e.getSource();
            originalColor = label.getForeground();
            label.setForeground(Color.BLUE);

            //JLabel field = (JLabel) e.getSource();
            if (label == gestionProfil.lb_first_name) {
                gestionProfil.lbl_help.setText(gestionProfil.lbl_help.getText() + "Veuillez entrer votre nom.");
            } else if (label == gestionProfil.lb_last_name) {
                gestionProfil.lbl_help.setText(gestionProfil.lbl_help.getText() + "Veuillez entrer votre prénom.");
            } else if (label == gestionProfil.lb_nickname) {
                gestionProfil.lbl_help.setText(gestionProfil.lbl_help.getText() + "Veuillez entrer votre pseudo.");
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (label != null) {
            label.setForeground(originalColor);
            gestionProfil.lbl_help.setText("Help");
        }
    }
}
