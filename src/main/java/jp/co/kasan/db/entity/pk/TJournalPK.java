/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.db.entity.pk;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author rued97
 */
@Embeddable
public class TJournalPK implements Serializable {

	@Basic(optional = false)
    @NotNull
    @Column(name = "account_book_no")
	private long accountBookNo;
	@Basic(optional = false)
    @NotNull
    @Column(name = "no")
	private long no;

	public TJournalPK() {
	}

	public TJournalPK(long accountBookNo, long no) {
		this.accountBookNo = accountBookNo;
		this.no = no;
	}

	public long getAccountBookNo() {
		return accountBookNo;
	}

	public void setAccountBookNo(long accountBookNo) {
		this.accountBookNo = accountBookNo;
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
		hash += (int) no;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof TJournalPK)) {
			return false;
		}
		TJournalPK other = (TJournalPK) object;
		if (this.accountBookNo != other.accountBookNo) {
			return false;
		}
		if (this.no != other.no) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jp.co.kasan.db.TJournalPK[ accountBookNo=" + accountBookNo + ", no=" + no + " ]";
	}
	
}
