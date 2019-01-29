package servlet;

import utils.Validate;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * 接收更换验证码的请求
 */
public class validate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("接收更换验证码的请求");
        // 禁止图像缓存。
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", -1);

        Validate validate=new Validate();
        Map<String,Object> map=validate.paint();
        //将验证码字符存入session空间
        HttpSession httpSession=req.getSession();
        httpSession.setAttribute("code",map.get("code").toString());

        ImageIO.write((BufferedImage)map.get("image"),"jpg",resp.getOutputStream());
    }
}
