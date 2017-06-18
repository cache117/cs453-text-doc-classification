package edu.byu.cstaheli.cs453.classification.mnb.probability;

import edu.byu.cstaheli.cs453.classification.mnb.document.MultinomialSet;

import java.util.Set;

/**
 * This class is used for training an MNB.
 */
public class ProbabilityTrainer implements MultinomialNaiveBayesProbability
{
    @Override
    public WordProbabilities computeWordProbabilities(MultinomialSet trainingSet)
    {
        WordProbabilities wordProbabilities = new WordProbabilities();
        Set<String> classes = trainingSet.getClasses();
        Set<String> wordsInSet = trainingSet.getWordsInSet();
        int vocabSize = trainingSet.getVocabularySize();
        for (String outputClass : classes)
        {
            int numberOfTermsInClass = trainingSet.getNumberOfTermsWithClass(outputClass);
            for (String word : wordsInSet)
            {
                int termFrequency = trainingSet.getTermFrequencyInClass(word, outputClass);
                double probability = getWordProbability(termFrequency, numberOfTermsInClass, vocabSize);
                wordProbabilities.add(word, outputClass, probability);
            }
        }
        return wordProbabilities;
    }

    @Override
    public ClassProbabilities computeClassProbabilities(MultinomialSet trainingSet)
    {
        ClassProbabilities classProbabilities = new ClassProbabilities();
        Set<String> classes = trainingSet.getClasses();
        int numberOfDocuments = trainingSet.getNumberOfDocuments();
        for (String outputClass : classes)
        {
            int numberOfDocumentsWithClass = trainingSet.getNumberOfDocumentsWithClass(outputClass);
            double probability = getClassProbability(numberOfDocumentsWithClass, numberOfDocuments);
            classProbabilities.add(outputClass, probability);
        }
        return classProbabilities;
    }

}
