package cormaccallanca5;

import Exceptions.DaoException;
import java.sql.*;
import java.sql.Connection;

/**
 *
 * @author Cormac Callan D00182306
 */
public class MainApp {

    public static void main(String[] args) {

        new MainApp().start();

    }

    public void start() {


        java.sql.Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "movies";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "";  // 

        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, userName, password);

            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM movies LIMIT 10");

            while (resultSet.next()) {
                int custID = resultSet.getInt("id");
                String fname = resultSet.getString("title");

                System.out.print(custID + ", ");
                System.out.print(fname + ", \n");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    }


