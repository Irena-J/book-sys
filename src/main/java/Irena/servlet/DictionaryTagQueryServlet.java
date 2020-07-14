package Irena.servlet;

import Irena.dao.DictionaryTagDAO;
import Irena.model.DictionaryTag;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/dict/tag/query")
public class DictionaryTagQueryServlet extends AbstractBaseServlet{

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 先解析获取 dictionaryKey
        String key = req.getParameter("dictionaryKey");
        // 通过键到数据库查询所有的数据字典以及数据字典标签，把所有字段返回给前端
        // 数据标签查询、访问的DAO
        List<DictionaryTag> tags = DictionaryTagDAO.query(key);
        return tags;
    }
}
