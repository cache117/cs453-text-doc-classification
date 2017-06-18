package edu.byu.cstaheli.cs453.classification.mnb.document;

import edu.byu.cstaheli.cs453.classification.Utilities;
import edu.byu.cstaheli.cs453.classification.document.Document;
import edu.byu.cstaheli.cs453.classification.document.DocumentCollection;
import edu.byu.cstaheli.cs453.classification.mnb.classification.Classifier;
import edu.byu.cstaheli.cs453.classification.mnb.classification.MultinomialNaiveBayesClassification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MultinomialSetTest
{
    private static MultinomialSet trainingSet;

    @BeforeAll
    static void setUpAll()
    {
        DocumentCollection documentCollection = Utilities.getHandoutDocumentCollection();
        List<Document> trainingSetDocuments = documentCollection.getDocuments().subList(0, 6);
        trainingSet = Utilities.getMultinomialSetFromDocuments(trainingSetDocuments);
    }

    @Test
    void getNumberOfTermsWithClass()
    {
        int numberOfTermsWithClass = trainingSet.getNumberOfTermsWithClass("Yes");
        assertEquals(16, numberOfTermsWithClass);

        numberOfTermsWithClass = trainingSet.getNumberOfTermsWithClass("No");
        assertEquals(25, numberOfTermsWithClass);

        numberOfTermsWithClass = trainingSet.getNumberOfTermsWithClass("Invalid");
        assertEquals(0, numberOfTermsWithClass);
    }

    @Test
    void getVocabularySize()
    {
        int vocabSize = trainingSet.getVocabularySize();
        assertEquals(6, vocabSize);
    }

    @Test
    void getTermFrequencyInClass()
    {
        int termFrequencyInClass = trainingSet.getTermFrequencyInClass("Rome", "Yes");
        assertEquals(6, termFrequencyInClass);

        termFrequencyInClass = trainingSet.getTermFrequencyInClass("Rome", "No");
        assertEquals(3, termFrequencyInClass);

        termFrequencyInClass = trainingSet.getTermFrequencyInClass("Rome", "Inavlid");
        assertEquals(0, termFrequencyInClass);

        termFrequencyInClass = trainingSet.getTermFrequencyInClass("Invalid", "Yes");
        assertEquals(0, termFrequencyInClass);

        termFrequencyInClass = trainingSet.getTermFrequencyInClass("France", "Yes");
        assertEquals(0, termFrequencyInClass);

        termFrequencyInClass = trainingSet.getTermFrequencyInClass("Italy", "No");
        assertEquals(5, termFrequencyInClass);

        termFrequencyInClass = trainingSet.getTermFrequencyInClass("Paris", "Yes");
        assertEquals(3, termFrequencyInClass);

        termFrequencyInClass = trainingSet.getTermFrequencyInClass("Paris", "No");
        assertEquals(3, termFrequencyInClass);

        termFrequencyInClass = trainingSet.getTermFrequencyInClass("Athens", "Yes");
        assertEquals(5, termFrequencyInClass);

        termFrequencyInClass = trainingSet.getTermFrequencyInClass("Athens", "No");
        assertEquals(1, termFrequencyInClass);
    }

    @Test
    void getNumberOfDocumentsWithClass()
    {
        int numberOfDocumentsWithClass = trainingSet.getNumberOfDocumentsWithClass("Yes");
        assertEquals(3, numberOfDocumentsWithClass);

        numberOfDocumentsWithClass = trainingSet.getNumberOfDocumentsWithClass("No");
        assertEquals(3, numberOfDocumentsWithClass);
    }

    @Test
    void getNumberOfDocuments()
    {
        int numberOfDocuments = trainingSet.getNumberOfDocuments();
        assertEquals(6, numberOfDocuments);
    }
}