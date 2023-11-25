package com.zm;
import cn.hutool.core.thread.ThreadUtil;
import java.sql.*;

public class JDBCUtil {

    /**
     * 创建连接
     * return 连接实例
     */
    public static Connection getConnection(){
        //加载驱动
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //创建连接
            String url = "jdbc:mysql://localhost:3306/koki?serverTimezone=GMT%2B8&characterEncoding=utf8";
            String user = "root";
            String pwd = "root";
            connection = DriverManager.getConnection(url, user, pwd);
        } catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 释放资源
     */
    public static void release(Connection connection,Statement statement,ResultSet resultSet){
        try {
            if(connection != null) connection.close();
            if(statement != null) statement.close();
            if(resultSet != null) resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static void main(String[] args) {
        for (int i = 0; i < 300; i++) {
            System.out.println(i);
            JDBCUtil.getConnection();
        }
        ThreadUtil.sleep(50000);
    }

}