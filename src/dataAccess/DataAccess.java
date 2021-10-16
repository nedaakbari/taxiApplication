package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataAccess {
    private static final String URL = "jdbc:mysql://localhost:3306/taxi";
    private static final String USERNAME = "neda";
    private static final String PASSWORD = "13730203@Neda";
    protected Connection connection;

    public DataAccess() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}

