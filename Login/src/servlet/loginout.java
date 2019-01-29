package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 接收登出请求
 */
public class loginout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("接收登出请求");

        resp.setContentType("text/html;charset=UTF-8");
        String name=req.getParameter("name");
        System.out.println(name);
        Cookie [] cookies=req.getCookies();
        for(int i=0;i<cookies.length;i++){
            if (cookies[i].getName().equals(name)){
                System.out.println(cookies[i].getName());
                cookies[i]=new Cookie("userName","");
                //设置有效路径
                cookies[i].setMaxAge(0);
                cookies[i].setPath("/");
                resp.addCookie(cookies[i]);
                req.getSession().removeAttribute("user");
            }
        }
        req.setAttribute("reset","reset");
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }
}
