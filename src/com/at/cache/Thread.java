package com.at.cache;

import sun.awt.SunHints;

import java.sql.*;
import java.util.*;

import java.util.concurrent.*;

public class Thread {
    List<String> list = new ArrayList<>();


    public static void main(String[] args) {
        ExecutorService servicePoll = new ThreadPoolExecutor(5, 5, 10, TimeUnit.MICROSECONDS,
                new LinkedBlockingDeque<Runnable>());
        List<String> list = new ArrayList<>();

        List<String> list1 = new ArrayList<>();
            Connection ct = null;
            Statement sm = null;
            ResultSet rs = null;
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                ct = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "test", "147852369");
                sm = ct.createStatement();
                rs = sm.executeQuery("select * from student");
                int count = 0;
                while (rs.next()) {
                    //map.put(rs.getString(1),rs.getString(2));
                    String data = rs.getString(1) + ": " + rs.getString(2);
                    list.add(data);
                    String data2 = rs.getString(2);
                    list1.add(data2);
                }
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).contains("乌鲁木齐")) {
                        count = count + 1;
                    }
                }
                System.out.println("乌鲁木齐 " + count + "次");
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
            for (int i = 0; i < list.size(); i++) {
                int finalI = i;
                servicePoll.execute(new Runnable() {
                    public void run() {
                        try {
                            TimeUnit.MICROSECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(java.lang.Thread.currentThread().getName() + ":" + list.get(finalI));
                    }
                });
            }
    }
}