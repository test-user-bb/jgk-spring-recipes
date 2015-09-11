## ajc Help ##
  * [ajc developer guide](http://eclipse.org/aspectj/doc/released/devguide/index.html)
  * [ajc compiler/weaver detail](http://www.eclipse.org/aspectj/doc/released/devguide/ajc-ref.html)
  * command-line help:
```

	Usage: <options> <source file | @argfile>..

AspectJ-specific options:
	-inpath <list>      use classes in dirs and jars/zips in <list> as source
	                    (<list> uses platform-specific path delimiter)
	-injars <jarList>   use classes in <jarList> zip files as source
	                    (<jarList> uses classpath delimiter)
	                    deprecated - use inpath instead.
	-aspectpath <list>  weave aspects in .class files from <list> dirs and jars/zip into sources
	                    (<list> uses classpath delimiter)
	-outjar <file>      put output classes in zip file <file>
	-outxml             generate META-INF/aop.xml
	-outxmlfile <file>  specify alternate destination output of -outxml
	-argfile <file>     specify line-delimited list of source files
	-showWeaveInfo      display information about weaving
	-incremental        continuously-running compiler, needs -sourceroots
	                    (reads stdin: enter to recompile and 'q' to quit)
	-sourceroots <dirs> compile all .aj and .java files in <dirs>
	                    (<dirs> uses classpath delimiter)
	-crossrefs          generate .ajsym file into the output directory
	-emacssym           generate .ajesym symbol files for emacs support
	-Xlint              same as '-Xlint:warning'
	-Xlint:<level>      set default level for crosscutting messages
	                    (<level> may be ignore, warning, or error)
	-Xlintfile <file>   specify properties file to set per-message levels
	                    (cf org/aspectj/weaver/XlintDefault.properties)
	-X                  print help on non-standard options

Standard Eclipse compiler options:
 Options enabled by default are prefixed with '+'
 
 Classpath options:
    -cp -classpath <directories and zip/jar files separated by ;>
                       specify location for application classes and sources
    -bootclasspath <directories and zip/jar files separated by ;>
                       specify location for system classes
    -d <dir>           destination directory (if omitted, no directory is created)
    -d none            generate no .class files
    -encoding <enc>    specify custom encoding for all sources. Each file/directory can override it
                       when suffixed with '['<enc>']' (e.g. X.java[utf8])
 
 Compliance options:
    -1.3               use 1.3 compliance level (implicit -source 1.3 -target 1.1)
    -1.4             + use 1.4 compliance level
    -1.5               use 1.5 compliance level
    -1.6               use 1.6 compliance level
    -source <version>  set source level (1.3, 1.4, 1.5 or 1.6)
    -target <version>  set classfile target (1.1 to 1.4)
 
 Warning options:
    -deprecation     + deprecation outside deprecated code
    -nowarn            disable all warnings except xlint or declare warning
    -warn:none         disable all warnings except xlint or declare warning
    -warn:<warnings separated by ,>    enable exactly the listed warnings
    -warn:+<warnings separated by ,>   enable additional warnings
    -warn:-<warnings separated by ,>   disable specific warnings
      allDeprecation       deprecation including inside deprecated code
      allJavadoc           invalid or missing javadoc
      assertIdentifier   + 'assert' used as identifier
      charConcat         + char[] in String concat
      conditionAssign      possible accidental boolean assignment
      constructorName    + method with constructor name
      deprecation        + deprecation outside deprecated code
      emptyBlock           undocumented empty block
      fieldHiding          field hiding another variable
      finally            + finally block not completing normally
      indirectStatic       indirect reference to static member
      intfNonInherited   + interface non-inherited method compatibility
      javadoc              invalid javadoc
      localHiding          local variable hiding another variable
      maskedCatchBlock   + hidden catch block
      nls                  string literal lacking non-nls tag //$NON-NLS-<n>$
      noEffectAssign     + assignment without effect
      pkgDefaultMethod   + attempt to override package-default method
      semicolon            superfluous semicolon
      unqualifiedField     unqualified reference to field
      unusedImport       + unused import declaration
      unusedLocal          unread local variable
      unusedPrivate        unused private member declaration
      unusedThrown         unused declared thrown exception
      uselessTypeCheck     unnecessary cast/instanceof operation
      specialParamHiding   constructor or setter parameter hiding another field
      staticReceiver     + non-static reference to static member
      syntheticAccess      synthetic access for innerclass
      tasks(<tags separated by |>) tasks identified by tags inside comments
 
 Debug options:
    -g[:lines,vars,source] custom debug info
    -g:lines,source  + both lines table and source debug info
    -g                 all debug info
    -g:none            no debug info
    -preserveAllLocals preserve unused local vars for debug purpose
 
 Advanced options:
    -log <file>        log to a file
    -proceedOnError    do not stop at first error, dumping class files with problem methods
    -verbose           enable verbose output
    -referenceInfo     compute reference info
    -progress          show progress (only in -log mode)
    -time              display speed information 
    -noExit            do not call System.exit(n) at end of compilation (n==0 if no error)
    -repeat <n>        repeat compilation process <n> times for perf analysis
    @<file>            read command line arguments from file
 
    -? -help           print this help message
    -v -version        print compiler version
    -showversion       print compiler version and continue


```