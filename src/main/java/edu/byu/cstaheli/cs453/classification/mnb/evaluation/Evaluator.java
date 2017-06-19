package edu.byu.cstaheli.cs453.classification.mnb.evaluation;

import edu.byu.cstaheli.cs453.classification.document.Document;
import edu.byu.cstaheli.cs453.classification.mnb.classification.Classifier;
import edu.byu.cstaheli.cs453.classification.mnb.classification.MultinomialNaiveBayesFeatureSelector;
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
    private Classifier classifier;
    public Evaluator(List<Document> trainingSetDocuments, int numberOfFeatures)
    {
        classifier = new Classifier();
        Set<String> selectedFeatures = classifier.featureSelection(trainingSetDocuments, numberOfFeatures);

        MultinomialSet trainingSet = new MultinomialSet(selectedFeatures);
        trainingSet.add(trainingSetDocuments);
        MultinomialNaiveBayesProbability probability = new ProbabilityTrainer();
        WordProbabilities wordProbabilities = probability.computeWordProbabilities(trainingSet);
        ClassProbabilities classProbabilities = probability.computeClassProbabilities(trainingSet);
        classifier.setWordProbabilities(wordProbabilities);
        classifier.setClassProbabilities(classProbabilities);
    }

    @Override
    public double accuracyMeasure(TestSet testSet)
    {
        List<Document> testDocuments = testSet.getDocuments();
        int count = 0;
        int correct = 0;
        for (Document document : testDocuments)
        {
            String suggestedLabel = classifier.label(document);
            String actualLabel = document.getOutputClass();
            if (suggestedLabel.equals(actualLabel))
            {
                ++correct;
            }
            ++count;
        }
        return (double) correct / (double) count;
    }
}
