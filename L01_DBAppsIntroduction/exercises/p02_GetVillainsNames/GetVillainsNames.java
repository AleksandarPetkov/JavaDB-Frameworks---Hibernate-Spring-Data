package L01_DBAppsIntroduction.exercises.p02_GetVillainsNames;

import java.sql.*;
import java.util.Properties;

public class GetVillainsNames {
    static final String URL = "jdbc:mysql://localhost:3306/";
    static final String DATABASE = "minions_db";
    static final String USER = "root";
    static final String PASS = "1234";

    public static void main(String[] args) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", USER);
        props.setProperty("password", PASS);

        Connection conn = DriverManager.getConnection(URL + DATABASE, props);
        Statement statement = conn.createStatement();

        String query = "SELECT CONCAT_WS(' ', v.name, COUNT(mv.minion_id)) AS output\n" +
                "FROM villains AS v\n" +
                "JOIN minions_villains AS mv\n" +
                "ON mv.villain_id = v.id\n" +
                "GROUP BY v.id\n" +
                "HAVING COUNT(mv.minion_id) > 3";
        ResultSet rs = statement.executeQuery(query);

        while (rs.next()) {
            System.out.println(rs.getString("output"));
        }

        rs.close();
        statement.closeOnCompletion();
        conn.close();
    }
}
