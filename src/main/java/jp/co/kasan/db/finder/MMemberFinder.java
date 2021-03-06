/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.db.finder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import jp.co.kasan.db.entity.MMember;
import jp.co.kasan.utils.StringUtils;

/**
 * 会員マスタの検索を担当します。
 * @author rued97
 */
@RequestScoped
public class MMemberFinder {

	@Inject
	private EntityManager EM;

	/**
	 * すべての会員を取得します。
	 * @return 全会員
	 */
	public List<MMember> load() {
		return this.EM.createQuery("SELECT m FROM MMember m", MMember.class).getResultList();
	}
	
	/**
	 * 会員番号から会員を取得します。
	 * @param no 会員番号
	 * @return 会員リスト。みつからない場合はnull。
	 */
	public MMember findByNo(Long no) {
		Condition c = new Condition();
		c.No = no;
		List<MMember> members = this.findBy(c);
		if(members.isEmpty()) {
			return null;
		}
		// PKを指定しているので1つしかない前提。
		return members.get(0);
	}

	/**
	 * Eメールから会員リストを取得します。
	 * @param email Eメール
	 * @return 会員リスト
	 */
	public List<MMember> findByEMail(String email) {
		Condition c = new Condition();
		c.EMail = email;
		return this.findBy(c);
	}

	/**
	 * Eメールとパスワードから会員リストを取得します。
	 * @param email Eメール
	 * @param password ハッシュ化済みのパスワード
	 * @return 会員リスト
	 */
	public List<MMember> findByEMailAndPassword(String email, byte[] password) {
		Condition c = new Condition();
		c.EMail = email;
		c.Password = password;
		return this.findBy(c);
	}

	/**
	 * すべての会員の中で最大Noを取得します。
	 * @return 最大No
	 */
	public Long findMaxNo() {
		TypedQuery<MMember> query = this.EM.createQuery("SELECT m FROM MMember m ORDER BY m.no DESC", MMember.class);
		query.setMaxResults(1);
		try {
			return query.getSingleResult().getNo();
		} catch(NoResultException e) {
			return null;
		}
	}

	/**
	 * 指定された検索条件から会員リストを取得します。
	 * @param c 検索条件
	 * @return 会員マスタリスト
	 */
	public List<MMember> findBy(Condition c) {
		if(c == null) {
			return this.load();
		}
		List<String> wheres = new ArrayList<>();
		Map<String, Object> params = new HashMap<>();
		if(c.No != null) {
			wheres.add("m.no = :no");
			params.put("no", c.No);
		}
		if(StringUtils.isEmpty(c.EMail) == false) {
			wheres.add("m.email = :email");
			params.put("email", c.EMail);
		}
		if(c.Password.length > 0) {
			wheres.add("m.password = :password");
			params.put("password", c.Password);
		}
		if(wheres.isEmpty()) {
			return this.load();
		}
		String jqql = "SELECT m FROM MMember m WHERE " + wheres.stream().collect(Collectors.joining(" AND "));
		TypedQuery<MMember> query = this.EM.createQuery(jqql, MMember.class);
		params.entrySet().stream().forEach(e -> query.setParameter(e.getKey(), e.getValue()));
		return query.getResultList();
	}

	/**
	 * 検索条件。
	 */
	public static class Condition {
		/** 会員番号 */
		public Long No;
		/** Eメール */
		public String EMail;
		/** パスワード */
		public byte[] Password;
	}
}
