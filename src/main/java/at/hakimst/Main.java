package at.hakimst;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("JDBC DEMO!");
        //        INSERT INTO `student`(`id`, `name`, `email`)
        //        VALUES(NULL, 'Claudio Landerer', 'claudio.landerer@myimst.at'), (NULL, 'Elmira Parfjonova', 'e.parfjonova@tsn.at')        ;

        selectAllDemo();


        //Code von Dienstag wo ich nicht da war
        /*try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("Treiber nicht gefunden");
        }*/
    }

    //alle Studenten auswählen
        public static void selectAllDemo(){
            System.out.println("Select DEMO mit JDBC");
            String sqlSelectAllPerson = "SELECT * FROM `student`";
            String connectionUrl = "jdbc:mysql://127.0.0.1:3306/jdbcdemo";

            //Username und Passwort um die Verbindung aufzubauen
            String user = "root";
            String pwd = "";

            //versucht die Verbindung aufzustellen
            try(Connection conn = DriverManager.getConnection(connectionUrl, user, pwd)){
                System.out.println("Verbindung zur DB hergestellt!");
            }catch(SQLException e){
                System.out.println("Fehler beim Aufbau der Verbindung zur DB: " + e.getMessage());
            }
        }

}