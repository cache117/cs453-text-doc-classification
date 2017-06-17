package edu.byu.cstaheli.cs453.classification.mnb;

import edu.byu.cstaheli.cs453.classification.document.Document;
import edu.byu.cstaheli.cs453.classification.set.dc.DcTrainingSet;

import java.util.Set;

/**
 * This class is used for
 * <ol>
 * <li>training an MNB and </li>
 * <li>classifying unlabeled documents using the trained MNB.</li>
 * </ol>
 * <br>
 * Given a document collection <i>DC</i>, which is a set of plain-text documents, all the stopwords in <i>DC</i> are
 * first removed. This is done with {@link edu.byu.cstaheli.cs453.common.util.StopWordsRemover} which gets it's list of
 * stopwords from <i>src/resources/stopwords.txt</i>. Thereafter, <i>DC</i> should be partitioned into two subsets:
 * the <i>DC_training</i> set, which contains 80% of the documents randomly selected from <i>DC</i>, and the
 * <i>DC_test</i> set, which contains the remaining 20% of the documents in <i>DC</i>. If you are using the entire
 * vocabulary, i.e., when no <i>feature selection</i> is applied, then a data structure <i>training_set</i>
 * (<i>test_set</i>, respectively), which includes the e multinomial representation of each document in the
 * <i>DC_training</i> (<i>DC_test</i>, respectively) set should be created. On the other hand, if <i>feature
 * selection</i> is applied, then the documents in the <i>DC_training</i> set should first be used to determine the
 * <code>selectedFeatures</code> (as defined in {@link MultinomialNaiveBayesClassification#featureSelection(DcTrainingSet, int)}.
 * Hereafter, <i>training_set</i> (<i>test_set</i>, respectively), which consists of the multinomial representation of
 * each document in the <i>DC_training</i> (<i>DC_test</i>, respectively) set using only the terms in the
 * <code>selectedFeatures</code>, should be created.
 */
public interface MultinomialNaiveBayesClassification
{
    /**
     * This method determines the words which should be chosen to represent documents in
     *
     * @param trainingSet
     * @param m
     * @return
     */
    Set<String> featureSelection(DcTrainingSet trainingSet, int m);

    /**
     * This method assigns the most probable class for a particular document in test set. In performing
     * the classification task, it uses the methods getWordProbability and getClassProbability, which are defined
     * in {@link MultinomialNaiveBayesProbability#getWordProbability(String, String)} and
     * {@link MultinomialNaiveBayesProbability#getClassProbability(String)}, respectively.
     *
     * @param document The input of label is a document D in test_set.
     * @return the output is the class c that should be assigned to D.
     */
    String label(Document document);
}
