package test;

import dao.userDaoI;
import org.junit.Test;
import servlet.register;

import static org.junit.Assert.*;

public class userDaoITest extends register {

    dao.userDaoI userDaoI=new userDaoI();

    @Test
    public void register() {
        System.out.println(userDaoI.register("罗森","2345"));
    }

    @Test
    public void login() {
    }
}