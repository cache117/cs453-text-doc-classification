package edu.byu.cstaheli.cs453.classification;

import edu.byu.cstaheli.cs453.classification.document.Document;
import edu.byu.cstaheli.cs453.classification.document.DocumentCollection;
import edu.byu.cstaheli.cs453.classification.document.EmailProcessor;
import edu.byu.cstaheli.cs453.classification.mnb.document.TestSet;
import edu.byu.cstaheli.cs453.classification.mnb.evaluation.Evaluator;
import edu.byu.cstaheli.cs453.classification.mnb.evaluation.MultinomialNaiveBayesEvaluation;
import edu.byu.cstaheli.cs453.common.util.StopWordsRemover;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The driver for project 4.
 */
public class Driver
{
    private static DocumentCollection documentCollection;
    private static StopWordsRemover stopWordsRemover;

    /**
     * Gets the instance of DocumentCollection so that all classes use the same instance.
     *
     * @return the instance of DocumentCollection.
     */
    public static DocumentCollection getDocumentCollectionInstance()
    {
        if (documentCollection == null)
        {
            documentCollection = new DocumentCollection(5);
        }
        return documentCollection;
    }

    /**
     * Gets the instance of {@link StopWordsRemover} since there is a bit of overhead in loading the list of stop words.
     *
     * @return the stop words instance.
     */
    public static StopWordsRemover getStopWordsRemoverInstance()
    {
        if (stopWordsRemover == null)
        {
            stopWordsRemover = new StopWordsRemover();
        }
        return stopWordsRemover;
    }

    /**
     * The entry point for the driver
     *
     * @param args args[0] should contain the directory that the 20NG dataset is in.
     */
    public static void main(String[] args)
    {
        Driver driver = new Driver();
        List<Document> documents = driver.readInCorpus(args[0]);
        driver.train(documents, -1);

    }

    /**
     * runs the training algorithm for the given documents with the given number of features, if any.
     *
     * @param documents        the documents to train and test on.
     * @param numberOfFeatures the number of features to use for training. -1 indicates that all should be used.
     */
    public void train(List<Document> documents, int numberOfFeatures)
    {
        if (numberOfFeatures == -1)
        {
            System.out.println("Using all features in collection");
        }
        else
        {
            System.out.format("Using %s features in collection\n", numberOfFeatures);
        }
        System.out.println("Calculating accuracy using cross-validation...");
        DocumentCollection documentCollection = getDocumentCollectionInstance();
        documentCollection.addAll(documents);
        documentCollection.shuffle(new Random());

        double sumAccuracy = 0.0;
        double trainingElapsedTime = 0.0;
        double testElapsedTime = 0.0;
        int folds = documentCollection.getFolds();
        System.out.format("Number of folds: %s\n\n", folds);
        for (int i = 0; i < folds; ++i)
        {
            double trainingStartTime = System.currentTimeMillis();
            List<Document> trainingSetDocuments = documentCollection.getTrainingSet(i);
            MultinomialNaiveBayesEvaluation evaluator = new Evaluator(trainingSetDocuments, numberOfFeatures);
            trainingElapsedTime += System.currentTimeMillis() - trainingStartTime;

            double testStartTime = System.currentTimeMillis();
            List<Document> testSetDocuments = documentCollection.getTestSet(i);
            TestSet testSet = new TestSet(testSetDocuments);
            double accuracy = evaluator.accuracyMeasure(testSet);
            testElapsedTime += System.currentTimeMillis() - testStartTime;
            sumAccuracy += accuracy;
            System.out.format("Fold: %s, Accuracy: %s\n", i, accuracy);
        }
        System.out.format("Total time to train (in seconds): %s\n", trainingElapsedTime / 1000d);
        System.out.format("Total time to test (in seconds): %s\n", testElapsedTime / 1000d);

        trainingElapsedTime /= folds;
        testElapsedTime /= folds;
        System.out.format("Average time to train (in seconds): %s\n", trainingElapsedTime / 1000d);
        System.out.format("Average time to test (in seconds): %s\n", testElapsedTime / 1000d);

        System.out.format("Mean accuracy: %s\n\n", sumAccuracy / folds);
    }

    /**
     * Reads in the corpus from the given directory.
     *
     * @param directory the directory to read from.
     * @return the list of documents parsed from the directory.
     */
    public List<Document> readInCorpus(String directory)
    {
        try (Stream<Path> paths = Files.walk(Paths.get(directory)))
        {
            return paths
                    .filter(Files::isRegularFile)
                    .map(this::readInDocumentFromFile)
                    .collect(Collectors.toList());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     * Reads the data from a path to a file into a document.
     *
     * @param path the path to a file.
     * @return the document containing the relevant file information.
     */
    private Document readInDocumentFromFile(Path path)
    {
        String fileName = path.getFileName().toString();
        String outputClass = path.getParent().getFileName().toString();
        List<String> words = EmailProcessor.readEmail(path);
        return new Document(fileName, outputClass, removeStopwords(words));
    }

    private List<String> removeStopwords(List<String> words)
    {
        StopWordsRemover stopWordsRemover = getStopWordsRemoverInstance();
        return words
                .stream()
                .filter(word -> !stopWordsRemover.contains(word))
                .collect(Collectors.toList());
    }
}
