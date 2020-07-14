package Irena.servlet;

import Irena.dao.BorrowRecordDAO;
import Irena.exception.BusinessException;
import Irena.model.BorrowRecord;
import Irena.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/borrowRecord/delete")
public class BorrowRecordDeleteServlet extends AbstractBaseServlet{

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String[] ids = req.getParameterValues("ids");
        int num = BorrowRecordDAO.delete(ids);
        if (num != ids.length) {
            throw new BusinessException("000007","删除借阅信息数量异常");
        }
        return null;
    }
}
