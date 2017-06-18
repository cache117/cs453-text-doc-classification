package edu.byu.cstaheli.cs453.classification.document;

import edu.byu.cstaheli.cs453.classification.Driver;
import edu.byu.cstaheli.cs453.classification.Utilities;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by cstaheli on 6/17/2017.
 */
class DocumentCollectionTest
{
    private DocumentCollection documentCollection;

    @BeforeEach
    void setUp()
    {
        documentCollection = Utilities.getDocumentCollection();
    }

    @Test
    void getTrainingSet()
    {
        List<Document> trainingSet = documentCollection.getTrainingSet(1);
        assertEquals(8, trainingSet.size());

        documentCollection.clear();
        documentCollection.addAll(new Driver().readInCorpus("src/main/resources/20NG"));
        trainingSet = documentCollection.getTrainingSet(1);
        assertEquals(7998, trainingSet.size());
    }

    @Test
    void getTestSet()
    {
        List<Document> testSet = documentCollection.getTestSet(1);
        assertEquals(2, testSet.size());
        documentCollection.addAll(new Driver().readInCorpus("src/main/resources/20NG"));
        testSet = documentCollection.getTestSet(1);
        assertEquals(2001, testSet.size());
    }

}