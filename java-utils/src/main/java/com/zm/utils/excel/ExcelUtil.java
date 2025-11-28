package com.zm.utils.excel;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.ZipUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.excel.write.metadata.fill.FillWrapper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URLEncoder;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Slf4j
public class ExcelUtil {

    public static void main(String[] args) {

        List<OrderData> orderDataList = new ArrayList<>();
        orderDataList.add(OrderData.builder()
                .orderNo("202310010001")
                .goodsList(Lists.newArrayList(Goods.builder()
                        .name("商品1")
                        .build()))

                .build());


        Path tempZipFilePath = null;
        Path tempDir = null;
        InputStream zipFileInputStream = null;

        try (InputStream templateInputStream = ResourceUtil.getStream("order_template.xlsx");
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            if (templateInputStream == null) {
                throw new RuntimeException("获取模版文件异常");
            }

            IOUtils.copy(templateInputStream, outputStream);

            tempDir = Files.createTempDirectory("1_export_dir");
            int partitionSize = 5;
            List<List<OrderData>> partitionedOrderData = Lists.partition(orderDataList, partitionSize);

            Path finalTempDir = tempDir;
            CompletableFuture[] salesOrderCf = partitionedOrderData.stream()
                    .map(orderDataSubList -> CompletableFuture.supplyAsync(() -> {
                                return orderDataSubList.stream()
                                        .map(orderData -> exportExcelToFile(finalTempDir, outputStream, orderData))
                                        .collect(Collectors.toList());
                            }, Executors.newFixedThreadPool(5))
                            .exceptionally(e -> {
                                throw new RuntimeException("导出 Excel 异常");
                            }))
                    .toArray(CompletableFuture[]::new);

            CompletableFuture.allOf(salesOrderCf).get(3, TimeUnit.MINUTES);

            tempZipFilePath = Files.createTempFile("1_export_zip", ".zip");
            System.out.println(tempZipFilePath);
            ZipUtil.zip(tempDir.toString(), tempZipFilePath.toString());

        } catch (Exception e) {
            log.error("导出异常", e);
            throw new RuntimeException("导出异常，请稍后重试");
        } finally {
//            try {
//                if (zipFileInputStream != null) {
//                    zipFileInputStream.close();
//                }
//                if (tempDir != null) {
//                    Files.walkFileTree(tempDir, new SimpleFileVisitor<Path>() {
//                        @Override
//                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//                            Files.deleteIfExists(file);
//                            return FileVisitResult.CONTINUE;
//                        }
//
//                        @Override
//                        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
//                            Files.deleteIfExists(dir);
//                            return FileVisitResult.CONTINUE;
//                        }
//                    });
//                }
//                if (tempZipFilePath != null) {
//                    Files.deleteIfExists(tempZipFilePath);
//                }
//            } catch (Exception e) {
//                log.error("资源清理失败", e);
//            }
        }


    }

    private static Path exportExcelToFile(Path temporaryDir, ByteArrayOutputStream templateOutputStream, OrderData data) {
        Path temporaryFilePath = null;
        try {
            temporaryFilePath = Files.createTempFile(temporaryDir, data.getOrderNo(), ".xlsx");
        } catch (IOException e) {
            throw new RuntimeException("创建 Excel 临时文件失败", e);
        }

        try (InputStream templateInputStream = new ByteArrayInputStream(templateOutputStream.toByteArray());
             OutputStream temporaryFileOs = Files.newOutputStream(temporaryFilePath);
             BufferedOutputStream tempOutStream = new BufferedOutputStream(temporaryFileOs)) {

            ExcelWriter excelWriter = EasyExcel.write(tempOutStream, OrderData.class)
                    .withTemplate(templateInputStream)
                    .excelType(ExcelTypeEnum.XLSX)
                    .build();

            WriteSheet writeSheet = EasyExcel.writerSheet().build();
            FillConfig fillConfig = FillConfig.builder().forceNewRow(true).build();

            excelWriter.fill(new FillWrapper("goods", data.getGoodsList()), fillConfig, writeSheet);
            excelWriter.fill(data, writeSheet);
            excelWriter.finish();

            return temporaryFilePath;

        } catch (Exception e) {
            throw new RuntimeException("导出 Excel 文件失败", e);
        }
    }
}
