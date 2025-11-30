package com.nntk.demo.calcite.es;

import cn.hutool.core.io.resource.ResourceUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.ElasticSearchDruidDataSourceFactory;

import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Properties;

import static com.alibaba.druid.pool.DruidDataSourceFactory.PROP_CONNECTIONPROPERTIES;
import static com.alibaba.druid.pool.DruidDataSourceFactory.PROP_URL;

public class TestEs4Nlp {

    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        properties.put(PROP_URL, "jdbc:elasticsearch://127.0.0.1:9300");
//        properties.put(PROP_CONNECTIONPROPERTIES, "cluster.name=851bf98ed24a");
        properties.put(PROP_CONNECTIONPROPERTIES, "client.transport.ignore_cluster_name=true");

//        properties.put(PROP_CONNECTIONPROPERTIES, "client.transport.ignore_cluster_name=true;xpack.security.user=elastic:5laftq1NilavFTibKOaZ");
        DruidDataSource dds = (DruidDataSource) ElasticSearchDruidDataSourceFactory.createDataSource(properties);
        Connection connection = dds.getConnection();
        String sql = ResourceUtil.readStr("es-sql.sql", Charset.defaultCharset());

        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            System.out.println(metaData.getColumnName(i));
        }

        // 打印数据
        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                Object value = resultSet.getObject(i);
                System.out.print(value + "\t");
            }
            System.out.println();
            System.out.println("----------------");
        }
        ps.close();
        connection.close();
        dds.close();

    }
}
