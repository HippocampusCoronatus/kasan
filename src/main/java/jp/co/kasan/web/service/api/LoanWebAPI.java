/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.web.service.api;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import jp.co.kasan.web.auth.LoggedIn;
import jp.co.kasan.web.auth.Member;

/**
 * 自身の会計APIを提供します。
 * @author rued97
 */
@RequestScoped
@Path("loans")
public class LoanWebAPI {

	@Inject @LoggedIn
	private Member LoggedInMember;

	/**
	 * 自身のすべてのローン情報を提供します。
	 * @return ローン情報
	 */
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Loan> getLoans() {
		// TODO:とりあえずテストデータ返すだけのやつ。
		List<Loan> accounts = new ArrayList<>();
		{
			Loan loan = new Loan();
			loan.PartnerMemberID = "sato";
			loan.LoanAmount = 3500;
			accounts.add(loan);
		}
		{
			Loan loan = new Loan();
			loan.PartnerMemberID = "yamada";
			loan.LoanAmount = -34120;
			accounts.add(loan);
		}
		return accounts;
	}
	
	/**
	 * 会計の情報を保持します。
	 */
	public static class Loan {
		/** 取引相手会員ID */
		public String PartnerMemberID;
		/** 貸付金（融資額）　借りてる場合はマイナスになります。 */
		public int LoanAmount;
	}

}
