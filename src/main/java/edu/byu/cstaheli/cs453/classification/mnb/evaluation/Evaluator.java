package edu.byu.cstaheli.cs453.classification.mnb.evaluation;

import edu.byu.cstaheli.cs453.classification.document.Document;
import edu.byu.cstaheli.cs453.classification.mnb.classification.Classifier;
import edu.byu.cstaheli.cs453.classification.mnb.classification.MultinomialNaiveBayesClassification;
import edu.byu.cstaheli.cs453.classification.mnb.document.MultinomialSet;
import edu.byu.cstaheli.cs453.classification.mnb.document.TestSet;
import edu.byu.cstaheli.cs453.classification.mnb.probability.ClassProbabilities;
import edu.byu.cstaheli.cs453.classification.mnb.probability.MultinomialNaiveBayesProbability;
import edu.byu.cstaheli.cs453.classification.mnb.probability.ProbabilityTrainer;
import edu.byu.cstaheli.cs453.classification.mnb.probability.WordProbabilities;

import java.util.List;
import java.util.Set;

/**
 * This class computes the <i>accuracy</i> of the trained MNB in text document classification.
 */
public class Evaluator implements MultinomialNaiveBayesEvaluation
{
    private WordProbabilities wordProbabilities;
    private ClassProbabilities classProbabilities;
    public Evaluator(List<Document> trainingSetDocuments, int numberOfFeatures)
    {
        MultinomialNaiveBayesClassification classifier = new Classifier();
        Set<String> selectedFeatures = classifier.featureSelection(trainingSetDocuments, numberOfFeatures);

        MultinomialSet trainingSet = new MultinomialSet(selectedFeatures);
        trainingSet.add(trainingSetDocuments);
        MultinomialNaiveBayesProbability probability = new ProbabilityTrainer();
        wordProbabilities = probability.computeWordProbabilities(trainingSet);
        classProbabilities = probability.computeClassProbabilities(trainingSet);
    }

    @Override
    public double accuracyMeasure(TestSet testSet)
    {
        return 0;
    }
}
