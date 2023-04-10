package com.nntk.lucene;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.ByteBuffersDirectory;
import org.apache.lucene.store.Directory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class FullTextSearch {

    public static void main(String[] args) throws IOException, ParseException, SQLException {
//        Analyzer analyzer = new StandardAnalyzer();
        Analyzer analyzer = new IKAnalyzer(true);

        Directory index = new ByteBuffersDirectory();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(index, config);
        List<Entity> allBank = Db.use().query("select * from t_question_bank");

        for (int i = 0; i < allBank.size(); i++) {
            Entity entity = allBank.get(i);
            Document doc = new Document();
            doc.add(new TextField("id", entity.getStr("id"), Field.Store.YES));
            doc.add(new TextField("question", entity.getStr("question"), Field.Store.YES));
            doc.add(new TextField("answer", entity.getStr("answer"), Field.Store.YES));
            writer.addDocument(doc);
        }
        writer.close();

        String keyword = "御前科举大赛第3关：这一关考的是古代工艺。题目：君山银针属于";
        Query query = new QueryParser("question", analyzer).parse(keyword);


        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        int numberPerPage = 1000;
        System.out.printf("查询关键字是：\"%s\"%n", keyword);
        ScoreDoc[] hits = searcher.search(query, numberPerPage).scoreDocs;

        for (int i = 0; i < hits.length; i++) {
            ScoreDoc hit = hits[i];
            Document doc = searcher.doc(hit.doc);
            System.out.println(hit.score + "====" + doc.getField("question"));
        }
        System.out.println(hits.length);
    }


}
