package at.hakimst;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("JDBC DEMO!");
        //        INSERT INTO `student`(`id`, `name`, `email`)
        //        VALUES(NULL, 'Claudio Landerer', 'claudio.landerer@myimst.at'), (NULL, 'Elmira Parfjonova', 'e.parfjonova@tsn.at')        ;

        selectAllDemo();

        insertStudentDemo("Name", "email@gmail.com");
        selectAllDemo();

        updateStudentDemo(6,"Susi Müller", "susi.mueller@gmail.com");
        selectAllDemo();

        deleteStudentDemo(9);
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

    //alle Studenten auswählen (drittes JDBC Intro Video)
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

                //4. Video Beginn
                PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM `student`");
                ResultSet rs = preparedStatement.executeQuery();
                //Resultset zeigt die einzelnen durchläufe die einzelnen Datensatz
                while(rs.next()){
                    //wir holen uns die drei Variablen aus der Datenbank
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    //die Datensätze werden ausgegeben
                    System.out.println("Student aus der DB: ID => " + id + ", Name => " + name + ", Email => " + email);
                }
                //4. Video Ende

            }catch(SQLException e){
                System.out.println("Fehler beim Aufbau der Verbindung zur DB: " + e.getMessage());
            }
        }

        //Video 5. Daten einfügen
    public static void insertStudentDemo(String neuerName, String neueEmail){


        System.out.println("Insert DEMO mit JDBC");
        String sqlSelectAllPerson = "SELECT * FROM `student`";
        String connectionUrl = "jdbc:mysql://127.0.0.1:3306/jdbcdemo";

        //Username und Passwort um die Verbindung aufzubauen
        String user = "root";
        String pwd = "";

        //versucht die Verbindung aufzustellen
        //die Connection wird wegendem try catch block automatisch geschlossen deswegen muss man das nicht manuel machen
        try(Connection conn = DriverManager.getConnection(connectionUrl, user, pwd)){

            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO `student` (`id`, `name`, `email`) VALUES (Null, ?, ?)");

            //noch ein Try Catch Block um zu unterscheiden ob es beim absetzen vom SQL Code nicht schon ein Problem gegeben hat
            try{
                //SQL Injection (werden mit dieser Methide als Strings und nicht als SQL angeshen)
                preparedStatement.setString(1, neuerName);
                preparedStatement.setString(2, neueEmail);
                //liefert die Anzahl der betroffenen Datensätze zurück
                int rowAffected = preparedStatement.executeUpdate();
                System.out.println(rowAffected + " Datensätze eingefügt");
            }catch(SQLException ex){
                System.out.println("Fehler im SQL-Insert Statement: " + ex.getMessage());
            }

        }catch(SQLException e){
            System.out.println("Fehler beim Aufbau der Verbindung zur DB: " + e.getMessage());
        }


    }

    //Video 6. Daten Aktualisieren

    public static void updateStudentDemo(int ID, String neuerName, String neueEmail){


        System.out.println("UPDATE DEMO mit JDBC");
        String sqlSelectAllPerson = "SELECT * FROM `student`";
        String connectionUrl = "jdbc:mysql://127.0.0.1:3306/jdbcdemo";

        //Username und Passwort um die Verbindung aufzubauen
        String user = "root";
        String pwd = "";

        //versucht die Verbindung aufzustellen
        //die Connection wird wegendem try catch block automatisch geschlossen deswegen muss man das nicht manuel machen
        try(Connection conn = DriverManager.getConnection(connectionUrl, user, pwd)){

            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE `student` SET `name` = ?, `email` =? WHERE `student`.`id`=?");

            //noch ein Try Catch Block um zu unterscheiden ob es beim absetzen vom SQL Code nicht schon ein Problem gegeben hat
            try{
                //SQL Injection (werden mit dieser Methide als Strings und nicht als SQL angeshen)
                preparedStatement.setString(1, neuerName);
                preparedStatement.setString(2, neueEmail);
                preparedStatement.setInt(3, ID);
                //liefert die Anzahl der betroffenen Datensätze zurück
                int rowAffected = preparedStatement.executeUpdate();
                System.out.println(rowAffected + " Datensätze aktuallisiert Anzahl");
            }catch(SQLException ex){
                System.out.println("Fehler im SQL-Update Statement: " + ex.getMessage());
            }

        }catch(SQLException e){
            System.out.println("Fehler beim Aufbau der Verbindung zur DB: " + e.getMessage());
        }


    }

    //Video 7. Datensätze löschen

    public static void deleteStudentDemo(int studentId){


        System.out.println("DELETE DEMO mit JDBC");
        String sqlSelectAllPerson = "SELECT * FROM `student`";
        String connectionUrl = "jdbc:mysql://127.0.0.1:3306/jdbcdemo";

        //Username und Passwort um die Verbindung aufzubauen
        String user = "root";
        String pwd = "";

        //versucht die Verbindung aufzustellen
        //die Connection wird wegendem try catch block automatisch geschlossen deswegen muss man das nicht manuel machen
        try(Connection conn = DriverManager.getConnection(connectionUrl, user, pwd)){

            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM `student` WHERE `student`.`id`=?");

            //noch ein Try Catch Block um zu unterscheiden ob es beim absetzen vom SQL Code nicht schon ein Problem gegeben hat
            try{
                preparedStatement.setInt(1, studentId);
                //liefert die Anzahl der betroffenen Datensätze zurück
                int rowAffected = preparedStatement.executeUpdate();
                System.out.println(rowAffected + " Datensätze gelöscht");
            }catch(SQLException ex){
                System.out.println("Fehler im SQL-DELETE Statement: " + ex.getMessage());
            }

        }catch(SQLException e){
            System.out.println("Fehler beim Aufbau der Verbindung zur DB: " + e.getMessage());
        }


    }



}