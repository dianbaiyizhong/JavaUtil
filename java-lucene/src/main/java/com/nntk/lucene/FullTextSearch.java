package com.nntk.lucene;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
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
import org.apache.lucene.search.TopDocs;
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

        Directory directory = new ByteBuffersDirectory();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(directory, config);
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

        IndexReader reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);


        String keyword = "礼部考题，已答1题，答对1题。长安城中有一处地方可以为人疗伤，这个地方是";
        Query query = new QueryParser("question", analyzer).parse(QueryParser.escape(keyword));


        int numberPerPage = 10;
        System.out.printf("查询关键字是：\"%s\"%n", keyword);
        ScoreDoc[] hits = searcher.search(query, numberPerPage).scoreDocs;


        for (int i = 0; i < hits.length; i++) {
            ScoreDoc hit = hits[i];
            Document doc = searcher.doc(hit.doc);
            System.out.println(hit.score + "====" + doc.get("question") + "=====" + doc.get("answer"));
        }
        System.out.println(hits.length);
    }


}
