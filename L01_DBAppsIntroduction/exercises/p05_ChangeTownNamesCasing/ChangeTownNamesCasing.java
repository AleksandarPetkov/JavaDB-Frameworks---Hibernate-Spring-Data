package L01_DBAppsIntroduction.exercises.p05_ChangeTownNamesCasing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ChangeTownNamesCasing {
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
        String country = reader.readLine();

        String query = "UPDATE towns\n" +
                "SET name = UPPER(name)\n" +
                "WHERE country = '" + country + "'";
        int affectedRows = statement.executeUpdate(query);
        if (affectedRows == 0) {
            System.out.printf("%d town names were affected.%n", affectedRows);
            return;
        }

        List<String> affectedTowns = new ArrayList<>();
        query = "SELECT name FROM towns WHERE country = '" + country + "'";
        ResultSet rs = statement.executeQuery(query);

        while (rs.next()) {
            affectedTowns.add(rs.getString("name"));
        }
        System.out.printf("%d town names were affected.%n", affectedRows);
        System.out.println(affectedTowns);

        rs.close();
        statement.closeOnCompletion();
        conn.close();
    }

}
