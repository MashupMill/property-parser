package com.mashupmill.property.cli.commands;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;

public class TemplateTest extends AbstractCliTest {

    private final static Command command = new Template();

    @Test
    public void testExec() throws Exception {
        command.exec(new String[]{getResourceFile("test.properties").getAbsolutePath(), getResourceFile("template.html").getAbsolutePath()});
        String output = stdout.toString();
        Assert.assertTrue(output.contains("<title>bar</title>"));
        Assert.assertTrue(output.contains("${non-existant.variable}"));
    }

    @Test
    public void testExecStdIn() throws Exception {
        System.setIn(new FileInputStream(getResourceFile("template.html")));
        command.exec(new String[]{getResourceFile("test.properties").getAbsolutePath()});
        String output = stdout.toString();
        Assert.assertTrue(output.contains("<title>bar</title>"));
        Assert.assertTrue(output.contains("${non-existant.variable}"));
    }

    @Test
    public void testExecStdInStr() throws Exception {
        System.setIn(new ByteArrayInputStream("${foo}".getBytes()));
        command.exec(new String[]{getResourceFile("test.properties").getAbsolutePath()});
        String output = stdout.toString();
        Assert.assertEquals("bar", output);
    }

    @Test
    public void testExecOutput() throws Exception {
        File temp = File.createTempFile("temp-file-name", ".tmp");
        temp.deleteOnExit();
        command.exec(new String[]{"-o", temp.getAbsolutePath(), getResourceFile("test.properties").getAbsolutePath(), getResourceFile("template.html").getAbsolutePath()});
        String output = IOUtils.toString(new FileInputStream(temp), Charset.defaultCharset());
        Assert.assertTrue(output.contains("<title>bar</title>"));
        Assert.assertTrue(output.contains("${non-existant.variable}"));
    }
}