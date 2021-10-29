package team.group33.utils;
import java.util.ResourceBundle;
import java.sql.*;

public class JDBCUtil {
    //定义出来数据库连接的信息
    private static String url;
    private static String username;
    private static String password;

    static {
        // 加载配置文件
        ResourceBundle rb = ResourceBundle.getBundle("dbinfo");
        String driverClass = rb.getString("driverClass");
        url = rb.getString("url");
        username = rb.getString("username");
        System.out.println("--"+username);
        password = rb.getString("password");
        //获取驱动类
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //数据库连接
    public static Connection getConnect() throws Exception {
        return DriverManager.getConnection(url, username, password);
    }

    //数据库关闭
    public static void close(PreparedStatement preparedStatement, Connection connection, ResultSet resultSet){

        if (preparedStatement != null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
