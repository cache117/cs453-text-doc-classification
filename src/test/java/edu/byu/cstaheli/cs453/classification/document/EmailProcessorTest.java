package edu.byu.cstaheli.cs453.classification.document;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.List;

class EmailProcessorTest
{
    @Test
    void readEmail()
    {
        String documents = "src/main/resources/20NG";
        List<String> words = EmailProcessor.readEmail(Paths.get(documents + "/comp.graphics/37261"));
        assertEquals(266, words.size());

        words = EmailProcessor.readEmail(Paths.get(documents + "/comp.graphics/37913"));
        assertEquals(68, words.size());
        assertFalse(words.contains("2"));
        assertTrue(words.contains("any"));
        assertFalse(words.contains("Any"));
    }
}