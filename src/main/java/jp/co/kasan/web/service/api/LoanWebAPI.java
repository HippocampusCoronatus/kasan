/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.web.service.api;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import jp.co.kasan.journal.LoanService;
import jp.co.kasan.journal.LoanService.Loan;

/**
 * 自身の会計APIを提供します。
 * @author rued97
 */
@RequestScoped
@Path("loan")
public class LoanWebAPI {

	/** ローンサービス */
	@Inject
	private LoanService Service;

	/**
	 * 自身のすべてのローン情報を提供します。
	 * @return ローン情報
	 */
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Loan> getAccounts() {
		return this.Service.getLoans();
	}

	/**
	 * 新規に取引相手を登録して現金を借ります。
	 * @param loan 登録するローン情報
	 */
	@Path("/lend")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void addAccount(Loan loan) {
		this.Service.enter(null, loan.getPartnerMemberName(), 0, 0);
	}

	/**
	 * 自身の特定の会計情報を提供します。
	 * @param partnerMemberID 取引相手会員ID
	 * @return 会計情報
	 */
//	@Path("{PartnerMemberID}")
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Account getAccount(@PathParam("PartnerMemberID") String partnerMemberID) {
//		// TODO:とりあえずテストデータ返すだけのやつ。
//		Account account = new Account();
//		account.MemberID = this.LoggedInMemberID;
//		account.PartnerMemberID = partnerMemberID;
//		account.LoanAmount = 3500;
//		return account;
//	}

	/**
	 * 自身の会計情報を変更します。
	 * @param partnerMemberID 取引相手会員ID
	 * @param account 会計情報
	 * @return 成功時は成功メッセージ付きのレスポンス
	 */
//	@Path("{PartnerMemberID}")
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Account modifyAccount(@PathParam("PartnerMemberID") String partnerMemberID, Account account) {
//		// 変更の場合はPartnerMemberIDをパスで指定するため、JSONは無視する。
//		account.PartnerMemberID = partnerMemberID;
//		// TODO:変更処理
//		return null;
//	}
	

}
