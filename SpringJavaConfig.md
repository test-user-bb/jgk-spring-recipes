## Info ##
  * in order for `JavaConfig` to work, you must include the CGLIB jar in your list of dependencies.
  * All `@Configuration` classes are subclassed at startup-time with CGLIB.
  * Restrictions due to the fact that CGLIB dynamically adds features at startup-time:
    * Configuration classes should not be final
    * They should have a constructor with no arguments