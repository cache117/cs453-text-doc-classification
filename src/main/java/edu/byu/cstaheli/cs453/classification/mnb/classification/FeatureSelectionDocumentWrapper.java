package edu.byu.cstaheli.cs453.classification.mnb.classification;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import edu.byu.cstaheli.cs453.classification.document.Document;
import edu.byu.cstaheli.cs453.classification.mnb.probability.WordProbabilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by cstaheli on 6/19/2017.
 */
public class FeatureSelectionDocumentWrapper
{
    private List<Document> documents;
    private Multimap<String, String> documentsWhereWordIsPresent;
    private Multimap<WordProbabilities.WordClassPair, String> documentsLabeledClassWhereWordIsPresent;
    private Multimap<String, String> documentsWithClass;
    private Map<String, Document> documentMap;

    public FeatureSelectionDocumentWrapper(List<Document> documents)
    {
        this.documents = documents;
        documentsWhereWordIsPresent = HashMultimap.create();
        documentsWithClass = HashMultimap.create();
        documentMap = new HashMap<>();
        documentsLabeledClassWhereWordIsPresent = HashMultimap.create();
        for (Document document : documents)
        {
            Set<String> words = document.getDistinctWords();
            String documentId = document.getDocumentId();
            String outputClass = document.getOutputClass();
            documentMap.put(documentId, document);
            documentsWithClass.put(outputClass, documentId);

            for (String word : words)
            {
                WordProbabilities.WordClassPair pair = new WordProbabilities.WordClassPair(word, outputClass);
                documentsWhereWordIsPresent.put(word, documentId);
                documentsLabeledClassWhereWordIsPresent.put(pair, documentId);
            }
        }
        System.out.println();
    }

    public int getNumberOfDocuments()
    {
        return documents.size();
    }

    public int getNumberOfDocumentsWithWord(String word)
    {
        return documentsWhereWordIsPresent.get(word).size();
    }

    public int getNumberOfDocumentsWithoutWord(String word)
    {
        return getNumberOfDocuments() - getNumberOfDocumentsWithWord(word);
    }

    public Set<String> getClasses()
    {
        return documentsWithClass.keySet();
    }

    public int getNumberOfDocumentsLabeledClass(String outputClass)
    {
        return documentsWithClass.get(outputClass).size();
    }

    public int getNumberOfDocumentsWithWordLabeledAsClass(String word, String outputClass)
    {
        return documentsLabeledClassWhereWordIsPresent.get(new WordProbabilities.WordClassPair(word, outputClass)).size();
    }

    public int getNumberOfDocumentsWithoutWordLabeledAsClass(String word, String outputClass)
    {
        return getNumberOfDocuments() - getNumberOfDocumentsWithWordLabeledAsClass(word, outputClass);
    }
}
