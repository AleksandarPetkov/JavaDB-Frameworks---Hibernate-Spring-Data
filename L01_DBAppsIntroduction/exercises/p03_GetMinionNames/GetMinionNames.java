package L01_DBAppsIntroduction.exercises.p03_GetMinionNames;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class GetMinionNames {
    static final String URL = "jdbc:mysql://localhost:3306/";
    static final String DATABASE = "minions_db";
    static final String USER = "root";
    static final String PASS = "1234";

    public static void main(String[] args) throws SQLException, IOException {
        Properties props = new Properties();
        props.setProperty("user", USER);
        props.setProperty("password", PASS);

        Connection conn = DriverManager.getConnection(URL + DATABASE, props);
        Statement statement = conn.createStatement();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int villainId = Integer.parseInt(reader.readLine());

        String query = "SELECT v.name FROM villains AS v\n" +
                "WHERE v.id = " + villainId;
        ResultSet rs = statement.executeQuery(query);

        try {
            rs.next();
            String villainName = rs.getString("name");
            System.out.println("Villain: " + villainName);
        } catch (SQLException ex) {
            System.out.printf("No villain with ID %d exists in the database.", villainId);
        }

        query = "SELECT m.name AS name, m.age AS age\n" +
                "FROM minions_villains AS mv\n" +
                "JOIN minions AS m\n" +
                "ON mv.minion_id = m.id\n" +
                "WHERE mv.villain_id = " + villainId;
        rs = statement.executeQuery(query);

        int count = 0;
        while (rs.next()) {
            System.out.printf("%d. %s %s%n", ++count,
                    rs.getString("name"), rs.getString("age"));
        }

        rs.close();
        statement.closeOnCompletion();
        conn.close();
    }
}
