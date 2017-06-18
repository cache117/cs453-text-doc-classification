package edu.byu.cstaheli.cs453.classification.mnb.evaluation;

import edu.byu.cstaheli.cs453.classification.mnb.classification.MultinomialNaiveBayesClassification;
import edu.byu.cstaheli.cs453.classification.mnb.document.TestSet;

/**
 * This class computes the <i>accuracy</i> of the trained MNB in text document classification.
 */
public interface MultinomialNaiveBayesEvaluation
{
    /**
     * This method computes the <i>accuracy</i> of the trained MNB. <b>Accuracy</b> is defined as the <i>proportion
     * of</i> documents in <code>testSet</code> for which their classification labels determined using the trained MNB
     * are the <u>same</u> as their pre-defined, i.e., original, labels over the total number of documents in
     * <code>testSet</code>.
     *
     * @param testSet the test set to run accuracy on. This is the set of documents in <code>testSet</code> and their
     *                labels determined by using {@link MultinomialNaiveBayesClassification}
     * @return the classification accuracy of the documents in <code>testSet</code>.
     */
    double accuracyMeasure(TestSet testSet);
}
