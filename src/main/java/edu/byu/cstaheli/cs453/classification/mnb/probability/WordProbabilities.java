package edu.byu.cstaheli.cs453.classification.mnb.probability;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cstaheli on 6/18/2017.
 */
public class WordProbabilities
{
    private Map<WordClassPair, Double> wordProbabilites;

    public WordProbabilities()
    {
        wordProbabilites = new HashMap<>();
    }
}
