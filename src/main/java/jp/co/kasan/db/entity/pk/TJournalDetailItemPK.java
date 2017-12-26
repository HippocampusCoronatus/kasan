/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.db.entity.pk;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import jp.co.kasan.journal.type.DebitCreditType;

/**
 *
 * @author rued97
 */
@Embeddable
public class TJournalDetailItemPK implements Serializable {

	@NotNull
	@Column(name = "account_book_no")
	private long accountBookNo;

	@NotNull
	@Column(name = "journal_no")
	private long journalNo;

	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "journal_detail_type")
	private DebitCreditType journalDetailType;

	@NotNull
	@Column(name = "journal_detail_no")
	private long journalDetailNo;

	@NotNull
	@Column(name = "no")
	private long no;

	public TJournalDetailItemPK() {
	}

	public long getAccountBookNo() {
		return accountBookNo;
	}

	public void setAccountBookNo(long accountBookNo) {
		this.accountBookNo = accountBookNo;
	}

	public long getJournalNo() {
		return journalNo;
	}

	public void setJournalNo(long journalNo) {
		this.journalNo = journalNo;
	}

	public DebitCreditType getJournalDetailType() {
		return journalDetailType;
	}

	public void setJournalDetailType(DebitCreditType journalDetailType) {
		this.journalDetailType = journalDetailType;
	}

	public long getJournalDetailNo() {
		return journalDetailNo;
	}

	public void setJournalDetailNo(long journalDetailNo) {
		this.journalDetailNo = journalDetailNo;
	}

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 47 * hash + (int) (this.accountBookNo ^ (this.accountBookNo >>> 32));
		hash = 47 * hash + (int) (this.journalNo ^ (this.journalNo >>> 32));
		hash = 47 * hash + Objects.hashCode(this.journalDetailType);
		hash = 47 * hash + (int) (this.journalDetailNo ^ (this.journalDetailNo >>> 32));
		hash = 47 * hash + (int) (this.no ^ (this.no >>> 32));
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
		final TJournalDetailItemPK other = (TJournalDetailItemPK) obj;
		if(this.accountBookNo != other.accountBookNo) {
			return false;
		}
		if(this.journalNo != other.journalNo) {
			return false;
		}
		if(this.journalDetailNo != other.journalDetailNo) {
			return false;
		}
		if(this.no != other.no) {
			return false;
		}
		if(this.journalDetailType != other.journalDetailType) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jp.co.kasan.db.entity.TJournalDetailItemPK[ accountBookNo=" + accountBookNo + ", journalNo=" + journalNo + ", journalDetailType=" + journalDetailType + ", journalDetailNo=" + journalDetailNo + ", no=" + no + " ]";
	}

}
