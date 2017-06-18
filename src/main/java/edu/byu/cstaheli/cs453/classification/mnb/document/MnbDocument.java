package edu.byu.cstaheli.cs453.classification.mnb.document;

import edu.byu.cstaheli.cs453.classification.document.Document;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A multinomial representation of a document, containing the words and their frequencies, as well as the document id
 * and output class related to the document.
 */
public class MnbDocument
{
    private String documentId;
    private String outputClass;
    private Map<String, Long> words;

    public MnbDocument(String documentId, String outputClass, List<String> words)
    {
        this.documentId = documentId;
        this.outputClass = outputClass;
        this.words = words
                .stream()
                .collect(Collectors.groupingBy(o -> o, Collectors.counting()));
    }

    public MnbDocument(Document document)
    {
        this(document.getDocumentId(), document.getOutputClass(), document.getWords());
    }

    /**
     * Gets the number of times the given word appears.
     * @param word the word to get the frequency of.
     * @return the frequency of the word in this document.
     */
    public int getTermFrequency(String word)
    {
        return Math.toIntExact(words.get(word));
    }

    /**
     * Gets the total number of terms in the document.
     * @return he total number of terms in the document.
     */
    public int getNumberOfTerms()
    {
        return words.size();
    }

    /**
     * Gets all of the unique terms in the document.
     * @return all of the unique terms in the document.
     */
    public Set<String> getUniqueTerms()
    {
        return words.keySet();
    }

    /**
     * Gets the document id.
     * @return the document id.
     */
    public String getDocumentId()
    {
        return documentId;
    }

    /**
     * Gets the output class.
     * @return the output class.
     */
    public String getOutputClass()
    {
        return outputClass;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MnbDocument that = (MnbDocument) o;

        if (!documentId.equals(that.documentId)) return false;
        if (!outputClass.equals(that.outputClass)) return false;
        return words.equals(that.words);
    }

    @Override
    public int hashCode()
    {
        int result = documentId.hashCode();
        result = 31 * result + outputClass.hashCode();
        result = 31 * result + words.hashCode();
        return result;
    }

    @Override
    public String toString()
    {
        return String.format("Doc: %s - %s", documentId, outputClass);
    }
}
