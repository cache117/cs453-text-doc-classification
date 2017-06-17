package edu.byu.cstaheli.cs453.classification.document;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * This class processes and sanitizes emails.
 */
public class EmailProcessor
{
    public static List<String> readEmail(Path file)
    {
        List<String> words = new ArrayList<>();
        try
        {
            // Skip header
            Scanner inScanner = new Scanner(new FileReader(file.toString()));
            inScanner.useDelimiter("\n\n");
            inScanner.next();

            String domainPattern = "[a-z0-9\\-\\.]+\\.(com|org|net|mil|edu|(co\\.[a-z].))";
            Pattern pFind = Pattern.compile(domainPattern);

            while (inScanner.hasNextLine())
            {
                String str = inScanner.nextLine().toLowerCase();
                if (!pFind.matcher(str).find())
                {
                    str = str.replaceAll("[^a-zA-Z]", " ");
                    StringTokenizer token = new StringTokenizer(str, " \t\n\r\f\",.:;?![]>-()/|'");
                    while (token.hasMoreTokens())
                    {
                        words.add(token.nextToken());
                    }
                }
            }
            inScanner.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return words;
    }
}
