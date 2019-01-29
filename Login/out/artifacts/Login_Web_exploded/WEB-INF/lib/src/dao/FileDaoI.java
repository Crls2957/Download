package dao;

import bean.PageBean;
import entity.File;
import utils.DBhelper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件操作的具体实现类
 */
public class FileDaoI implements FileDao{

    //声明数据库连接对象
    Connection connection=null;
    //声明数据库操作对象
    PreparedStatement preparedStatement=null;
    //声明数据库结果对象
    ResultSet resultSet=null;


    //上传文件
    @Override
    public Boolean upload(String name, String path, Date time) {
        connection= DBhelper.getConnection();
        String addSql="insert into upload(name,filepath,uploadtime) values(?,?,?)";
        try{
            preparedStatement=connection.prepareStatement(addSql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,path);
            preparedStatement.setDate(3,time);
            int count=preparedStatement.executeUpdate();
            return count>0?true:false;
        }catch (Exception e){
            e.printStackTrace();
        }
        DBhelper.close(connection,preparedStatement,null);
        return false;
    }
    //删除文件
    @Override
    public Boolean delete(int id) {
        connection=DBhelper.getConnection();
        String deleteSql="update upload set status=0 where id=?";
        try{
            preparedStatement=connection.prepareStatement(deleteSql);
            preparedStatement.setInt(1,id);
            int count=preparedStatement.executeUpdate();
            return count>0?true:false;
        }catch (Exception e){
            e.printStackTrace();
        }
        DBhelper.close(connection,preparedStatement,null);
        return false;
    }

    //通过编号查询文件
    @Override
    public File search(int id) {
        File file=new File();
        connection=DBhelper.getConnection();
        String findSql="select *from upload where id=?";
        try{
            preparedStatement=connection.prepareStatement(findSql);
            preparedStatement.setInt(1,id);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                file.setId(resultSet.getInt("id"));
                file.setName(resultSet.getString("name"));
                file.setPath(resultSet.getString("filepath"));
                file.setTime(resultSet.getDate("uploadtime"));
                file.setStatus(resultSet.getInt("status"));
                return file;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        DBhelper.close(connection,preparedStatement,resultSet);
        return null;
    }

    //查询所有文件
    @Override
    public List<File> all() {
        List<File> list=new ArrayList<>();
        connection=DBhelper.getConnection();
        String findAllSql="select *from upload where status=1";
        try{
            preparedStatement=connection.prepareStatement(findAllSql);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                File file=new File();
                file.setId(resultSet.getInt("id"));
                file.setName(resultSet.getString("name"));
                file.setPath(resultSet.getString("filepath"));
                file.setTime(resultSet.getDate("uploadtime"));
                file.setStatus(resultSet.getInt("status"));
                list.add(file);
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        DBhelper.close(connection,preparedStatement,resultSet);
        return null;
    }

    private int lineCount;//总的数据条数
    private int totalPage;//总的页数

    public int getLineCount() {
        return lineCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    //分页查找文件
    @Override
    public PageBean page(int pageNow) {
        int pageSize=3;  //每页显示三条数据
        PageBean pageBean=new PageBean();
        List<File> list=new ArrayList<>();
        connection=DBhelper.getConnection();
        String findCountSql="select count(*) from upload";
        try{
            preparedStatement=connection.prepareStatement(findCountSql);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                lineCount=resultSet.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        totalPage=(int) Math.ceil(lineCount/pageSize);
        String findAllSql="select *from upload limit ?,?";
        try{
            preparedStatement=connection.prepareStatement(findAllSql);
            preparedStatement.setInt(1,(pageNow-1)*pageSize);
            preparedStatement.setInt(2,pageSize);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                File file=new File();
                file.setId(resultSet.getInt("id"));
                file.setName(resultSet.getString("name"));
                file.setPath(resultSet.getString("filepath"));
                file.setTime(resultSet.getDate("uploadtime"));
                file.setStatus(resultSet.getInt("status"));
                list.add(file);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        int n=pageNow;
        pageBean.setLineCount(lineCount);
        pageBean.setPageNow(n);
        pageBean.setTotalPage(totalPage);
        pageBean.setList(list);
        DBhelper.close(connection,preparedStatement,resultSet);
        return pageBean;
    }
}
