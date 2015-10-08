package com.mashupmill.property;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Created 10/7/15 @ 11:44 AM
 *
 * @author brandencash
 */
public class PropertyParserCli {

    public static void main(String[] args) {
        try {
            CommandLine line = getCli(args);

            EscapeType escapeType = line.hasOption("s") ? EscapeType.SLASH : null;
            escapeType = line.hasOption("q") ? EscapeType.SINGLE_QUOTE : escapeType;
            escapeType = line.hasOption("d") ? EscapeType.DOUBLE_QUOTE : escapeType;

            System.out.println(PropertyParser.getOptionList(args[0], escapeType));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    protected static CommandLine getCli(String[] args) throws ParseException {
        // create the command line parser
        CommandLineParser parser = new DefaultParser();

        // create the Options
        Options options = new Options();
        OptionGroup escapeType = new OptionGroup();
        escapeType.addOption(new Option("s", "slash", false, "escape with back slash"));
        escapeType.addOption(new Option("q", "single-quote", false, "escape with single quotes"));
        escapeType.addOption(new Option("d", "double-quote", false, "escape with double quotes"));
        options.addOptionGroup(escapeType);

        // parse the command line arguments
        return parser.parse(options, args);
    }
}
