package edu.byu.cstaheli.cs453.classification.mnb.classification;

import edu.byu.cstaheli.cs453.classification.document.Document;
import edu.byu.cstaheli.cs453.classification.mnb.document.MnbDocument;
import edu.byu.cstaheli.cs453.classification.mnb.document.MultinomialSet;
import edu.byu.cstaheli.cs453.classification.mnb.probability.ClassProbabilities;
import edu.byu.cstaheli.cs453.classification.mnb.probability.ProbabilityTrainer;
import edu.byu.cstaheli.cs453.classification.mnb.probability.WordProbabilities;

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
 * <code>selectedFeatures</code> (as defined in {@link MultinomialNaiveBayesFeatureSelector#featureSelection(List, int)}.
 * Hereafter, <i>training_set</i> (<i>test_set</i>, respectively), which consists of the multinomial representation of
 * each document in the <i>DC_training</i> (<i>DC_test</i>, respectively) set using only the terms in the
 * <code>selectedFeatures</code>, should be created.
 */
public class Classifier implements MultinomialNaiveBayesFeatureSelector, MultinomialNaiveBayesClassification
{
    private WordProbabilities wordProbabilities;
    private ClassProbabilities classProbabilities;

    /**
     * Since Math.pow is a bit of overkill, this does a semi-quicker exponent operation.
     *
     * @param base  the base of the exponent.
     * @param power the power to raise the base to.
     * @return the result of the operation.
     */
    static double pow(double base, int power)
    {
        if (base == 0)
        {
            return 0;
        }
        else if (base == 1 || power == 0)
        {
            return 1;
        }
        else if (power == 1)
        {
            return base;
        }
        else
        {
            double product = 1;
            for (int i = 0; i < power; ++i)
            {
                product *= base;
            }
            return product;
        }
    }

    /**
     * Gets the key in the map that has the highest value.
     *
     * @param probabilities the map.
     * @return the key in the map that has the highest value.
     */
    static String getKeyWithMaxValue(Map<String, Double> probabilities)
    {
        return Collections
                .max(probabilities.entrySet(), Map.Entry.comparingByValue())
                .getKey();
    }

    /**
     * Calculates the log base two of a value.
     *
     * @param value the value to calculate the log log.
     * @return the log base two of a value.
     */
    private static double log2(double value)
    {
        return Math.log(value) / Math.log(2);
    }

    /**
     * Used for information gain, this will calculate the probability times the log of the probability.
     *
     * @param p the probability.
     * @return the probability times the log of the probability.
     */
    private static double calcPLogP(double p)
    {
        if (p == 0) return 0;
        return p * log2(p);
    }

    @Override
    public Set<String> featureSelection(List<Document> trainingSet, int m)
    {
        Set<String> allWordsInDocuments = getAllWordsInDocuments(trainingSet);
        if (m == -1)
        {
            return allWordsInDocuments;
        }
        else
        {
            Map<String, Double> informationGainMap = new HashMap<>();
            MultinomialSet set = new MultinomialSet(allWordsInDocuments);
            set.add(trainingSet);
            for (String word : allWordsInDocuments)
            {
                double informationGain = calculateInformationGain(word, set);
                informationGainMap.put(word, informationGain);
            }
            return getTopMKeysSortedByValue(informationGainMap, m);
        }
    }

    double calculateInformationGain(String word, MultinomialSet documents)
    {
        ProbabilityTrainer trainer = new ProbabilityTrainer();
        ClassProbabilities classProbabilities = trainer.computeClassProbabilities(documents);
        WordProbabilities wordProbabilities = trainer.computeWordProbabilities(documents);

        double classProbabilitiesSum = 0;
        double wordProbabilitiesSum = 0;
        double notWordProbabilitiesSum = 0;

        for (String outputClass : documents.getClasses())
        {
            classProbabilitiesSum += calcPLogP(classProbabilities.getProbability(outputClass));
            double probabilityOfClassGivenWord = wordProbabilities.getProbability(word, outputClass);
            wordProbabilitiesSum += calcPLogP(probabilityOfClassGivenWord);
            notWordProbabilitiesSum += calcPLogP(1 - probabilityOfClassGivenWord);
        }
        double numberOfDocumentsWithWord = documents.getNumberOfDocumentsWithWord(word);
        double numberOfDocuments = documents.getNumberOfDocuments();
        double probabilityOfWord = numberOfDocumentsWithWord / numberOfDocuments;
        double probabilityOfNotWord = 1 - probabilityOfWord;
        return -1 * classProbabilitiesSum + (probabilityOfWord * wordProbabilitiesSum) + (probabilityOfNotWord * notWordProbabilitiesSum);
    }

    private Set<String> getAllWordsInDocuments(List<Document> documents)
    {
        return documents
                .stream()
                .map(Document::getWords)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    private Set<String> getTopMKeysSortedByValue(Map<String, Double> map, int m)
    {
        return new HashSet<>(map
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList())
                .subList(0, m));
    }

    @Override
    public String label(Document document)
    {
        if (wordProbabilities == null || classProbabilities == null)
        {
            throw new RuntimeException("This method cannot be called unless the probabilities for the words and " +
                    "classes have been set.");
        }
        else
        {
            MnbDocument mnbDocument = new MnbDocument(document);
            Map<String, Double> documentInClassProbabilities = getDocumentInClassProbabilities(mnbDocument);
            Map<String, Double> classInDocumentProbabilities = new HashMap<>(documentInClassProbabilities.size());
            double documentProbability = calculateDocumentProbability(documentInClassProbabilities, classProbabilities);
            for (Map.Entry<String, Double> entry : documentInClassProbabilities.entrySet())
            {
                String outputClass = entry.getKey();
                double probability = entry.getValue();
                double probabilityOfClass = classProbabilities.getProbability(outputClass);
                //Using Bayes rule to get the inverse probability
                double classInDocumentProbability = (probability * probabilityOfClass) / documentProbability;
                classInDocumentProbabilities.put(outputClass, classInDocumentProbability);
            }
            return getKeyWithMaxValue(classInDocumentProbabilities);
        }
    }

    /**
     * Calculates the probability of the document given the probabilities of the document in the classes and the
     * probabilities of the classes.
     *
     * @param documentInClassProbabilities the probabilities of the document in the classes.
     * @param classProbabilities           the probabilities of the classes.
     * @return the probability of the document.
     */
    private double calculateDocumentProbability(Map<String, Double> documentInClassProbabilities, ClassProbabilities classProbabilities)
    {
        double sum = 0;
        for (Map.Entry<String, Double> entry : documentInClassProbabilities.entrySet())
        {
            String outputClass = entry.getKey();
            double probability = entry.getValue();
            double probabilityOfClass = classProbabilities.getProbability(outputClass);
            sum += probability * probabilityOfClass;
        }
        return sum;
    }

    /**
     * Calculates the document in class probabilities given the document.
     *
     * @param mnbDocument the document.
     * @return the document in class probabilities.
     */
    Map<String, Double> getDocumentInClassProbabilities(MnbDocument mnbDocument)
    {
        Map<String, Double> documentInClassProbabilities = new HashMap<>(classProbabilities.getClasses().size());
        for (String outputClass : classProbabilities.getClasses())
        {
            double product = 1;

            //Words that don't exist in the document have a 0 term frequency, which means their factor in the
            //probability is 1, so they can be safely ignored.
            for (String word : mnbDocument.getUniqueTerms())
            {
                int termFrequency = mnbDocument.getTermFrequency(word);
                double probability = wordProbabilities.getProbability(word, outputClass);
                product *= pow(probability, termFrequency);
            }
            documentInClassProbabilities.put(outputClass, product);
        }
        return documentInClassProbabilities;
    }

    @Override
    public void setWordProbabilities(WordProbabilities wordProbabilities)
    {
        this.wordProbabilities = wordProbabilities;
    }

    @Override
    public void setClassProbabilities(ClassProbabilities classProbabilities)
    {
        this.classProbabilities = classProbabilities;
    }
}
