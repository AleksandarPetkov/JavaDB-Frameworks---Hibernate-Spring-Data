package L01_DBAppsIntroduction.exercises.p01_InitialSetup;

import java.sql.*;
import java.util.Properties;

public class InitialSetup {
    static final String URL = "jdbc:mysql://localhost:3306/";
    static final String DATABASE = "minions_db";
    static final String USER = "root";
    static final String PASS = "1234";

    public static void main(String[] args) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", USER);
        props.setProperty("password", PASS);

        Connection conn = DriverManager.getConnection(URL, props);
        Statement statement = conn.createStatement();

        //Create database
        String query = "DROP DATABASE IF EXISTS minions_db";
        statement.executeUpdate(query);

        query = "CREATE DATABASE minions_db";
        statement.executeUpdate(query);

        conn = DriverManager.getConnection(URL + DATABASE, USER, PASS);
        statement = conn.createStatement();

        //Create tables
        query = "CREATE TABLE towns(\n" +
                "id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                "name VARCHAR(255) NOT NULL,\n" +
                "country VARCHAR(100) NOT NULL\n" +
                ")";
        statement.executeUpdate(query);

        query = "CREATE TABLE minions (\n" +
                "id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                "name VARCHAR(255) NOT NULL,\n" +
                "age INT,\n" +
                "town_id INT,\n" +
                "CONSTRAINT fk_minion_towns\n" +
                "FOREIGN KEY (town_id)\n" +
                "REFERENCES towns(id)\n" +
                ")";
        statement.executeUpdate(query);

        query = "CREATE TABLE villains (\n" +
                "id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                "name VARCHAR(255) NOT NULL,\n" +
                "evilness_factor ENUM('good', 'bad', 'evil', 'super evil')\n" +
                ")";
        statement.executeUpdate(query);

        query = "CREATE TABLE minions_villains(\n" +
                "minion_id INT,\n" +
                "villain_id INT,\n" +
                "CONSTRAINT fk_mv_minions\n" +
                "FOREIGN KEY (minion_id)\n" +
                "REFERENCES minions(id),\n" +
                "CONSTRAINT fk_mv_villains\n" +
                "FOREIGN KEY (villain_id)\n" +
                "REFERENCES villains(id),\n" +
                "PRIMARY KEY (minion_id, villain_id)\n" +
                ")";
        statement.executeUpdate(query);

        //Insert data
        query = "insert into towns (id, `name`, country) " +
                "values (1, 'Sofia', 'Bulgaria')," +
                " (2, 'Plovdiv', 'Bulgaria')," +
                " (3, 'Burgas', 'Bulgaria')," +
                " (4, 'Berlin', 'Germany')," +
                " (5, 'London', 'England');";
        statement.executeUpdate(query);

        query = "insert into minions (id, name, age, town_id)\n" +
                "values (1, 'May', 44, 4),\n" +
                "  (2, 'Brina', 43, 5),\n" +
                "  (3, 'Roslyn', 50, 4),\n" +
                "  (4, 'Virgie', 53, 2),\n" +
                "  (5, 'Nananne', 23, 1),\n" +
                "  (6, 'Gayleen', 14, 1),\n" +
                "  (7, 'Ole', 53, 1),\n" +
                "  (8, 'Eldredge', 32, 2),\n" +
                "  (9, 'Marge', 16, 4),\n" +
                "  (10, 'Vi', 49, 3),\n" +
                "  (11, 'Ilka', 17, 4),\n" +
                "  (12, 'Pancho', 53, 5),\n" +
                "  (13, 'Stephi', 31, 5),\n" +
                "  (14, 'Cobby', 21, 5),\n" +
                "  (15, 'Florence', 67, 5),\n" +
                "  (16, 'Ardeen', 52, 3),\n" +
                "  (17, 'Sax', 28, 3),\n" +
                "  (18, 'Shurlocke', 33, 3),\n" +
                "  (19, 'Orsola', 16, 3),\n" +
                "  (20, 'Anselm', 59, 1),\n" +
                "  (21, 'Noble', 17, 3),\n" +
                "  (22, 'Colin', 13, 4),\n" +
                "  (23, 'Minette', 29, 4),\n" +
                "  (24, 'Katine', 23, 4),\n" +
                "  (25, 'Chevalier', 53, 2),\n" +
                "  (26, 'Abbe', 17, 5),\n" +
                "  (27, 'Skipp', 16, 1),\n" +
                "  (28, 'Wilhelm', 11, 4),\n" +
                "  (29, 'Madelyn', 41, 1),\n" +
                "  (30, 'Bryant', 50, 4),\n" +
                "  (31, 'Davey', 22, 4),\n" +
                "  (32, 'Jasen', 68, 4),\n" +
                "  (33, 'Dominique', 67, 2),\n" +
                "  (34, 'Mella', 63, 4),\n" +
                "  (35, 'Gaye', 22, 4),\n" +
                "  (36, 'Pearl', 48, 2),\n" +
                "  (37, 'Rozella', 20, 4),\n" +
                "  (38, 'Marika', 47, 1),\n" +
                "  (39, 'Annabella', 34, 4),\n" +
                "  (40, 'Jeffry', 48, 3),\n" +
                "  (41, 'Fiann', 51, 2),\n" +
                "  (42, 'Burgess', 15, 4),\n" +
                "  (43, 'Loydie', 51, 2),\n" +
                "  (44, 'Hermia', 56, 4),\n" +
                "  (45, 'Reggy', 34, 2),\n" +
                "  (46, 'Norah', 19, 4),\n" +
                "  (47, 'Lu', 26, 4),\n" +
                "  (48, 'Theodor', 66, 4),\n" +
                "  (49, 'Tara', 40, 5),\n" +
                "  (50, 'Brandie', 32, 3);";
        statement.executeUpdate(query);

