package edu.byu.cstaheli.cs453.common.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Represents all possible stopwords.
 */
public class StopWordsRemover
{
    private final Set<String> stopWords;

    /**
     * Creates a {@link StopWordsRemover} from a file. This file must be src/main/resources/stopwords.txt or it will
     * not work.
     */
    public StopWordsRemover()
    {
        stopWords = new HashSet<>();
        //validWords = new HashSet<>();
        try
        {
            // Read the unordered file in
            BufferedReader in = new BufferedReader(new FileReader("src/main/resources/stopwords.txt"));
            StringBuilder str = new StringBuilder();
            String nextLine;
            while ((nextLine = in.readLine()) != null)
            {
                str.append(nextLine).append("\n");
            }
            in.close();
            //save it to a bin tree.
            StringTokenizer st = new StringTokenizer(str.toString());//create a string
            while (st.hasMoreTokens())
            {
                nextLine = st.nextToken();
                if (nextLine.matches("[a-zA-Z'.]*"))
                {
                    stopWords.add(nextLine.trim());
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Gets all stopwords.
     * @return all stopwords.
     */
    public Set<String> getStopWords()
    {
        return stopWords;
    }

    /**
     * Determines if a word is a stopword.
     * @param word the word to check.
     * @return true if the word is a stopword, false otherwise.
     */
    public boolean contains(String word)
    {
        return stopWords.contains(word.toLowerCase().trim());
    }
}
