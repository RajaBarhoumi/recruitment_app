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

    private int getColumnIndexByName(String columnName) {
        try {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                if (rsmd.getColumnName(i).equalsIgnoreCase(columnName)) {
                    return i - 1;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }


    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        int cinIndex = getColumnIndexByName("cin");
        if (getColumnName(columnIndex).equalsIgnoreCase("moyenne")) {
            try {
                int cin = (int) data.get(rowIndex)[cinIndex];
                double nouvelleMoyenne = Double.parseDouble(aValue.toString());

                int result = em.updateMoyenne(cin, nouvelleMoyenne);
                if (result > 0) {
                    data.get(rowIndex)[columnIndex] = nouvelleMoyenne;
                    fireTableCellUpdated(rowIndex, columnIndex);
                    System.out.println("Moyenne updated in UI and DB for CIN: " + cin);
                } else {
                    System.out.println("Failed to update moyenne for CIN: " + cin);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid moyenne input: " + aValue);
            }
        }
    }


    //inserer les donnees ds la base si 1 ajouter les donnees en data
    public int addStudent(int cin,String nom, String prenom,double moyenne) {
        int a=em.insertStudent(cin,nom,prenom,moyenne);
        if(a>0){
            data.add(new Object[]{cin,nom,prenom,moyenne});
            //refreche lel interface
            fireTableDataChanged();

        }
        return a;
    }
    public int deleteStudent(int cin) {
        int result = em.deleteStudent(cin);
        if (result > 0) {
            data.removeIf(row -> (int) row[0] == cin);
            fireTableDataChanged();
        }
        return result;
    }

}
