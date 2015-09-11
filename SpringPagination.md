# Introduction #

```

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class JpaRdossrc0Repository implements Rdossrc0Repository {
	private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
            this.entityManager = entityManager;
    }

	@Override
	public List<Rdossrc0> findAll() {
        return entityManager.createQuery("select p from com.gs.fdb.oracle.Rdossrc0 p",
        		Rdossrc0.class).getResultList();
	}

	@Override
    @Transactional
	public void save(Rdossrc0 lm) {
        entityManager.persist(lm);
		
	}

	@Override
	public List<Rdossrc0> findAllByPage(int page, int pageSize) {
		TypedQuery query = entityManager.createQuery("select c from Rdossrc0 c", Rdossrc0.class);
		    query.setFirstResult(page * pageSize);
		    query.setMaxResults(pageSize);
		    return query.getResultList();
	}

}

```