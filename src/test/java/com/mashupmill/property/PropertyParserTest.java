package com.mashupmill.property;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class PropertyParserTest {

    public final static String PROPERTIES_FILE = "test.properties";

    public final static String EXPECTED_SINGLE_QUOTE = "'-Dhello=it\\'s a foo bar world' -Descaped=this\\ is\\ already\\ escaped -Dfoo=bar";
    public final static String EXPECTED_DOUBLE_QUOTE = "\"-Dhello=it's a foo bar world\" -Descaped=this\\ is\\ already\\ escaped -Dfoo=bar";
    public final static String EXPECTED_SLASH = "-Dhello=it\\'s\\ a\\ foo\\ bar\\ world -Descaped=this\\ is\\ already\\ escaped -Dfoo=bar";
    public final static String EXPECTED_NO_ESCAPE = "-Dhello=it's a foo bar world -Descaped=this\\ is\\ already\\ escaped -Dfoo=bar";

    @Test
    public void testGetOptionListDefaultEscape() throws Exception {
        String actual = JavaOptsUtil.getOptionList(getPropertiesFile().getAbsolutePath());
        Assert.assertNotNull(actual);
        Assert.assertEquals(EXPECTED_DOUBLE_QUOTE, actual);
    }

    @Test
    public void testGetOptionListSingleQuote() throws Exception {
        String actual = JavaOptsUtil.getOptionList(getPropertiesFile().getAbsolutePath(), EscapeType.SINGLE_QUOTE);
        Assert.assertNotNull(actual);
        Assert.assertEquals(EXPECTED_SINGLE_QUOTE, actual);
    }

    @Test
    public void testGetOptionListDoubleQuote() throws Exception {
        String actual = JavaOptsUtil.getOptionList(getPropertiesFile().getAbsolutePath(), EscapeType.DOUBLE_QUOTE);
        Assert.assertNotNull(actual);
        Assert.assertEquals(EXPECTED_DOUBLE_QUOTE, actual);
    }

    @Test
    public void testGetOptionListSlash() throws Exception {
        String actual = JavaOptsUtil.getOptionList(getPropertiesFile().getAbsolutePath(), EscapeType.SLASH);
        Assert.assertNotNull(actual);
        Assert.assertEquals(EXPECTED_SLASH, actual);
    }

    @Test
    public void testGetOptionListNoEscape() throws Exception {
        String actual = JavaOptsUtil.getOptionList(getPropertiesFile().getAbsolutePath(), EscapeType.NONE);
        Assert.assertNotNull(actual);
        Assert.assertEquals(EXPECTED_NO_ESCAPE, actual);
    }

    private File getPropertiesFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(PROPERTIES_FILE).getFile());
    }
}