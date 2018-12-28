package tun.int2jpg.jdbc;

import java.sql.*;

public class Int2jpgJdbc {

    private static final String URL = "jdbc:postgresql://tantor.db.elephantsql.com:5432/gogltput";
    private static final String USER_NAME = "gogltput";
    private static final String PASSWORD = "uegIYpaiQTFJUm1Sqjcf3s3Z1TCBaVqU";

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

    /**
     * Run given query and print all columns of returned data
     *
     * @param query
     */
    public static void runSelectQuery(String query) {
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnsNumber = resultSetMetaData.getColumnCount();

            while (resultSet.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) {
                        System.out.print(", ");
                    }
                    String columnValue = resultSet.getString(i);
                    System.out.print(resultSetMetaData.getColumnName(i) + ":" + columnValue);
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Runs given insert, update or delete query
     *
     * @param query
     */
    public static void runInsertUpdateDeleteQuery(String query) {
        try {
            Statement statement = getConnection().createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
