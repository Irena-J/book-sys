package Irena.servlet;

import Irena.dao.BorrowRecordDAO;
import Irena.model.BorrowRecord;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/borrowRecord/query")
public class BorrowRecordQueryServlet extends AbstractBaseServlet {

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<BorrowRecord> records = new BorrowRecordDAO().query();
        return records;
    }
}
