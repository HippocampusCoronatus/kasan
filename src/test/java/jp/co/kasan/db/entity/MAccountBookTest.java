/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.db.entity;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import jp.co.kasan.journal.type.SystemAccountTitle;
import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.junit.Test;

/**
 * MAccountBookのテスト。
 * @author rued97
 */
public class MAccountBookTest {
	
	@Test
	public void 登録_勘定科目sを設定して登録できる() {
//		EntityManager em = getEntityManager();
//		EntityTransaction tran = em.getTransaction();
//		tran.begin();
//		Long maxNo = 9L;
//		long no = (maxNo == null) ? 0 : maxNo + 1;
//		MAccountBook book = new MAccountBook(no, "テスト用");
//		// システムで使用する固定の勘定科目を設定。
//		List<MAccountTitle> titles = EnumSet.allOf(SystemAccountTitle.class).stream()
//				.map(s -> {
//					MAccountTitle title = new MAccountTitle(book, s.getCode());
//					title.setName(s.getName());
//					title.setType(s.getType());
//					title.setAccountTitleGroup("一般");
//					return title;
//				})
//				.collect(Collectors.toList());
//		book.setMAccountTitleList(titles);
//		em.persist(book);
//
//		tran.commit();
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
