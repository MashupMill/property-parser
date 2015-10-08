package com.mashupmill.property;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class PropertyParserTest {

    private final static String PROPERTIES_FILE = "test.properties";

    private final static String EXPECTED_SINGLE_QUOTE = "-Dfoo=bar -Dhello='it\\'s a foo bar world' -Descaped=this\\ is\\ already\\ escaped";
    private final static String EXPECTED_DOUBLE_QUOTE = "-Dfoo=bar -Dhello=\"it's a foo bar world\" -Descaped=this\\ is\\ already\\ escaped";
    private final static String EXPECTED_SLASH = "-Dfoo=bar -Dhello=it\\'s\\ a\\ foo\\ bar\\ world -Descaped=this\\ is\\ already\\ escaped";

    @Test
    public void testGetOptionListDefaultEscape() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(PROPERTIES_FILE).getFile());
        String actual = PropertyParser.getOptionList(file.getAbsolutePath());
        Assert.assertNotNull(actual);
        Assert.assertEquals(EXPECTED_SLASH, actual);
    }

    @Test
    public void testGetOptionListSingleQuote() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(PROPERTIES_FILE).getFile());
        String actual = PropertyParser.getOptionList(file.getAbsolutePath(), EscapeType.SINGLE_QUOTE);
        Assert.assertNotNull(actual);
        Assert.assertEquals(EXPECTED_SINGLE_QUOTE, actual);
    }

    @Test
    public void testGetOptionListDoubleQuote() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(PROPERTIES_FILE).getFile());
        String actual = PropertyParser.getOptionList(file.getAbsolutePath(), EscapeType.DOUBLE_QUOTE);
        Assert.assertNotNull(actual);
        Assert.assertEquals(EXPECTED_DOUBLE_QUOTE, actual);
    }

    @Test
    public void testGetOptionListSlash() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(PROPERTIES_FILE).getFile());
        String actual = PropertyParser.getOptionList(file.getAbsolutePath(), EscapeType.SLASH);
        Assert.assertNotNull(actual);
        Assert.assertEquals(EXPECTED_SLASH, actual);
    }
}