package com.nntk.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.ByteBuffersDirectory;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;

public class FullTextSearch {

    public static void main(String[] args) throws IOException, ParseException {


        Analyzer analyzer = new IKAnalyzer(false);

//        Analyzer analyzer = new StandardAnalyzer();
        Directory index = new ByteBuffersDirectory();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(index, config);
        Document doc = new Document();
        doc.add(new TextField("question", "以画竹子著称的书画家郑板桥是哪个朝代的人", Field.Store.YES));
        writer.addDocument(doc);
        writer.close();


        String keyword = "画家";
        Query query = new QueryParser("question", analyzer).parse(keyword);


        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        int numberPerPage = 1000;
        System.out.printf("查询关键字是：\"%s\"%n", keyword);
        ScoreDoc[] hits = searcher.search(query, numberPerPage).scoreDocs;

        System.out.println(hits.length);

    }
}
