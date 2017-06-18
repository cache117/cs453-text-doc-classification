package edu.byu.cstaheli.cs453.classification.document;

import java.util.*;

/**
 * Represents all of the documents in a collection. This class has methods to get the training and test sets using a
 * N-fold cross validation strategy.
 */
public class DocumentCollection
{
    private int folds;
    private List<Document> documents;

    /**
     * Creates a document collection with no documents in it and a number of folds to use for the N-fold cross validation
     * strategy used for getting the training and test sets.
     *
     * @param folds the number of folds to use for the N-fold cross validation strategy.
     */
    public DocumentCollection(int folds)
    {
        this.folds = folds;
        documents = new ArrayList<>();
    }

    /**
     * Gets all of the documents in the collection.
     *
     * @return all of the documents in the collection.
     */
    public List<Document> getDocuments()
    {
        return documents;
    }

    /**
     * Adds the given document to the collection.
     *
     * @param document the document to add to the collection.
     */
    public void add(Document document)
    {
        documents.add(document);
    }

    /**
     * Adds the given documents to the collection.
     *
     * @param c the documents to add.
     */
    public void addAll(Collection<? extends Document> c)
    {
        documents.addAll(c);
    }

    public void clear()
    {
        documents.clear();
    }

    /**
     * Shuffles the documents randomly. This is used to ensure a different training and test set are obtained.
     */
    public void shuffle(Random random)
    {
        Collections.shuffle(documents, random);
    }

    /**
     * Gets the number of documents in the collection.
     *
     * @return the number of documents in the collection.
     */
    public int size()
    {
        return documents.size();
    }

    /**
     * Gets the training set from the documents using a N-cross fold validation strategy. This will contain all of the
     * documents that are not part of the Nth fold.
     *
     * @param foldIteration which N the algorithm is currently at.
     * @return the training set from the documents.
     */
    public List<Document> getTrainingSet(int foldIteration)
    {
        int begin = foldIteration * size() / folds;
        int end = (foldIteration + 1) * size() / folds;
        List<Document> trainingSet = new ArrayList<>(documents.subList(0, begin));
        trainingSet.addAll(documents.subList(end, size()));
        return trainingSet;
    }

    /**
     * Gets the test set from the documents using a N-cross fold validation strategy. This will contain all of the
     * documents that are part of the Nth fold.
     *
     * @param foldIteration which N the algorithm is currently at.
     * @return the test set from the documents.
     */
    public List<Document> getTestSet(int foldIteration)
    {
        int begin = foldIteration * size() / folds;
        int end = (foldIteration + 1) * size() / folds;
        return new ArrayList<>(documents.subList(begin, end));
    }

    public int getFolds()
    {
        return folds;
    }

    public int getNumberOfDistinctWords()
    {
        return getNumberOfDistinctWords(documents);
    }

    public static int getNumberOfDistinctWords(List<Document> documents)
    {
        return documents
                .stream()
                .mapToInt(Document::getNumberOfDistinctWords)
                .sum();
    }
}
