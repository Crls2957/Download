package dao;

import utils.DBhelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 用户登录注册具体实现类
 */
public class userDaoI implements userDao {

    public userDaoI() {
        super();
    }

    //声明数据库连接对象
    Connection connection=null;
    //声明指令操作对象
    PreparedStatement preparedStatement=null;
    //声明结果操作对象
    ResultSet resultSet=null;
    @Override
    public int register(String name, String passwd) {
        System.out.println(name);
        int flag=0;//1代表注册成功，2代表用户已存在，3代表注册失败
        connection= DBhelper.getConnection();
        String selectSql="select name from login";
        try{
            preparedStatement=connection.prepareStatement(selectSql);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                if(name.equals(resultSet.getString(1))){
                    flag=2;
                    return flag;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        String insertSql="insert into login(name,passwd,status) values(?,?,?);";
        try{
            preparedStatement=connection.prepareStatement(insertSql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,passwd);
            preparedStatement.setInt(3,1);
            int count=preparedStatement.executeUpdate();
            return flag=count>0?1:3;
        }catch (Exception e){
            e.printStackTrace();
        }
        DBhelper.close(connection,null,null);
        return 3;
    }

    @Override
    public int login(String name, String passwd) {

        int flag=0; //1代表登陆成功，2代表账密错误登陆失败，3代表查无此人
        connection=DBhelper.getConnection();
        String selectSql="select passwd from login where name=? and status=1";
        try{
            preparedStatement=connection.prepareStatement(selectSql);
            preparedStatement.setString(1,name);
            resultSet=preparedStatement.executeQuery();
            String password=null;
            while (resultSet.next()){
                password=resultSet.getString(1);
            }
            if(passwd.equals(password)&&password!=null){
                flag=1;
            }else if(password!=null&&!passwd.equals(password)){
                flag=2;
            }else {
                flag=3;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    public static void main(String [] args){
        userDaoI userDaoI=new userDaoI();
        System.out.println(userDaoI.register("刘备","3456"));
    }
}
