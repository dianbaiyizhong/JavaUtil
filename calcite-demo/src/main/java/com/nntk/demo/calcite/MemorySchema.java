package com.nntk.demo.calcite;

import cn.hutool.core.collection.CollectionUtil;
import org.apache.calcite.schema.Table;
import org.apache.calcite.schema.impl.AbstractSchema;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemorySchema extends AbstractSchema {
    public MemorySchema(Map<String, Table> tableMap) {
        this.tableMap = tableMap;
    }

    private Map<String, Table> tableMap;
//    private List<MemoryColumn> meta;
//    private List<List<Object>> source;
//
//    public MemorySchema(List<MemoryColumn> meta, List<List<Object>> source) {
//        this.meta = meta;
//        this.source = source;
//    }

    @Override
    public Map<String, Table> getTableMap() {
//        if (CollectionUtil.isEmpty(tableMap)) {
//            tableMap = new HashMap<>();
//            tableMap.put("memory", new MemoryTable(meta, source));
//        }
        return tableMap;
    }
}
