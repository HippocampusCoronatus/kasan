/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.db.entity;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * MMemberのテスト。
 * @author rued97
 */
public class MMemberTest {

	@BeforeClass
	public static void テスト事前準備() {
		EntityManager em = getEntityManager();
		EntityTransaction tran = em.getTransaction();

		tran.begin();
		em.createNativeQuery("DELETE FROM m_member_account_book_relation").executeUpdate();
		em.createNativeQuery("DELETE FROM m_member").executeUpdate();
		em.createNativeQuery("DELETE FROM m_account_book").executeUpdate();
		tran.commit();
	}
	
	@Test
	public void 登録_登録できる() {
		EntityManager em = getEntityManager();
		EntityTransaction tran = em.getTransaction();

		tran.begin();

		MMember m = new MMember();
		m.setNo(1L);
		m.setName("テスト太郎");
		m.setEmail("abc@example.com");

		{
			MAccountBook b = new MAccountBook();
			b.setNo(1L);
			b.setName("テストブック");
			m.addMAccountBook(b);
		}

		em.persist(m);

		tran.commit();
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
