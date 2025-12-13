package org.s3team.DataBaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL_Data_Base_Connection implements Data_Base_Connection {

    private static MySQL_Data_Base_Connection instance;
    private Connection connection;
    /*private String url;
    private String user;
    private String password;*/

    private static final String URL = "jdbc:mysql://localhost:3306/escape_room";
    private static final String USER = "root";
    private static final String PASSWORD = "888888";

   /* private MySQL_Data_Base_Connection() {
        getDatabaseProperties();
        this.connection = null;
    }*/
   public MySQL_Data_Base_Connection() {
        // 2. 【移除】不需要调用 getDatabaseProperties()
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

   /* private void getDatabaseProperties() {
        Map<String, String> env = System.getenv();
        url = env.get("DB_URL");
        user = env.get("DB_USER");
        password = env.get("DB_PASSWORD");
    }*/

    @Override
    public void openConnection() {
        try {
            if (this.connection == null || this.connection.isClosed()) {
                System.out.println("Connection is closed or null. Attempting to re-establish connection...");
                if (URL == null || USER == null || PASSWORD == null) {
                    throw new IllegalStateException("Database connection properties (DB_URL, DB_USER, DB_PASSWORD) are missing from environment variables. Check your .env file and docker-compose.yaml.");
                }
                this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Successful connection to MySQL.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }

    }

    public Connection getConnection() {
        return this.connection;
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