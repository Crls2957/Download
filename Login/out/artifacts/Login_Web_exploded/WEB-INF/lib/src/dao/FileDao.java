package dao;

import bean.PageBean;
import entity.File;

import java.sql.Date;
import java.util.List;

/**
 * 文件操作的dao层接口
 */
public interface FileDao {

    //文件上传
    public Boolean upload(String name, String path, Date time);

    //文件删除 通过文件id进行查找
    public Boolean delete(int id);

    //文件查找 通过文件id进行查找
    public File search(int id);

    //查找全部
    public List<File> all();

    //分页查找
    public PageBean page(int pageNow);
}
