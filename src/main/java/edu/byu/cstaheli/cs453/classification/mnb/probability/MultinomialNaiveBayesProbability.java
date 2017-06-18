package edu.byu.cstaheli.cs453.classification.mnb.probability;

import edu.byu.cstaheli.cs453.classification.mnb.document.MultinomialSet;

/**
 * This class is used for training an MNB.
 */
public interface MultinomialNaiveBayesProbability
{
    /**
     * This method computes the probability of each distinct word in each natural class in C using the trainingSet. It
     * uses the Laplacian Smoothed Estimate to compute the WordProbabilities.
     *
     * @param trainingSet the training set to compute the probabilities for.
     * @return word probabilities that includes for each word in the vocabulary its probability for each class in C.
     */
    WordProbabilities computeWordProbabilities(MultinomialSet trainingSet);

    /**
     * This method computes the probability of each natural class in C using the trainingSet.
     *
     * @param trainingSet the training set to compute the probabilities for.
     * @return class probabilities that contain the probability for each class in C.
     */
    ClassProbabilities computeClassProbabilities(MultinomialSet trainingSet);

    /**
     * This method retrieves the probability value of a word in a particular class, which includes the probability
     * value of each word not seen during the training phase of MNB.
     *
     * @param termFrequency        the frequency of the word in the class in the set.
     * @param numberOfTermsInClass the number of terms that are present in the class.
     * @param vocabSize            the size of the vocabulary in the training set.
     * @return the probability of word w in c.
     */
    default double getWordProbability(int termFrequency, int numberOfTermsInClass, int vocabSize)
    {
        return (double) (termFrequency + 1) / (double) (numberOfTermsInClass + vocabSize);
    }

    /**
     * This method retrieves the probability value of a natural class.
     *
     * @param numberOfDocumentsWithClass the number of documents that contain the class.
     * @param numberOfDocuments          the total number of documents.
     * @return the probability of c.
     */
    default double getClassProbability(int numberOfDocumentsWithClass, int numberOfDocuments)
    {
        return (double) numberOfDocumentsWithClass / (double) numberOfDocuments;
    }
}
