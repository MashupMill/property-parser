package com.mashupmill.property.cli.commands;

import org.junit.Assert;
import org.junit.Test;
import org.junit.contrib.java.lang.system.Assertion;

import java.io.File;

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
public class ToJavaOptsTest extends AbstractCliTest {

    private final Command command = new ToJavaOpts();

    @Test
    public void testCliDefault() throws Exception {
        command.exec(new String[]{getPropertiesFile().getAbsolutePath()});
        Assert.assertEquals(EXPECTED_DOUBLE_QUOTE, stdout.toString().trim());
    }

    @Test
    public void testCliSlash() throws Exception {
        command.exec(new String[]{getPropertiesFile().getAbsolutePath(), "-s"});
        Assert.assertEquals(EXPECTED_SLASH, stdout.toString().trim());
    }

    @Test
    public void testCliSingleQuote() throws Exception {
        command.exec(new String[]{getPropertiesFile().getAbsolutePath(), "-q"});
        Assert.assertEquals(EXPECTED_SINGLE_QUOTE, stdout.toString().trim());
    }

    @Test
    public void testCliDoubleQuote() throws Exception {
        command.exec(new String[]{getPropertiesFile().getAbsolutePath(), "-d"});
        Assert.assertEquals(EXPECTED_DOUBLE_QUOTE, stdout.toString().trim());
    }

    @Test
    public void testCliNoEscape() throws Exception {
        command.exec(new String[]{getPropertiesFile().getAbsolutePath(), "-n"});
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
        command.exec(new String[]{getPropertiesFile().getAbsolutePath(), "-d", "-s"});
    }

    private File getPropertiesFile() {
        return getResourceFile(PROPERTIES_FILE);
    }

}
