package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    public static Connection getDBConn() throws SQLException {
        return DriverManager.getConnection(PropertyUtil.getPropertyString());
    }

}
