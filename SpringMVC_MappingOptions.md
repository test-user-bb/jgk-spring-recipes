  * Path
```
@RequestMapping("path")
```
  * HTTP method
```
@RequestMapping("path", method=RequestMethod.GET)
```
    * Supports:  GET, POST, PUT, DELETE, OPTIONS, and TRACE
  * Presence of a query parameter
```
@RequestMapping("path", method=RequestMethod.GET,params="jed")
```
    * Negation supported: ` params={"jed","!jethro"} `
  * Presence of a request header
```
@RequestMapping("path", header="context-type=text/*")
```
    * Negation supported