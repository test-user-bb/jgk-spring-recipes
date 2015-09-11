### Transactions ###
```
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.PlatformTransactionManager;

public aspect TransactionPointcuts {
	public pointcut transactionBegin(TransactionDefinition txDefinition)
	    : execution(* PlatformTransactionManager.*(TransactionDefinition))
	      && args(txDefinition);

	public pointcut transactionEnd(TransactionStatus txStatus)
	    : execution(* PlatformTransactionManager.*(TransactionStatus))
	      && args(txStatus);

	public pointcut transactionalExecution()
	    : execution(@Transactional * *(..))
	      || execution(* (@Transactional *).*(..));
}
```

### Spring JDBC calls ###
```
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.simple.SimpleJdbcOperations;

public aspect SpringPointcuts {
    public pointcut jdbcTemplateCall()
        : call(* JdbcOperations+.*(..))
        || call(* SimpleJdbcOperations+.*(..));

    public pointcut jdbcTemplateExecution()
        : execution(* JdbcOperations+.*(..))
        || execution(* SimpleJdbcOperations+.*(..));
}
```
### JPA / EntityManager calls ###
```
import javax.persistence.EntityManager;

public aspect JPAPointcuts {
    public pointcut entityManagerExecution()
        : execution(* EntityManager.*(..));
}

```
### Any Java Object call ###
```
public aspect JavaPointcuts {
    public pointcut objectExecution() : execution(* Object.*(..));
}
```

### JDBC Pointcuts ###
```
public aspect JDBCPointcuts {
    public pointcut jdbcCall()
        : call(* java.sql..*.*(..))
        || call(* javax.sql..*.*(..));

    public pointcut jdbcExecution()
        : execution(* java.sql..*.*(..))
        || execution(* javax.sql..*.*(..));
}
```