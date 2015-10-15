package com.mashupmill.property.cli;

import com.mashupmill.property.cli.commands.AbstractCliTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.contrib.java.lang.system.Assertion;

public class MainTest extends AbstractCliTest {

    @Test
    public void testMainWithoutArgs() throws Exception {
        exit.expectSystemExitWithStatus(1);
        exit.checkAssertionAfterwards(new Assertion() {
            @Override
            public void checkAssertion() throws Exception {
                Assert.assertTrue(stderr.toString().contains("Available commands are"));
            }
        });
        Main.main(new String[]{});
    }
}