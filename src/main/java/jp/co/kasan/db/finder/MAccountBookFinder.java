/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.db.finder;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import jp.co.kasan.db.entity.MAccountBook;

/**
 * 会計帳簿マスタの検索を担当します。
 * @author rued97
 */
@RequestScoped
public class MAccountBookFinder {

	@Inject
	private EntityManager EM;

	/**
	 * すべての会計帳簿を取得します。
	 * @return 全会計帳簿
	 */
	public List<MAccountBook> load() {
		return this.EM.createQuery("SELECT b FROM MAccountBook b", MAccountBook.class).getResultList();
	}

	/**
	 * すべての会計帳簿の中で最大Noを取得します。
	 * @return 最大No
	 */
	public Long findMaxNo() {
		TypedQuery<MAccountBook> query = this.EM.createQuery("SELECT b FROM MAccountBook b ORDER BY b.no DESC", MAccountBook.class);
		query.setMaxResults(1);
		try {
			return query.getSingleResult().getNo();
		} catch(NoResultException e) {
			return null;
		}
	}

}
