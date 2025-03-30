package Database;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class MyTableModel extends AbstractTableModel {
    ArrayList<Object[]> data=new ArrayList<Object[]>();
    EtudiantManager em;
    //int nbligne=0;
    ResultSetMetaData rsmd;
    MyTableModel(ResultSet rs,EtudiantManager em)  {
        this.em=em;
        try {
            rsmd= rs.getMetaData();
            while (rs.next()) {
                Object[] ligne=new Object[rsmd.getColumnCount()];
                for (int i=0;i<rsmd.getColumnCount();i++) {
                    ligne[i]=rs.getObject(i+1);

                } data.add(ligne);
                //nbligne++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    //nombre de ligne a afficher
    public int getRowCount() {
        return data.size();
    }

    @Override
    //nbre de colonne a a afficher
    public int getColumnCount() {
        try {
            return rsmd.getColumnCount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override

    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];    }

    @Override
    public String getColumnName(int column) {
        try {
            return rsmd.getColumnName(column+1);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (getColumnName(columnIndex).equalsIgnoreCase("moyenne")){
            return true;
        }
        else {
            return false;
        }   }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data.get(rowIndex)[columnIndex]=aValue;
    }
    //inserer les donnees ds la base si 1 ajouter les donnees en data
    public int ajouterEtudiant(int cin,String nom, String prenom,double moyenne) {
        int a=em.insertStudent(cin,nom,prenom,moyenne);
        if(a>0){
            data.add(new Object[]{cin,nom,prenom,moyenne});
            //refreche lel interface
            fireTableDataChanged();

        }
        return a;
    }
    public int supprimerEtudiant(int cin) {
        int a=0;
        return a;
    }

}
