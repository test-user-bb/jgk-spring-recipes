## [AspectJ](http://www.eclipse.org/aspectj/) ##
### Resources ###
  * [docs](http://eclipse.org/aspectj/docs.php)
  * [Programming Guide](http://www.eclipse.org/aspectj/doc/released/progguide/index.html)
  * [faq](http://eclipse.org/aspectj/doc/released/faq.php)
  * [Eclipse plugin](http://eclipse.org/aspectj/docs.php)
  * [Aspect-Oriented Dependency Inversion (.pdf)](http://www.cs.ubc.ca/~kdvolder/Workshops/OOPSLA2001/submissions/12-nordberg.pdf)
  * AspectJ mailing list: **`aspectj-dev@eclipse.org`**
  * [AOP Glossary](http://www.aosd.net/wiki/index.php?title=Glossary)
### How does it work? ###
  * How it works with aspectj:
    * Create your regular program (.java files)
    * Create aspects (.aj files) that work on the Classes in your .java files
    * Place all the interesting files (and ajc options) in a `*.lst` file.
    * Run the aspectj compiler (include aspectjrt.jar in your classpath), give the name of the .lst file mentioned above.
    * ![http://www.manning.com/laddad/laddad_cover150.jpg](http://www.manning.com/laddad/laddad_cover150.jpg) Fender - Books - Computer - Manning - Manning - AspectJ in Action Enterprise AOP with Spring Applications 2nd-2010.pdf
    * [AspectJ in Action Code](http://manning.com/laddad2/)

Now you have all your .class files that have been weaved (or enhanced) with aspects.

### Other ###
  * Initial thoughts on the aspectj documentation:
    * Start rant
      * Was this written by a bureaucrat?
      * Significantly into the document and no descriptions of how to actually do things.
      * WTF is this ... countless caveats about considering this and that without ever showing a single practical example of the system in use.  _Q:  Do the authors want AOP to be adopted or do they simply like to hear themselves talk?_
      * This is one of the most tedious pieces of technical documentation that I can remember reading in recent history.
      * This reminds me of another tedious tome read during a masters program by Cornbluth?  It was like alphabet soup and buzz-word compliance...lots of parsley but no meat.
      * The "Getting Started" section should be renamed "Getting Teased" - it is like starting a '73 Ford LTD at 5 degrees Fahrenheit.  Sure the engine turns over a few times but it never actually Gets Started.
    * End rant
### Terms ###
  * ajc - aspectj compiler