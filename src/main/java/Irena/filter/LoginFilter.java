package Irena.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// 模糊匹配，所有请求来了要过滤
@WebFilter("/*")
public class LoginFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getServletPath();
        // 可以放行的
        if ("/login.html".equals(uri) || uri.startsWith("/public/") || uri.startsWith("/static/")
                || "/user/login".equals(uri)) {
            chain.doFilter(request,response);
        } else {
            // 需要校验的
            HttpSession session = req.getSession(false);
            if (session == null) {// 访问铭感资源没有登录需要跳转到登录页面,也就是重定向
                String schema = req.getScheme(); // http
                String host = req.getServerName(); //服务器 ip 或 域名
                int port = req.getServerPort(); // 服务器端口号
                String contextPath = req.getContextPath();
                String basePath = schema+"://"+host+":"+port+contextPath;
                ((HttpServletResponse) response).sendRedirect(basePath+"/public/index.html");
                return;
            }
            chain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {

    }
}
