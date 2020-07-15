package com.at.cache;

import java.sql.*;


public class DBTest {
    public static void main(String[] args) {
        Connection ct = null;
        Statement sm = null;
        ResultSet rs = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            ct = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "test", "147852369");
            sm = ct.createStatement();
            rs = sm.executeQuery("select * from student");
            while (rs.next()) {
                System.out.println("用户名为：" + rs.getString(2));
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
    }
}
