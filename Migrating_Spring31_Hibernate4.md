# Links #
  * [Issues](Migrating_Spring31_Hibernate4#Issues.md)
  * [Building Spring 3](http://blog.springsource.com/2009/03/03/building-spring-3/)
  * [Spring JIRA](http://jira.springsource.org/) | [Spring Fisheye](https://fisheye.springsource.org/browse/spring-framework/trunk)
  * [Hibernate 4 support in Spring 3.1.0.RC1](https://jira.springsource.org/browse/SPR-8096)
  * [Forum Entry](http://forum.springsource.org/showthread.php?115682-Migrating-application-context-configuration-to-Spring-3.1-and-Hibernate-4.0&highlight=org.hibernate.cache.CacheProvider)
  * [Forum-HibernateDaoSupport for Hibernate4](http://forum.springsource.org/showthread.php?117222-HibernateDaoSupport-for-Hibernate4&p=386866#post386866)
  * [Download Spring](http://www.springsource.org/download)
  * [Hibernate 4 migration guide](http://community.jboss.org/wiki/HibernateCoreMigrationGuide40)
  * Forum [How to stay up to date with the latest Release Candidate (RC)? RC2](http://forum.springsource.org/showthread.php?117647-How-to-stay-up-to-date-with-the-latest-Release-Candidate-(RC)-RC2)
  * Forum [No CurrentSessionContext configured!, No session currently bound to execution context](http://forum.springsource.org/showthread.php?117655-No-CurrentSessionContext-configured!-No-session-currently-bound-to-execution-context)
## hibernate changes ##

## Building Spring [blog instructions](http://blog.springsource.com/2009/03/03/building-spring-3/) ##
  * [Local Spring Updates](http://code.google.com/p/jgk-spring-recipes/wiki/SpringUpdates)
  * download source from svn
```
$ svn co https://src.springsource.org/svn/spring-framework/trunk spring-framework
```
  * build it (cd build-spring-framework) - 'Total time: 13 minutes 3 seconds'
```
$ cd build-spring-framework
$ ant
```
  * bundle:
```
$ ant jar
```
### Useful Activities ###
  * install to local maven repository
```
$ ant install-maven-central   <== in spring- prefix format
$ ant install-maven        <== in org.springframework. prefix format
```
  * NOTE: at the top level of the spring checkout directory there is a file called `build.properties` that contains some properties that are used during the build process to construct the names of the artifacts.
```
## For GA releases
#release.type=release
#build.stamp=RELEASE

## For milestone releases
#release.type=milestone
#build.stamp=M1

## For trunk development / ci builds
release.type=integration
#build.stamp=BUILD-SNAPSHOT
build.stamp=RC1-GSSHADE-1
```
  * after building (ant) the directory {springframework\_home}/integration-repo will contain directories that can be copied to your local maven directory with the build.properties incorporated names.
```
$ cd {springframework_home}/integration-repo
$ cp -a org.springframework* ~/.m2/repository/org/springframework
```

## Issues ##
  * `org.springframework.transaction.CannotCreateTransactionException: Could not open Hibernate Session for transaction; nested exception is org.hibernate.SessionException: Session is closed!`
    * spring forum entry [HERE](http://forum.springsource.org/showthread.php?120118-CannotCreateTransactionException-non-forked-integration-testing-quot-Session-is-closed)
```
Tests run: 1, Failures: 0, Errors: 1, Skipped: 0, Time elapsed: 0.862 sec <<< FAILURE!
testGetDiseaseByClinicalCode(com.gs.core.services.impl.ServiceImplIntegrationTest)  Time elapsed: 0.853 sec  <<< ERROR!
org.springframework.transaction.CannotCreateTransactionException: Could not open Hibernate Session for transaction; nested exception is org.hibernate.SessionException: Session is closed!
        at org.springframework.orm.hibernate4.HibernateTransactionManager.doBegin(HibernateTransactionManager.java:428)
        at org.springframework.transaction.support.AbstractPlatformTransactionManager.getTransaction(AbstractPlatformTransactionManager.java:371)
        at org.springframework.test.context.transaction.TransactionalTestExecutionListener$TransactionContext.startTransaction(TransactionalTestExecutionListener.java:513)
        at org.springframework.test.context.transaction.TransactionalTestExecutionListener.startNewTransaction(TransactionalTestExecutionListener.java:271)
        at org.springframework.test.context.transaction.TransactionalTestExecutionListener.beforeTestMethod(TransactionalTestExecutionListener.java:164)
        at org.springframework.test.context.TestContextManager.beforeTestMethod(TestContextManager.java:358)
        at org.springframework.test.context.junit4.statements.RunBeforeTestMethodCallbacks.evaluate(RunBeforeTestMethodCallbacks.java:73)
        at org.junit.internal.runners.statements.RunAfters.evaluate(RunAfters.java:31)
        at org.springframework.test.context.junit4.statements.RunAfterTestMethodCallbacks.evaluate(RunAfterTestMethodCallbacks.java:83)
        at org.springframework.test.context.junit4.statements.SpringRepeat.evaluate(SpringRepeat.java:72)
        at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:231)
        at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:49)
        at org.junit.runners.ParentRunner$3.run(ParentRunner.java:193)
        at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:52)
        at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:191)
        at org.junit.runners.ParentRunner.access$000(ParentRunner.java:42)
        at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:184)
        at org.springframework.test.context.junit4.statements.RunBeforeTestClassCallbacks.evaluate(RunBeforeTestClassCallbacks.java:61)

```
  * `TransactionSynchronization.beforeCompletion threw exception org.hibernate.SessionException: Session is closed!`
```
ERROR TransactionSynchronizationUtils - TransactionSynchronization.beforeCompletion threw exception
org.hibernate.SessionException: Session is closed!
	at org.hibernate.internal.AbstractSessionImpl.errorIfClosed(AbstractSessionImpl.java:126)
	at org.hibernate.internal.SessionImpl.disconnect(SessionImpl.java:465)
	at org.springframework.orm.hibernate4.SpringSessionSynchronization.beforeCompletion(SpringSessionSynchronization.java:107)
	at org.springframework.transaction.support.TransactionSynchronizationUtils.triggerBeforeCompletion(TransactionSynchronizationUtils.java:106)
	at org.springframework.transaction.support.AbstractPlatformTransactionManager.triggerBeforeCompletion(AbstractPlatformTransactionManager.java:937)
	at org.springframework.transaction.support.AbstractPlatformTransactionManager.processRollback(AbstractPlatformTransactionManager.java:834)
	at org.springframework.transaction.support.AbstractPlatformTransactionManager.rollback(AbstractPlatformTransactionManager.java:822)
	at org.springframework.transaction.interceptor.TransactionAspectSupport.completeTransactionAfterThrowing(TransactionAspectSupport.java:411)
	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:114)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:172)
	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:202)
	at $Proxy131.loadAll(Unknown Source)
	at com.gs.core.services.ConceptTemplateServiceIntegrationTest.onTearDown(ConceptTemplateServiceIntegrationTest.java:557)
```
  * Spring Security (3.1.0.RELEASE)
    * `java.io.InvalidClassException: org.springframework.security.core.context.SecurityContextImpl`
    * `local class incompatible: stream classdesc serialVersionUID = -8599415456903658526, local class serialVersionUID = 310`
```
SEVERE: IOException while loading persisted sessions: java.io.InvalidClassException: org.springframework.security.core.context.SecurityContextImpl; local class incompatible: stream classdesc serialVersionUID = -8599415456903658526, local class serialVersionUID = 310
java.io.InvalidClassException: org.springframework.security.core.context.SecurityContextImpl; local class incompatible: stream classdesc serialVersionUID = -8599415456903658526, local class serialVersionUID = 310
	at java.io.ObjectStreamClass.initNonProxy(ObjectStreamClass.java:562)
	at java.io.ObjectInputStream.readNonProxyDesc(ObjectInputStream.java:1582)
	at java.io.ObjectInputStream.readClassDesc(ObjectInputStream.java:1495)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1731)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1328)
	at java.io.ObjectInputStream.readObject(ObjectInputStream.java:350)
	at org.apache.catalina.session.StandardSession.readObject(StandardSession.java:1441)
	at org.apache.catalina.session.StandardSession.readObjectData(StandardSession.java:942)
	at org.apache.catalina.session.StandardManager.doLoad(StandardManager.java:394)
	at org.apache.catalina.session.StandardManager.load(StandardManager.java:321)
	at org.apache.catalina.session.StandardManager.start(StandardManager.java:637)
	at org.apache.catalina.core.ContainerBase.setManager(ContainerBase.java:438)
	at org.apache.catalina.core.StandardContext.start(StandardContext.java:4358)
	at org.apache.catalina.core.ContainerBase.start(ContainerBase.java:1045)
	at org.apache.catalina.core.StandardHost.start(StandardHost.java:722)
	at org.apache.catalina.core.ContainerBase.start(ContainerBase.java:1045)
	at org.apache.catalina.core.StandardEngine.start(StandardEngine.java:443)
	at org.apache.catalina.core.StandardService.start(StandardService.java:516)
	at org.apache.catalina.core.StandardServer.start(StandardServer.java:710)
	at org.apache.catalina.startup.Catalina.start(Catalina.java:583)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
	at java.lang.reflect.Method.invoke(Method.java:597)
	at org.apache.catalina.startup.Bootstrap.start(Bootstrap.java:288)
	at org.apache.catalina.startup.Bootstrap.main(Bootstrap.java:413)
Dec 12, 2011 8:33:40 AM org.apache.catalina.session.StandardManager start
SEVERE: Exception loading sessions from persistent storage
java.io.InvalidClassException: org.springframework.security.core.context.SecurityContextImpl; local class incompatible: stream classdesc serialVersionUID = -8599415456903658526, local class serialVersionUID = 310
	at java.io.ObjectStreamClass.initNonProxy(ObjectStreamClass.java:562)
	at java.io.ObjectInputStream.readNonProxyDesc(ObjectInputStream.java:1582)
	at java.io.ObjectInputStream.readClassDesc(ObjectInputStream.java:1495)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1731)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1328)
	at java.io.ObjectInputStream.readObject(ObjectInputStream.java:350)
	at org.apache.catalina.session.StandardSession.readObject(StandardSession.java:1441)
	at org.apache.catalina.session.StandardSession.readObjectData(StandardSession.java:942)
	at org.apache.catalina.session.StandardManager.doLoad(StandardManager.java:394)
	at org.apache.catalina.session.StandardManager.load(StandardManager.java:321)
	at org.apache.catalina.session.StandardManager.start(StandardManager.java:637)
	at org.apache.catalina.core.ContainerBase.setManager(ContainerBase.java:438)
	at org.apache.catalina.core.StandardContext.start(StandardContext.java:4358)
	at org.apache.catalina.core.ContainerBase.start(ContainerBase.java:1045)
	at org.apache.catalina.core.StandardHost.start(StandardHost.java:722)
	at org.apache.catalina.core.ContainerBase.start(ContainerBase.java:1045)
	at org.apache.catalina.core.StandardEngine.start(StandardEngine.java:443)
	at org.apache.catalina.core.StandardService.start(StandardService.java:516)
	at org.apache.catalina.core.StandardServer.start(StandardServer.java:710)
	at org.apache.catalina.startup.Catalina.start(Catalina.java:583)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
	at java.lang.reflect.Method.invoke(Method.java:597)
	at org.apache.catalina.startup.Bootstrap.start(Bootstrap.java:288)
	at org.apache.catalina.startup.Bootstrap.main(Bootstrap.java:413)

```
  * `org.hibernate.HibernateException: No Session found for current thread`
    * Cause:  no session
    * Fix:  mark test method with `@Transactional`
```
org.hibernate.HibernateException: No Session found for current thread
	at org.springframework.orm.hibernate4.SpringSessionContext.currentSession(SpringSessionContext.java:97)
	at org.hibernate.internal.SessionFactoryImpl.getCurrentSession(SessionFactoryImpl.java:881)
	at com.gs.core.utils.hibernate.AbstractBaseHibernateDao.getCurrentSession(AbstractBaseHibernateDao.java:67)
	at com.gs.core.domain.adt.PhoneHibernateDAO.getAllPhoneWithPhoneIds(PhoneHibernateDAO.java:28)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
	at java.lang.reflect.Method.invoke(Method.java:597)
	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:318)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:183)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:150)
	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:110)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:172)
	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:202)
	at $Proxy122.getAllPhoneWithPhoneIds(Unknown Source)
	at com.gs.core.domain.adt.PhoneHibernateDAOIntegrationTest.testPhone(PhoneHibernateDAOIntegrationTest.java:29)

```
  * `org.hibernate.exception.GenericJDBCException: This function is not supported`
    * Cause:  `<generator class="native">`
    * Fix: change to `<generator class="sequence">`
  * `ClassNotFoundException net.sf.ehcache.util.Timestamper`
```
Caused by: java.lang.ClassNotFoundException: net.sf.ehcache.util.Timestamper
	at java.net.URLClassLoader$1.run(URLClassLoader.java:202)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.net.URLClassLoader.findClass(URLClassLoader.java:190)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:306)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:301)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:247)
	... 67 more


```
  * `ClassNotFoundException`
    * Cause: using a `org.springframework.orm.hibernate3.LocalSessionFactoryBean` with spring 3.1.0.xxx, should use `org.springframework.orm.hibernate4.LocalSessionFactoryBean`.
```
Caused by: java.lang.ClassNotFoundException: org.hibernate.cache.CacheProvider
```
  * [SPR-8776](https://issues.springsource.org/browse/SPR-8776) - fixed in [3.1 RC2](http://www.springsource.org/node/3122)
    * **`NoSuchMethodError: org.hibernate.SessionFactory.openSession()Lorg/hibernate/classic/Session;`**
```
java.lang.NoSuchMethodError: org.hibernate.SessionFactory.openSession()Lorg/hibernate/classic/Session;
	at org.springframework.orm.hibernate4.HibernateTransactionManager.doBegin(HibernateTransactionManager.java:328)
	at org.springframework.transaction.support.AbstractPlatformTransactionManager.getTransaction(AbstractPlatformTransactionManager.java:371)
	at org.springframework.test.context.transaction.TransactionalTestExecutionListener$TransactionContext.startTransaction(TransactionalTestExecutionListener.java:513)
	at org.springframework.test.context.transaction.TransactionalTestExecutionListener.startNewTransaction(TransactionalTestExecutionListener.java:271)
	at org.springframework.test.context.transaction.TransactionalTestExecutionListener.beforeTestMethod(TransactionalTestExecutionListener.java:164)
	at org.springframework.test.context.TestContextManager.beforeTestMethod(TestContextManager.java:358)
	at org.springframework.test.context.junit4.statements.RunBeforeTestMethodCallbacks.evaluate(RunBeforeTestMethodCallbacks.java:73)
	at org.springframework.test.context.junit4.statements.RunAfterTestMethodCallbacks.evaluate(RunAfterTestMethodCallbacks.java:83)
	at org.springframework.test.context.junit4.statements.SpringRepeat.evaluate(SpringRepeat.java:72)
	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:231)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:49)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:193)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:52)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:191)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:42)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:184)
	at org.springframework.test.context.junit4.statements.RunBeforeTestClassCallbacks.evaluate(RunBeforeTestClassCallbacks.java:61)
	at org.springframework.test.context.junit4.statements.RunAfterTestClassCallbacks.evaluate(RunAfterTestClassCallbacks.java:71)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:236)
	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.run(SpringJUnit4ClassRunner.java:174)
	at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:49)
	at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:467)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:683)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:390)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:197)


```
  * Session related issues
    * Resolution:
```
<prop key="hibernate.current_session_context_class">org.springframework.orm.hibernate4.SpringSessionContext</prop>
```
    * **`No session currently bound to execution context`**
```
org.hibernate.HibernateException: No session currently bound to execution context
	at org.hibernate.context.internal.ManagedSessionContext.currentSession(ManagedSessionContext.java:72)
	at org.hibernate.internal.SessionFactoryImpl.getCurrentSession(SessionFactoryImpl.java:874)
	at com.gs.hcpcs.base.AbstractHcpcsBaseHibernateDao.getCurrentSession(AbstractHcpcsBaseHibernateDao.java:33)
	at com.gs.hcpcs.dao.hibernate.HcpcsHibernateDao.getActionCodes(HcpcsHibernateDao.java:216)
	at com.gs.BBBSpringTest.nothing(BBBSpringTest.java:31)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
	at java.lang.reflect.Method.invoke(Method.java:597)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:44)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:15)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:41)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:20)
	at org.junit.internal.runners.statements.RunBefores.evaluate(RunBefores.java:28)
	at org.springframework.test.context.junit4.statements.RunBeforeTestMethodCallbacks.evaluate(RunBeforeTestMethodCallbacks.java:74)
	at org.springframework.test.context.junit4.statements.RunAfterTestMethodCallbacks.evaluate(RunAfterTestMethodCallbacks.java:83)
	at org.springframework.test.context.junit4.statements.SpringRepeat.evaluate(SpringRepeat.java:72)
	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:231)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:49)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:193)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:52)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:191)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:42)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:184)
	at org.springframework.test.context.junit4.statements.RunBeforeTestClassCallbacks.evaluate(RunBeforeTestClassCallbacks.java:61)
	at org.springframework.test.context.junit4.statements.RunAfterTestClassCallbacks.evaluate(RunAfterTestClassCallbacks.java:71)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:236)
	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.run(SpringJUnit4ClassRunner.java:174)
	at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:49)
	at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:467)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:683)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:390)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:197)

```
    * `No CurrentSessionContext configured!`
      * This happens when missing **`<prop key="hibernate.current_session_context_class">managed</prop>`**
```
org.hibernate.HibernateException: No CurrentSessionContext configured!
	at org.hibernate.internal.SessionFactoryImpl.getCurrentSession(SessionFactoryImpl.java:872)
	at com.gs.hcpcs.base.AbstractHcpcsBaseHibernateDao.getCurrentSession(AbstractHcpcsBaseHibernateDao.java:33)
	at com.gs.hcpcs.dao.hibernate.HcpcsHibernateDao.getActionCodes(HcpcsHibernateDao.java:214)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
	at java.lang.reflect.Method.invoke(Method.java:597)
	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:318)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:183)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:150)
	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:110)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:172)
	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:202)
	at $Proxy19.getActionCodes(Unknown Source)
	at com.gs.hcpcs.service.impl.HcpcsServiceImpl.getActionCodes(HcpcsServiceImpl.java:38)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
	at java.lang.reflect.Method.invoke(Method.java:597)
	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:318)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:183)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:150)
	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:110)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:172)
	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:202)
	at $Proxy20.getActionCodes(Unknown Source)
	at com.gs.hcpcs.service.HcpcsServiceIntegrationTest.testActionCodes(HcpcsServiceIntegrationTest.java:60)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
	at java.lang.reflect.Method.invoke(Method.java:597)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:44)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:15)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:41)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:20)
	at org.junit.internal.runners.statements.RunBefores.evaluate(RunBefores.java:28)
	at org.springframework.test.context.junit4.statements.RunBeforeTestMethodCallbacks.evaluate(RunBeforeTestMethodCallbacks.java:74)
	at org.springframework.test.context.junit4.statements.RunAfterTestMethodCallbacks.evaluate(RunAfterTestMethodCallbacks.java:83)
	at org.springframework.test.context.junit4.statements.SpringRepeat.evaluate(SpringRepeat.java:72)
	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:231)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:49)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:193)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:52)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:191)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:42)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:184)
	at org.springframework.test.context.junit4.statements.RunBeforeTestClassCallbacks.evaluate(RunBeforeTestClassCallbacks.java:61)
	at org.springframework.test.context.junit4.statements.RunAfterTestClassCallbacks.evaluate(RunAfterTestClassCallbacks.java:71)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:236)
	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.run(SpringJUnit4ClassRunner.java:174)
	at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:49)
	at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:467)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:683)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:390)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:197)


```
  * `UserType` derivatives are failing.
```
org.hibernate.PropertyAccessException: IllegalArgumentException occurred while calling setter of com.gs.firstdb.Rddcmma1.ddxcnSl
	at org.hibernate.property.BasicPropertyAccessor$BasicSetter.set(BasicPropertyAccessor.java:119)
	at org.hibernate.tuple.entity.AbstractEntityTuplizer.setPropertyValues(AbstractEntityTuplizer.java:703)
	at org.hibernate.tuple.entity.PojoEntityTuplizer.setPropertyValues(PojoEntityTuplizer.java:371)
	at org.hibernate.persister.entity.AbstractEntityPersister.setPropertyValues(AbstractEntityPersister.java:4232)
	at org.hibernate.engine.internal.TwoPhaseLoad.initializeEntity(TwoPhaseLoad.java:168)
	at org.hibernate.loader.Loader.initializeEntitiesAndCollections(Loader.java:1006)
	at org.hibernate.loader.Loader.doQuery(Loader.java:883)
	at org.hibernate.loader.Loader.doQueryAndInitializeNonLazyCollections(Loader.java:289)
	at org.hibernate.loader.Loader.doList(Loader.java:2463)
	at org.hibernate.loader.Loader.doList(Loader.java:2449)
	at org.hibernate.loader.Loader.listIgnoreQueryCache(Loader.java:2279)
	at org.hibernate.loader.Loader.list(Loader.java:2274)
	at org.hibernate.loader.hql.QueryLoader.list(QueryLoader.java:470)
	at org.hibernate.hql.internal.ast.QueryTranslatorImpl.list(QueryTranslatorImpl.java:355)
	at org.hibernate.engine.query.spi.HQLQueryPlan.performList(HQLQueryPlan.java:196)
	at org.hibernate.internal.SessionImpl.list(SessionImpl.java:1118)
	at org.hibernate.internal.QueryImpl.list(QueryImpl.java:101)
	at com.gs.firstdb.dao.hibernate.FirstDbHibernateDao.getDrugDiseaseContrasByRoutedMedIds(FirstDbHibernateDao.java:3832)
	at com.gs.firstdb.dao.hibernate.FirstDbHibernateDaoJunit4IntegrationTest.testGetDrugDiseaseContras(FirstDbHibernateDaoJunit4IntegrationTest.java:263)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
	at java.lang.reflect.Method.invoke(Method.java:597)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:44)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:15)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:41)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:20)
	at org.junit.internal.runners.statements.RunBefores.evaluate(RunBefores.java:28)
	at org.springframework.test.context.junit4.statements.RunBeforeTestMethodCallbacks.evaluate(RunBeforeTestMethodCallbacks.java:74)
	at org.junit.internal.runners.statements.RunAfters.evaluate(RunAfters.java:31)
	at org.springframework.test.context.junit4.statements.RunAfterTestMethodCallbacks.evaluate(RunAfterTestMethodCallbacks.java:83)
	at org.junit.rules.TestWatchman$1.evaluate(TestWatchman.java:48)
	at org.springframework.test.context.junit4.statements.SpringRepeat.evaluate(SpringRepeat.java:72)
	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:231)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:49)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:193)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:52)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:191)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:42)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:184)
	at org.springframework.test.context.junit4.statements.RunBeforeTestClassCallbacks.evaluate(RunBeforeTestClassCallbacks.java:61)
	at org.springframework.test.context.junit4.statements.RunAfterTestClassCallbacks.evaluate(RunAfterTestClassCallbacks.java:71)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:236)
	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.run(SpringJUnit4ClassRunner.java:174)
	at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:49)
	at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:467)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:683)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:390)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:197)
Caused by: java.lang.IllegalArgumentException: argument type mismatch
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
	at java.lang.reflect.Method.invoke(Method.java:597)
	at org.hibernate.property.BasicPropertyAccessor$BasicSetter.set(BasicPropertyAccessor.java:65)
	... 49 more


```