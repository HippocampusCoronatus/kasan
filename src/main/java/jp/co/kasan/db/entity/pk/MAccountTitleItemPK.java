/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.db.entity.pk;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author rued97
 */
@Embeddable
public class MAccountTitleItemPK implements Serializable {

	@Basic(optional = false)
    @NotNull
    @Column(name = "account_book_no")
	private long accountBookNo;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "account_title_code")
	private String accountTitleCode;
	@Basic(optional = false)
    @NotNull
    @Column(name = "no")
	private long no;

	public MAccountTitleItemPK() {
	}

	public MAccountTitleItemPK(long accountBookNo, String accountTitleCode, long no) {
		this.accountBookNo = accountBookNo;
		this.accountTitleCode = accountTitleCode;
		this.no = no;
	}

	public long getAccountBookNo() {
		return accountBookNo;
	}

	public void setAccountBookNo(long accountBookNo) {
		this.accountBookNo = accountBookNo;
	}

	public String getAccountTitleCode() {
		return accountTitleCode;
	}

	public void setAccountTitleCode(String accountTitleCode) {
		this.accountTitleCode = accountTitleCode;
	}

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) accountBookNo;
		hash += (accountTitleCode != null ? accountTitleCode.hashCode() : 0);
		hash += (int) no;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof MAccountTitleItemPK)) {
			return false;
		}
		MAccountTitleItemPK other = (MAccountTitleItemPK) object;
		if (this.accountBookNo != other.accountBookNo) {
			return false;
		}
		if ((this.accountTitleCode == null && other.accountTitleCode != null) || (this.accountTitleCode != null && !this.accountTitleCode.equals(other.accountTitleCode))) {
			return false;
		}
		if (this.no != other.no) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jp.co.kasan.db.entity.MAccountTitleItemPK[ accountBookNo=" + accountBookNo + ", accountTitleCode=" + accountTitleCode + ", no=" + no + " ]";
	}
	
}
