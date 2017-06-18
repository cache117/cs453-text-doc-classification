package edu.byu.cstaheli.cs453.classification.mnb.probability;

import edu.byu.cstaheli.cs453.classification.mnb.document.MultinomialSet;

import java.util.Set;

/**
 * This class is used for training an MNB.
 */
public class ProbabilityTrainer implements MultinomialNaiveBayesProbability
{
    private MultinomialSet multinomialSet;

    public ProbabilityTrainer(MultinomialSet multinomialSet)
    {
        this.multinomialSet = multinomialSet;
    }

    public MultinomialSet getMultinomialSet()
    {
        return multinomialSet;
    }

    public void setMultinomialSet(MultinomialSet multinomialSet)
    {
        this.multinomialSet = multinomialSet;
    }

    @Override
    public WordProbabilities computeWordProbability(MultinomialSet trainingSet)
    {
        WordProbabilities wordProbabilities = new WordProbabilities();
        Set<String> classes = trainingSet.getClasses();
        Set<String> wordsInSet = trainingSet.getWordsInSet();
        for (String outputClass : classes)
        {
            for (String word : wordsInSet)
            {
                double probability = getWordProbability(word, outputClass);
                wordProbabilities.add(word, outputClass, probability);
            }
        }
        return wordProbabilities;
    }

    @Override
    public ClassProbabilities computeClassProbability(MultinomialSet trainingSet)
    {
        ClassProbabilities classProbabilities = new ClassProbabilities();
        Set<String> classes = trainingSet.getClasses();
        for (String outputClass : classes)
        {
            double probability = getClassProbability(outputClass);
            classProbabilities.add(outputClass, probability);
        }
        return classProbabilities;
    }

    @Override
    public double getWordProbability(String word, String outputClass)
    {
        return 0;
    }

    @Override
    public double getClassProbability(String outputClass)
    {
        return 0;
    }
}
