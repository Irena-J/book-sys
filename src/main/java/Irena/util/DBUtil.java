package Irena.util;

import Irena.exception.SystemException;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

    private static final String URL =  "jdbc:mysql://localhost:3306/book";
    private static final String USERNAME = "root";
    // 这里很重要，你之前数据库没设置密码，就不写就行，不要写个空格
    private static final String PASSWORD = "";
    private static volatile DataSource DATA_SOURCE;
    private DBUtil(){}
    private static DataSource getDataSource() {
        // 获取数据库连接池，单例模式，理解双重校验锁每一行的意思
        if(DATA_SOURCE == null) {
            synchronized (DBUtil.class) {
                if(DATA_SOURCE == null) {
                    DATA_SOURCE = new MysqlDataSource();
                    ((MysqlDataSource) DATA_SOURCE).setUrl(URL);
                    ((MysqlDataSource) DATA_SOURCE).setUser(USERNAME);
                    ((MysqlDataSource) DATA_SOURCE).setPassword(PASSWORD);
                }
            }
        }
        return DATA_SOURCE;
    }

    // 获取数据库链接,
    public static Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            throw new SystemException("000001", "获取数据库连接失败",e);
        }
    }
    public static void close(Connection c,Statement s) {
        close(c,s,null);
    }
    public static void close(Connection c, Statement s, ResultSet r) {
        // 反向释放
        try {
            if (r != null)
                r.close();
            if (s != null)
                s.close();
            if (c != null)
                c.close();
        } catch (SQLException e) {
            throw new SystemException("000002","释放数据库资源出错",e);
        }
    }
}
