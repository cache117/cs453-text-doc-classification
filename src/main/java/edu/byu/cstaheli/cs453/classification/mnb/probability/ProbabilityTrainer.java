package edu.byu.cstaheli.cs453.classification.mnb.probability;

import edu.byu.cstaheli.cs453.classification.mnb.document.MultinomialSet;

/**
 * Created by cstaheli on 6/18/2017.
 */
public class ProbabilityTrainer implements MultinomialNaiveBayesProbability
{
    @Override
    public WordProbabilities computeWordProbability(MultinomialSet trainingSet)
    {
        return null;
    }

    @Override
    public ClassProbabilities computeClassProbability(MultinomialSet trainingSet)
    {
        return null;
    }

    @Override
    public WordProbabilities getWordProbability(String word, String outputClass)
    {
        return null;
    }

    @Override
    public ClassProbabilities getClassProbability(String outputClass)
    {
        return null;
    }
}
