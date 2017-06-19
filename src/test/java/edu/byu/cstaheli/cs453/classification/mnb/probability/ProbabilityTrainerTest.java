package edu.byu.cstaheli.cs453.classification.mnb.probability;

import edu.byu.cstaheli.cs453.classification.Utilities;
import edu.byu.cstaheli.cs453.classification.document.Document;
import edu.byu.cstaheli.cs453.classification.document.DocumentCollection;
import edu.byu.cstaheli.cs453.classification.mnb.document.MultinomialSet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProbabilityTrainerTest
{
    private static MultinomialSet trainingSet;
    private MultinomialNaiveBayesProbability probabilityTrainer;

    @BeforeAll
    static void setUpAll()
    {
        DocumentCollection documentCollection = Utilities.getHandoutDocumentCollection();
        List<Document> trainingSetDocuments = documentCollection.getDocuments().subList(0, 6);
        trainingSet = Utilities.getMultinomialSetFromDocuments(trainingSetDocuments);
    }

    @BeforeEach
    void setUp()
    {
        probabilityTrainer = new ProbabilityTrainer();
    }

    @Test
    void computeWordProbabilities()
    {
        WordProbabilities wordProbabilities = probabilityTrainer.computeWordProbabilities(trainingSet);
        double probability = wordProbabilities.getProbability("Rome", "Yes");
        assertEquals(7d / 22d, probability);

        probability = wordProbabilities.getProbability("Italy", "Yes");
        assertEquals(3d / 22d, probability);

        probability = wordProbabilities.getProbability("Rome", "No");
        assertEquals(4d / 31d, probability);

        probability = wordProbabilities.getProbability("Athens", "No");
        assertEquals(2d / 31d, probability);

        probability = wordProbabilities.getProbability("France", "Yes");
        assertEquals(1d / 22d, probability);
    }

    @Test
    void computeClassProbabilities()
    {
        ClassProbabilities classProbabilities = probabilityTrainer.computeClassProbabilities(trainingSet);
        double probability = classProbabilities.getProbability("Yes");
        assertEquals(1d / 2d, probability);
        probability = classProbabilities.getProbability("No");
        assertEquals(1d / 2d, probability);
    }

    @Test
    void getWordProbability()
    {
        int termFrequency = 6;
        int termsInClass = 16;
        int vocabSize = 6;
        MultinomialNaiveBayesProbability probability = new ProbabilityTrainer();
        double wordProbability = probability.calculateWordProbability(termFrequency, termsInClass, vocabSize);
        assertEquals(7d / 22d, wordProbability);

        termFrequency = 3;
        wordProbability = probability.calculateWordProbability(termFrequency, termsInClass, vocabSize);
        assertEquals(4d / 22d, wordProbability);

        termFrequency = 0;
        wordProbability = probability.calculateWordProbability(termFrequency, termsInClass, vocabSize);
        assertEquals(1d / 22d, wordProbability);

        termFrequency = 2;
        wordProbability = probability.calculateWordProbability(termFrequency, termsInClass, vocabSize);
        assertEquals(3d / 22d, wordProbability);

        termFrequency = 3;
        termsInClass = 25;
        wordProbability = probability.calculateWordProbability(termFrequency, termsInClass, vocabSize);
        assertEquals(4d / 31d, wordProbability);

        termFrequency = 5;
        wordProbability = probability.calculateWordProbability(termFrequency, termsInClass, vocabSize);
        assertEquals(6d / 31d, wordProbability);

    }

    @Test
    void getClassProbability()
    {
        int documentsWithClass = 3;
        int documents = 6;
        MultinomialNaiveBayesProbability probability = new ProbabilityTrainer();
        double classProbability = probability.calculateClassProbability(documentsWithClass, documents);
        assertEquals(3d / 6d, classProbability);
        assertEquals(1d / 2d, classProbability);
    }

}