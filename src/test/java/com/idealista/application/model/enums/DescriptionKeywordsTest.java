package com.idealista.application.model.enums;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DescriptionKeywordsTest {

    @Test
    public void assertDescriptionKeywordsName() {
        var expectedSize = 5;

        assertEquals(expectedSize, DescriptionKeywords.values().length);
        assertEquals("BRIGHT", DescriptionKeywords.BRIGHT.name());
        assertEquals("NEW", DescriptionKeywords.NEW.name());
        assertEquals("DOWNTOWN", DescriptionKeywords.DOWNTOWN.name());
        assertEquals("REFORMED", DescriptionKeywords.REFORMED.name());
        assertEquals("ATTIC", DescriptionKeywords.ATTIC.name());

    }

    @Test
    public void assertDescriptionKeywordsValue() {
        var expectedSize = 5;
        assertEquals(expectedSize, DescriptionKeywords.values().length);
        assertEquals("Luminoso", DescriptionKeywords.BRIGHT.getValue());
        assertEquals("Nuevo", DescriptionKeywords.NEW.getValue());
        assertEquals("Céntrico", DescriptionKeywords.DOWNTOWN.getValue());
        assertEquals("Reformado", DescriptionKeywords.REFORMED.getValue());
        assertEquals("Ático", DescriptionKeywords.ATTIC.getValue());

    }
}
