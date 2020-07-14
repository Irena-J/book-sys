package Irena.servlet;

import Irena.dao.ClassesDAO;
import Irena.model.Classes;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

// 把班级当作一个格式字典的方式返回
@WebServlet("/classes/queryAsDict")
public class ClassesQueryAsDictServlet extends AbstractBaseServlet {

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Classes> classesList = ClassesDAO.queryAsDict();
        return classesList;
    }
}
