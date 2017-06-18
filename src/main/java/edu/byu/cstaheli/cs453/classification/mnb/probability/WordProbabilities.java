package edu.byu.cstaheli.cs453.classification.mnb.probability;

import java.util.HashMap;
import java.util.Map;

/**
 * A representation of a probability of a word in a class. This includes for each word in the vocabulary its probability
 * for each class in C.
 */
public class WordProbabilities
{
    private Map<WordClassPair, Double> wordProbabilities;

    public WordProbabilities()
    {
        wordProbabilities = new HashMap<>();
    }

    /**
     * Adds the given word in the class with the probability.
     *
     * @param word        the word in the class.
     * @param outputClass the class.
     * @param probability the probability of the occurrence.
     */
    public void add(String word, String outputClass, double probability)
    {
        WordClassPair pair = new WordClassPair(word, outputClass);
        wordProbabilities.put(pair, probability);
    }

    /**
     * Gets the probability of the given word in the given class.
     *
     * @param word        the given word.
     * @param outputClass the given class.
     * @return the probability of the word in the class if it exists, -1 otherwise.
     */
    public double getProbability(String word, String outputClass)
    {
        WordClassPair pair = new WordClassPair(word, outputClass);
        Double probability = wordProbabilities.get(pair);
        return (probability != null) ? probability : -1;
    }

    /**
     * A pair of word to class. This is used in {@link WordProbabilities} to denote that a word in a class has a certain
     * probability.
     */
    public static class WordClassPair
    {
        private final String word;
        private final String outputClass;

        /**
         * Creates a {@link WordClassPair} with the given word and class.
         *
         * @param word        the word to use.
         * @param outputClass the class to use.
         */
        public WordClassPair(String word, String outputClass)
        {
            this.word = word;
            this.outputClass = outputClass;
        }

        /**
         * Gets the word.
         *
         * @return the word.
         */
        public String getWord()
        {
            return word;
        }

        /**
         * Gets the class.
         *
         * @return the class.
         */
        public String getOutputClass()
        {
            return outputClass;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            WordClassPair that = (WordClassPair) o;

            if (!word.equals(that.word)) return false;
            return outputClass.equals(that.outputClass);
        }

        @Override
        public int hashCode()
        {
            int result = word.hashCode();
            result = 31 * result + outputClass.hashCode();
            return result;
        }

        @Override
        public String toString()
        {
            return String.format("%s : %s", word, outputClass);
        }
    }
}
