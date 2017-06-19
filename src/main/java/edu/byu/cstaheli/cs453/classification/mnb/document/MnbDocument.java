package edu.byu.cstaheli.cs453.classification.mnb.document;

import edu.byu.cstaheli.cs453.classification.document.Document;
import edu.byu.cstaheli.cs453.classification.mnb.classification.MultinomialNaiveBayesFeatureSelector;

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
    private Map<String, Integer> words;

    /**
     * Creates a multinomial representation of a document given the document id, class, and list of words to use from
     * the document. Note, the words should be only the words that are wanted. If only a list of selected features
     * should be used, use the {@link MnbDocument#MnbDocument(Document, Set)} constructor instead, which provides the
     * actual document and the selected features to use from that document.
     *
     * @param documentId  the id of the document.
     * @param outputClass the class associated with the document.
     * @param words       the list of words to use from the document. All will be used, without removing any.
     */
    public MnbDocument(String documentId, String outputClass, List<String> words)
    {
        this.documentId = documentId;
        this.outputClass = outputClass;
        this.words = words
                .stream()
                .collect(Collectors.groupingBy(o -> o, Collectors.reducing(0, e -> 1, Integer::sum)));
    }

    /**
     * Creates a Multinomial representation of a document given the document itself and a set of the selected features
     * or words to use from the document. This list could be generated by
     * {@link MultinomialNaiveBayesFeatureSelector#featureSelection(List, int)}.
     *
     * @param document         the document to create the mnb representation from
     * @param selectedFeatures the selected features to use. Only the words that exist in this set will be used in the
     *                         mnb document representation. All others will be filtered out.
     * @see MnbDocument#getWordsOverlap(Set, List)
     */
    public MnbDocument(Document document, Set<String> selectedFeatures)
    {
        this(document.getDocumentId(), document.getOutputClass(), getWordsOverlap(selectedFeatures, document.getWords()));
    }

    /**
     * Creates a Multinomial representation of a document given the document itself. This does no feature selection.
     *
     * @param document the document to create from.
     */
    public MnbDocument(Document document)
    {
        this(document.getDocumentId(), document.getOutputClass(), document.getWords());
    }

    /**
     * Determines which words from <code>words</code> are also in <code>selectedFeatures</code> and returns a list
     * containing only those words. This is essentially the intersect between the set and the list.
     *
     * @param selectedFeatures the selected features to be used. These are the only words that should be be used in
     *                         training.
     * @param words            the words to filter.
     * @return a filtered list of words that exist in both the feature set and list of original words.
     */
    private static List<String> getWordsOverlap(Set<String> selectedFeatures, List<String> words)
    {
        return words
                .stream()
                .filter(selectedFeatures::contains)
                .collect(Collectors.toList());
    }

    /**
     * Gets the number of times the given word appears.
     *
     * @param word the word to get the frequency of.
     * @return the frequency of the word in this document. If the word doesn't appear in the document, this will be 0;
     */
    public int getTermFrequency(String word)
    {
        Integer frequency = words.get(word);
        return (frequency != null) ? frequency : 0;
    }

    /**
     * Gets the total number of terms in the document.
     *
     * @return the total number of terms in the document.
     */
    public int getNumberOfTerms()
    {
        return words
                .entrySet()
                .stream()
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    /**
     * Gets all of the unique terms in the document.
     *
     * @return all of the unique terms in the document.
     */
    public Set<String> getUniqueTerms()
    {
        return words.keySet();
    }

    /**
     * Gets the document id.
     *
     * @return the document id.
     */
    public String getDocumentId()
    {
        return documentId;
    }

    /**
     * Gets the output class.
     *
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
