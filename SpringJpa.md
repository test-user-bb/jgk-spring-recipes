# Introduction #

  * JPA = Java Persistence API
## Annotations ##
  * `@AssociationOverride`
  * `@AttributeOverride`
  * `@AttributeOverrides`
  * `@Basic`
  * `@Column`
  * `@DiscriminatorColumn`
  * `@DiscriminatorValue("FT")`
  * `@Entity`
  * `@Embedded`
  * `@EmbeddedId`
  * `@Embeddable`
  * `@GeneratedValue`
  * `@Id`
  * `@IdClass`
  * `@Inheritance`
  * `@JoinColumn(name="ADDR_ID")`
  * `@JoinTable(name="FIRST_SECOND")`
  * `@ManyToOne`
  * `@ManyToMany(mappedBy = "employees")`
  * `@MapKey`
  * `@MappedSuperclass`
  * `@OneToOne`
  * `@OrderBy`
  * `@PrimaryKeyJoinColumn(name="FT_EMPID")`
  * `@PrimaryKeyJoinColumns`
  * `@SecondaryTable`
  * `@SequenceGenerator`
  * `@Table(name="FT_EMP")`
  * `@TableGenerator`
  * `@Transient`
  * `@Version`
## Annotation Attributes ##
  * `allocationSize` - The amount to increment by when allocating sequence numbers from the sequence. (default: 50)
    * Ex.: `@SequenceGenerator(name="EMP_SEQ", allocationSize=25)`
  * `cascade` - The operations that must be cascaded to the target of the association. (`CascadeType[] - ALL, PERSIST, MERGE, REMOVE, DETACH)`
  * `catalog` - The catalog of the table.
  * `columnDefinition` - The SQL fragment that is used when generating the DDL for the discriminator column.
  * `discriminatorType` - The type of object/column to use as a class discriminator.
  * `fetch` - Whether the association should be lazily loaded or must be eagerly fetched. The EAGER strategy is a requirement on the persistence provider runtime that the associated entity must be eagerly fetched. The LAZY strategy is a hint to the persistence provider runtime.
  * `initialValue` - The value from which the sequence object is to start generating.
  * `inverseJoinColumns` - The foreign key columns of the join table which reference the primary table of the entity that does not own the association (i.e. the inverse side of the association).
  * `joinColumns` - The foreign key columns of the join table which reference the primary table of the entity owning the association (i.e. the owning side of the association).
    * Ex.: `@JoinTable(name="CUST_PHONE", joinColumns=@JoinColumn(name="CUST_ID",referencedColumnName="ID"),inverseJoinColumns=@JoinColumn(name="PHONE_ID", referencedColumnName="ID"))`
  * `length` -
  * `mappedBy` - The field that owns the relationship. The mappedBy element is only specified on the inverse (non-owning) side of the association. (JoinColumn[.md](.md))
  * `nullable` - ?
  * `optional` - Whether the association is optional. If set to false then a non-null relationship must always exist.
  * `pkColumnName` - Name of the primary key column in the table.
  * `referencedColumnName` - The name of the primary key column of the table being joined to.
  * `schema` - The schema of the table.
  * `strategy` - ?
    * Ex.: `@Inheritance(strategy=InheritanceType.JOINED)`
  * `sequenceName` - The name of the database sequence object from which to obtain primary key values.
  * `table` - ?
  * `targetEntity` - The entity class that is the target of the association.
    * Ex.: `@OneToMany(targetEntity=com.jgk.springrecipes.domain.Customer.class, cascade=ALL, mappedBy=”company”)`
  * `uniqueConstraints` - Unique constraints that are to be placed on the table. These are only used if table generation is in effect.
  * `value` - ?
  * `valueColumnName` - Name of the column that stores the last value generated.
## Entity Manager ##
  * The set of entities that can be managed by a given EntityManager instance is defined by a persistence unit. A persistence unit defines the set of all classes that are related or grouped by the application, and which must be colocated in their mapping to a single database.
  * Runtime exceptions thrown by the methods of the EntityManager interface will cause the current
transaction to be rolled back.
## Query ##
  * A named parameter is an identifier that is prefixed by the ":" symbol.
```
User user = (User)em.createQuery
("SELECT u FROM User u WHERE u.name=:name AND
u.pass=:pass")
.setParameter("name", args[0])
.setParameter("pass", args[1])
.getSingleResult();
```
### Examples ###
  * Sample usage:
```
@Stateless public class OrderEntryBean implements OrderEntry {
   @PersistenceContext EntityManager em;
   public void enterOrder(int custID, Order newOrder) {
      Customer cust = em.find(Customer.class, custID);
      cust.getOrders().add(newOrder);
      newOrder.setCustomer(cust);
   }
}
```
  * Query sample:
```
public List findWithName(String name) {
   return em.createQuery(
       "SELECT c FROM Customer c WHERE c.name LIKE :custName")
       .setParameter("custName", name)
       .setMaxResults(10)
       .getResultList();
}
```

### persistence.xml ###
  * Typically located on classpath at /META-INF/persistence.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="http://java.sun.com/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd"
             version="2.0">
   <persistence-unit name="manager1">
      <!--<provider>org.hibernate.ejb.HibernatePersistence</provider>-->
      <!--<jta-data-source>java:/DefaultDS</jta-data-source>-->
      <!--<mapping-file>ormap.xml</mapping-file>-->
      <!--<jar-file>MyApp.jar</jar-file>-->
  	<class>com.jgk.springrecipes.jpa.domain.Person</class>
  	<!-- 
      <properties>
         <property name="hibernate.dialect" value="org.hibernate.dialect.HSQLDialect"/>
         <property name="hibernate.hbm2ddl.auto" value="create-drop"/>
         <property name="hibernate.showSql" value="true"/>
      </properties>
       -->
   </persistence-unit>
</persistence>
```
## Table Mapping Strategies ##
  * single table per class hierarchy
  * table per concrete entity class
  * strategy in which fields that are specific to a subclass are mapped to a separate table than the fields that are common to the parent class, and a join is performed to instantiate the subclass.
## Collections supported ##
  * Collection
  * List
  * Set
  * Map
## Libraries/Dependencies ##
  * javax.persistence.Entity  org.hibernate:hibernate-jpa-2.0-api:3.5.6-Final
## Good to know ##
  * A mapped superclass, unlike an entity, is not queryable and cannot be passed as an argument to EntityManager or Query operations. A mapped superclass cannot be the target of a persistent relationship.
  * An entity may inherit from another entity class. Entities support inheritance, polymorphic associations,
and polymorphic queries.
  * Both abstract and concrete classes can be entities. Both abstract and concrete classes can be annotated
with the Entity annotation, mapped as entities, and queried for as entities.
  * Entities can extend non-entity classes and non-entity classes can extend entity classes.
  * Runtime exceptions thrown by property accessor methods cause the current transaction to be rolled
back.
  * An instance becomes persistent by means of the EntityManager API.
  * Composite primary keys typically arise when mapping from legacy databases when the
database key is comprised of several columns. The EmbeddedId and IdClass annotations are used
to denote composite primary keys.
  * The primary key class must define equals and hashCode methods. The semantics of value
equality for these methods must be consistent with the database equality for the database types
to which the key is mapped.
  * Embeddable classes are not annotated as Entity. Embeddable classes must be annotated as
Embeddable or denoted in the XML descriptor as such.
  * Relationships among entities may be one-to-one, one-to-many, many-to-one, or many-to-many. Relationships are polymorphic.
  * Relationships may be bidirectional or unidirectional. A bidirectional relationship has both an owning
side and an inverse side. A unidirectional relationship has only an owning side. The owning side of a
relationship determines the updates to the relationship in the database.
  * In ManyToMany there is a join table that is named A\_B (owner name first).
### Rules for bidirectional relationships ###
  * The following rules apply to bidirectional relationships:
    * The inverse side of a bidirectional relationship must refer to its owning side by use of the mappedBy element of the OneToOne, OneToMany, or ManyToMany annotation. The mappedBy element designates the property or field in the entity that is the owner of the relationship.
    * The many side of one-to-many / many-to-one bidirectional relationships must be the owning side, hence the mappedBy element cannot be specified on the ManyToOne annotation.
    * For one-to-one bidirectional relationships, the owning side corresponds to the side that contains the corresponding foreign key.
    * For many-to-many bidirectional relationships either side may be the owning side.
## Resources ##
  * [JPA Spec - jsr-220](http://jcp.org/aboutJava/communityprocess/final/jsr220/)
  * [JPA Reference Implementation](http://www.oracle.com/technetwork/java/javaee/downloads/index.html)
  * [Wikipedia](http://en.wikipedia.org/wiki/Java_Persistence_API)