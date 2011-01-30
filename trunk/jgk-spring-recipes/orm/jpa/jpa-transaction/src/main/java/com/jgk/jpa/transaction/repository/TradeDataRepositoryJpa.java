package com.jgk.jpa.transaction.repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jgk.jpa.transaction.domain.TradeData;

@Repository(value="tradeDataRepository")
public class TradeDataRepositoryJpa implements TradeDataRepository {
	
	@Inject
	private String brokerageName;

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public String toString() {
		return "TradeDataRepositoryJpa [brokerageName=" + brokerageName + "]";
	}


	@Override
	public TradeData findById(Long id) {
		return entityManager.find(TradeData.class, id);
	}


	@Override
	@Transactional
	public TradeData makePersistent(TradeData td) {
		System.out.println("persist it");
		entityManager.persist(td);
		return td;
	}

}
