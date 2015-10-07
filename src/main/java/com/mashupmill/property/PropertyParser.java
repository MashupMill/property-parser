package com.mashupmill.property;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.util.Iterator;

/**
 * Created 10/7/15 @ 11:44 AM
 *
 * @author brandencash
 */
public class PropertyParser {

    public static void main(String[] args) {
        try {
            System.out.println(getOptionList(args[0]));
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static String getOptionList(String filename) throws ConfigurationException {
        StringBuilder opts = new StringBuilder();

        PropertiesConfiguration props = new PropertiesConfiguration(filename);
        Iterator<String> it = props.getKeys();

        while(it.hasNext()) {
            String key = it.next();
            String value = props.getString(key);
            if (value.contains(" ")) {
                value = "'" + value.replace("'", "\\'") + "'";
            }
            opts.append(String.format(" -D%s=%s", key, value));
        }

        return opts.toString().trim();
    }
}
