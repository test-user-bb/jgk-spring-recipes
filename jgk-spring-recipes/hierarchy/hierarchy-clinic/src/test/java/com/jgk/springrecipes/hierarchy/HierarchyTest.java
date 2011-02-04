package com.jgk.springrecipes.hierarchy;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.gs.core.domain.events.ClinicalObservation;
import com.gs.core.domain.visit.Visit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/com/jgk/springrecipes/hierarchy/HierarchyTest-config.xml")
public class HierarchyTest {

	@PersistenceContext
	EntityManager e;

	@Test
	@Transactional
	public void test() {
		Query q = e.createQuery("select v from Visit v");
		q.getResultList();
		Visit v = new Visit();
		v.setAnnotation("HELLO");
		ClinicalObservation co = new ClinicalObservation();
		co.setAnnotation("I am annoation for cobs");
		v.addClinicalObservation(co);
		e.persist(v);
		assertNotNull(v.getId());
		Query qobs = e.createQuery("select v from ClinicalObservation v");
		System.out.println(qobs.getResultList());
		
	}
}
