package edu.byu.cstaheli.cs453.classification;

import edu.byu.cstaheli.cs453.classification.document.Document;
import edu.byu.cstaheli.cs453.classification.document.DocumentCollection;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DriverTest
{
    @Test
    void train()
    {
        String basePath = "src/test/resources/20NG";
        Driver driver = new Driver();

        DocumentCollection documentCollection = Utilities.getDocumentCollection();
        driver.train(documentCollection.getDocuments());
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