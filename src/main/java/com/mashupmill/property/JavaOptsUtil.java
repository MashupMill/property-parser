package com.mashupmill.property;

import org.apache.commons.lang3.text.StrSubstitutor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * Created 10/7/15 @ 11:44 AM
 *
 * @author brandencash
 */
public class JavaOptsUtil {

    public static String getOptionList(String filename) throws IOException {
        return getOptionList(filename, null);
    }

    public static String getOptionList(String filename, EscapeType escapeType) throws IOException {
        if (escapeType == null) {
            escapeType = EscapeType.DOUBLE_QUOTE;
        }

        StringBuilder opts = new StringBuilder();
        Map props = new Properties();
        ((Properties) props).load(new FileInputStream(filename));
        StrSubstitutor substitutor = new StrSubstitutor(props);
        substitutor.setEnableSubstitutionInVariables(true);

        //PropertiesConfiguration props = new PropertiesConfiguration(filename);
        for (Map.Entry<Object, Object> set : ((Properties) props).entrySet()) {
            String key = String.valueOf(set.getKey());
            String value = substitutor.replace(String.valueOf(set.getValue()));
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
