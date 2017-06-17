package edu.byu.cstaheli.cs453.classification;

import edu.byu.cstaheli.cs453.classification.document.Document;
import edu.byu.cstaheli.cs453.classification.document.DocumentCollection;
import edu.byu.cstaheli.cs453.classification.document.EmailProcessor;
import edu.byu.cstaheli.cs453.common.util.StopWordsRemover;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The driver for project 4.
 */
public class Driver
{
    private static DocumentCollection documentCollection;
    private static StopWordsRemover stopWordsRemover;

    public static DocumentCollection getDocumentCollectionInstance()
    {
        if (documentCollection == null)
        {
            documentCollection = new DocumentCollection(5);
        }
        return documentCollection;
    }

    public static StopWordsRemover getStopWordsRemoverInstance()
    {
        if (stopWordsRemover == null)
        {
            stopWordsRemover = new StopWordsRemover();
        }
        return stopWordsRemover;
    }

    public static void main(String[] args)
    {
        Driver driver = new Driver();
        driver.readInCorpus(args[0]);
    }

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

    private Document readInDocumentFromFile(Path path)
    {
        String fileName = path.getFileName().toString();
        String outputClass = path.getParent().getFileName().toString();
        List<String> words = EmailProcessor.readEmail(path);
        return new Document(fileName, outputClass, words);
    }
}
