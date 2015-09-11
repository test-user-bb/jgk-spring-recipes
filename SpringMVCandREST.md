## Articles et al ##
  * [Blog rest + spring mvc](http://blog.springsource.com/2009/03/08/rest-in-spring-3-mvc/)
## Content negotiation ##
  * Do not rely on accept header
  * Use file extension (or path mechanism)
## Spring MVC RESTful applications ##
  * Request/Response processing
  * Uses `MessageConverters`
  * Content negotiation
## Spring MVC REST ##
  * URI templates to parse `RESTful` URLs
  * Views for multiple resource representations (json, pdf, xml, etc.)
### Request mapping at method ###
  * you can map HTTP requests based on method
    * same URL can be mapped to multiple methods
    * used for form-based controllers (GET/POST)
```
@RequestMapping(value="/routes", method=RequestMethod.GET)
public void getTheRoutes(Model model) {
  // retrieve all administration routes and add them to the model
}

@RequestMapping(value="/routes", method=RequestMethod.POST)
public void createRoute(HttpServletRequest request, Model model) {
  // process route data from request
}
```
## REST + JAVA:  a match made in Jersey? ##
  * [JAX-RS](http://en.wikipedia.org/wiki/JAX-RS) is JEE 6 standard for building RESTful applications _NOTE: Spring MVC does NOT implement JAX-RS_
  * _JSR 311: JAX-RS_
  * Programmatic clients - (don't worry about the browser - data to data)
  * JAX-RS Implementations:
    * [Jersey](http://jersey.java.net/) - reference implementation (RI)
    * [RESTEasy](http://en.wikipedia.org/wiki/RESTEasy) - from JBoss
    * [Restlet](http://en.wikipedia.org/wiki/Restlet)
    * [CXF](http://en.wikipedia.org/wiki/Apache_CXF)
## JAX-RS Annotations ##
  * `@Path` specifies the relative path for a resource class.
  * `@GET`, `@PUT`, `@POST`，`@DELETE`, specifies the HTTP request type of a resource method.
  * `@Produces`, specifies the returned MIME media types
  * `@Consumes`, specifies the acceptable request media types.
  * `@PathParam`, `@QueryParam`, `@HeaderParam`, `@CookieParam`, `@MatrixParam`, `@FormParam`, specifies the source of the method parameter values
    * `@PathParam` comes from URL path
    * `@QueryParam` comes from URL query parameter
    * `@HeaderParam` comes from HTTP header
    * `@CookieParam` comes from Cookie in HTTP request.
## REST General ##
  * REST = REpresentational State Transfer (Roy Fielding's term)
  * HTTP as an application protocol, not just transport
  * Emphasizes scalability
## REST ##
  * Expose resources through `URIs`
    * Model nouns, not verbs
  * Resource operations:
    * GET - read or retrieve a resource
    * PUT - update (or put) a resource
    * POST
    * DELETE
## What comes back? ##
  * Clients ask for the specific representation
    * HTML
    * XML
    * JSON
    * ...anything you can imagine (or just about)
## Value of REST ##
  * loose coupling between client and server
  * clients track state (not server - stateless)
  * MIME types provide contract for resource interaction
## More REST ##
  * REST is a **Stateless** architecture
    * so no `HttpSession` used
    * GETs can be cached on the URL
    * It is `statelessness` that makes it scalable
  * Focus on nouns (rather than verbs)
## Communication ##
  * Communicate to clients through
    * HTTP Headers
    * HTTP Status codes
> > _defined in HTTP specification_