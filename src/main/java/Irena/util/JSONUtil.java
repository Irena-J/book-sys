package Irena.util;
// 数据格式的包装类
// 解析传过来的 IO 流，通过IO流进行 JSON 数据读取，转换为相应的类

import Irena.exception.SystemException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

public class JSONUtil {

    private static final ObjectMapper MAPPER;
    static {
        MAPPER = new ObjectMapper();
        MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }
    // 通过HTTP请求对象里获取输入流来获取json数据
    // 反序列化操作，把输入流解析成类
    // 读取输入流的json数据，反序列化为对象，
    // 泛型的操作：<T>方法上定义为泛型，返回值和传入参数都可以用泛型
    public static <T> T read(InputStream is, Class<T> clazz) {
        try {
            return MAPPER.readValue(is,clazz);
        } catch (IOException e) {
            throw new SystemException("000003","http请求，解析json数据出错",e);
        }
    }
    // 写操作，把一个类、对象写成json 字符串
    public static String write(Object o) {
        try {
            return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new SystemException("000004","json序列化对象出错："+o,e);
        }
    }
}
