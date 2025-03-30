package Rappel;

import Database.GestionEtudiant;
import Rappel.CurriculumForm;
import Rappel.GestionProfil;

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
    JMenuItem studentManagement_tp;
    GestionProfil gp;
    JDesktopPane desktop;
    CurriculumForm cv;

    GestionEtudiant studentManagement;

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
        studentManagement_tp = new JMenuItem("Gestion des etudiants");
        menu_TP_Swing.add(menuItem_TP1);
        menu_TP_Swing.add(menuItem_TP2);
        menu_TP_BD.add(studentManagement_tp);
        menubar.add(menu_TP_Swing);
        menubar.add(menu_TP_BD);
        setJMenuBar(menubar);
        desktop = new JDesktopPane();
        gp = new GestionProfil();
        cv = new CurriculumForm();
        studentManagement = new GestionEtudiant();

        // Action listener for menuItem_TP1
        menuItem_TP1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gp.isVisible() || studentManagement.isVisible()) {
                    gp.setVisible(false);
                    studentManagement.setVisible(false);
                }
                desktop.add(cv);
                cv.setVisible(true);
            }
        });

        // Action listener for menuItem_TP2
        menuItem_TP2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cv.isVisible() || studentManagement.isVisible()) {
                    cv.setVisible(false);
                    studentManagement.setVisible(false);
                }
                if (!gp.isVisible()) {
                    desktop.add(gp);
                    gp.setVisible(true);
                }
            }
        });

        // Action listener for menu_TP_BD
        studentManagement_tp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hello from student management");

                // Hide other windows if they are visible
                if (gp.isVisible() || cv.isVisible()) {
                    gp.setVisible(false);
                    cv.setVisible(false);
                }

                if (!studentManagement.isVisible()) {
                    desktop.add(studentManagement);
                    studentManagement.setVisible(true);
                }
            }
        });

        this.add(desktop);
    }

    public static void main(String[] args) {
        Bureau b = new Bureau();
        b.setVisible(true);
    }
}
