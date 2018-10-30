package L01_DBAppsIntroduction.exercises.p09_IncreaseAgeStoredProcedure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class IncreaseAgeStoredProcedure {

    static final String URL = "jdbc:mysql://localhost:3306/";
    static final String DATABASE = "minions_db";
    static final String USER = "root";
    static final String PASS = "1234";

    public static void main(String[] args) throws SQLException, IOException, SQLException {
        Properties props = new Properties();
        props.setProperty("user", USER);
        props.setProperty("password", PASS);

        Connection conn = DriverManager.getConnection(URL + DATABASE, props);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int id = Integer.parseInt(reader.readLine());

        String call = "{CALL usp_get_older (?)}";
        CallableStatement cStatement = conn.prepareCall(call);
        cStatement.setInt(1, id);
        cStatement.execute();
        cStatement.closeOnCompletion();

        Statement statement = conn.createStatement();

        String query = "SELECT CONCAT_WS(' ', m.name, m.age) AS output FROM minions AS m\n" +
                "WHERE m.id = " + id;
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            System.out.println(rs.getString("output"));
        }

        rs.close();
        statement.closeOnCompletion();
        conn.close();
    }
}
