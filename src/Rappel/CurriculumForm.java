/*
package Rappel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CurriculumForm extends JFrame {

    //declaration des composantes
    JButton btnValider, btnQuitter;
    JLabel l;

    TextField tfNom;






    public CurriculumForm()  {
//creation de l'IHM
        this.setLayout(new FlowLayout());
        btnValider = new JButton("Valider");
        this.add(btnValider);
        btnQuitter = new JButton("Quitter");
        this.add(btnQuitter);


        l = new JLabel("Hello world");

        l.setForeground(Color.RED);
        l.setFont(new Font("Serif", Font.BOLD, 20));
        l.setBackground(Color.GRAY);
        //couleur de l'arriere plan transparent => label transparente
        l.setOpaque(true);
        l.setPreferredSize(new Dimension(800, 200));
        l.setHorizontalAlignment(JLabel.CENTER);
        this.add(l);
        this.setTitle("Ma fenetre");
        this.setSize(800, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lbNom = new JLabel("Nom : ");
        lbNom.setForeground(Color.RED);
        lbNom.setFont(new Font("Serif", Font.ITALIC, 20));
        tfNom = new TextField(15);
        this.add(lbNom);
        this.add(tfNom);

        //event
        btnQuitter.addActionListener(new Ecouteur());
        btnValider.addActionListener(new Ecouteur());



    }

    class Ecouteur implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==btnValider){
                File f = new File("cv.html");
                try {

                    FileWriter fWriter = new FileWriter(f, false);
                    fWriter.write("<html><title>Mon cv</title> <body><h1>"+tfNom.getText()+"</h1></body></html>");
                    fWriter.close();
                    Desktop.getDesktop().open(f);
                } catch (IOException ex) {
                    System.out.println("Erreur d'ecriture dans le fichier");

                }



            }
            if(e.getSource()==btnQuitter){
                System.exit(0);
            }
        }
    }
}

//interface de creation de cv apres avoir saisir ses données et cliquer sur valider ca va generer une page html/pdf
*/

package Rappel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CurriculumForm extends JInternalFrame {
    // Components
    private JButton btnValider, btnQuitter;
    private JTextField tfNom, tfEmail, tfPhone, tfAddress, tfSkills, tfExperience, tfEducation;
    private JComboBox<String> cbSexe;
    private JSpinner spDateNaissance;

    public CurriculumForm() {
        setTitle("Création de CV");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setClosable(true);
        this.setIconifiable(true);
        this.setMaximizable(true);
        this.setResizable(true);
        this.setVisible(true);

        setLayout(new GridLayout(10, 2, 5, 5));
        getContentPane().setBackground(new Color(230, 230, 250)); // Light lavender background

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Color labelColor = new Color(50, 50, 150);

        // Labels and Fields
        add(createStyledLabel("Nom:", labelFont, labelColor));
        tfNom = new JTextField(15);
        add(tfNom);

        add(createStyledLabel("Sexe:", labelFont, labelColor));
        cbSexe = new JComboBox<>(new String[]{"Homme", "Femme"});
        add(cbSexe);

        add(createStyledLabel("Date de naissance:", labelFont, labelColor));
        spDateNaissance = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spDateNaissance, "dd/MM/yyyy");
        spDateNaissance.setEditor(dateEditor);
        add(spDateNaissance);

        add(createStyledLabel("Email:", labelFont, labelColor));
        tfEmail = new JTextField(15);
        add(tfEmail);

        add(createStyledLabel("Téléphone:", labelFont, labelColor));
        tfPhone = new JTextField(15);
        add(tfPhone);

        add(createStyledLabel("Adresse:", labelFont, labelColor));
        tfAddress = new JTextField(15);
        add(tfAddress);

        add(createStyledLabel("Compétences:", labelFont, labelColor));
        tfSkills = new JTextField(15);
        add(tfSkills);

        add(createStyledLabel("Expérience:", labelFont, labelColor));
        tfExperience = new JTextField(15);
        add(tfExperience);

        add(createStyledLabel("Éducation:", labelFont, labelColor));
        tfEducation = new JTextField(15);
        add(tfEducation);

        // Buttons
        btnValider = new JButton("Valider");
        btnQuitter = new JButton("Quitter");
        add(btnValider);
        add(btnQuitter);

        // Event Listeners
        btnQuitter.addActionListener(e -> System.exit(0));
        btnValider.addActionListener(new Ecouteur());
    }

    private JLabel createStyledLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        label.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0)); // Left padding
        return label;
    }

    class Ecouteur implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnValider) {
                File file = new File("cv.html");
                try (FileWriter writer = new FileWriter(file, false)) {
                    writer.write("<html><head><title>Mon CV</title></head><body>");
                    writer.write("<h1>" + tfNom.getText() + "</h1>");
                    writer.write("<p><strong>Sexe:</strong> " + cbSexe.getSelectedItem() + "</p>");
                    writer.write("<p><strong>Date de Naissance:</strong> " + spDateNaissance.getValue() + "</p>");
                    writer.write("<p><strong>Email:</strong> " + tfEmail.getText() + "</p>");
                    writer.write("<p><strong>Téléphone:</strong> " + tfPhone.getText() + "</p>");
                    writer.write("<p><strong>Adresse:</strong> " + tfAddress.getText() + "</p>");
                    writer.write("<p><strong>Compétences:</strong> " + tfSkills.getText() + "</p>");
                    writer.write("<p><strong>Expérience:</strong> " + tfExperience.getText() + "</p>");
                    writer.write("<p><strong>Éducation:</strong> " + tfEducation.getText() + "</p>");
                    writer.write("</body></html>");
                    Desktop.getDesktop().open(file);
                } catch (IOException ex) {
                    System.out.println("Erreur lors de l'écriture du fichier");
                }
            }
        }
    }


}


