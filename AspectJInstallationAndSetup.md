## Summary ##
  * Download
  * Install
  * Post install setup

### Download ###
  * download from [Aspectj Downloads](http://eclipse.org/aspectj/downloads.php) page

### Install ###
```
java -jar aspectj-1.6.11.jar
```
### Post install setup ###
  * modify PATH by editing .bash\_profile
```
export PATH="${PATH}:/Users/jkroub/aspectj1.6/bin"
```
  * when aspectJ required simply include the jar (aspectj-1.6.11.jar) on the classpath
    * There are several ways to add this jar file to your classpath:
```
copy aspectjrt.jar to the jdk/jre/lib/ext directory
add aspectjrt.jar to your CLASSPATH environment variable (see the next section for details)
always use the "-classpath aspectjrt.jar" option when running programs compiled with ajc

```

### Screenshots ###
> ![http://i299.photobucket.com/albums/mm283/javapda/Screenshot2011-05-17at114915PM.png](http://i299.photobucket.com/albums/mm283/javapda/Screenshot2011-05-17at114915PM.png)
> ![http://i299.photobucket.com/albums/mm283/javapda/Screenshot2011-05-17at114904PM.png](http://i299.photobucket.com/albums/mm283/javapda/Screenshot2011-05-17at114904PM.png)
> ![http://i299.photobucket.com/albums/mm283/javapda/Screenshot2011-05-17at114859PM.png](http://i299.photobucket.com/albums/mm283/javapda/Screenshot2011-05-17at114859PM.png)
> ![http://i299.photobucket.com/albums/mm283/javapda/Screenshot2011-05-17at114852PM.png](http://i299.photobucket.com/albums/mm283/javapda/Screenshot2011-05-17at114852PM.png)
> ![http://i299.photobucket.com/albums/mm283/javapda/Screenshot2011-05-17at114751PM.png](http://i299.photobucket.com/albums/mm283/javapda/Screenshot2011-05-17at114751PM.png)
> ![http://i299.photobucket.com/albums/mm283/javapda/Screenshot2011-05-17at114651PM.png](http://i299.photobucket.com/albums/mm283/javapda/Screenshot2011-05-17at114651PM.png)