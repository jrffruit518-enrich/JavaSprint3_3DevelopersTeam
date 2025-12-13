package org.s3team.DataBaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class MySQL_Data_Base_Connection implements Data_Base_Connection {

    private static MySQL_Data_Base_Connection instance;
    private Connection connection;


    private final String url = "jdbc:mysql://localhost:3306/escape_room";
    private final String user = "root";
    private final String password = "888888";
    // ----------------------

    private MySQL_Data_Base_Connection() {

        this.connection = null;
    }

    public static synchronized MySQL_Data_Base_Connection getInstance() {
        if (instance == null) {
            try {
                instance = new MySQL_Data_Base_Connection();
                instance.openConnection();
            } catch (Exception e) {

                throw new RuntimeException("Error connecting to the database", e);
            }
        }
        return instance;
    }



  /*  @Override
    public void openConnection() {
        try {
            if (this.connection == null || this.connection.isClosed()) {
                System.out.println("Connection is closed or null. Attempting to re-establish connection...");

                // 尝试使用硬编码的 URL, user, password 进行连接
                this.connection = DriverManager.getConnection(url, user, password);
                System.out.println("Successful connection to MySQL.");
            }
        } catch (SQLException e) {
            // 如果连接失败（例如 MySQL 服务未运行，或用户名/密码错误），则抛出运行时异常
            throw new RuntimeException("Error connecting to the database", e);
        }

    }*/
    @Override
    public void openConnection() {
        try {
            // 如果连接为 null 或已关闭，则重新建立连接
            if (this.connection == null || this.connection.isClosed()) {
                System.out.println("Connection is closed or null. Attempting to re-establish connection...");
                // ... (使用 DriverManager.getConnection 重新建立连接)
                this.connection = DriverManager.getConnection(url, user, password);
                System.out.println("Successful connection to MySQL.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

 /*   public Connection getConnection() {
        return this.connection;
    }*/

    public Connection getConnection() {
        try {
            openConnection();
            return this.connection;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @Override
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Error closing connection to data base", e);
            }
        }
    }
}