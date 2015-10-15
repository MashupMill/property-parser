package com.mashupmill.property.cli.commands;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.configuration.ConfigurationConverter;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.text.StrSubstitutor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * Created 10/14/15 @ 10:40 PM
 *
 * @author brandencash
 */
public class Template implements Command {
    public final static String NAME = "template";

    private static Options options;

    static {
        options = new Options();
        options.addOption("h", "help", false, "show this help");
        options.addOption("o", "out", true, "the file to output to. defaults to stdout");
    }

    @Override
    public void exec(String[] args) {
        try {
            CommandLine line = getCli(args);

            if (line.getArgs().length == 0) {
                System.err.println("No arguments provided");
                exec(new String[]{"-h"});
                System.exit(1);
            }

            if (line.hasOption("h")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp(String.format("%s [OPTIONS] <PROPERTIES-FILE> [TEMPLATE-FILE]", NAME), options);
                return;
            }

            PropertiesConfiguration props = new PropertiesConfiguration(line.getArgs()[0]);

            InputStream input = System.in;
            OutputStream output = System.out;

            try {
                if (line.getArgs().length > 1) input = new FileInputStream(line.getArgs()[1]);
                if (line.hasOption("o")) output = new FileOutputStream(line.getOptionValue("o"));

                String template = IOUtils.toString(input, Charset.defaultCharset());

                output.write(StrSubstitutor.replace(template, ConfigurationConverter.getMap(props)).getBytes());

            } finally {
                if (input != System.in) IOUtils.closeQuietly(input);
                if (output != System.out) IOUtils.closeQuietly(output);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    protected CommandLine getCli(String[] args) throws ParseException {
        // create the command line parser
        CommandLineParser parser = new DefaultParser();

        // parse the command line arguments
        return parser.parse(options, args);
    }
}
