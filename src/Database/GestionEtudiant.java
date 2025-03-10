package Database;



import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class GestionEtudiant extends JFrame {
    JLabel lb_first_name;
    JLabel lb_last_name;
    JLabel lb_cin;
    JLabel lb_moyenne;
    TextField tf_moyenne;
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
        this.setLayout(new BorderLayout());
        this.setTitle("Gestion Etudiant");
        this.setSize(700, 700);
        this.setResizable(true);
        //creation de l'interface graphique
        getContentPane().setBackground(new Color(230, 230, 250));

        lb_first_name = new JLabel("Nom : ");
        lb_moyenne = new JLabel("Moyenne : ");
        tf_moyenne = new TextField(15);
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

        model = new MyTableModel();

        jt = new JTable(model);
        //jt.setModel(model);
        this.add(new JScrollPane(jt), BorderLayout.CENTER);


    }

    public static void main(String[] args) {
        GestionEtudiant gp = new GestionEtudiant();
        gp.setVisible(true);

    }
}


