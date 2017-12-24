/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.db.entity.pk;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author rued97
 */
@Embeddable
public class TJournalPK implements Serializable {

	@NotNull
	@Column(name = "account_book_no")
	private long accountBookNo;

	@NotNull
	@Column(name = "no")
	private long no;

	public TJournalPK() {
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
		int hash = 7;
		hash = 59 * hash + (int) (this.accountBookNo ^ (this.accountBookNo >>> 32));
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(getClass() != obj.getClass()) {
			return false;
		}
		final TJournalPK other = (TJournalPK) obj;
		if(this.accountBookNo != other.accountBookNo) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jp.co.kasan.db.TJournalPK[ accountBookNo=" + accountBookNo + ", no=" + no + " ]";
	}

}
