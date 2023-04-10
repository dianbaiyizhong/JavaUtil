package com.nntk.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;

public class LookToken {

    public static void main(String[] args) {
        Analyzer analyzer = new IKAnalyzer(true);

//        Analyzer analyzer = new StandardAnalyzer();
        getTokens(analyzer, "以画竹子著称的书画家郑板桥是哪个朝代的人");

    }


    private static void getTokens(Analyzer analyzer, String msg) {
        try {
            TokenStream tokenStream = analyzer.tokenStream("question", new StringReader(msg));
            tokenStream.reset();
            printTokens(analyzer.getClass().getSimpleName(), tokenStream);
            tokenStream.end();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void printTokens(String analyzerType, TokenStream tokenStream) {
        CharTermAttribute ta = tokenStream.addAttribute(CharTermAttribute.class);
        StringBuffer result = new StringBuffer();
        try {
            while (tokenStream.incrementToken()) {
                if (result.length() > 0) {
                    result.append(",");
                }
                result.append("[" + ta.toString() + "]");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(analyzerType + "->" + result.toString());
    }

}
