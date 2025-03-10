package Database;

import java.sql.*;

public class Test_DB {
    public static void main(String[] args) {

        EtudiantManager em = new EtudiantManager();
        int a = em.insertStudent(456, "Raja", "Barhoumii", 20);
        if(a>0){
            System.out.println("Insert successful");
        }else {
            System.out.println("Insert failed");
        }
        //em.fetchStudents("SELECT * FROM ETUDIANT");
        ResultSet rs = em.getStudents("SELECT * FROM Etudiant");
        em.fetchStudents(rs);
    }
}

//execute => bool =>alter
//executeUpdate => int =>update/insert/delete
//executeQuery => ResultSet =>select
//java.sql => first_index = 1
