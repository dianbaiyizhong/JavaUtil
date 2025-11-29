package com.nntk.demo.calcite.es;

import cn.hutool.core.io.resource.ResourceUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.calcite.adapter.elasticsearch.ElasticsearchSchema;
import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.Properties;
public class TestEs {

    public static void main(String[] args) throws SQLException, IOException {

        // https://houbb.github.io/2018/11/15/database-apache-calcite-05-es-integration
        RestClient restClient = RestClient.builder(new HttpHost("127.0.0.1", 9200)).build();

        ElasticsearchSchema elasticsearchSchema = new ElasticsearchSchema(restClient, new ObjectMapper(), null);


        // 2.构建Connection
        // 2.1 设置连接参数
        Properties info = new Properties();
        // 不区分sql大小写
        info.setProperty("caseSensitive", "false");

        // 2.2 获取标准的JDBC Connection
        Connection connection = DriverManager.getConnection("jdbc:calcite:", info);
        // 2.3 获取Calcite封装的Connection
        CalciteConnection calciteConnection = connection.unwrap(CalciteConnection.class);

        // 3.构建RootSchema，在Calcite中，RootSchema是所有数据源schema的parent，多个不同数据源schema可以挂在同一个RootSchema下
        // 以实现查询不同数据源的目的
        SchemaPlus rootSchema = calciteConnection.getRootSchema();

        // 4.将不同数据源schema挂载到RootSchema，这里添加ElasticsearchSchema
        rootSchema.add("es", elasticsearchSchema);

        // 5.执行SQL查询，通过SQL方式访问object对象实例
        // 分页查询
        String sql = ResourceUtil.readStr("es_all_column.sql", Charset.defaultCharset());
        Statement statement = calciteConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        // 6.遍历打印查询结果集
        System.out.println(ResultSetUtil.resultString(resultSet));
        restClient.close();

    }
}
