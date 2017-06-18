package edu.byu.cstaheli.cs453.classification;

import edu.byu.cstaheli.cs453.classification.document.Document;
import edu.byu.cstaheli.cs453.classification.document.DocumentCollection;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by cstaheli on 6/17/2017.
 */
public class Utilities
{
    public static DocumentCollection getDocumentCollection()
    {
        DocumentCollection documentCollection = Driver.getDocumentCollectionInstance();
        documentCollection.clear();
        List<String> words1 = Arrays.asList("the", "the");
        List<String> words2 = Arrays.asList("cheap", "cheap", "cheap", "banking", "the");
        List<String> words3 = Collections.singletonList("the");
        List<String> words4 = Arrays.asList("cheap", "cheap", "banking", "banking", "banking", "the", "the");
        List<String> words5 = Arrays.asList("cheap", "cheap", "cheap", "cheap", "cheap", "buy", "buy", "the");
        List<String> words6 = Arrays.asList("banking", "the");
        List<String> words7 = Arrays.asList("buy", "banking", "the");
        List<String> words8 = Collections.singletonList("the");
        List<String> words9 = Collections.singletonList("the");
        List<String> words10 = Arrays.asList("cheap", "buy", "dinner", "the", "the");

        Document document1 = new Document("1", "not spam", words1);
        documentCollection.add(document1);
        Document document2 = new Document("2", "spam", words2);
        documentCollection.add(document2);
        Document document3 = new Document("3", "not spam", words3);
        documentCollection.add(document3);
        Document document4 = new Document("4", "spam", words4);
        documentCollection.add(document4);
        Document document5 = new Document("5", "spam", words5);
        documentCollection.add(document5);
        Document document6 = new Document("6", "not spam", words6);
        documentCollection.add(document6);
        Document document7 = new Document("7", "not spam", words7);
        documentCollection.add(document7);
        Document document8 = new Document("8", "not spam", words8);
        documentCollection.add(document8);
        Document document9 = new Document("9", "not spam", words9);
        documentCollection.add(document9);
        Document document10 = new Document("10", "not spam", words10);
        documentCollection.add(document10);
        return documentCollection;
    }
}