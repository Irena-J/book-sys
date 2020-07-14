package Irena.util;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class DBUtilTest extends TestCase {
    // 先做单元测试，测试数据库是否能正常获取连接
    @Test
    public void test(){
        //返回数据库连接对象是否为空，说明数据库连接没获取到
        Assert.assertNotNull(DBUtil.getConnection());
    }
    // 再测试 jsonutil
}