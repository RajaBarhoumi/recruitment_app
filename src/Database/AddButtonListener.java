package Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddButtonListener implements ActionListener {
    private GestionEtudiant gestionEtudiant;
    private MyTableModel model;

    public AddButtonListener(GestionEtudiant gestionEtudiant, MyTableModel model) {
        this.gestionEtudiant = gestionEtudiant;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int cin = Integer.parseInt(gestionEtudiant.tf_cin.getText());
            String nom = gestionEtudiant.tf_first_name.getText();
            String prenom = gestionEtudiant.tf_last_name.getText();
            double moyenne = Double.parseDouble(gestionEtudiant.tf_moyenne.getText());
            model.addStudent(cin, nom, prenom, moyenne);

            gestionEtudiant.tf_first_name.setText("Entrer votre nom");
            gestionEtudiant.tf_last_name.setText("Entrer votre prenom");
            gestionEtudiant.tf_moyenne.setText("Entrer votre moyenne");
            gestionEtudiant.tf_cin.setText("Entrer votre CIN");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(gestionEtudiant, "Veuillez entrer des données valides.");
        }
    }
}
