package L01_DBAppsIntroduction.exercises.p04_AddMinions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class AddMinions {
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
        String[] tokens = reader.readLine().split(" ");
        String minionName = tokens[1];
        int minionAge = Integer.parseInt(tokens[2]);
        String minionTown = tokens[3];

        tokens = reader.readLine().split(" ");
        String villainName = tokens[1];


        //Create town if it doesn't exist
        String query = "SELECT t.id FROM towns AS t\n" +
                "WHERE t.name = '" + minionTown + "'";
        ResultSet rs = statement.executeQuery(query);
        if (!rs.isBeforeFirst()) {
            query = "INSERT INTO towns(name, country)\n" +
                    "VALUES('" + minionTown + "', 'some country')";
            statement.executeUpdate(query);
        }

        //Create villain if it doesn't exist
        query = "SELECT v.id FROM villains AS v\n" +
                "WHERE v.name = '" + villainName + "'";
        rs = statement.executeQuery(query);

        if (!rs.isBeforeFirst()) {
            query = "INSERT INTO villains(name, evilness_factor)\n" +
                    "VALUES('" + villainName + "', 'evil')";
            statement.executeUpdate(query);
        }

        //Insert minion
        query = "SELECT t.id FROM towns AS t\n" +
                "WHERE t.name = '" + minionTown + "'";
        rs = statement.executeQuery(query);
        rs.next();
        int townId = rs.getInt("id");

        query = "INSERT INTO minions(name, age, town_id)\n" +
                String.format("VALUES('%s', %d, %d)", minionName, minionAge, townId);
        statement.executeUpdate(query);

        //Insert minion in minions_villains table
        query = "SELECT m.id FROM minions AS m\n" +
                "WHERE m.name = '" + minionName + "'";
        rs = statement.executeQuery(query);
        rs.next();
        int minionId = rs.getInt("id");

        query = "SELECT v.id FROM villains AS v\n" +
                "WHERE v.name = '" + villainName + "'";
        rs = statement.executeQuery(query);
        rs.next();
        int villainId = rs.getInt("id");

        query = "INSERT INTO minions_villains(minion_id, villain_id)\n" +
                String.format("VALUES(%d, %d)", minionId, villainId);
        statement.executeUpdate(query);

        //Close connections
        rs.close();
        statement.closeOnCompletion();
        conn.close();
    }
}
