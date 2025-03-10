package Database;

import java.sql.*;

import static Database.Config.*;

public class EtudiantManager {
    private Connection con = null;
    private Statement st = null;

    public EtudiantManager() {
        // Load Driver
        try {
            Class.forName(DriverName);
            System.out.println("Driver loaded successfully");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found: " + e.getMessage());
        }

        // Connect to database
        try {
            this.con = DriverManager.getConnection(databasUrl, USERNAME, PASSWORD);
            this.st = con.createStatement();
            System.out.println("Connection successful");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }

    public int insertStudent(int cin, String nom, String prenom, double moy) {
        int rowsAffected = 0;
        try {
        String query = "INSERT INTO etudiant VALUES (" + cin + ", '" + nom + "', '" + prenom + "', " + moy + ")";

            rowsAffected = st.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
        return rowsAffected;
    }

    public void fetchStudents(ResultSet rs) {
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            // Print column names
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(rsmd.getColumnName(i) + " ");
            }
            System.out.println();

            // Print rows
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(rs.getObject(i) + " ");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
    }

    public ResultSet getStudents(String query) {
        try {
            ResultSet rs = st.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            return rs;
        }catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
        return null;
    }
}
