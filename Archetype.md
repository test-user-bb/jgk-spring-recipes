## Archetype ##
```
groupId: org.apache.maven.archetypes
artifactId: maven-archetype-webapp
```
### command to run ###
```
mvn archetype:create -DgroupId=com.jgk.myapp -DartifactId=jgkmyapp -DarchetypeArtifactId=maven-archetype-webapp

```
### output ###
```
CMD=mvn archetype:create -DgroupId=com.jgk.myapp -DartifactId=jgkmyapp -DarchetypeArtifactId=maven-archetype-webapp
[INFO] Scanning for projects...
[INFO]                                                                         
[INFO] ------------------------------------------------------------------------
[INFO] Building Maven Stub Project (No POM) 1
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- maven-archetype-plugin:2.0:create (default-cli) @ standalone-pom ---
[WARNING] This goal is deprecated. Please use mvn archetype:generate instead
[INFO] Defaulting package to group ID: com.jgk.myapp
Downloading: http://maven.springframework.org/milestone/org/apache/maven/archetypes/maven-archetype-webapp/maven-metadata.xml
Downloading: http://cruisebox.gs.local:80/nexus/content/groups/public/org/apache/maven/archetypes/maven-archetype-webapp/maven-metadata.xml
[WARNING] Could not transfer metadata org.apache.maven.archetypes:maven-archetype-webapp/maven-metadata.xml from nexus (http://cruisebox.gs.local:80/nexus/content/groups/public): Error transferring file: cruisebox.gs.local
[INFO] ----------------------------------------------------------------------------
[INFO] Using following parameters for creating project from Old (1.x) Archetype: maven-archetype-webapp:RELEASE
[INFO] ----------------------------------------------------------------------------
[INFO] Parameter: groupId, Value: com.jgk.myapp
[INFO] Parameter: packageName, Value: com.jgk.myapp
[INFO] Parameter: package, Value: com.jgk.myapp
[INFO] Parameter: artifactId, Value: jgkmyapp
[INFO] Parameter: basedir, Value: /private/tmp
[INFO] Parameter: version, Value: 1.0-SNAPSHOT
[INFO] ********************* End of debug info from resources from generated POM ***********************
[INFO] project created from Old (1.x) Archetype in dir: /private/tmp/jgkmyapp
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 7.037s
[INFO] Finished at: Sat Aug 06 15:41:08 MST 2011
[INFO] Final Memory: 7M/265M
[INFO] ------------------------------------------------------------------------
t
```