### fmt ###
  * Need the following page directive:
```
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
```
### out ###
  * sends output to the page
```
<c:out value="Hello JSTL"/>
```
### url ###
    * Nice sample [HERE](http://www.roseindia.net/jsp/simple-jsp-example/JSTLConstructingURLs.shtml)
    * Pages need the following directive:
```
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
```
    * use ` <c:url/> `
      * Simplest is url with no params:
```
  <c:url value="/some/path"/>
```
      * ` <c:url value=""/> `
```
<c:url value="${param.url}" var="url">
<c:param name="name" value="Ben.Franklin/>
<c:param name="pwd" value="bfrank"/>
<c:param name="email" value="kiteman@philly.org"/>
</c:url>
....
The url is: ${url}, which can be used in a link:
<a href="${url}"/>
```
      * Other
```
<c:url value="${param.url}" var="url">
<c:param name="name" value="${param.name}"/>
<c:param name="pwd" value="${param.pwd}"/>
<c:param name="email" value="${param.email}"/>
</c:url>
```