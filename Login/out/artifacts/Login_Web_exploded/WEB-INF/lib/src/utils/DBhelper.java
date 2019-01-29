package utils;

import java.sql.*;

/**
 * JDBC连接数据库工具类
 */
public class DBhelper {

    private static String url="jdbc:mysql://localhost:3306/login?characterEncoding=utf8&useSSL=false";
    private static String user="luosen";
    private static String passwd="2035";
    private static String driver="com.mysql.jdbc.Driver";

    //声明连接对象
    private  static Connection connection=null;

    public static Connection getConnection(){
        if (connection==null){
            try{
                Class.forName(driver);
                connection= DriverManager.getConnection(url,user,passwd);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return connection;
    }
    public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
        if(connection!=null){
            try{
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(preparedStatement!=null){
            try{
                preparedStatement.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(resultSet!=null){
            try{
                resultSet.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    /*public static void main(String [] args){
        System.err.println(DBhelper.getConnection());
    }*/
}
