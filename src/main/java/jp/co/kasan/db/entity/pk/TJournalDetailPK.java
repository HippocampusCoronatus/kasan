/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.db.entity.pk;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import jp.co.kasan.journal.type.DebitCreditType;

/**
 *
 * @author rued97
 */
@Embeddable
public class TJournalDetailPK implements Serializable {

	@NotNull
	@Column(name = "account_book_no")
	private long accountBookNo;

	@NotNull
	@Column(name = "journal_no")
	private long journalNo;

	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private DebitCreditType type;

	@NotNull
	@Column(name = "no")
	private long no;

	public TJournalDetailPK() {
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
		int hash = 7;
		hash = 43 * hash + (int) (this.accountBookNo ^ (this.accountBookNo >>> 32));
		hash = 43 * hash + (int) (this.journalNo ^ (this.journalNo >>> 32));
		hash = 43 * hash + Objects.hashCode(this.type);
		hash = 43 * hash + (int) (this.no ^ (this.no >>> 32));
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
		final TJournalDetailPK other = (TJournalDetailPK) obj;
		if(this.accountBookNo != other.accountBookNo) {
			return false;
		}
		if(this.journalNo != other.journalNo) {
			return false;
		}
		if(this.no != other.no) {
			return false;
		}
		if(this.type != other.type) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jp.co.kasan.db.entity.TJournalDetailPK[ accountBookNo=" + accountBookNo + ", journalNo=" + journalNo + ", type=" + type + ", no=" + no + " ]";
	}

}
