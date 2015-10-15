package com.mashupmill.property.cli.commands;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static com.mashupmill.property.PropertyParserTest.PROPERTIES_FILE;

/**
 * Created 10/14/15 @ 11:31 PM
 *
 * @author brandencash
 */
public abstract class AbstractCliTest {
    protected final ByteArrayOutputStream stdout = new ByteArrayOutputStream();
    protected final ByteArrayOutputStream stderr = new ByteArrayOutputStream();

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

    protected File getResourceFile(String file) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(file).getFile());
    }
}
