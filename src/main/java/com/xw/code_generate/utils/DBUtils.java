package com.xw.code_generate.utils;

import com.xw.code_generate.model.DB;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 处理数据库连接工具类
 */
public class DBUtils {
    private static Connection connection;

    public static Connection getConnection() {
        return connection;
    }

    public static Connection initDB(DB db) {
        if (connection == null) {
            try {
                String url = db.getUrl();
                String username = db.getUsername();
                String password = db.getPassword();
                // 加载数据库驱动程序类
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, username, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
