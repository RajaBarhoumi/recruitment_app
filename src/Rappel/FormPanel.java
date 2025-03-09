package Rappel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class FormPanel extends JPanel {

    public FormPanel() {}
    private JLabel label_nickname;
    private JButton button_valider;
    private JComboBox<String> levelDropdown;
    private JComboBox<String> degreeDropdown;
    private JRadioButton webRadio, mobileRadio, aiRadio, cloudRadio;
    private JLabel optionLabel;
    private JComboBox<String> frameworkDropdown;
    private JTextField otherField;
    private ButtonGroup domainGroup;

    public FormPanel(Profil p) {
        this.setLayout(new BorderLayout());

        // Welcome Label
        label_nickname = new JLabel("Bienvenue " + p.getFirst_name() + " " + p.getLast_name(), SwingConstants.CENTER);
        label_nickname.setOpaque(true);
        this.add(label_nickname, BorderLayout.NORTH);

        // Center Panel
        JPanel centerPanel = new JPanel();
        centerPanel.setBorder(BorderFactory.createTitledBorder("Postuler pour un Job"));
        centerPanel.setLayout(new GridLayout(6, 1));

        // Degree Dropdown
        degreeDropdown = new JComboBox<>(new String[]{"Licence", "Ingénierie", "Master"});
        centerPanel.add(new JLabel("Niveau d'étude :"));
        centerPanel.add(degreeDropdown);

        // Level Dropdown
        levelDropdown = new JComboBox<>(new String[]{"Débutant", "Intermédiaire", "Avancé"});
        centerPanel.add(new JLabel("Niveau :"));
        centerPanel.add(levelDropdown);


        // Radio Buttons for Domains
        JPanel radioPanel = new JPanel(new GridLayout(1, 4));
        webRadio = new JRadioButton("Web");
        mobileRadio = new JRadioButton("Mobile");
        aiRadio = new JRadioButton("AI");
        cloudRadio = new JRadioButton("Cloud");

        domainGroup = new ButtonGroup();
        domainGroup.add(webRadio);
        domainGroup.add(mobileRadio);
        domainGroup.add(aiRadio);
        domainGroup.add(cloudRadio);

        webRadio.addActionListener(e -> updateOptionLabel("Web"));
        mobileRadio.addActionListener(e -> updateOptionLabel("Mobile"));
        aiRadio.addActionListener(e -> updateOptionLabel("AI"));
        cloudRadio.addActionListener(e -> updateOptionLabel("Cloud"));

        radioPanel.add(webRadio);
        radioPanel.add(mobileRadio);
        radioPanel.add(aiRadio);
        radioPanel.add(cloudRadio);
        centerPanel.add(new JLabel("Sélectionnez votre domaine préféré:"));
        centerPanel.add(radioPanel);

        // Dynamic Option Label & Framework Dropdown
        optionLabel = new JLabel("Option", SwingConstants.CENTER);
        optionLabel.setOpaque(true);
        optionLabel.setBackground(Color.LIGHT_GRAY);
        frameworkDropdown = new JComboBox<>();
        frameworkDropdown.setVisible(false);

        centerPanel.add(optionLabel);
        centerPanel.add(frameworkDropdown);

        this.add(centerPanel, BorderLayout.CENTER);

        // Bottom Panel
        JPanel bottomPanel = new JPanel(new GridBagLayout());
        button_valider = new JButton("Valider");
        button_valider.addActionListener(e -> saveToFile(p));
        bottomPanel.add(button_valider);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void updateOptionLabel(String domain) {
        optionLabel.setText(domain);
        frameworkDropdown.removeAllItems();
        frameworkDropdown.setVisible(true);

        switch (domain) {
            case "Web":
                frameworkDropdown.addItem("React");
                frameworkDropdown.addItem("Angular");
                frameworkDropdown.addItem("Vue.js");
                break;
            case "Mobile":
                frameworkDropdown.addItem("Flutter");
                frameworkDropdown.addItem("React Native");
                frameworkDropdown.addItem("Swift");
                break;
            case "AI":
                frameworkDropdown.addItem("TensorFlow");
                frameworkDropdown.addItem("PyTorch");
                frameworkDropdown.addItem("Scikit-Learn");
                break;
            case "Cloud":
                frameworkDropdown.addItem("AWS");
                frameworkDropdown.addItem("Google Cloud");
                frameworkDropdown.addItem("Azure");
                break;
        }
    }

    private void saveToFile(Profil p) {
        File file = new File("cv.html");
        try (FileWriter writer = new FileWriter(file, false)) {
            writer.write("<html><head><title>Choix Utilisateur</title></head><body>");
            writer.write("<h1>Choix de " + p.getFirst_name() + " " + p.getLast_name() + "</h1>");
            writer.write("<p>Niveau: " + levelDropdown.getSelectedItem() + "</p>");
            writer.write("<p>Niveau d'étude: " + degreeDropdown.getSelectedItem() + "</p>");
            writer.write("<p>Domaine sélectionné: " + optionLabel.getText() + "</p>");
            writer.write("<p>Technologies :</p><ul>");

            if (frameworkDropdown.isVisible()) {
                for (int i = 0; i < frameworkDropdown.getItemCount(); i++) {
                    String framework = frameworkDropdown.getItemAt(i);
                    boolean isSelected = framework.equals(frameworkDropdown.getSelectedItem());
                    writer.write("<li>" + framework + " - " + (isSelected ? "true" : "false") + "</li>");
                }
            }

            writer.write("</ul>");
            writer.write("</body></html>");
            writer.flush();
            JOptionPane.showMessageDialog(this, "Choix enregistrés avec succès!");
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
