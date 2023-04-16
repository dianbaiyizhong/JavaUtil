package com.nntk.demo.calcite;

import com.github.javafaker.Faker;
import org.apache.calcite.config.CalciteConnectionProperty;
import org.apache.calcite.config.NullCollation;
import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.jdbc.Driver;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.schema.Table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class MyDriver extends Driver {

    public static final String CONNECT_STRING_PREFIX = "jdbc:calcite:";

    static {
        new MyDriver().register();
    }

    @Override
    protected String getConnectStringPrefix() {
        return CONNECT_STRING_PREFIX;
    }

    Connection connection;

    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        // 设置连接参数
        info = new Properties();
        info.setProperty(CalciteConnectionProperty.DEFAULT_NULL_COLLATION.camelName(), NullCollation.LAST.name());
        info.setProperty(CalciteConnectionProperty.CASE_SENSITIVE.camelName(), "false");
        // 建立连接
//        connection = DriverManager.getConnection("jdbc:calcite:", info);
        connection = super.connect(url, info);

        // 执行查询
        // 取得Calcite连接
        CalciteConnection calciteConnection = connection.unwrap(CalciteConnection.class);
        // 取得RootSchema RootSchema是所有Schema的父Schema
        SchemaPlus rootSchema = calciteConnection.getRootSchema();
        // 添加schema
        rootSchema.add("my_db", buildSchema());
        return connection;
    }


    private static MemorySchema buildSchema() {
        // 1. 定义表集合
        Map<String, Table> tableMap = new HashMap<>();

        // 定义表1

        Faker faker = new Faker();

        {
            List<List<Object>> userInfoList = new ArrayList<>();
            // 定义表1数据
            for (int i = 0; i < 100; i++) {
                List<Object> list = new ArrayList<>();
                list.add(faker.idNumber().invalid());
                list.add(faker.name().fullName());
                list.add(faker.job().title());
                userInfoList.add(list);
            }

            // 定义表2的结构
            List<MemoryColumn> meta = new ArrayList<>();
            MemoryColumn id = new MemoryColumn("id", String.class);
            MemoryColumn name = new MemoryColumn("name", String.class);
            MemoryColumn job = new MemoryColumn("job", String.class);
            meta.add(id);
            meta.add(name);
            meta.add(job);

            tableMap.put("t_user_info", new MemoryTable(meta, userInfoList));

        }

        {
            // 定义表2数据
            List<List<Object>> dataList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                List<Object> list = new ArrayList<>();
                list.add(faker.idNumber().invalid());
                list.add(faker.job().title());
                dataList.add(list);
            }
            // 定义表2结构
            List<MemoryColumn> meta = new ArrayList<>();
            MemoryColumn id = new MemoryColumn("id", String.class);
            MemoryColumn job = new MemoryColumn("job", String.class);
            meta.add(id);
            meta.add(job);

            tableMap.put("t_dept_info", new MemoryTable(meta, dataList));
        }

        MemorySchema memorySchema = new MemorySchema(tableMap);

        return memorySchema;

    }

}
