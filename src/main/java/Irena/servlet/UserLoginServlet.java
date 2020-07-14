package Irena.servlet;

import Irena.dao.UserDAO;
import Irena.exception.BusinessException;
import Irena.model.User;
import Irena.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/user/login")
public class UserLoginServlet extends AbstractBaseServlet {

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // req.getParameter("") 这个方式只能获取 url 和请求体，k=v形式的数据
        User user = JSONUtil.read(req.getInputStream(),User.class); // http请求解析的用户数据
        User queryUser = UserDAO.query(user); // 通过请求的用户名密码在数据库查询，获取用户
        if (queryUser == null) {
            throw new BusinessException("0001","用户名密码校验失败");
        }
        // 创建session
        HttpSession session = req.getSession();
        session.setAttribute("user",queryUser);
        return null;
    }
}
