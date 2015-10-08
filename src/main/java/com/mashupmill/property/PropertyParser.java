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

    public static String getOptionList(String filename) throws ConfigurationException {
        return getOptionList(filename, null);
    }

    public static String getOptionList(String filename, EscapeType escapeType) throws ConfigurationException {
        if (escapeType == null) {
            escapeType = EscapeType.SLASH;
        }

        StringBuilder opts = new StringBuilder();

        PropertiesConfiguration props = new PropertiesConfiguration(filename);
        Iterator<String> it = props.getKeys();

        while(it.hasNext()) {
            String key = it.next();
            String value = props.getString(key);
            if (value.replaceAll("\\\\ ", "").contains(" ")) {
                // Get the escape character
                String echar = escapeType.getEscapeChar();

                // if the escape type is a wrapper, then we should wrap the value, otherwise we escape individual characters
                if (escapeType.isWrapper()) {
                    value = echar + value.replace(String.valueOf(echar), "\\" + echar) + echar;
                } else {
                    value = value.replaceAll("([ '\"])", "\\" + echar + "$1");
                }
            }
            opts.append(String.format(" -D%s=%s", key, value));
        }

        return opts.toString().trim();
    }
}
