package edu.byu.cstaheli.cs453.classification.mnb.classification;

import edu.byu.cstaheli.cs453.classification.document.Document;
import edu.byu.cstaheli.cs453.classification.mnb.probability.ClassProbabilities;
import edu.byu.cstaheli.cs453.classification.mnb.probability.WordProbabilities;

/**
 * This class is used for labeling a document. this will use the
 */
public interface MultinomialNaiveBayesClassification
{
    /**
     * This method assigns the most probable class for a particular document in test set. In performing
     * the classification task, it uses the {@link WordProbabilities} and {@link ClassProbabilities} to determine the
     * probabilities of the various pieces necessary for classification.
     *
     * @param document The input of label is a document D in test_set.
     * @return the output is the class c that should be assigned to D.
     */
    String label(Document document);

    /**
     * Sets the word probabilities that should be used for classification.
     *
     * @param wordProbabilities the word probabilities to use.
     */
    void setWordProbabilities(WordProbabilities wordProbabilities);

    /**
     * Sets the class probabilities that should be used for classification.
     *
     * @param classProbabilities the class probabilities to use.
     */
    void setClassProbabilities(ClassProbabilities classProbabilities);
}
