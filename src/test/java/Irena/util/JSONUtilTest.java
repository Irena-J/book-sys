package Irena.util;

import Irena.model.ResponseResult;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;

public class JSONUtilTest extends TestCase {

    // 读取操作测试，记得要自定义读取文件
    @Test
    public void testRead() {
        InputStream is = getClass().getClassLoader().getResourceAsStream("response.json");
        ResponseResult r = JSONUtil.read(is,ResponseResult.class);
        System.out.println(r);
        Assert.assertNotNull(r);

    }

    // json写入操作测试
    @Test
    public void testWrite() {
        ResponseResult r = new ResponseResult();
        r.setCode("k3000");
        r.setMessage("请回答");
        r.setSuccess(true);
        r.setTotal(9);
        String s = JSONUtil.write(r);
        System.out.println(s);
        Assert.assertNotNull(s);
        // trim 去除前后空格的
        Assert.assertTrue(s.trim().length()>0);
    }
}