package edu.byu.cstaheli.cs453.classification.mnb.classification;

import edu.byu.cstaheli.cs453.classification.document.Document;

import java.util.*;
import java.util.stream.Collectors;

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
 * <code>selectedFeatures</code> (as defined in {@link MultinomialNaiveBayesClassification#featureSelection(List, int)}.
 * Hereafter, <i>training_set</i> (<i>test_set</i>, respectively), which consists of the multinomial representation of
 * each document in the <i>DC_training</i> (<i>DC_test</i>, respectively) set using only the terms in the
 * <code>selectedFeatures</code>, should be created.
 */
public class Classifier implements MultinomialNaiveBayesClassification
{
    @Override
    public Set<String> featureSelection(List<Document> trainingSet, int m)
    {
        if (m == -1)
        {
            return trainingSet
                    .stream()
                    .map(Document::getWords)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toSet());
        }
        else
        {
            //TODO map the words to their information gain and return the top m results as a set.
            return null;
        }
    }

    @Override
    public String label(Document document)
    {
        return null;
    }
}
