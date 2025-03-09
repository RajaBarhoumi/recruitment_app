package Rappel;

import javax.swing.*;
import java.awt.*;

public class ProfileUpdateDialog {

    public ProfileUpdateDialog(GestionProfil gestionProfil, Profil profil, int selectedIndex) {
        JDialog dialog = new JDialog((Frame) null, "Modifier le Profil", true);
        dialog.setSize(300, 200);
        dialog.setLayout(new GridLayout(4, 2));

        JLabel lblFirstName = new JLabel("Nom: ");
        JTextField txtFirstName = new JTextField(profil.getFirst_name());

        JLabel lblLastName = new JLabel("Prénom: ");
        JTextField txtLastName = new JTextField(profil.getLast_name());

        JLabel lblNickname = new JLabel("Pseudo: ");
        JTextField txtNickname = new JTextField(profil.getNickname());
        txtNickname.setEditable(false); // Nickname should not be changed

        JButton btnUpdate = new JButton("Modifier");

        btnUpdate.addActionListener(e -> {
            profil.setFirst_name(txtFirstName.getText().trim());
            profil.setLast_name(txtLastName.getText().trim());

            // Update the JList display
            gestionProfil.model.set(selectedIndex, profil.getNickname());

            // Refresh the FormPanel in the JTabbedPane
            for (int i = 0; i < gestionProfil.jtp.getTabCount(); i++) {
                if (gestionProfil.jtp.getTitleAt(i).equals(profil.getNickname())) {
                    FormPanel updatedPanel = new FormPanel(profil);
                    gestionProfil.jtp.setComponentAt(i, updatedPanel);
                    break;
                }
            }

            JOptionPane.showMessageDialog(dialog, "Profil modifié avec succès !");
            dialog.dispose();
        });


        dialog.add(lblFirstName);
        dialog.add(txtFirstName);
        dialog.add(lblLastName);
        dialog.add(txtLastName);
        dialog.add(lblNickname);
        dialog.add(txtNickname);
        dialog.add(new JLabel()); // Empty space
        dialog.add(btnUpdate);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}

