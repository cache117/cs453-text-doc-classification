package edu.byu.cstaheli.cs453.classification.mnb.document;

import edu.byu.cstaheli.cs453.classification.document.Document;

import java.util.List;

/**
 * Created by cstaheli on 6/18/2017.
 */
public class TestSet
{
    private List<Document> documents;

    public TestSet(List<Document> documents)
    {
        this.documents = documents;
    }

    public List<Document> getDocuments()
    {
        return documents;
    }
}
