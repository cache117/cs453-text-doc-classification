package edu.byu.cstaheli.cs453.classification.mnb;

import edu.byu.cstaheli.cs453.classification.set.ClassProbabilities;
import edu.byu.cstaheli.cs453.classification.set.mnb.TrainingSet;
import edu.byu.cstaheli.cs453.classification.set.WordProbabilities;

/**
 * This class is used for training an MNB.
 */
public interface MultinomialNaiveBayesProbability
{
    /**
     * This method computes the probability of each distinct word in each natural class in C using the trainingSet. It
     * uses the Laplacian Smoothed Estimate to compute the WordProbabilities.
     * @param trainingSet the training set to compute the probabilities for.
     * @return word probabilities that includes for each word in the vocabulary its probability for each class in C.
     */
    WordProbabilities computeWordProbability(TrainingSet trainingSet);

    /**
     * This method computes the probability of each natural class in C using the trainingSet.
     * @param trainingSet the training set to compute the probabilities for.
     * @return class probabilities that contain the probability for each class in C.
     */
    ClassProbabilities computeClassProbability(TrainingSet trainingSet);

    /**
     * This method retrieves the probability value of a word in a particular class, which includes the probability
     * value of each word not seen during the training phase of MNB.
     * @param word a word w.
     * @param outputClass a class c.
     * @return the probability of word w in c
     */
    WordProbabilities getWordProbability(String word, String outputClass);

    /**
     * This method retrieves the probability value of a natural class.
     * @param outputClass a class c.
     * @return the probability of c, stored in a ClassProbabilities.
     */
    ClassProbabilities getClassProbability(String outputClass);
}
