package at.hakimst;

import java.sql.*;

public class Jdbc_demo_teil2 {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("JDBC DEMO!");

        selectAllDemo();
    }


    public static void selectAllDemo () {

        System.out.println("Select DEMO mit JDBC");
        String connectionUrl = "jdbc:mysql://127.0.0.1:3306/jdbcdemo";

        String user = "root";
        String pwd = "";

        try (Connection conn = DriverManager.getConnection(connectionUrl, user, pwd)) {
            System.out.println("Verbindung zur DB hergestellt!");

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM `buch`");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("buch_id");
                String titel = rs.getString("buch_titel");
                String genre = rs.getString("buch_genre");
                int isbn = rs.getInt("buch_isbn");
                System.out.println("Buch aus der DB: ID => " + id + ", Titel => " + titel + ", Genre => " + genre + ", ISBN => " + isbn);
            }

        } catch (SQLException e) {
            System.out.println("Fehler beim Aufbau der Verbindung zur DB: " + e.getMessage());
        }
    }



        public static void insertBuchDemo (String neuerTitel, String neuesGenre, int neueIsbn) {


            System.out.println("Insert DEMO mit JDBC");
            String sqlSelectAllPerson = "SELECT * FROM `student`";
            String connectionUrl = "jdbc:mysql://127.0.0.1:3306/jdbcdemo";

            String user = "root";
            String pwd = "";

            try (Connection conn = DriverManager.getConnection(connectionUrl, user, pwd)) {

                PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO `buch` (`buch_id`, `buch_titel`, `buch_genre`, `buch_isbn`) VALUES (Null, ?, ?, ?)");

                try {
                    preparedStatement.setString(1, neuerTitel);
                    preparedStatement.setString(2, neuesGenre);
                    preparedStatement.setInt(3, neueIsbn);
                    int rowAffected = preparedStatement.executeUpdate();
                    System.out.println(rowAffected + " Datensätze eingefügt");
                } catch (SQLException ex) {
                    System.out.println("Fehler im SQL-Insert Statement: " + ex.getMessage());
                }

            } catch (SQLException e) {
                System.out.println("Fehler beim Aufbau der Verbindung zur DB: " + e.getMessage());
            }


        }



        public static void updateStudentDemo (int id, String neuerTitel, String neuesGenre, int neueIsbn){


            System.out.println("UPDATE DEMO mit JDBC");
            String sqlSelectAllPerson = "SELECT * FROM `student`";
            String connectionUrl = "jdbc:mysql://127.0.0.1:3306/jdbcdemo";

            String user = "root";
            String pwd = "";

            try (Connection conn = DriverManager.getConnection(connectionUrl, user, pwd)) {

                PreparedStatement preparedStatement = conn.prepareStatement("UPDATE `buch` SET `buch_titel` = ?, `buch_genre` =? ,`buch_isbn` =? WHERE `buch`.`buch_id`=?");

                try {
                    preparedStatement.setString(1, neuerTitel);
                    preparedStatement.setString(2, neuesGenre);
                    preparedStatement.setInt(3, neueIsbn);
                    int rowAffected = preparedStatement.executeUpdate();
                    System.out.println(rowAffected + " Datensätze aktuallisiert Anzahl");
                } catch (SQLException ex) {
                    System.out.println("Fehler im SQL-Update Statement: " + ex.getMessage());
                }

            } catch (SQLException e) {
                System.out.println("Fehler beim Aufbau der Verbindung zur DB: " + e.getMessage());
            }


        }

        public static void deleteStudentDemo (int buchId){


            System.out.println("DELETE DEMO mit JDBC");
            String sqlSelectAllPerson = "SELECT * FROM `student`";
            String connectionUrl = "jdbc:mysql://127.0.0.1:3306/jdbcdemo";

            String user = "root";
            String pwd = "";

            try (Connection conn = DriverManager.getConnection(connectionUrl, user, pwd)) {

                PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM `buch` WHERE `buch`.`buch_id`=?");

                try {
                    preparedStatement.setInt(1, buchId);
                    int rowAffected = preparedStatement.executeUpdate();
                    System.out.println(rowAffected + " Datensätze gelöscht");
                } catch (SQLException ex) {
                    System.out.println("Fehler im SQL-DELETE Statement: " + ex.getMessage());
                }

            } catch (SQLException e) {
                System.out.println("Fehler beim Aufbau der Verbindung zur DB: " + e.getMessage());
            }


        }


        public static void findAllByNameLike (String nameTeil){
            System.out.println("Find all by Name DEMO mit JDBC");
            String sqlSelectAllPerson = "SELECT * FROM `student`";
            String connectionUrl = "jdbc:mysql://127.0.0.1:3306/jdbcdemo";

            String user = "root";
            String pwd = "";

            try (Connection conn = DriverManager.getConnection(connectionUrl, user, pwd)) {
                System.out.println("Verbindung zur DB hergestellt!");

                PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM `buch` WHERE `buch`.`buch_titel` LIKE ?");
                preparedStatement.setString(1, "%" + nameTeil + "%");


                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {

                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");

                    System.out.println("Student aus der DB: ID => " + id + ", Name => " + name + ", Email => " + email);
                }

            } catch (SQLException e) {
                System.out.println("Fehler beim Aufbau der Verbindung zur DB: " + e.getMessage());
            }
        }


}

