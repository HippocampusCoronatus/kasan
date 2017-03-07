/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.members;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import jp.co.kasan.db.finder.MMemberFinder;

/**
 * 会員番号の裁判を担当します。
 * @author rued97
 */
@Dependent
public class MemberNoIncrementor {

	@Inject
	private MMemberFinder MemberFinder;

	/**
	 * 会員番号の次番号を取得します。
	 * TODO:とくに深く考えていない。DBで管理する必要あり？
	 * @return 次番号
	 */
	public long next() {
		return this.MemberFinder.load().size();
	}
}
