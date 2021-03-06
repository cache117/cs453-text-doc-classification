package edu.byu.cstaheli.cs453.classification;

import edu.byu.cstaheli.cs453.classification.document.Document;
import edu.byu.cstaheli.cs453.classification.document.DocumentCollection;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DriverTest
{
    @Test
    void train()
    {
        Driver driver = new Driver();
        List<Document> documents;
        String basePath;
        DocumentCollection documentCollection = Utilities.getHandoutDocumentCollection();
//        driver.train(documentCollection.getDocuments(), -1);
//
//        documentCollection.clear();
//        basePath = "src/test/resources/20NG";
//        documents = driver.readInCorpus(basePath);
//        driver.train(documents, -1);

        documentCollection.clear();
        basePath = "src/main/resources/20NG";
        documents = driver.readInCorpus(basePath);
        driver.train(documents, -1);
    }

    @Test
    void readInCorpus()
    {
        String basePath = "src/test/resources/20NG";
        Driver driver = new Driver();
        List<Document> documents = driver.readInCorpus(basePath);
        assertEquals(3 * 10, documents.size());

        basePath = "src/main/resources/20NG";
        documents = driver.readInCorpus(basePath);
        //There are actually 3 less than 10000
        assertEquals(9997, documents.size());
    }

}