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
