package object;

import common.Ignore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ObjectIo { // 싱글톤
    private static ObjectIo objectIo = new ObjectIo();

    private static Connection connection;

    private ObjectIo(){}

    public static ObjectIo getinstance(){
        return objectIo;
    }

    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(Ignore.URL.getMsg(),"Employee", Ignore.PASSWORD.getMsg());
        }  catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
