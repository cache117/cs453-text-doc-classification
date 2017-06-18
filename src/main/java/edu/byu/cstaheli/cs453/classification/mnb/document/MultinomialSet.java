package edu.byu.cstaheli.cs453.classification.mnb.document;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import edu.byu.cstaheli.cs453.classification.document.Document;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Represents a collection of MnbDocuments, which represent the multinomial naive bayes statistics for a document.
 */
public class MultinomialSet
{
    private Multimap<String, MnbDocument> documents;
    private Set<String> selectedFeatures;

    /**
     * Creates an empty multinomial collection.
     *
     * @param selectedFeatures the list of words to use in the set.
     */
    public MultinomialSet(Set<String> selectedFeatures)
    {
        documents = ArrayListMultimap.create();
        this.selectedFeatures = selectedFeatures;
    }

    /**
     * Adds a document to the set.
     *
     * @param document the document to add.
     */
    public void add(Document document)
    {
        documents.put(document.getOutputClass(), new MnbDocument(document, selectedFeatures));
    }

    /**
     * Adds all of the given documents to the set. The documents will be transformed into a {@link MnbDocument}.
     *
     * @param documents the documents to add.
     */
    public void add(List<Document> documents)
    {
        documents.forEach(this::add);
    }

    /**
     * Counts the number of terms that are associated with the given class.
     *
     * @param outputClass the given class.
     * @return the number of terms that are associated with the given class.
     */
    public int getNumberOfTermsWithClass(String outputClass)
    {
        Collection<MnbDocument> set = getDocumentsWithClass(outputClass);
        if (!set.isEmpty())
        {
            return set
                    .stream()
                    .mapToInt(MnbDocument::getNumberOfTerms)
                    .sum();
        }
        else
        {
            return 0;
        }
    }

    /**
     * Gets all of the documents that belong to the given class.
     *
     * @param outputClass the class to get the associated classes for.
     * @return the documents that belong to the given class.
     */
    private Collection<MnbDocument> getDocumentsWithClass(String outputClass)
    {
        return documents.get(outputClass);
    }

    /**
     * Returns all of the words that are available in the Multinomial set. The documents may or may not contain these
     * words, but these are all of the selected features for training.
     *
     * @return the set of words in the multinomial set.
     */
    public Set<String> getWordsInSet()
    {
        return selectedFeatures;
    }

    /**
     * Gets the total size of the vocabulary in the set.
     *
     * @return the total size of the vocabulary in the set.
     */
    public int getVocabularySize()
    {
        return documents
                .values()
                .stream()
                .mapToInt(MnbDocument::getNumberOfTerms)
                .sum();
    }

    /**
     * Gets a set of all of the classes present in the multinomial set. This won't necessarily be all of the classes
     * in C, but very likely will be.
     *
     * @return the set of classes in the multinomial set of documents.
     */
    public Set<String> getClasses()
    {
        return documents.keySet();
    }

    /**
     * Gets the number of terms w in Class c.
     *
     * @param word        the term to check.
     * @param outputClass the class to check.
     * @return the frequency of w in c.
     */
    public int getTermFrequencyInClass(String word, String outputClass)
    {
        Collection<MnbDocument> set = getDocumentsWithClass(outputClass);
        if (!set.isEmpty())
        {
            return set
                    .stream()
                    .mapToInt(document -> document.getTermFrequency(word))
                    .sum();
        }
        else
        {
            return 0;
        }
    }

    /**
     * Gets the number of documents with the given class.
     *
     * @param outputClass the class to check.
     * @return the number of documents with the given class.
     */
    public int getNumberOfDocumentsWithClass(String outputClass)
    {
        return getDocumentsWithClass(outputClass).size();
    }

    /**
     * Gets the number of documents that exist in the set.
     *
     * @return the number of documents that exist in the set.
     */
    public int getNumberOfDocuments()
    {
        return documents.size();
    }
}
