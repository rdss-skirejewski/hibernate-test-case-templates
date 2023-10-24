package org.hibernate.bugs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import jakarta.persistence.Query;
import org.hibernate.dto.TestEntityOne;
import org.hibernate.dto.TestEntityTwo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class JPAUnitTestCase {

	private EntityManagerFactory entityManagerFactory;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory( "templatePU" );
	}

	@After
	public void destroy() {
		entityManagerFactory.close();
	}

	// Entities are auto-discovered, so just add them anywhere on class-path
	// Add your tests, using standard JUnit.
	@Test
	public void hhh123Test() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		var one = new TestEntityOne();
		var two = new TestEntityTwo();
		var anotherTwo = new TestEntityTwo();
		one.setTwoList(Set.of(two, anotherTwo));
		entityManager.persist(one);
		entityManager.getTransaction().commit();
		entityManager.close();


		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Query q = entityManager.createNativeQuery("SELECT * FROM one", TestEntityOne.class);
		List<TestEntityOne> list = (List<TestEntityOne>)q.getResultList();

		System.out.println(list);
		var fetchedEntity = list.get(0);
		var twos = fetchedEntity.getTwoList();
		System.out.println(twos);

		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
