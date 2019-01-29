package filter;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class filter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //强转
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        HttpServletResponse resp=(HttpServletResponse) servletResponse;

        String user=(String) req.getSession().getAttribute("user");
        //判断当前资源是否需要权限控制
        String url=req.getRequestURI();
        String contextPath=req.getContextPath();
        String path=url.substring(contextPath.length());
        System.out.println(path);
        //
        /*resp.setContentType("text/html;charset=UTF-8");
        if (!path.equals("/")) {
            if (path.equals("register.jsp")){
                filterChain.doFilter(req,resp);
            }
            if (path.equals("/login")){
                filterChain.doFilter(req,resp);
            }
            if (user!=null){
                filterChain.doFilter(req,resp);
            }
        }*/
        filterChain.doFilter(req,resp);
    }

    @Override
    public void destroy() {

    }
}
