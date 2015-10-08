# property-parser
Simple java application that parses a java properties file (using apache's commons-configuration) and outputs it as program arguments

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
java -jar target/property-parser-1.0-SNAPSHOT.jar src/test/resources/test.properties
```

It should output something like this: `-Dfoo=bar -Dhello='it\'s a foo bar world'`

# Changelog

### 1.1

* Added escape type options:
    * -s / --slash which escapes spaces with a back slash
    * -q / --single-quote which escapes values with spaces by wrapping them in single quotes
    * -d / --double-quote which escapes values with spaces by wrapping them in double quotes
* Default escape type is back slash (which is different from v1.0...single quotes)

### 1.0

* Initial release. Parses properties file as java `-D` options. Escapes the values with spaces by wrapping the value in single quotes.
