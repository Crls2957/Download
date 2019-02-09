package servlet;

import dao.userDaoI;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 接收登录请求
 */
public class login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止中文乱码
        //设置缓冲区字符集编码
        resp.setCharacterEncoding("UTF-8");
        //设置浏览器打开文件的编码
        resp.setHeader("Content-Type","text/html;charset=UTF-8");
        //简写形式
        /*resp.setContentType("text/html;charset=UTF-8");*/

        // 禁止图像缓存。
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", -1);

        System.out.println("接收登录请求");
        String name=req.getParameter("user");
        String passwd=req.getParameter("passwd");
        String inputValidate=req.getParameter("inputValidate");
        String check=req.getParameter("check");
        if (check==null){
            check="0";
        }
        userDaoI userDaoI=new userDaoI();
        int flag=userDaoI.login(name,passwd);
        if (inputValidate==""||!inputValidate.equals(req.getSession().getAttribute("code").toString())){
            resp.getWriter().write("<script>alert('验证码输入错误！');history.back();</script>");
        }else if(flag==1){
            if (check.equals("1")){
                //创建cookie对象
                Cookie nameCookie=new Cookie("userName",name);
                //设置有效期
                nameCookie.setMaxAge(Integer.parseInt(check)*60*60*24);
                //设置有效路径
                nameCookie.setPath("/");
                Cookie passwdCookie=new Cookie("userPasswd",passwd);
                passwdCookie.setMaxAge(Integer.parseInt(check)*60*60*24);
                passwdCookie.setPath("/");
                Cookie checkCookie=new Cookie("check",check);
                resp.addCookie(nameCookie);
                resp.addCookie(passwdCookie);
                req.getSession().setAttribute("user",name);
                req.getSession().setAttribute("flag","登录成功");
                req.getSession().setAttribute("name",name);
                req.getRequestDispatcher("sucess.jsp").forward(req,resp);
            }else {
                req.getSession().setAttribute("user",name);
                req.getSession().setAttribute("flag", "登录成功");
                req.getSession().setAttribute("name", name);
                req.getRequestDispatcher("sucess.jsp").forward(req, resp);
            }
        }else if(flag==2){
            req.getSession().setAttribute("flag","账密错误");
            req.getRequestDispatcher("sucess.jsp").forward(req,resp);
        }else if(flag==3){
            req.getSession().setAttribute("flag","查无此人");
            req.getRequestDispatcher("sucess.jsp").forward(req,resp);
        }
    }
}
