package edu.byu.cstaheli.cs453.classification.mnb.classification;

import edu.byu.cstaheli.cs453.classification.Driver;
import edu.byu.cstaheli.cs453.classification.document.Document;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by cstaheli on 6/19/2017.
 */
class FeatureSelectionDocumentWrapperTest
{
    private static FeatureSelectionDocumentWrapper wrapper;

    @BeforeAll
    static void setUpAll()
    {
        String basePath = "src/main/resources/20NG";
        List<Document> documents = new Driver().readInCorpus(basePath);
        wrapper = new FeatureSelectionDocumentWrapper(documents);
    }

    @Test
    void getNumberOfDocuments()
    {
        assertEquals(9997, wrapper.getNumberOfDocuments());
    }

    @Test
    void getNumberOfDocumentsWithWord()
    {
        assertEquals(557, wrapper.getNumberOfDocumentsWithWord("fax"));

    }

    @Test
    void getNumberOfDocumentsWithoutWord()
    {
    }

    @Test
    void getClasses()
    {
    }

    @Test
    void getNumberOfDocumentsLabeledClass()
    {
    }

    @Test
    void getNumberOfDocumentsWithWordLabeledAsClass()
    {
    }

    @Test
    void getNumberOfDocumentsWithoutWordLabeledAsClass()
    {
    }

}