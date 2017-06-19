package edu.byu.cstaheli.cs453.classification.mnb.probability;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A representation of classes and their probabilities. This includes the probability of each class in C.
 */
public class ClassProbabilities
{
    private Map<String, Double> classProbabilities;

    /**
     * Creates an class probabilities structure.
     */
    public ClassProbabilities()
    {
        classProbabilities = new HashMap<>();
    }

    /**
     * Adds the given output class and probability to the structure.
     *
     * @param outputClass the class to put in the structure.
     * @param probability the probability to associate with the class.
     */
    public void add(String outputClass, double probability)
    {
        classProbabilities.put(outputClass, probability);
    }

    /**
     * Gets the probability of the given class.
     *
     * @param outputClass the class to get the probability of.
     * @return the probability of the given class if it exists in the structure, -1 if it doesn't.
     */
    public double getProbability(String outputClass)
    {
        Double probability = classProbabilities.get(outputClass);
        return (probability != null) ? probability : -1;
    }

    public Set<String> getClasses()
    {
        return classProbabilities.keySet();
    }
}
