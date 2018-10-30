package L01_DBAppsIntroduction.exercises.p08_IncreaseMinionAge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class IncreaseMinionAge {
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
        String[] ids = reader.readLine().split(" ");

        for (String id : ids) {
            String query = "UPDATE minions\n" +
                    "SET age = age + 1,\n" +
                    "name = CONCAT(UPPER(SUBSTR(name, 1, 1)), LOWER(SUBSTR(name, 2)))\n" +
                    "WHERE id = " + id;
            statement.executeUpdate(query);
        }
        String query = "SELECT CONCAT_WS(' ', m.name, m.age) AS output FROM minions AS m";
        ResultSet rs = statement.executeQuery(query);

        while (rs.next()) {
            System.out.println(rs.getString("output"));
        }

        rs.close();
        statement.closeOnCompletion();
        conn.close();
    }
}
