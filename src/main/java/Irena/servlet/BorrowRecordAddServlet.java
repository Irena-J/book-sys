package Irena.servlet;

import Irena.dao.BorrowRecordDAO;
import Irena.exception.BusinessException;
import Irena.model.BorrowRecord;
import Irena.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/borrowRecord/add")
public class BorrowRecordAddServlet extends AbstractBaseServlet{

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        BorrowRecord record = JSONUtil.read(req.getInputStream(),BorrowRecord.class);
        int num = BorrowRecordDAO.insert(record);
        if (num != 1) {
            throw new BusinessException("000006","插入借阅信息数量异常");
        }
        return null;
    }
}
