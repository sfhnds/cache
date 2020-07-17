
package com.at.cache;
import java.sql.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {

    private static Map<Object, Object> cache = new ConcurrentHashMap<>();
    private static LRU<Object, Object> lru = new LRU<>(3);
    private static final Integer CACHE_MAX_NUMBER = 1024;
    //添加缓存
    public static Object addCache(String data){
     //   cache.clear();
        if(cache.containsKey(data)){
            return cache.get(data);
        }
        else
            makeCache(data);
        return cache.get(data);

    }

    private static String makeCache(String data) {
        //数据库读数据STUDENT
        Connection ct = null;
        Statement sm = null;
        ResultSet rs = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            ct = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "test", "147852369");
            sm = ct.createStatement();
            rs = sm.executeQuery("select * from student");
            while (rs.next()) {
                 data = rs.getString(1) + ": " + rs.getString(2);
                 System.out.println(data);
                cache.put(rs.getString(1),rs.getString(2));
                lru.put(rs.getString(1),rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                sm.close();
                ct.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            } finally {
            }
        }
        return data;
    }

    //删除缓存
    private static Object removeCache(String data) {
        return cache.remove(data);
    }
    public void isFull(){
        if (CACHE_MAX_NUMBER>1024)
            cache.clear();
    }
    public static void main(String[] args) {
        addCache("");
        //removeCache("李磊");
//        System.out.println(cache.get("李雷"));
//        System.out.println(cache.get("韩梅梅"));
//        System.out.println(cache.get("韩梅梅"));
//        System.out.println(lru);
        System.out.println(cache);

    }
}

