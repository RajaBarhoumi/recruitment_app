package Rappel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bureau extends JFrame {
    //Declaration des composantes
    JMenuBar menubar;
    JMenu menu_TP_Swing;
    JMenu menu_TP_BD;
    JMenuItem menuItem_TP1;
    JMenuItem menuItem_TP2;
    GestionProfil gp;
    JDesktopPane desktop;
    CurriculumForm cv;
    public Bureau() {
        setTitle("Java swing project");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //creation de l'interface graphique
        menubar = new JMenuBar();
        menu_TP_Swing = new JMenu("TP Swing");
        menu_TP_BD = new JMenu("TP BD");
        menuItem_TP1 = new JMenuItem("TP 1");
        menuItem_TP2 = new JMenuItem("TP 2");
        menu_TP_Swing.add(menuItem_TP1);
        menu_TP_Swing.add(menuItem_TP2);
        menubar.add(menu_TP_Swing);
        menubar.add(menu_TP_BD);
        setJMenuBar(menubar);
        desktop = new JDesktopPane();
        gp = new GestionProfil();
        cv = new CurriculumForm();
        menuItem_TP1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gp.isVisible()) {
                    gp.setVisible(false);
                }
                cv.setVisible(true);
                desktop.add(cv);
            }
        });

        menuItem_TP2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cv.isVisible()) {
                    cv.setVisible(false);
                }
                gp.setVisible(true);
                desktop.add(gp);
            }
        });

        this.add(desktop);
    }

    public static void main(String[] args) {
        Bureau b = new Bureau();
        b.setVisible(true);
    }
}
