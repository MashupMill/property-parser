package com.mashupmill.property;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class PropertyParserTest {

    private final static String PROPERTIES_FILE = "test.properties";

    @Test
    public void testGetOptionList() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(PROPERTIES_FILE).getFile());
        String expected = "-Dfoo=bar -Dhello='it\\'s a foo bar world'";
        String actual = PropertyParser.getOptionList(file.getAbsolutePath());
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);
    }
}