package edu.byu.cstaheli.cs453.classification.document;

import java.util.List;

/**
 * A representation of a document. In this case, this is an email.
 */
public class Document
{
    private final String documentId;
    private final String outputClass;
    private final List<String> words;

    public Document(String documentId, String outputClass, List<String> words)
    {
        this.documentId = documentId;
        this.outputClass = outputClass;
        this.words = words;
    }

    public String getDocumentId()
    {
        return documentId;
    }

    public String getOutputClass()
    {
        return outputClass;
    }

    public List<String> getWords()
    {
        return words;
    }

    public int size()
    {
        return words.size();
    }

    @Override
    public String toString()
    {
        return String.format("Document %s : %s", documentId, outputClass);
    }
}
