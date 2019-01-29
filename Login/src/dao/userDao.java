package dao;

/**
 * 用户登陆注册功能dao层接口
 */
public interface userDao {

    //用户注册
    public int register(String name,String passwd);

    //用户登录
    public int login(String name,String passwd);
}
