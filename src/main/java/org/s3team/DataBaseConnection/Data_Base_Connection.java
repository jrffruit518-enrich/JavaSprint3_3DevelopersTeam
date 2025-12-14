package org.s3team.DataBaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public interface Data_Base_Connection {
    //void openConnection();
    Connection getConnection() throws SQLException;
   // void closeConnection();
}
