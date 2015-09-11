# Introduction #

```
java.io.InvalidClassException: org.hibernate.collection.AbstractPersistentCollection; local class incompatible: stream classdesc serialVersionUID = -5723701046347946317, local class serialVersionUID = 7602608801868099635
	at java.io.ObjectStreamClass.initNonProxy(Unknown Source)
	at java.io.ObjectInputStream.readNonProxyDesc(Unknown Source)
	at java.io.ObjectInputStream.readClassDesc(Unknown Source)
	at java.io.ObjectInputStream.readNonProxyDesc(Unknown Source)
	at java.io.ObjectInputStream.readClassDesc(Unknown Source)
	at java.io.ObjectInputStream.readOrdinaryObject(Unknown Source)
	at java.io.ObjectInputStream.readObject0(Unknown Source)
	at java.io.ObjectInputStream.defaultReadFields(Unknown Source)
	at java.io.ObjectInputStream.readSerialData(Unknown Source)
	at java.io.ObjectInputStream.readOrdinaryObject(Unknown Source)
	at java.io.ObjectInputStream.readObject0(Unknown Source)
	at java.io.ObjectInputStream.defaultReadFields(Unknown Source)
	at java.io.ObjectInputStream.readSerialData(Unknown Source)
	at java.io.ObjectInputStream.readOrdinaryObject(Unknown Source)
	at java.io.ObjectInputStream.readObject0(Unknown Source)
	at java.io.ObjectInputStream.defaultReadFields(Unknown Source)
	at java.io.ObjectInputStream.readSerialData(Unknown Source)
	at java.io.ObjectInputStream.readOrdinaryObject(Unknown Source)
	at java.io.ObjectInputStream.readObject0(Unknown Source)
	at java.io.ObjectInputStream.readObject(Unknown Source)
	at com.gs.web.httpTunnel.HttpTransaction.transaction(HttpTransaction.java:112)
	at com.gs.lab.rr.web.applets.CumResultsViewer.getResults(CumResultsViewer.java:302)
	at com.gs.lab.rr.web.applets.CumResultsViewer.init(CumResultsViewer.java:182)
	at sun.plugin2.applet.Plugin2Manager$AppletExecutionRunnable.run(Unknown Source)
	at java.lang.Thread.run(Unknown Source)
java.lang.NullPointerException
	at com.gs.lab.rr.web.applets.CumResultsViewer.getResults(CumResultsViewer.java:307)
	at com.gs.lab.rr.web.applets.CumResultsViewer.init(CumResultsViewer.java:182)
	at sun.plugin2.applet.Plugin2Manager$AppletExecutionRunnable.run(Unknown Source)
	at java.lang.Thread.run(Unknown Source)

```