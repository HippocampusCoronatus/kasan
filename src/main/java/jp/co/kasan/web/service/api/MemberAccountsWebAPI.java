/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.web.service.api;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 各会員の会計APIを提供します。
 * @author rued97
 */
@RequestScoped
@Path("accounts")
public class MemberAccountsWebAPI {

	/** ログイン中会員ID */
	// TODO:のっと実装
	// @Inject
	private String LoggedInMemberID;

	/**
	 * 自身のすべての会計情報を提供します。
	 * @return 
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
		return accounts;
	}
	
	/**
	 * 会計の情報を保持します。
	 */
	public static class Account {
		/** 会員ID */
		public String MemberID;
		/** 取引相手会員ID */
		public String PartnerMemberID;
		/** 貸付金（融資額）　借りてる場合はマイナスになります。 */
		public int LoanAmount;
	}

}