        query = "insert into villains (id, name, evilness_factor)\n" +
                "values (1, 'Carl', 'good'),\n" +
                " (2, 'Crissy', 'bad'),\n" +
                " (3, 'Arabele', 'bad'),\n" +
                " (4, 'Sheeree', 'evil'),\n" +
                " (5, 'Flo', 'super evil'),\n" +
                " (6, 'Minionless', 'good');";
        statement.executeUpdate(query);

        query = "insert into minions_villains (minion_id, villain_id)\n" +
                "values (39, 1),\n" +
                "(6, 2),\n" +
                "(38, 5),\n" +
                "(35, 1),\n" +
                "(27, 2),\n" +
                "(40, 5),\n" +
                "(11, 5),\n" +
                "(40, 2),\n" +
                "(10, 1),\n" +
                "(37, 2),\n" +
                "(31, 3),\n" +
                "(8, 1),\n" +
                "(48, 2),\n" +
                "(19, 3),\n" +
                "(28, 2),\n" +
                "(25, 5),\n" +
                "(37, 1),\n" +
                "(12, 2),\n" +
                "(44, 5),\n" +
                "(47, 3),\n" +
                "(22, 4),\n" +
                "(4, 5),\n" +
                "(45, 3),\n" +
                "(46, 2),\n" +
                "(44, 2),\n" +
                "(35, 5),\n" +
                "(48, 1),\n" +
                "(37, 5),\n" +
                "(38, 4),\n" +
                "(3, 5),\n" +
                "(19, 1),\n" +
                "(50, 4),\n" +
                "(21, 1),\n" +
                "(34, 3),\n" +
                "(43, 4),\n" +
                "(10, 2),\n" +
                "(34, 2),\n" +
                "(29, 5),\n" +
                "(17, 4),\n" +
                "(11, 2),\n" +
                "(41, 1),\n" +
                "(23, 1),\n" +
                "(22, 5),\n" +
                "(3, 1),\n" +
                "(22, 3),\n" +
                "(24, 2),\n" +
                "(30, 3),\n" +
                "(41, 5),\n" +
                "(38, 1),\n" +
                "(12, 1),\n" +
                "(48, 3),\n" +
                "(37, 3),\n" +
                "(30, 2),\n" +
                "(7, 3),\n" +
                "(25, 4),\n" +
                "(26, 1),\n" +
                "(40, 3),\n" +
                "(44, 4),\n" +
                "(45, 5),\n" +
                "(20, 5),\n" +
                "(41, 4),\n" +
                "(40, 4),\n" +
                "(24, 5),\n" +
                "(13, 3),\n" +
                "(42, 3);";
        statement.executeUpdate(query);


        //Close connections
        statement.closeOnCompletion();
        conn.close();


    }
}
