package com.mashupmill.property.cli;

import com.mashupmill.property.cli.commands.Command;
import com.mashupmill.property.cli.commands.Template;
import com.mashupmill.property.cli.commands.ToJavaOpts;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created 10/14/15 @ 10:43 PM
 *
 * @author brandencash
 */
public class Main {
    private static Map<String, Class<? extends Command>> commands;

    static {
        Map<String, Class<? extends Command>> cmds = new HashMap<String, Class<? extends Command>>();

        // Register commands here
        cmds.put(ToJavaOpts.NAME.toLowerCase(), ToJavaOpts.class);
        cmds.put(Template.NAME.toLowerCase(), Template.class);

        commands = Collections.unmodifiableMap(cmds);
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Must provide a command");
            System.err.println("Available commands are:");
            for(String name : commands.keySet()) {
                System.err.println("\t- " + name);
            }
            System.exit(1);
        }

        String cmd = args[0].toLowerCase();
        if (commands.containsKey(cmd)) {
            // if the command exists then pull it out of the arguments to be passed to the command
            args = Arrays.copyOfRange(args, 1, args.length);
        } else {
            // otherwise default to the parse command
            cmd = ToJavaOpts.NAME;
        }

        try {
            Command command = commands.get(cmd).newInstance();
            command.exec(args);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
