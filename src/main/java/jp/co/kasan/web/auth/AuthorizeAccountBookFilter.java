/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.web.auth;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import jp.co.kasan.db.entity.MAccountBook;
import jp.co.kasan.utils.StringUtils;

/**
 * 認可する会計帳簿を設定します。
 * ログインしていない場合は、認可されません。
 * 指定されている会計帳簿番号が使用可能な会計帳簿の場合だけ、その会計帳簿が認可されます。
 * 指定されている会計帳簿番号が指定されていない場合は、標準の会計帳簿が認可されます。
 * @author rued97
 */
@WebFilter(filterName = "AuthorizeAccountBookFilter")
public class AuthorizeAccountBookFilter implements Filter {

	@Inject
	private Logger logger;
	@Inject @LoggedIn
	private Member LoggedInMember;
	@Inject
	private AuthorizedAccountBookProducer Producer;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		this.logger.log(Level.FINE, "★☆★☆★☆会計帳簿フィルター★☆★☆★☆");
		System.out.println("Inside filter two.");
		// 会員認証がされていない場合はどの会計帳簿も使用できない。
		if(this.LoggedInMember == null) {
			this.Producer.setAuthorizedAccountBook(null);
		}
		List<MAccountBook> books = this.LoggedInMember.getEntity().getMAccountBookList();
		if((books == null) || books.isEmpty()) {
			throw new IllegalStateException("会計帳簿が存在しない会員です。[会員番号:" + this.LoggedInMember.getNo() + "]");
		}
		String account_book_no = request.getParameter("account_book_no");
		if(StringUtils.isEmpty(account_book_no)) {
			// TODO:現在は1会員に対して1会計帳簿しかないため、それを標準としています。
			// …本来は標準は設定しないほうが良いかも。
			this.Producer.setAuthorizedAccountBook(books.get(0));
		}
		if(account_book_no.matches("\\A[0-0]*\\z") == false) {
			throw new IllegalStateException("会計帳簿番号の指定が不正です。[会計帳簿番号:" + account_book_no + "]");
		}
		MAccountBook book = books.stream()
				.filter(b -> b.getNo().equals(Long.valueOf(account_book_no)))
				.findFirst()
				.orElse(null);
		if(book == null) {
			throw new IllegalStateException("認可できない会計帳簿番号です。[会員番号:" + this.LoggedInMember.getNo() + " 会計帳簿番号:" + account_book_no + "]");
		}
		this.Producer.setAuthorizedAccountBook(book);
        chain.doFilter(request, response);
    }

    @Override
	public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
	public void destroy() {}

}
