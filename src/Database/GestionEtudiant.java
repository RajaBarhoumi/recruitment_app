package Database;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class GestionEtudiant extends JInternalFrame {
    JLabel lb_first_name;
    JLabel lb_last_name;
    JLabel lb_cin;
    JLabel lb_moyenne;
    JTextField tf_moyenne;
    JTextField tf_first_name;
    JTextField tf_last_name;
    JTextField tf_cin;
    JButton btn_add;
    JPanel panel;
    JTable jt;

    EtudiantManager manager;
    ResultSet rs;
    MyTableModel model;


    public GestionEtudiant()  {
        super("Gestion Etudiant", true, true, true, true);
        this.setLayout(new BorderLayout());

        this.setSize(700, 700);
        this.setResizable(true);
        //creation de l'interface graphique
        getContentPane().setBackground(new Color(230, 230, 250));

        lb_first_name = new JLabel("Nom : ");
        lb_moyenne = new JLabel("Moyenne : ");
        tf_moyenne = new JTextField(15);
        tf_moyenne.setText("Entrer votre moyenne");
        lb_last_name = new JLabel("Prenom : ");
        lb_cin = new JLabel("CIN : ");
        tf_first_name = new JTextField(10);
        tf_first_name.setText("Entrer votre nom");
        tf_last_name = new JTextField(10);
        tf_last_name.setText("Entrer votre prenom");
        tf_cin = new JTextField(10);
        tf_cin.setText("Entrer votre CIN");

        btn_add = new JButton("Ajouter");
        panel = new JPanel();
        panel.add(lb_first_name);
        panel.add(tf_first_name);
        panel.add(lb_last_name);
        panel.add(tf_last_name);
        panel.add(lb_cin);
        panel.add(tf_cin);
        panel.add(lb_moyenne);
        panel.add(tf_moyenne);
        panel.add(btn_add);
        this.add(panel, BorderLayout.NORTH);

        manager = new EtudiantManager();
        String req = "SELECT * FROM Etudiant";
        rs = manager.getStudents(req);

        model = new MyTableModel(rs,manager);

        jt = new JTable(model);
        //jt.setModel(model);
        this.add(new JScrollPane(jt), BorderLayout.CENTER);
        jt.setModel(model);

        tf_first_name.addFocusListener(new EcouteurFocus(this));
        tf_last_name.addFocusListener(new EcouteurFocus(this));
        tf_moyenne.addFocusListener(new EcouteurFocus(this));
        tf_cin.addFocusListener(new EcouteurFocus(this));

        btn_add.addActionListener(new AddButtonListener(this, model));

        jt.addMouseListener(new TableMouseListener(model, jt));


    }

    /*
    public static void main(String[] args) {
        GestionEtudiant ge = new GestionEtudiant();
        ge.setVisible(true);

    }
     */
}


