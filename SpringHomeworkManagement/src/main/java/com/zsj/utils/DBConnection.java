package com.zsj.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public Connection connect() {
        Connection conn = null;
        String url1 = "jdbc:mysql://localhost:3306/zzty_zsj";
        String url2 = "?user=root&password=AzusaL.815730";
        String url3 = "&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
        String url = url1 + url2 + url3;
        try {

            //现版本jdk1.8不需要通过此方法显示的注册驱动，但之前版本可能需要，所以最好显示注册此驱动
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
