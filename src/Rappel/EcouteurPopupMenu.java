package Rappel;

import javax.swing.*;
import java.awt.event.*;

public class EcouteurPopupMenu implements ActionListener {

    GestionProfil gestionProfil;

    public EcouteurPopupMenu(GestionProfil gestionProfil) {
        this.gestionProfil = gestionProfil;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == gestionProfil.itemSupprimer) {
            int selectedIndex = gestionProfil.jl.getSelectedIndex();

                String selectedNickname = (String) gestionProfil.model.getElementAt(selectedIndex);

                Profil profileToRemove = Profil.getProfilByNickname(selectedNickname);
                if (profileToRemove != null) {
                    Data.profils.remove(profileToRemove);
                }

                gestionProfil.model.remove(selectedIndex);

                for (int i = 0; i < gestionProfil.jtp.getTabCount(); i++) {
                    if (gestionProfil.jtp.getTitleAt(i).equals(selectedNickname)) {
                        gestionProfil.jtp.remove(i);
                        break;
                    }
                }

                JOptionPane.showMessageDialog(gestionProfil, "Profil supprimé avec succès !");

        }

        if(e.getSource() == gestionProfil.itemSupprimerTous){
            Data.profils.clear();
            gestionProfil.tabList.clear();
            gestionProfil.jl.removeAll();
            gestionProfil.model.clear();
            gestionProfil.jtp.removeAll();
        }
        if (e.getSource() == gestionProfil.itemModifier) {
            int selectedIndex = gestionProfil.jl.getSelectedIndex();
            if (selectedIndex != -1) {
                String selectedNickname = (String) gestionProfil.model.getElementAt(selectedIndex);
                Profil selectedProfil = Profil.getProfilByNickname(selectedNickname);

                if (selectedProfil != null) {
                    new ProfileUpdateDialog(gestionProfil, selectedProfil, selectedIndex);

                    // Update the corresponding FormPanel (Tab)
                    for (int i = 0; i < gestionProfil.jtp.getTabCount(); i++) {
                        if (gestionProfil.jtp.getTitleAt(i).equals(selectedNickname)) {
                            FormPanel updatedPanel = new FormPanel(selectedProfil);
                            gestionProfil.jtp.setComponentAt(i, updatedPanel);
                            break;
                        }
                    }
                }
            }
        }

    }
}

//DOUBLE CLICK ON THE SAME NICKNAME => ACTIVATES HIS TAB
//CANNOT ADD 2 WITH THE SAME NICKNAME
//ADD UPDATE FUNCTION
