package edu.byu.cstaheli.cs453.classification.mnb.classification;

import edu.byu.cstaheli.cs453.classification.document.Document;

import java.util.List;
import java.util.Set;

/**
 * Created by cstaheli on 6/18/2017.
 */
public class Classifier implements MultinomialNaiveBayesClassification
{
    @Override
    public Set<String> featureSelection(List<Document> trainingSet, int m)
    {
        return null;
    }

    @Override
    public String label(Document document)
    {
        return null;
    }
}
