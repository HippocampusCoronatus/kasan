/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.db;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * EntityManager創造主。
 * @author rued97
 */
@Dependent
public class EntityManagerProducer {

	@PersistenceContext(unitName="KasanDB")
	private EntityManager em;

	/**
	 * EntityManagerを取得します。
	 * @return EntityManager
	 */
	@Produces
	public EntityManager getEntityManager() {
		return this.em;
	}

}