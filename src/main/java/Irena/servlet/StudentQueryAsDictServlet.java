package Irena.servlet;

import Irena.dao.StudentDAO;
import Irena.model.Student;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/student/queryAsDict")
public class StudentQueryAsDictServlet extends AbstractBaseServlet {

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 获取班级id
        int id = Integer.parseInt(req.getParameter("dictionaryKey"));
        List<Student> students = StudentDAO.queryAsDict(id);
        return students;
    }
}
