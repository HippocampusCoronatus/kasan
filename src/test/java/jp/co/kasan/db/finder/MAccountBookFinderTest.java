/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.db.finder;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import jp.co.kasan.db.entity.MMember;
import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.junit.Test;

/**
 *
 * @author rued97
 */
public class MAccountBookFinderTest {
	
	public MAccountBookFinderTest() {
	}

	/**
	 * Test of load method, of class MAccountBookFinder.
	 */
	@Test
	public void testLoad() {
		getEntityManager().createQuery("SELECT m FROM MMember m", MMember.class).getResultList();
	}

	/**
	 * Test of findMaxNo method, of class MAccountBookFinder.
	 */
	@Test
	public void testFindMaxNo() {
	}

	/**
	 * EntityManagerをMock化します。
	 */
	private static EntityManager getEntityManager() {
		// Test用のpersistence.xmlを読み込む
		Map<String, String> setting = new HashMap<>();
		setting.put(PersistenceUnitProperties.ECLIPSELINK_PERSISTENCE_XML, "META-INF/test.persistence.xml");
		// RESOURCE_LOCALなEntityManagerを取得
		final EntityManager testEM = Persistence.createEntityManagerFactory("KasanDB", setting)
			.createEntityManager();
		return testEM;
	}
	
}
