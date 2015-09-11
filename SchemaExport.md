### general ###
  * SchemaExport is a Hibernate utility which generates DDL from your mapping files.
  * The generated schema includes:
    * referential integrity constraints
    * primary and foreign keys, for entity and collection tables.
  * It also creates tables and sequences for mapped identifier generators.
### format ###
```
java -cp hibernate_classpaths org.hibernate.tool.hbm2ddl.SchemaExport options mapping_files
```
### options ###
```
--quiet	do not output the script to standard output
--drop	only drop the tables
--create	only create the tables
--text	do not export to the database
--output=my_schema.ddl	output the ddl script to a file
--naming=eg.MyNamingStrategy	select a NamingStrategy
--config=hibernate.cfg.xml	read Hibernate configuration from an XML file
--properties=hibernate.properties	read database properties from a file
--format	format the generated SQL nicely in the script
--delimiter=;	set an end-of-line delimiter for the script
```
### script ###
```
#!/bin/bash
echo "Performing SchemaExport"
MAVEN_REPO=~/.m2/repository
HIB_VERSION=4.0.0.CR7
HIB_JAR=${MAVEN_REPO}/org/hibernate/hibernate-core/${HIB_VERSION}/hibernate-core-${HIB_VERSION}.jar
HIB_ANNOT_JAR=${MAVEN_REPO}/org/hibernate/common/hibernate-commons-annotations/4.0.1.Final/hibernate-commons-annotations-4.0.1.Final.jar
HIB_JPA_JAR=${MAVEN_REPO}/org/hibernate/javax/persistence/hibernate-jpa-2.0-api/1.0.0.Final/hibernate-jpa-2.0-api-1.0.0.Final.jar
HSQLDB_JAR=${MAVEN_REPO}/hsqldb/hsqldb/1.8.0.7/hsqldb-1.8.0.7.jar
JBOSS_LOG_JAR=${MAVEN_REPO}/org/jboss/logging/jboss-logging/3.0.0.GA/jboss-logging-3.0.0.GA.jar
JTA_JAR=${MAVEN_REPO}/javax/transaction/jta/1.1/jta-1.1.jar
DOM4J_JAR=${MAVEN_REPO}/dom4j/dom4j/1.6.1/dom4j-1.6.1.jar
CP="${CP}:${HIB_JAR}"
CP="${CP}:${HIB_ANNOT_JAR}"
CP="${CP}:${HIB_JPA_JAR}"
CP="${CP}:${HSQLDB_JAR}"
CP="${CP}:${JBOSS_LOG_JAR}"
CP="${CP}:${JTA_JAR}"
CP="${CP}:${DOM4J_JAR}"
#ls -trl ${HIB_JAR}
#ls -trl ${HIB_ANNOT_JAR}
ls -trl ${HIB_JPA_JAR}
#ls -trl ${HSQLDB_JAR}
#ls -trl ${JBOSS_LOG_JAR}
#ls -trl ${JTA_JAR}
#ls -trl ${DOM4J_JAR}
HSQLDB_PROPERTIES_FILE=~/bin/hsqldb.hibernate.properties
HIB_PROPERTIES=${HSQLDB_PROPERTIES_FILE}
OPTIONS="${OPTIONS} --properties=${HIB_PROPERTIES}"
OPTIONS="${OPTIONS} --format"
OPTIONS="${OPTIONS} --output=my_schema.ddl"
OPTIONS="${OPTIONS} --text"
#OPTIONS="${OPTIONS} --naming=com.MyNamingStrategy"
#OPTIONS="${OPTIONS} --create"
#OPTIONS="${OPTIONS} --drop"
#OPTIONS="${OPTIONS} --quiet"
WORKSPACE=/Users/jkroub/Documents/workspace-gsonly
MAP_HOME="${WORKSPACE}/gs/src/main/resources"
MAPPING_FILES="${MAPPING_FILES} ${MAP_HOME}/com/gs/core/domain/adt/Address.hbm.xml"
CMD="java -cp ${CP} org.hibernate.tool.hbm2ddl.SchemaExport ${OPTIONS} ${MAPPING_FILES}"
echo ${CMD}
${CMD}

```
### Tool ###
```
import java.io.File;
import java.net.URL;

import org.hibernate.cfg.DefaultNamingStrategy;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

public class SchemaExportTest {

    @Test public void doit() {
        File hibProperties = new File(System.getProperty("user.home"),"bin/hsqldb.hibernate.properties");
        if(hibProperties.exists()) {
            
            System.out.println(hibProperties.getAbsolutePath());
            String[] args = new String[] {
                    String.format("--properties=%s",new File(System.getProperty("user.home"),"bin/hsqldb.hibernate.properties").getAbsolutePath()),
//                    String.format("--import=%s",new File(System.getProperty("user.home"),"bin/hsqldb.hibernate.sql").getAbsolutePath()),
                    "--format",
//                    "--text",  // do not export to database
                    "--output=my_schema.ddl",  // output the ddl script to a file
                    String.format("--naming=%s",DefaultNamingStrategy.class.getName()), // select a naming strategy
//                    "--drop",  // only drop tables
                    "--create", // only create tables
//                    "--quiet",  // do not output script to standard out
                    getMappingFile("com/gs/core/domain/adt/Address.hbm.xml"),
            };
//            System.getProperties().list(System.out);
            SchemaExport.main(args);
        }
    }
    private String getMappingFile(String pathToFile) {
        URL url =SchemaExportTest.class.getClassLoader().getResource(pathToFile);
        System.out.println(url.getFile());
        return url.getFile();
    }
}

```