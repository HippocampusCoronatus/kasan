/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.journal;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import jp.co.kasan.db.entity.MAccountBook;
import jp.co.kasan.db.entity.MAccountTitle;
import jp.co.kasan.db.finder.MAccountBookFinder;
import jp.co.kasan.journal.type.SystemAccountTitle;

/**
 * 標準会計帳簿作り人。
 * @author rued97
 */
@Dependent
public class GeneralAccountBookGenerator {

	@Inject
	private MAccountBookFinder AccountBookFinder;

	@Inject
	private EntityManager EM;

	/**
	 * 新規に会計帳簿を作成します。
	 * DBへの登録は行いません。
	 * @param name 会計帳簿の名前
	 * @return 
	 */
	public MAccountBook generate(String name) {
		Long maxNo = this.AccountBookFinder.findMaxNo();
		long no = (maxNo == null) ? 0 : maxNo + 1;
		MAccountBook book = new MAccountBook();
		book.setNo(no);
		book.setName(name);
		// システムで使用する固定の勘定科目を設定。
		List<MAccountTitle> titles = EnumSet.allOf(SystemAccountTitle.class).stream()
				.map(s -> {
					MAccountTitle title = new MAccountTitle(book, s.getCode());
					title.setName(s.getName());
					title.setType(s.getType());
					title.setAccountTitleGroup("一般");
					return title;
				})
				.collect(Collectors.toList());
		book.setMAccountTitleList(titles);
		return book;
	}
}
