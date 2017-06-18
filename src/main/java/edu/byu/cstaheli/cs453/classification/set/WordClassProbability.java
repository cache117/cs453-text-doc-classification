package edu.byu.cstaheli.cs453.classification.set;

/**
 * Stores a word w, a class c, and the probability of the word w occurring in class c.
 */
public class WordClassProbability
{
    private final String outputClass;
    private final String word;
    private final double probability;

    public WordClassProbability(String outputClass, String word, double probability)
    {
        this.outputClass = outputClass;
        this.word = word;
        this.probability = probability;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WordClassProbability that = (WordClassProbability) o;

        if (Double.compare(that.probability, probability) != 0) return false;
        if (!outputClass.equals(that.outputClass)) return false;
        return word.equals(that.word);
    }

    @Override
    public int hashCode()
    {
        int result;
        long temp;
        result = outputClass.hashCode();
        result = 31 * result + word.hashCode();
        temp = Double.doubleToLongBits(probability);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString()
    {
        return String.format("(%s,%s) -> %s", word, outputClass, probability);
    }
}
