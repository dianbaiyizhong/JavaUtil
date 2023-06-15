package com.zm.utils.csv;

import com.opencsv.CSVWriter;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

public class Test {
    public static void main(String[] args) throws IOException {
//        StringWriter writer = new StringWriter();
//        CSVWriter csvWriter = new CSVWriter(writer);
//        String[] header = {"Name", "Age", "Country"};
//        csvWriter.writeNext(header);
//        csvWriter.writeNext(new String[]{"John", "25", "USA"});
//        csvWriter.writeNext(new String[]{"Tom", "30", "China"});
//        csvWriter.close();
//        System.out.println(writer.toString());

        try {
            // 创建工作簿
            WritableWorkbook workbook = Workbook.createWorkbook(new File("test.xls"));
            // 创建工作表
            WritableSheet sheet = workbook.createSheet("Sheet1", 0);
            // 添加数据
            Label label = new Label(0, 0, "Hello World");
            sheet.addCell(label);
            // 写入文件
            workbook.write();
            // 关闭工作簿
            workbook.close();
        } catch (IOException | WriteException e) {
            e.printStackTrace();
        }

    }

}
