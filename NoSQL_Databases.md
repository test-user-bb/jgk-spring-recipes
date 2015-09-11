  * based on talks by [Chris Richardson and Mark Pollack](http://www.infoq.com/presentations/Using-Spring-with-NoSQL-Databases)
## NoSQL Data Models ##
  * Key Value (Amazon Dynamo)
    * key -> value
    * Redis, Riak, Voldemort...
  * Column (Google Bigtable)
    * key -> column families
    * HBase, Cassandra
## NoSQL ##
  * No joins
  * very limited "transactions"
  * different durability and consistency guarantees
  * NoSQL datastores are used as "point solutions"
    * Amazon
    * Google
    * Facebook
    * LinkedIn
## NoSQL Examples ##
  * [Neo4J](http://neo4j.org)
  * [www.db4o.com db4o]
  * [Objectivity](http://www.objectivity.com/pages/objectivity/)
  * Amazon Dynamo
  * Scalaris
  * HBase
  * CouchDB
  * Terrastore
  * Gemfire
  * Sones
  * InfoGrid
  * Hadoop
  * MongoDB
  * RavenDB
  * Tokyo Cabinet
  * Hypertable
  * Cassandra
  * Azure Table
  * Coherence
  * Mnesia
  * Riak
  * Voldemort
  * Gigaspaces
  * Amazon SimpleDB
  * Redis
  * BerkeleyDB
  * MemcacheDB
  * Tamino
  * [InfiniteGraph](http://www.objectivity.com/infinitegraph/)
  * Chordless
## Why NoSQL ##
  * Internet scale is main driver in using non-relational databases
    * Large data size - TBs to PBs
    * High Transaction rates
    * Need for high availability

## Problems with RDBMS ##
  * ORM impedance mismatch
    * complicated to map domain model to relational schema
    * relational schema is rigid (non-flexible, not easily changed)
  * RDBMS difficult to scale writes to DB
    * vertical scaling is expensive
    * horizontal also
  * Performance could be better
  * Transactions and joins reduce access rates