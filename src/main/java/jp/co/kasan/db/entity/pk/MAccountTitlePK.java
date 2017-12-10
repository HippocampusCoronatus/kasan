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
public class MAccountTitlePK implements Serializable {

	@Basic(optional = false)
    @NotNull
    @Column(name = "account_book_no")
	private long accountBookNo;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "code")
	private String code;

	public MAccountTitlePK() {
	}

	public MAccountTitlePK(long accountBookNo, String code) {
		this.accountBookNo = accountBookNo;
		this.code = code;
	}

	public long getAccountBookNo() {
		return accountBookNo;
	}

	public void setAccountBookNo(long accountBookNo) {
		this.accountBookNo = accountBookNo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) accountBookNo;
		hash += (code != null ? code.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof MAccountTitlePK)) {
			return false;
		}
		MAccountTitlePK other = (MAccountTitlePK) object;
		if (this.accountBookNo != other.accountBookNo) {
			return false;
		}
		if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jp.co.kasan.db.entity.MAccountTitlePK[ accountBookNo=" + accountBookNo + ", code=" + code + " ]";
	}
	
}
