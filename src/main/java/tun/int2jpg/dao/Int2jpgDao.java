package tun.int2jpg.dao;


import java.sql.*;

public class Int2jpgDao {

    private static final String URL = "jdbc:postgresql://db";
    private static final String USER_NAME = "user_name";
    private static final String PASSWORD = "password";

    private static Connection connection;

    private static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void runSqlAndPrintColumn(String sql, String column) {
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                System.out.println(resultSet.getString(column));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
