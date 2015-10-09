package com.mashupmill.property;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.Assertion;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static com.mashupmill.property.PropertyParserTest.EXPECTED_DOUBLE_QUOTE;
import static com.mashupmill.property.PropertyParserTest.EXPECTED_NO_ESCAPE;
import static com.mashupmill.property.PropertyParserTest.EXPECTED_SINGLE_QUOTE;
import static com.mashupmill.property.PropertyParserTest.EXPECTED_SLASH;
import static com.mashupmill.property.PropertyParserTest.PROPERTIES_FILE;

/**
 * Created 10/8/15 @ 2:41 PM
 *
 * @author brandencash
 */
public class PropertyParserCliTest {

    private final ByteArrayOutputStream stdout = new ByteArrayOutputStream();
    private final ByteArrayOutputStream stderr = new ByteArrayOutputStream();

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(stdout));
        System.setErr(new PrintStream(stderr));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void testCliDefault() throws Exception {
        PropertyParserCli.main(new String[]{getPropertiesFile().getAbsolutePath()});
        Assert.assertEquals(EXPECTED_SLASH, stdout.toString().trim());
    }

    @Test
    public void testCliSlash() throws Exception {
        PropertyParserCli.main(new String[]{getPropertiesFile().getAbsolutePath(), "-s"});
        Assert.assertEquals(EXPECTED_SLASH, stdout.toString().trim());
    }

    @Test
    public void testCliSingleQuote() throws Exception {
        PropertyParserCli.main(new String[]{getPropertiesFile().getAbsolutePath(), "-q"});
        Assert.assertEquals(EXPECTED_SINGLE_QUOTE, stdout.toString().trim());
    }

    @Test
    public void testCliDoubleQuote() throws Exception {
        PropertyParserCli.main(new String[]{getPropertiesFile().getAbsolutePath(), "-d"});
        Assert.assertEquals(EXPECTED_DOUBLE_QUOTE, stdout.toString().trim());
    }

    @Test
    public void testCliNoEscape() throws Exception {
        PropertyParserCli.main(new String[]{getPropertiesFile().getAbsolutePath(), "-n"});
        Assert.assertEquals(EXPECTED_NO_ESCAPE, stdout.toString().trim());
    }

    @Test
    public void testCliMultipleEscapeTypes() throws Exception {
        exit.expectSystemExitWithStatus(1);
        exit.checkAssertionAfterwards(new Assertion() {
            @Override
            public void checkAssertion() throws Exception {
                Assert.assertTrue(stderr.toString().contains("an option from this group has already been selected"));
            }
        });
        PropertyParserCli.main(new String[]{getPropertiesFile().getAbsolutePath(), "-d", "-s"});
    }

    private File getPropertiesFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(PROPERTIES_FILE).getFile());
    }

}
