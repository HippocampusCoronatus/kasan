/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.db.entity;

import jp.co.kasan.db.entity.pk.TJournalDetailItemPK;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import jp.co.kasan.journal.type.DebitCreditType;

/**
 * 仕訳詳細品目
 * @author rued97
 */
@Entity
@Table(name = "t_journal_detail_item")
public class TJournalDetailItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	protected TJournalDetailItemPK tJournalDetailItemPK;

	@NotNull
	@Column(name = "amount")
	private long amount;

	@JoinColumns({
		@JoinColumn(name = "account_book_no", referencedColumnName = "account_book_no", insertable = false, updatable = false),
    	@JoinColumn(name = "account_title_code", referencedColumnName = "account_title_code"),
    	@JoinColumn(name = "account_title_item_no", referencedColumnName = "no")})
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private MAccountTitleItem mAccountTitleItem;

	@JoinColumns({
		@JoinColumn(name = "account_book_no", referencedColumnName = "account_book_no", insertable = false, updatable = false),
    	@JoinColumn(name = "journal_no", referencedColumnName = "journal_no", insertable = false, updatable = false),
    	@JoinColumn(name = "journal_detail_type", referencedColumnName = "type", insertable = false, updatable = false),
    	@JoinColumn(name = "journal_detail_no", referencedColumnName = "no", insertable = false, updatable = false)})
	@ManyToOne(optional = false)
	private TJournalDetail tJournalDetail;

	public TJournalDetailItem() {
		this.tJournalDetailItemPK = new TJournalDetailItemPK();
	}

	public TJournalDetailItemPK getTJournalDetailItemPK() {
		return this.tJournalDetailItemPK;
	}

	public void settJournalDetailItemPK(TJournalDetailItemPK tJournalDetailItemPK) {
		this.tJournalDetailItemPK = tJournalDetailItemPK;
	}

	public long getAccountBookNo() {
		return this.tJournalDetailItemPK.getAccountBookNo();
	}

	public void setAccountBookNo(long accountBookNo) {
		this.tJournalDetailItemPK.setAccountBookNo(accountBookNo);
	}

	public long getJournalNo() {
		return this.tJournalDetailItemPK.getJournalNo();
	}

	public void setJournalNo(long journalNo) {
		this.tJournalDetailItemPK.setJournalNo(journalNo);
	}

	public DebitCreditType getJournalDetailType() {
		return this.tJournalDetailItemPK.getJournalDetailType();
	}

	public void setJournalDetailType(DebitCreditType journalDetailType) {
		this.tJournalDetailItemPK.setJournalDetailType(journalDetailType);
	}

	public long getJournalDetailNo() {
		return this.tJournalDetailItemPK.getJournalDetailNo();
	}

	public void setJournalDetailNo(long journalDetailNo) {
		this.tJournalDetailItemPK.setJournalDetailNo(journalDetailNo);
	}

	public long getNo() {
		return this.tJournalDetailItemPK.getNo();
	}

	public void setNo(long no) {
		this.tJournalDetailItemPK.setNo(no);
	}


	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public MAccountTitleItem getMAccountTitleItem() {
		return mAccountTitleItem;
	}

	public void setMAccountTitleItem(MAccountTitleItem mAccountTitleItem) {
		this.mAccountTitleItem = mAccountTitleItem;
	}

	public TJournalDetail getTJournalDetail() {
		return tJournalDetail;
	}

	public void setTJournalDetail(TJournalDetail tJournalDetail) {
		this.tJournalDetail = tJournalDetail;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 47 * hash + Objects.hashCode(this.tJournalDetailItemPK);
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
		final TJournalDetailItem other = (TJournalDetailItem) obj;
		if(!Objects.equals(this.tJournalDetailItemPK, other.tJournalDetailItemPK)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jp.co.kasan.db.entity.TJournalDetailItem[ tJournalDetailItemPK=" + tJournalDetailItemPK + " ]";
	}

}
