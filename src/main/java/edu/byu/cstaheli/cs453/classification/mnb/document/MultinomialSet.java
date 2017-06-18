package edu.byu.cstaheli.cs453.classification.mnb.document;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import edu.byu.cstaheli.cs453.classification.document.Document;

import java.util.Collection;
import java.util.List;

/**
 * Represents a collection of MnbDocuments, which represent the multinomial naive bayes statistics for a document.
 */
public class MultinomialSet
{
    private Multimap<String, MnbDocument> documents;

    /**
     * Creates an empty multinomial collection.
     */
    public MultinomialSet()
    {
        documents = ArrayListMultimap.create();
    }

    /**
     * Adds a document to the set.
     * @param document the document to add.
     */
    public void add(Document document)
    {
        documents.put(document.getOutputClass(), new MnbDocument(document));
    }

    /**
     * Adds all of the given documents to the set. The documents will be transformed into a {@link MnbDocument}.
     * @param documents the documents to add.
     */
    public void add(List<Document> documents)
    {
        documents.forEach(this::add);
    }

    /**
     * Counts the number of terms that are associated with the given class.
     * @param outputClass the given class.
     * @return the number of terms that are associated with the given class.
     */
    public int getNumberOfTermsWithClass(String outputClass)
    {
        Collection<MnbDocument> set = this.documents.get(outputClass);
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
     * Gets the total size of the vocabulary in the set.
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
}
