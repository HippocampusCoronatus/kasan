/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.db;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * EntityManager創造主。
 * @author rued97
 */
@RequestScoped
public class EntityManagerProducer {

	@PersistenceUnit(unitName="KasanDB")
	private EntityManagerFactory EMF;

	private EntityManager EM;

	/**
	 * EntityManagerを取得します。
	 * @return EntityManager
	 */
	@Produces
	public EntityManager getEntityManager() {
		if(EM == null) {
			this.EM = this.EMF.createEntityManager();
		}
		return this.EM;
	}

}