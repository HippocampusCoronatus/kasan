/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.web.service.api;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import jp.co.kasan.db.entity.MMember;

/**
 * 自身の会計APIを提供します。
 * @author rued97
 */
@RequestScoped
@Path("me/accounts")
public class MeAccountsAPI {

	/** ログイン中会員ID */
	// TODO:のっと実装
	// @Inject
	private String LoggedInMemberID;

	@Inject
	private EntityManager EM;

	/**
	 * 自身のすべての会計情報を提供します。
	 * @return 会計情報
	 */
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Account> getAccounts() {
		// TODO:とりあえずテストデータ返すだけのやつ。
		this.LoggedInMemberID = "yamada";
		List<Account> accounts = new ArrayList<>();
		{
			Account account = new Account();
			account.MemberID = this.LoggedInMemberID;
			account.PartnerMemberID = "sato";
			account.LoanAmount = 3500;
			accounts.add(account);
		}
		{
			Account account = new Account();
			account.MemberID = this.LoggedInMemberID;
			account.PartnerMemberID = "yamada";
			account.LoanAmount = -34120;
			accounts.add(account);
		}
		List<MMember> ms = this.EM.createQuery("select m from MMember m", MMember.class).getResultList();
		return accounts;
	}

	/**
	 * 自身の会計情報を新規に追加します。
	 * @param account 登録する会計情報
	 * @return 成功時は成功メッセージ付きのレスポンス
	 */
	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAccount(Account account) {
		// 新規追加の場合はPartnerMemberIDを受け付けないため無視する。
		account.PartnerMemberID = null;
		// TODO:登録処理
		return Response.ok().build();
	}

	/**
	 * 自身の特定の会計情報を提供します。
	 * @param partnerMemberID 取引相手会員ID
	 * @return 会計情報
	 */
	@Path("{PartnerMemberID}")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Account getAccount(@PathParam("PartnerMemberID") String partnerMemberID) {
		// TODO:とりあえずテストデータ返すだけのやつ。
		Account account = new Account();
		account.MemberID = this.LoggedInMemberID;
		account.PartnerMemberID = partnerMemberID;
		account.LoanAmount = 3500;
		return account;
	}

	/**
	 * 自身の会計情報を変更します。
	 * @param partnerMemberID 取引相手会員ID
	 * @param account 会計情報
	 * @return 成功時は成功メッセージ付きのレスポンス
	 */
	@Path("{PartnerMemberID}")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response modifyAccount(@PathParam("PartnerMemberID") String partnerMemberID, Account account) {
		// 変更の場合はPartnerMemberIDをパスで指定するため、JSONは無視する。
		account.PartnerMemberID = partnerMemberID;
		// TODO:変更処理
		return Response.ok().build();
	}
	
	/**
	 * 会計の情報を保持します。
	 */
	public static class Account {
		/** 会員ID */
		public String MemberID;
		/** 取引相手会員ID */
		public String PartnerMemberID;
		/** 取引相手会員名 */
		public String PartnerMemberName;
		/** 貸付金（融資額）　借りてる場合はマイナスになります。 */
		public int LoanAmount;
	}

}
