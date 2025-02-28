package object;

import java.sql.Connection;

public class ObjectIo { // 싱글톤
    private static ObjectIo objectIo = new ObjectIo();

    private static Connection connection;

    public static ObjectIo getInstance(){
      return objectIo;
    };
//    public static Connection getConnection(){
//        return connection// 구현;
//    }
}
