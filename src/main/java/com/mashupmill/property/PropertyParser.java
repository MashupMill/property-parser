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
            String wrapper = "";
            String echar = escapeType.getEscapeChar();
            if (echar != null && value.replaceAll("\\\\ ", "").contains(" ")) {
                // if the escape type is a wrapper, then we should wrap the value, otherwise we escape individual characters
                if (escapeType.isWrapper()) {
                    value = value.replace(String.valueOf(echar), "\\" + echar);
                    wrapper = echar;
                } else {
                    value = value.replaceAll("([ '\"])", "\\" + echar + "$1");
                }
            }
            opts.append(String.format(" %s-D%s=%s%s", wrapper, key, value, wrapper));
        }

        return opts.toString().trim();
    }
}
