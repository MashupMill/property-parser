# property-parser
Simple java application that parses a java properties file (using apache's commons-configuration) and outputs it as program arguments

[![Build Status](https://travis-ci.org/MashupMill/property-parser.svg?branch=master)](https://travis-ci.org/MashupMill/property-parser)

# Compiling

Must have maven installed

Run `mvn clean package`

This creates an executable jar at `target/property-parser-1.0-SNAPSHOT.jar`

# Running

With a `test.properties` file like this:

```
foo=bar
hello=it's a foo ${foo} world
```

And once you have your executable jar you may then run something like this:

```bash
java -jar target/property-parser-1.0-SNAPSHOT.jar src/test/resources/test.properties -q
```

It should output something like this: `-Dfoo=bar '-Dhello=it\'s a foo bar world'`

# Commands

## `template`

The `template` command takes a properties file and a template file (or takes text from stdin) and does string substitution on the input using the properties file.
The string substitution is done using Apache's [StrSubstitutor](https://commons.apache.org/proper/commons-lang/javadocs/api-2.6/org/apache/commons/lang/text/StrSubstitutor.html).

You can run the `template` command using something like this:

```bash
java -jar target/property-parser-1.4.jar template src/test/resources/test.properties src/test/resources/template.html
```

It will write the output to stdout, unless you specify an `-o` or `--out` argument like this:

```bash
java -jar target/property-parser-1.4.jar template -o /tmp/output.html src/test/resources/test.properties src/test/resources/template.html
```

# Changelog

### Latest

* Added 'command' framework
* Added 'template' command. Which allows for replacing property placeholders in a file using Apache's [StrSubstitutor](https://commons.apache.org/proper/commons-lang/javadocs/api-2.6/org/apache/commons/lang/text/StrSubstitutor.html).

### 1.3

* Added EscapeType.NONE
* Change default EscapeType to DOUBLE_QUOTE

### 1.2.1

* Added travis-ci build

### 1.2

* Move the beginning of the wrapper quotes to include the `-Dkey=`. So instead of `-Dfoo='the bar'` it becomes `'-Dfoo=the bar'`

### 1.1

* Added escape type options:
    * -s / --slash which escapes spaces with a back slash
    * -q / --single-quote which escapes values with spaces by wrapping them in single quotes
    * -d / --double-quote which escapes values with spaces by wrapping them in double quotes
* Default escape type is back slash (which is different from v1.0...single quotes)

### 1.0

* Initial release. Parses properties file as java `-D` options. Escapes the values with spaces by wrapping the value in single quotes.
