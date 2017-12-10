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
public class TJournalDetailPK implements Serializable {

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
    @Column(name = "type")
	private DebitCreditType type;
	@Basic(optional = false)
    @NotNull
    @Column(name = "no")
	private long no;

	public TJournalDetailPK() {
	}

	public TJournalDetailPK(long accountBookNo, long journalNo, DebitCreditType type, long no) {
		this.accountBookNo = accountBookNo;
		this.journalNo = journalNo;
		this.type = type;
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

	public DebitCreditType getType() {
		return type;
	}

	public void setType(DebitCreditType type) {
		this.type = type;
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
		hash += (type != null ? type.hashCode() : 0);
		hash += (int) no;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof TJournalDetailPK)) {
			return false;
		}
		TJournalDetailPK other = (TJournalDetailPK) object;
		if (this.accountBookNo != other.accountBookNo) {
			return false;
		}
		if (this.journalNo != other.journalNo) {
			return false;
		}
		if ((this.type == null && other.type != null) || (this.type != null && !this.type.equals(other.type))) {
			return false;
		}
		if (this.no != other.no) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jp.co.kasan.db.entity.TJournalDetailPK[ accountBookNo=" + accountBookNo + ", journalNo=" + journalNo + ", type=" + type + ", no=" + no + " ]";
	}
	
}
