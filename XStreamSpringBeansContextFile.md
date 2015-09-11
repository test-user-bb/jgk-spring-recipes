# Introduction #
  * uses XStream to walk through and dump all the spring beans in a configuration file.
    * [xstream converters](http://xstream.codehaus.org/converter-tutorial.html)
<pre>
package com.jgk.springrecipes.util.unittest.support;<br>
<br>
import java.io.BufferedReader;<br>
import java.io.File;<br>
import java.io.FileNotFoundException;<br>
import java.io.FileReader;<br>
import java.io.IOException;<br>
import java.util.ArrayList;<br>
import java.util.Collections;<br>
import java.util.List;<br>
<br>
import org.junit.Assert;<br>
import org.junit.Ignore;<br>
import org.junit.Test;<br>
<br>
import com.thoughtworks.xstream.XStream;<br>
import com.thoughtworks.xstream.converters.Converter;<br>
import com.thoughtworks.xstream.converters.MarshallingContext;<br>
import com.thoughtworks.xstream.converters.UnmarshallingContext;<br>
import com.thoughtworks.xstream.io.HierarchicalStreamReader;<br>
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;<br>
<br>
public class SpringBeansFileExaminer {<br>
@Test<br>
public void testFromSpringFile() {<br>
File file = new File("src/main/resources/spring-beans-legacy.xml");<br>
//        File file = new File("src/main/resources/spring-beans-core.xml");<br>
Assert.assertTrue(file.exists());<br>
XStream xstream = new XStream();<br>
xstream.alias("bean", SpringBean.class);<br>
xstream.alias("beans", SpringBeans.class);<br>
xstream.alias("constructorArg", ConstructorArg.class);<br>
xstream.alias("constructor-arg", ConstructorArg.class);<br>
xstream.addImplicitCollection(SpringBean.class, "constructorArgs");<br>
xstream.addImplicitCollection(SpringBean.class, "properties");<br>
xstream.useAttributeFor(SpringBean.class, "id");<br>
xstream.useAttributeFor(SpringBean.class, "clazz");<br>
xstream.aliasAttribute("clazz", "class");<br>
xstream.registerConverter(new RefConverter());<br>
xstream.registerConverter(new SpringBeanConverter());<br>
xstream.registerConverter(new SpringBeanPropertyConverter());<br>
xstream.registerConverter(new ConstructorArgConverter());<br>
xstream.registerConverter(new SpringBeansConverter());<br>
BufferedReader br = null;<br>
try {<br>
SpringBeans sb = (SpringBeans) xstream.fromXML(new BufferedReader(<br>
new FileReader(file)));<br>
Collections.sort(sb.beans);<br>
int count = 0;<br>
int targetCount=0;<br>
int daoCount=0;<br>
int serviceCount=0;<br>
for (SpringBean springBean : sb.beans) {<br>
count++;<br>
//                System.out.printf("%3d. %s, %s, %s\n", count, springBean.id,<br>
//                        springBean.clazz, springBean.dependsOn);<br>
String id = springBean.id;<br>
if(id!=null&&id.toUpperCase().endsWith("DAOTARGET")) {<br>
targetCount++;<br>
daoCount++;<br>
System.out.printf("* %s, %s\n", springBean.id, springBean.clazz);<br>
//                    System.out.printf("%3d. %s, %s\n", targetCount, springBean.id, springBean.clazz);<br>
}<br>
if(id!=null&&id.toUpperCase().endsWith("SERVICETARGET")) {<br>
targetCount++;<br>
serviceCount++;<br>
System.out.printf("* %s, %s\n", springBean.id, springBean.clazz);<br>
//                    System.out.printf("%3d. %s, %s\n", targetCount, springBean.id, springBean.clazz);<br>
}<br>
}<br>
System.out.printf("NO. TOTAL: %d\n",count);<br>
System.out.printf("NO. TARGETS: %d\n",targetCount);<br>
System.out.printf("NO. DAO TARGETS: %d\n",daoCount);<br>
System.out.printf("NO. SERVICE TARGETS: %d\n",serviceCount);<br>
} catch (FileNotFoundException e) {<br>
e.printStackTrace();<br>
} finally {<br>
if (br != null) {<br>
try {<br>
br.close();<br>
} catch (IOException e) {<br>
// TODO Auto-generated catch block<br>
e.printStackTrace();<br>
}<br>
}<br>
}<br>
}<br>
<br>
@Ignore<br>
@Test<br>
public void testNow() {<br>
SpringBean springBean = new SpringBean("someBeanId",<br>
"com.some.classname.HEREEE", "noscope", "someparent");<br>
springBean.addConstructorArg(new ConstructorArg(new Ref(<br>
"someConstructorArg")));<br>
springBean.addConstructorArg(new ConstructorArg(new Ref(<br>
"anotherConstructorArg")));<br>
springBean.addProperty(new SpringBeanProperty("someBeanProperty"));<br>
<br>
XStream xstream = new XStream();<br>
xstream.alias("bean", SpringBean.class);<br>
xstream.alias("beans", SpringBeans.class);<br>
xstream.alias("constructorArg", ConstructorArg.class);<br>
xstream.alias("constructor-arg", ConstructorArg.class);<br>
xstream.addImplicitCollection(SpringBean.class, "constructorArgs");<br>
xstream.addImplicitCollection(SpringBean.class, "properties");<br>
xstream.useAttributeFor(SpringBean.class, "id");<br>
xstream.useAttributeFor(SpringBean.class, "clazz");<br>
xstream.aliasAttribute("clazz", "class");<br>
xstream.registerConverter(new RefConverter());<br>
xstream.registerConverter(new SpringBeanConverter());<br>
xstream.registerConverter(new SpringBeanPropertyConverter());<br>
xstream.registerConverter(new ConstructorArgConverter());<br>
xstream.registerConverter(new SpringBeansConverter());<br>
<br>
StringBuilder sb = new StringBuilder();<br>
sb.append("<beans>");<br>
sb.append("<bean class=\"freakClassHere\" id=\"Franklin\">");<br>
sb.append("<constructor-arg><ref bean=\"someref\"></ref></constructor-arg>");<br>
sb.append("<constructor-arg><ref bean=\"someOTHERref\"></ref></constructor-arg>");<br>
sb.append("<property>");<br>
sb.append("<ref bean=\"funkster\"/>");<br>
sb.append("<br>
<br>
Unknown end tag for </property><br>
<br>
");<br>
sb.append("<property>");<br>
sb.append("<value>some value here<br>
<br>
Unknown end tag for </value><br>
<br>
");<br>
sb.append("<br>
<br>
Unknown end tag for </property><br>
<br>
");<br>
sb.append("<br>
<br>
Unknown end tag for </bean><br>
<br>
");<br>
sb.append("<br>
<br>
Unknown end tag for </beans><br>
<br>
");<br>
SpringBeans b = (SpringBeans) xstream.fromXML(sb.toString());<br>
System.out.println(xstream.toXML(b));<br>
}<br>
<br>
static public class SpringBeans {<br>
List<SpringBean> beans = new ArrayList<SpringBean>();<br>
<br>
public SpringBeans() {<br>
}<br>
<br>
public void addSpringBean(SpringBean springBean) {<br>
this.beans.add(springBean);<br>
}<br>
}<br>
<br>
/**<br>
* http://www.springframework.org/schema/beans/spring-beans-3.1.xsd<br>
*<br>
* @author jkroub<br>
*<br>
*/<br>
static public class SpringBean implements Comparable<SpringBean> {<br>
public SpringBean() {<br>
this(null, null, null, null);<br>
}<br>
<br>
public SpringBean(String _id, String _clazz, String _scope,<br>
String _parent) {<br>
this.id = _id;<br>
this.clazz = _clazz;<br>
this.scope = _scope;<br>
this.parent = _parent;<br>
}<br>
<br>
public void addProperty(SpringBeanProperty springBeanProperty) {<br>
this.properties.add(springBeanProperty);<br>
<br>
}<br>
<br>
public void addConstructorArg(ConstructorArg constructorArg) {<br>
constructorArgs.add(constructorArg);<br>
}<br>
<br>
String id;<br>
String clazz;<br>
String scope;<br>
String parent;<br>
List<ConstructorArg> constructorArgs = new ArrayList<ConstructorArg>();<br>
List<SpringBeanProperty> properties = new ArrayList<SpringBeanProperty>();<br>
public String dependsOn;<br>
public String factoryMethod;<br>
public String factoryBean;<br>
public String lazyInit;<br>
<br>
@Override<br>
public int compareTo(SpringBean o) {<br>
if(id==null) { return -1; }<br>
if(o==null||o.id==null) { return 1; }<br>
return id.compareTo(o.id);<br>
}<br>
<br>
}<br>
<br>
static public class SpringBeanProperty {<br>
public SpringBeanProperty(Ref _ref) {<br>
this.ref = _ref;<br>
}<br>
<br>
public SpringBeanProperty(String _value) {<br>
this.value = _value;<br>
}<br>
<br>
public SpringBeanProperty() {<br>
}<br>
<br>
String name;<br>
Ref ref;<br>
String value;<br>
}<br>
<br>
static public class ConstructorArg {<br>
public ConstructorArg() {<br>
this(null);<br>
}<br>
<br>
public ConstructorArg(Ref _ref) {<br>
this.ref = _ref;<br>
}<br>
<br>
Ref ref;<br>
}<br>
<br>
static public class Ref {<br>
public Ref() {<br>
this(null);<br>
}<br>
<br>
public Ref(String _bean) {<br>
this.beanX = _bean;<br>
}<br>
<br>
private String beanX;<br>
}<br>
<br>
static class RefConverter implements Converter {<br>
@Override<br>
public boolean canConvert(Class type) {<br>
boolean result = Ref.class.equals(type);<br>
return result;<br>
}<br>
<br>
@Override<br>
/** produce xml */<br>
public void marshal(Object source, HierarchicalStreamWriter writer,<br>
MarshallingContext context) {<br>
Ref ref = (Ref) source;<br>
}<br>
<br>
@Override<br>
public Object unmarshal(HierarchicalStreamReader reader,<br>
UnmarshallingContext context) {<br>
Ref b = new Ref();<br>
b.beanX = reader.getAttribute("bean");<br>
return b;<br>
}<br>
}<br>
<br>
static class ConstructorArgConverter implements Converter {<br>
@Override<br>
public boolean canConvert(Class type) {<br>
boolean result = ConstructorArg.class.equals(type);<br>
return result;<br>
}<br>
<br>
@Override<br>
/** produce xml */<br>
public void marshal(Object source, HierarchicalStreamWriter writer,<br>
MarshallingContext context) {<br>
ConstructorArg constructorArg = (ConstructorArg) source;<br>
if (constructorArg.ref != null) {<br>
writer.startNode("ref");<br>
if (constructorArg.ref.beanX != null) {<br>
writer.addAttribute("bean", constructorArg.ref.beanX);<br>
}<br>
writer.endNode();<br>
}<br>
}<br>
<br>
@Override<br>
public Object unmarshal(HierarchicalStreamReader reader,<br>
UnmarshallingContext context) {<br>
ConstructorArg b = new ConstructorArg();<br>
if (reader.hasMoreChildren()) {<br>
reader.moveDown();<br>
if ("ref".equals(reader.getNodeName())) {<br>
b.ref = (Ref) context.convertAnother(b, Ref.class);<br>
}<br>
<br>
reader.moveUp();<br>
}<br>
return b;<br>
}<br>
}<br>
<br>
static class SpringBeanPropertyConverter implements Converter {<br>
@Override<br>
public boolean canConvert(Class type) {<br>
boolean result = SpringBeanProperty.class.equals(type);<br>
return result;<br>
}<br>
<br>
@Override<br>
/** produce xml */<br>
public void marshal(Object source, HierarchicalStreamWriter writer,<br>
MarshallingContext context) {<br>
SpringBeanProperty prop = (SpringBeanProperty) source;<br>
if (prop.name != null) {<br>
writer.addAttribute("name", prop.name);<br>
}<br>
if (prop.ref != null && prop.ref.beanX != null) {<br>
writer.startNode("ref");<br>
System.out.println("prop.ref:" + prop.ref);<br>
System.out.println("prop.ref.beanX:" + prop.ref.beanX);<br>
writer.addAttribute("bean", prop.ref.beanX);<br>
writer.endNode();<br>
}<br>
if (prop.value != null) {<br>
writer.startNode("value");<br>
writer.setValue(prop.value);<br>
writer.endNode();<br>
}<br>
}<br>
<br>
@Override<br>
/** from xml to object */<br>
public Object unmarshal(HierarchicalStreamReader reader,<br>
UnmarshallingContext context) {<br>
SpringBeanProperty b = new SpringBeanProperty();<br>
while (reader.hasMoreChildren()) {<br>
reader.moveDown();<br>
if ("ref".equals(reader.getNodeName())) {<br>
b.ref = (Ref) context.convertAnother(b, Ref.class);<br>
} else if ("value".equals(reader.getNodeName())) {<br>
b.value = reader.getValue();<br>
}<br>
reader.moveUp();<br>
}<br>
return b;<br>
}<br>
}<br>
<br>
static class SpringBeansConverter implements Converter {<br>
@Override<br>
public boolean canConvert(Class type) {<br>
boolean result = SpringBeans.class.equals(type);<br>
return result;<br>
}<br>
<br>
@Override<br>
/** produce xml */<br>
public void marshal(Object source, HierarchicalStreamWriter writer,<br>
MarshallingContext context) {<br>
SpringBeans b = (SpringBeans) source;<br>
for (SpringBean bean : b.beans) {<br>
context.convertAnother(bean);<br>
}<br>
}<br>
<br>
@Override<br>
public Object unmarshal(HierarchicalStreamReader reader,<br>
UnmarshallingContext context) {<br>
SpringBeans b = new SpringBeans();<br>
while (reader.hasMoreChildren()) {<br>
reader.moveDown();<br>
SpringBean springBean = (SpringBean) context.convertAnother(b,<br>
SpringBean.class);<br>
b.addSpringBean(springBean);<br>
reader.moveUp();<br>
}<br>
return b;<br>
}<br>
}<br>
<br>
static class SpringBeanConverter implements Converter {<br>
<br>
@Override<br>
public boolean canConvert(Class type) {<br>
boolean result = SpringBean.class.equals(type);<br>
return result;<br>
}<br>
<br>
@Override<br>
/** produce xml */<br>
public void marshal(Object source, HierarchicalStreamWriter writer,<br>
MarshallingContext context) {<br>
SpringBean b = (SpringBean) source;<br>
// System.out.println(b);<br>
if (b.id != null) {<br>
writer.addAttribute("id", b.id);<br>
}<br>
if (b.clazz != null) {<br>
writer.addAttribute("class", b.clazz);<br>
}<br>
if (b.scope != null) {<br>
writer.addAttribute("scope", b.scope);<br>
}<br>
if (b.parent != null) {<br>
writer.addAttribute("parent", b.parent);<br>
}<br>
if (b.lazyInit != null) {<br>
writer.addAttribute("lazy-init", b.lazyInit);<br>
}<br>
if (b.dependsOn != null) {<br>
writer.addAttribute("depends-on", b.dependsOn);<br>
}<br>
if (b.factoryMethod != null) {<br>
writer.addAttribute("factory-method", b.factoryMethod);<br>
}<br>
if (b.factoryBean != null) {<br>
writer.addAttribute("factory-bean", b.factoryBean);<br>
}<br>
if (b.constructorArgs != null && !b.constructorArgs.isEmpty()) {<br>
List<ConstructorArg> c = b.constructorArgs;<br>
for (ConstructorArg constructorArg : c) {<br>
writer.startNode("constructor-arg");<br>
context.convertAnother(constructorArg);<br>
writer.endNode();<br>
}<br>
}<br>
if (b.properties != null && !b.properties.isEmpty()) {<br>
List<SpringBeanProperty> c = b.properties;<br>
for (SpringBeanProperty prop : c) {<br>
writer.startNode("property");<br>
context.convertAnother(prop);<br>
writer.endNode();<br>
}<br>
}<br>
}<br>
<br>
@Override<br>
public Object unmarshal(HierarchicalStreamReader reader,<br>
UnmarshallingContext context) {<br>
SpringBean b = new SpringBean();// _id, _clazz, _scope, _parent);<br>
b.id = reader.getAttribute("id");<br>
b.clazz = reader.getAttribute("class");<br>
b.scope = reader.getAttribute("scope");<br>
b.parent = reader.getAttribute("parent");<br>
b.lazyInit = reader.getAttribute("lazy-init");<br>
b.dependsOn = reader.getAttribute("depends-on");<br>
b.factoryBean = reader.getAttribute("factory-bean");<br>
b.factoryMethod = reader.getAttribute("factory-method");<br>
while (reader.hasMoreChildren()) {<br>
reader.moveDown();<br>
if ("constructor-arg".equals(reader.getNodeName())) {<br>
ConstructorArg ca = (ConstructorArg) context<br>
.convertAnother(b, ConstructorArg.class);<br>
<br>
b.addConstructorArg(ca);<br>
} else if ("property".equals(reader.getNodeName())) {<br>
SpringBeanProperty springBeanProperty = (SpringBeanProperty) context<br>
.convertAnother(b, SpringBeanProperty.class);<br>
b.addProperty(springBeanProperty);<br>
}<br>
reader.moveUp();<br>
}<br>
return b;<br>
}<br>
<br>
}<br>
}<br>
</pre>