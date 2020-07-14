package Irena.servlet;

import Irena.dao.BookDAO;
import Irena.model.Book;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/book/queryAsDict")
public class BookQueryAsDictServlet extends AbstractBaseServlet {

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Book> books = BookDAO.queryAsDict();
        return books;
    }
}
