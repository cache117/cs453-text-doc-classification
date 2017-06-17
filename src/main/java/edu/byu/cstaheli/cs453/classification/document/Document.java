package edu.byu.cstaheli.cs453.classification.document;

import java.util.List;

/**
 * A representation of a document. In this case, this is an email.
 */
public class Document
{
    private final String outputClass;
    private final List<String> words;

    public Document(String outputClass, List<String> words)
    {
        this.outputClass = outputClass;
        this.words = words;
    }

    public String getOutputClass()
    {
        return outputClass;
    }

    public List<String> getWords()
    {
        return words;
    }
}
