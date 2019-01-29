package servlet;

import dao.userDaoI;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 接收注册请求
 */
public class register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("接收注册请求");
        String name=req.getParameter("user");
        String passwd=req.getParameter("passwd");
        userDaoI userDaoI=new userDaoI();
        int flag=userDaoI.register(name,passwd);
        if(flag==1){
            req.setAttribute("flag","注册成功");
            req.getRequestDispatcher("sucess.jsp").forward(req,resp);
        }else if(flag==3){
            req.setAttribute("flag","注册失败");
            req.getRequestDispatcher("sucess.jsp").forward(req,resp);
        }else if(flag==2){
            req.setAttribute("flag","用户名已存在");
            req.getRequestDispatcher("sucess.jsp").forward(req,resp);
        }
    }
}
