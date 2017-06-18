package edu.byu.cstaheli.cs453.classification.mnb.probability;

/**
 * A pair of word to class. This is used in {@link WordProbabilities} to denote that a word in a class has a certain
 * probability.
 */
public class WordClassPair
{
    private final String word;
    private final String outputClass;

    /**
     * Creates a {@link WordClassPair} with the given word and class.
     * @param word the word to use.
     * @param outputClass the class to use.
     */
    public WordClassPair(String word, String outputClass)
    {
        this.word = word;
        this.outputClass = outputClass;
    }

    /**
     * Gets the word.
     * @return the word.
     */
    public String getWord()
    {
        return word;
    }

    /**
     * Gets the class.
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
