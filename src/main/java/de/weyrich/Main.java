package de.weyrich;
import java.sql.*;

public class Main {
    static final String DB_URL = "";
    static final String USER = "guest";
    static final String PASS = "guest123";
    static final String QUERY = "SELECT table_name, owner FROM user_tables";

    public static void main(String[] args) {
        // load driver
        try {
            Driver myDriver = new oracle.jdbc.driver.OracleDriver();
            DriverManager.registerDriver( myDriver );
        } catch (SQLException e) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        }
        // Open a connection
        System.out.println("Try to connect");
        try(
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);
            )
        {
            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                System.out.print(", table_name: " + rs.getString("table_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
