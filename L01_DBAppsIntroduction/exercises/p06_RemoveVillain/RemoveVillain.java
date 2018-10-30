package L01_DBAppsIntroduction.exercises.p06_RemoveVillain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class RemoveVillain {
    static final String URL = "jdbc:mysql://localhost:3306/";
    static final String DATABASE = "minions_db";
    static final String USER = "root";
    static final String PASS = "1234";

    public static void main(String[] args) throws SQLException, IOException, SQLException {
        Properties props = new Properties();
        props.setProperty("user", USER);
        props.setProperty("password", PASS);

        Connection conn = DriverManager.getConnection(URL + DATABASE , props);
        Statement statement = conn.createStatement();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int villainId = Integer.parseInt(reader.readLine());

        String query = "SELECT v.name FROM villains AS v WHERE v.id = " + villainId;
        ResultSet rs = statement.executeQuery(query);
        if (!rs.isBeforeFirst()) {
            System.out.println("No such villain was found");
            return;
        }
        rs.next();
        String villainName = rs.getString("name");

        query = "DELETE FROM minions_villains\n" +
                "WHERE villain_id = " + villainId;
        int releasedMinions = statement.executeUpdate(query);

        query = "DELETE FROM villains\n" +
                "WHERE id = " + villainId;
        statement.executeUpdate(query);

        System.out.printf("%s was deleted%n", villainName);
        System.out.printf("%d minions released%n", releasedMinions);

        rs.close();
        statement.close();
        conn.close();
    }
}
