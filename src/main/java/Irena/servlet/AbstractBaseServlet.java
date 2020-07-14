package Irena.servlet;

import Irena.exception.BaseException;
import Irena.model.ResponseResult;
import Irena.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public abstract class AbstractBaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8"); // 针对请求体设置编码，注意对url中的请求数据无效
        resp.setCharacterEncoding("UTF-8"); // 针对相应体设置编码
        resp.setContentType("application/json"); // 设置响应的数据格式：响应头Content-Type告诉浏览器怎么解析
        ResponseResult r = new ResponseResult();

        try {
            Object data = process(req,resp);
            r.setSuccess(true);
            r.setCode("200");
            r.setMessage("成功操作");
            r.setData(data);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof BaseException) {
                BaseException be = (BaseException) e;
                r.setCode(be.getCode());
                r.setMessage(be.getMessage());
            } else {
                r.setCode("500");
                r.setMessage("未知的错误");
            }
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            r.setStackTrace(sw.toString());
        }
        PrintWriter pw = resp.getWriter();
        pw.println(JSONUtil.write(r));
        pw.flush();
    }

    public abstract Object process(HttpServletRequest req,HttpServletResponse resp) throws Exception;
}
