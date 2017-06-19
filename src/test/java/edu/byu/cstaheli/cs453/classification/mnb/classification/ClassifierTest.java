package edu.byu.cstaheli.cs453.classification.mnb.classification;

import edu.byu.cstaheli.cs453.classification.Driver;
import edu.byu.cstaheli.cs453.classification.Utilities;
import edu.byu.cstaheli.cs453.classification.document.Document;
import edu.byu.cstaheli.cs453.classification.document.DocumentCollection;
import edu.byu.cstaheli.cs453.classification.mnb.document.MnbDocument;
import edu.byu.cstaheli.cs453.classification.mnb.document.MultinomialSet;
import edu.byu.cstaheli.cs453.classification.mnb.probability.ClassProbabilities;
import edu.byu.cstaheli.cs453.classification.mnb.probability.MultinomialNaiveBayesProbability;
import edu.byu.cstaheli.cs453.classification.mnb.probability.ProbabilityTrainer;
import edu.byu.cstaheli.cs453.classification.mnb.probability.WordProbabilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by cstaheli on 6/18/2017.
 */
class ClassifierTest
{
    private static Classifier classifier;
    private static MultinomialSet trainingSet;

    @BeforeEach
    void setUp()
    {
        DocumentCollection documentCollection = Utilities.getHandoutDocumentCollection();
        List<Document> trainingSetDocuments = documentCollection.getDocuments().subList(0, 6);
        trainingSet = Utilities.getMultinomialSetFromDocuments(trainingSetDocuments);
        MultinomialNaiveBayesProbability probabilityTrainer = new ProbabilityTrainer();
        WordProbabilities wordProbabilities = probabilityTrainer.computeWordProbabilities(trainingSet);
        ClassProbabilities classProbabilities = probabilityTrainer.computeClassProbabilities(trainingSet);
        classifier = new Classifier();
        classifier.setWordProbabilities(wordProbabilities);
        classifier.setClassProbabilities(classProbabilities);
    }

    @Test
    void calculateInformationGain()
    {

    }

    @Test
    void featureSelection()
    {
        Classifier classifier = new Classifier();
        List<Document> documents;
        Set<String> features;
        String basePath;
//        documents = Utilities.getSlidesDocumentCollection().getDocuments();
//        features = classifier.featureSelection(documents, 3);
//        assertEquals(3, features.size());
//        assertTrue(features.contains("dinner"));
//        assertFalse(features.contains("the"));

//        basePath = "src/test/resources/20NG";
//        documents = new Driver().readInCorpus(basePath);
//        features = classifier.featureSelection(documents, 1600);
//        assertEquals(1600, features.size());

        basePath = "src/main/resources/20NG";
        documents = new Driver().readInCorpus(basePath);
        features = classifier.featureSelection(documents, 1600);
        assertEquals(1600, features.size());
    }

    @Test
    void label()
    {
        Document document = Utilities.getHandoutDocumentCollection().getDocuments().get(7 - 1);
        String label = classifier.label(document);
        assertEquals("No", label);
    }

    @Test
    void getDocumentInClassProbabilities()
    {
        MnbDocument doc7 = new MnbDocument(Utilities.getHandoutDocumentCollection().getDocuments().get(7 - 1));
        Map<String, Double> documentInClassProbabilities = classifier.getDocumentInClassProbabilities(doc7);
        assertEquals(2, documentInClassProbabilities.size());
        double probability = documentInClassProbabilities.get("Yes");
        assertEquals(55566d / Math.pow(22, 11), probability, 0.00000000001);

        probability = documentInClassProbabilities.get("No");
        assertEquals((80621568d / Math.pow(31, 11)), probability, 0.00000000001);
    }

    @Test
    void pow()
    {
        double result = Classifier.pow(1, 0);
        assertEquals(1, result);

        result = Classifier.pow(1, 1);
        assertEquals(1, result);

        result = Classifier.pow(0, 0);
        assertEquals(0, result);

        result = Classifier.pow(33, 11);
        result = Math.pow(31, 11);
        assertEquals(25408476896404831d, result);
    }
}