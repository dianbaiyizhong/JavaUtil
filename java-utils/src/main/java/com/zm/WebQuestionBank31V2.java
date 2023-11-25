package com.zm;

import com.alibaba.fastjson2.JSON;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

/**
 * 利用jsoup抓取网页表格
 */
public class WebQuestionBank31V2 {
    private static final String DRIVER_PATH = "D:\\software\\chromedriver.exe";

    static {
        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);

    }
    public static void main(String[] args) throws IOException {

        JSON.parseObject("",O)
        // 1.获取连接
        String url = "https://w.163.com/h5/xyq/dtk/index.html#/jiaoshijie";
        Document document = Jsoup.connect(url).get();


        WebDriver driver = new ChromeDriver();
        driver.get(url);

        String pageSource = driver.getPageSource();


        System.out.println(pageSource);
    }


}
