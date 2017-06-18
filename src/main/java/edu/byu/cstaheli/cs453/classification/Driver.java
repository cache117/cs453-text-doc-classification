package edu.byu.cstaheli.cs453.classification;

import edu.byu.cstaheli.cs453.classification.document.Document;
import edu.byu.cstaheli.cs453.classification.document.DocumentCollection;
import edu.byu.cstaheli.cs453.classification.document.EmailProcessor;
import edu.byu.cstaheli.cs453.classification.mnb.document.MultinomialSet;
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
     * @param args args[0] should contain the directory that the 20NG dataset is in.
     */
    public static void main(String[] args)
    {
        Driver driver = new Driver();
        List<Document> documents = driver.readInCorpus(args[0]);
        double startTime = System.currentTimeMillis();
        driver.train(documents);
        double elapsedTime = System.currentTimeMillis() - startTime;
    }

    public void train(List<Document> documents)
    {
        DocumentCollection documentCollection = getDocumentCollectionInstance();
        documentCollection.addAll(documents);
        documentCollection.shuffle(new Random());

        for (int i = 0; i < documentCollection.getFolds(); ++i)
        {
            List<Document> trainingSetDocuments = documentCollection.getTrainingSet(i);
            MultinomialSet trainingSet = new MultinomialSet();
            trainingSet.add(trainingSetDocuments);

            List<Document> testSetDocuments = documentCollection.getTestSet(i);
        }
    }

    /**
     * Reads in the corpus from the given directory.
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
     * @param path the path to a file.
     * @return the document containing the relevant file information.
     */
    private Document readInDocumentFromFile(Path path)
    {
        String fileName = path.getFileName().toString();
        String outputClass = path.getParent().getFileName().toString();
        List<String> words = EmailProcessor.readEmail(path);
        return new Document(fileName, outputClass, words);
    }
}
