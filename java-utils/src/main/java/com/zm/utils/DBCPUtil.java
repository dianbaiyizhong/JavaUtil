package com.zm.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

public class DBCPUtil {
    private static Properties properties = new Properties();
    private static DataSource dataSource;

    //加载DBCP配置文件
    static {
        try {
            //注意这里需要使用绝对路径
            FileInputStream is = new FileInputStream(new File("D:\\project\\JavaUtil\\java-utils\\src\\main\\resources\\dbcp.properties"));
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取数据源对象
        try {
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //从连接池中获取一个连接
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) throws SQLException {
        Connection conn = DBCPUtil.getConnection();

        Statement stmt = conn.createStatement();

        System.out.println("====");
        ResultSet rs = stmt.executeQuery("select  sleep(20000)");


    }
}
