package Irena.dao;

import Irena.exception.SystemException;
import Irena.model.Classes;
import Irena.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// 班级访问的数据层
public class ClassesDAO {
//数据字典下拉菜单
    public static List<Classes> queryAsDict() {
        List<Classes> classesList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = DBUtil.getConnection();
            String sql = "select id,classes_name,classes_graduate_year,classes_major from classes";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) {
                Classes classes = new Classes();
                classes.setDictionaryTagKey(String.valueOf(rs.getInt("id")));
                classes.setDictionaryTagValue(rs.getString("classes_name"));
                classes.setClassesGraduateYear(rs.getString("classes_graduate_year"));
                classes.setClassesMajor(rs.getString("classes_major"));
                classesList.add(classes);
            }
        } catch (Exception e){
            throw new SystemException("00003","查询班级数据字典信息出错");
        } finally{
            DBUtil.close(c,ps,rs);
        }
        return classesList;
    }
}
