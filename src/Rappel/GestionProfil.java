package Rappel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GestionProfil extends JInternalFrame {
    //Declaration des composantes
    JLabel lb_first_name;
    JLabel lb_last_name;
    JLabel lb_nickname;
    JTextField tf_first_name;
    JTextField tf_last_name;
    JTextField tf_nickname;
    JButton btn_save;
    JPanel panel_nord;
    JSplitPane jsp;
    JList jl;
    JTabbedPane jtp;
    DefaultListModel model;
    JLabel lbl_help;
    JPopupMenu popup;
    JMenuItem itemModifier;
    JMenuItem itemSupprimer;
    JMenuItem itemSupprimerTous;

    ArrayList<FormPanel> tabList = new ArrayList<>();

    public GestionProfil() {
        this.setTitle("Gestion Profil");
        this.setSize(700, 700);
        this.setClosable(true);
        this.setIconifiable(true);
        this.setMaximizable(true);
        this.setResizable(true);
        //creation de l'interface graphique
        getContentPane().setBackground(new Color(230, 230, 250));
        lb_first_name = new JLabel("Nom : ");
        lb_last_name = new JLabel("Prenom : ");
        lb_nickname = new JLabel("Pseudo : ");
        tf_first_name = new JTextField(10);
        tf_first_name.setText("Entrer votre nom");
        tf_last_name = new JTextField(10);
        tf_last_name.setText("Entrer votre prenom");
        tf_nickname = new JTextField(10);
        tf_nickname.setText("Entrer votre pseudo");
        btn_save = new JButton("Enregistrer");
        panel_nord = new JPanel();
        panel_nord.add(lb_first_name);
        panel_nord.add(tf_first_name);
        panel_nord.add(lb_last_name);
        panel_nord.add(tf_last_name);
        panel_nord.add(lb_nickname);
        panel_nord.add(tf_nickname);
        panel_nord.add(btn_save);


        this.add(panel_nord, BorderLayout.NORTH);
        jl = new JList();
        jl.setPreferredSize(new Dimension(500, 700));
        model = new DefaultListModel();
        jl.setModel(model);

        jsp = new JSplitPane();
        jtp = new JTabbedPane();
        //jtp.addTab("Tab 1", new JTabbedPane());
        //jtp.addTab("Tab 2", new JTabbedPane());
        jsp.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        jsp.setRightComponent(jtp);
        jsp.setLeftComponent(jl);
        //jsp.setPreferredSize(new Dimension(100, 100));
        this.add(jsp, BorderLayout.CENTER);
        lbl_help = new JLabel("Help : ");
        lbl_help.setForeground(Color.BLACK);
        this.add(lbl_help, BorderLayout.SOUTH);

        //Events
        btn_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = tf_first_name.getText().trim();
                String lastName = tf_last_name.getText().trim();
                String nickname = tf_nickname.getText().trim();

                // Vérifier si le champ nickname est vide
                if (nickname.isEmpty()) {
                    JOptionPane.showMessageDialog(GestionProfil.this, "Le pseudo ne peut pas être vide !", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (firstName.isEmpty()) {
                    JOptionPane.showMessageDialog(GestionProfil.this, "Le nom ne peut pas être vide !", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (lastName.isEmpty()) {
                    JOptionPane.showMessageDialog(GestionProfil.this, "Le prénom ne peut pas être vide !", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Vérifier si le pseudo existe déjà
                for (Profil p : Data.profils) {
                    if (p.getNickname().equalsIgnoreCase(nickname)) { // Ignore la casse (Maj/Min)
                        JOptionPane.showMessageDialog(GestionProfil.this, "Ce pseudo est déjà utilisé !", "Erreur", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                // Ajouter l'utilisateur s'il n'existe pas encore
                Profil newProfil = new Profil(firstName, lastName, nickname);
                Data.profils.add(newProfil);
                model.addElement(nickname);
                JOptionPane.showMessageDialog(GestionProfil.this, "Ajout avec succès !");
            }
        });


        tf_first_name.addFocusListener(new EcouteurFocus(this));
        tf_last_name.addFocusListener(new EcouteurFocus(this));
        tf_nickname.addFocusListener(new EcouteurFocus(this));

        lb_first_name.addMouseListener(new EcouteurLabel(this));
        lb_last_name.addMouseListener(new EcouteurLabel(this));
        lb_nickname.addMouseListener(new EcouteurLabel(this));

        popup = new JPopupMenu();
        itemModifier = new JMenuItem("Modifier");
        itemSupprimer = new JMenuItem("Supprimer");
        itemSupprimerTous = new JMenuItem("Supprimer tous");
        popup.add(itemModifier);
        popup.add(itemSupprimer);
        popup.add(itemSupprimerTous);

        //Action sur le JList
        jl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Double clic
                    String nickname = jl.getSelectedValue().toString();

                    // Vérifier si l'onglet existe déjà
                    for (int i = 0; i < jtp.getTabCount(); i++) {
                        if (jtp.getTitleAt(i).equals(nickname)) {
                            jtp.setSelectedIndex(i); // Activer l'onglet existant
                            return;
                        }
                    }

                    FormPanel pm = new FormPanel();
                    tabList.add(pm);
                    Profil p = Profil.getProfilByNickname(nickname);
                    pm.add(new FormPanel(p));
                    jtp.addTab(nickname, pm);
                    jtp.setSelectedIndex(jtp.getTabCount() - 1);
                }
                if(e.getButton() == MouseEvent.BUTTON3) {
                    //Right clic

                    popup.show(jl, e.getX(), e.getY());
                }
            }
        });

        itemModifier.addActionListener(new EcouteurPopupMenu(GestionProfil.this) );
        itemSupprimer.addActionListener(new EcouteurPopupMenu(GestionProfil.this));
        itemSupprimerTous.addActionListener(new EcouteurPopupMenu(GestionProfil.this));
        this.setVisible(true);

    }
}
