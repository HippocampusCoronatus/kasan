/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.db.entity.pk;

import java.io.Serializable;
import javax.persistence.Basic;
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

	@Basic(optional = false)
    @NotNull
    @Column(name = "account_book_no")
	private long accountBookNo;
	@Basic(optional = false)
    @NotNull
    @Column(name = "journal_no")
	private long journalNo;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "journal_detail_type")
	private DebitCreditType journalDetailType;
	@Basic(optional = false)
    @NotNull
    @Column(name = "journal_detail_no")
	private long journalDetailNo;
	@Basic(optional = false)
    @NotNull
    @Column(name = "no")
	private long no;

	public TJournalDetailItemPK() {
	}

	public TJournalDetailItemPK(long accountBookNo, long journalNo, DebitCreditType journalDetailType, long journalDetailNo, long no) {
		this.accountBookNo = accountBookNo;
		this.journalNo = journalNo;
		this.journalDetailType = journalDetailType;
		this.journalDetailNo = journalDetailNo;
		this.no = no;
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
		int hash = 0;
		hash += (int) accountBookNo;
		hash += (int) journalNo;
		hash += (journalDetailType != null ? journalDetailType.hashCode() : 0);
		hash += (int) journalDetailNo;
		hash += (int) no;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof TJournalDetailItemPK)) {
			return false;
		}
		TJournalDetailItemPK other = (TJournalDetailItemPK) object;
		if (this.accountBookNo != other.accountBookNo) {
			return false;
		}
		if (this.journalNo != other.journalNo) {
			return false;
		}
		if ((this.journalDetailType == null && other.journalDetailType != null) || (this.journalDetailType != null && !this.journalDetailType.equals(other.journalDetailType))) {
			return false;
		}
		if (this.journalDetailNo != other.journalDetailNo) {
			return false;
		}
		if (this.no != other.no) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jp.co.kasan.db.entity.TJournalDetailItemPK[ accountBookNo=" + accountBookNo + ", journalNo=" + journalNo + ", journalDetailType=" + journalDetailType + ", journalDetailNo=" + journalDetailNo + ", no=" + no + " ]";
	}
	
}
