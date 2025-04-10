package util;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDBConnection {
    public static void main(String[] args) {
        try
        {
            Connection con = DBUtil.getDBConn();
            System.out.println("Connected");
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }
}
