  * [Introduction](ArchetypeSpringRest#Introduction.md)
  * [create archetype from the project](ArchetypeSpringRest#create_archetype_from_the_project.md)
  * [install the archetype locally](ArchetypeSpringRest#install_the_archetype_locally.md)
  * [using the archetype](ArchetypeSpringRest#using_the_archetype.md)
  * [test in jetty](ArchetypeSpringRest#test_in_jetty.md)
  * [location of source](ArchetypeSpringRest#location_of_source.md)

# Introduction #
  * When creating a new project where you know you are going to want spring and REST capabilities, this archetype can get you started.
  * creates a maven war project with some directory structures
  * when completed you will have a working restful web application
  * to make this happen, the project skeleton was first manually created
  * then use mvn to make an archetype based on the project:

## create archetype from the project ##
```
jgk-spring-rest jkroub$ mvn archetype:create-from-project
[INFO] Scanning for projects...
[INFO]                                                                         
[INFO] ------------------------------------------------------------------------
[INFO] Building JGK Spring and REST Webapp 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] >>> maven-archetype-plugin:2.0:create-from-project (default-cli) @ jgk-spring-rest >>>
[INFO] 
[INFO] <<< maven-archetype-plugin:2.0:create-from-project (default-cli) @ jgk-spring-rest <<<
[INFO] 
[INFO] --- maven-archetype-plugin:2.0:create-from-project (default-cli) @ jgk-spring-rest ---
[INFO] Setting default groupId: com.jgk.springrecipes.rest
[INFO] Setting default artifactId: jgk-spring-rest
[INFO] Setting default version: 1.0-SNAPSHOT
[INFO] Setting default package: com.jgk.springrecipes.rest
[INFO] Scanning for projects...
[INFO]                                                                         
[INFO] ------------------------------------------------------------------------
[INFO] Building jgk-spring-rest-archetype 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.5:resources (default-resources) @ jgk-spring-rest-archetype ---
[debug] execute contextualize
[WARNING] Using platform encoding (MacRoman actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] Copying 27 resources
[INFO] 
[INFO] --- maven-resources-plugin:2.5:testResources (default-testResources) @ jgk-spring-rest-archetype ---
[debug] execute contextualize
[WARNING] Using platform encoding (MacRoman actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] Copying 2 resources
[INFO] 
[INFO] --- maven-archetype-plugin:2.0:jar (default-jar) @ jgk-spring-rest-archetype ---
[INFO] 
[INFO] --- maven-archetype-plugin:2.0:add-archetype-metadata (default-add-archetype-metadata) @ jgk-spring-rest-archetype ---
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 1.756s
[INFO] Finished at: Sun Aug 07 22:03:59 MST 2011
[INFO] Final Memory: 6M/265M
[INFO] ------------------------------------------------------------------------
[INFO] Archetype created in /Users/jkroub/Documents/workspace-jgk-spring-recipes/jgk-spring-rest/target/generated-sources/archetype
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 6.130s
[INFO] Finished at: Sun Aug 07 22:03:59 MST 2011
[INFO] Final Memory: 7M/265M
[INFO] ------------------------------------------------------------------------

```
## install the archetype locally ##
```
jgk-spring-rest jkroub$ cd target/generated-sources/archetype/
archetype jkroub$ mvn install
[INFO] Scanning for projects...
[INFO]                                                                         
[INFO] ------------------------------------------------------------------------
[INFO] Building jgk-spring-rest-archetype 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.5:resources (default-resources) @ jgk-spring-rest-archetype ---
[debug] execute contextualize
[WARNING] Using platform encoding (MacRoman actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] Copying 27 resources
[INFO] 
[INFO] --- maven-resources-plugin:2.5:testResources (default-testResources) @ jgk-spring-rest-archetype ---
[debug] execute contextualize
[WARNING] Using platform encoding (MacRoman actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] Copying 2 resources
[INFO] 
[INFO] --- maven-archetype-plugin:2.0:jar (default-jar) @ jgk-spring-rest-archetype ---
[INFO] 
[INFO] --- maven-archetype-plugin:2.0:add-archetype-metadata (default-add-archetype-metadata) @ jgk-spring-rest-archetype ---
[INFO] 
[INFO] --- maven-archetype-plugin:2.0:integration-test (default-integration-test) @ jgk-spring-rest-archetype ---
[INFO] 
[INFO] --- maven-install-plugin:2.3.1:install (default-install) @ jgk-spring-rest-archetype ---
[INFO] Installing /Users/jkroub/Documents/workspace-jgk-spring-recipes/jgk-spring-rest/target/generated-sources/archetype/target/jgk-spring-rest-archetype-1.0-SNAPSHOT.jar to /Users/jkroub/.m2/repository/com/jgk/springrecipes/rest/jgk-spring-rest-archetype/1.0-SNAPSHOT/jgk-spring-rest-archetype-1.0-SNAPSHOT.jar
[INFO] Installing /Users/jkroub/Documents/workspace-jgk-spring-recipes/jgk-spring-rest/target/generated-sources/archetype/pom.xml to /Users/jkroub/.m2/repository/com/jgk/springrecipes/rest/jgk-spring-rest-archetype/1.0-SNAPSHOT/jgk-spring-rest-archetype-1.0-SNAPSHOT.pom
[INFO] 
[INFO] --- maven-archetype-plugin:2.0:update-local-catalog (default-update-local-catalog) @ jgk-spring-rest-archetype ---
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 3.036s
[INFO] Finished at: Sun Aug 07 22:04:58 MST 2011
[INFO] Final Memory: 6M/265M
[INFO] ------------------------------------------------------------------------

```
## using the archetype ##
  * the `archetype:generate` is used
  * user is prompted for some information:
    * the locally installed archetype to use (here it is #2)
    * `groupId`
    * `artifactId`
    * `version`
    * `package`
  * since this is a spring-based web application, there is a servlet configuration file that is read by the context when the web application is boot-strapped.  For this archetype it is called `jgk-spring-rest-webapp-config.xml`, which is related to the `artifactId`.  If you specify `jgk-spring-rest` as the artifactId then all will be well.  If you specify a different `artifactId` you will need to rename the configuration file.
jgk-spring-rest
```
 jkroub$ mvn archetype:generate -DarchetypeCatalog=local
[INFO] Scanning for projects...
[INFO]                                                                         
[INFO] ------------------------------------------------------------------------
[INFO] Building Maven Stub Project (No POM) 1
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] >>> maven-archetype-plugin:2.0:generate (default-cli) @ standalone-pom >>>
[INFO] 
[INFO] <<< maven-archetype-plugin:2.0:generate (default-cli) @ standalone-pom <<<
[INFO] 
[INFO] --- maven-archetype-plugin:2.0:generate (default-cli) @ standalone-pom ---
[INFO] Generating project in Interactive mode
[INFO] No archetype defined. Using maven-archetype-quickstart (org.apache.maven.archetypes:maven-archetype-quickstart:1.0)
Choose archetype:
1: local -> maven-archetype-webapp (-)
2: local -> jgk-spring-rest-archetype (jgk-spring-rest-archetype)
Choose a number: : 2
Define value for property 'groupId': : com.jgk.testspringrest
Define value for property 'artifactId': : jgk-spring-rest
Define value for property 'version':  1.0-SNAPSHOT: : 
Define value for property 'package':  com.jgk.testspringrest: : 
Confirm properties configuration:
groupId: com.jgk.testspringrest
artifactId: jgk-spring-rest
version: 1.0-SNAPSHOT
package: com.jgk.testspringrest
 Y: : 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 43.793s
[INFO] Finished at: Sun Aug 07 22:11:54 MST 2011
[INFO] Final Memory: 7M/265M
[INFO] ------------------------------------------------------------------------

```
## test in jetty ##
  * run jetty
```
$ mvn jetty:run
```
  * access in browser:
```
http://localhost:8080/jgk-spring-rest/
```
## location of source ##
  * [here](https://jgk-spring-recipes.googlecode.com/svn/trunk/jgk-spring-rest)