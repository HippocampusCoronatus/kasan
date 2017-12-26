/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.db.entity;

import jp.co.kasan.db.entity.pk.TJournalDetailPK;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;
import jp.co.kasan.journal.type.DebitCreditType;

/**
 * 仕訳詳細
 * @author rued97
 */
@Entity
@Table(name = "t_journal_detail")
public class TJournalDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	protected TJournalDetailPK tJournalDetailPK;

	@NotNull
	@Column(name = "amount")
	private long amount;

	@JoinColumns({
		@JoinColumn(name = "account_book_no", referencedColumnName = "account_book_no", insertable = false, updatable = false),
    	@JoinColumn(name = "account_title_code", referencedColumnName = "code")})
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private MAccountTitle mAccountTitle;

	@JoinColumns({
		@JoinColumn(name = "account_book_no", referencedColumnName = "account_book_no", insertable = false, updatable = false),
    	@JoinColumn(name = "journal_no", referencedColumnName = "no", insertable = false, updatable = false)})
	@ManyToOne(optional = false)
	private TJournal tJournal;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tJournalDetail")
	private List<TJournalDetailItem> tJournalDetailItemList;

	public TJournalDetail() {
		this.tJournalDetailPK = new TJournalDetailPK();
		this.tJournalDetailItemList = new ArrayList<>();
	}

	public TJournalDetailPK getTJournalDetailPK() {
		return tJournalDetailPK;
	}

	public void settJournalDetailPK(TJournalDetailPK tJournalDetailPK) {
		this.tJournalDetailPK = tJournalDetailPK;
	}

	public long getAccountBookNo() {
		return this.tJournalDetailPK.getAccountBookNo();
	}

	public void setAccountBookNo(long accountBookNo) {
		this.tJournalDetailPK.setAccountBookNo(accountBookNo);
	}

	public long getJournalNo() {
		return this.tJournalDetailPK.getJournalNo();
	}

	public void setJournalNo(long journalNo) {
		this.tJournalDetailPK.setJournalNo(journalNo);
	}

	public DebitCreditType getType() {
		return this.tJournalDetailPK.getType();
	}

	public void setType(DebitCreditType type) {
		this.tJournalDetailPK.setType(type);
	}

	public long getNo() {
		return this.tJournalDetailPK.getNo();
	}

	public void setNo(long no) {
		this.tJournalDetailPK.setNo(no);
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public MAccountTitle getMAccountTitle() {
		return mAccountTitle;
	}

	public void setMAccountTitle(MAccountTitle mAccountTitle) {
		this.mAccountTitle = mAccountTitle;
	}

	public TJournal getTJournal() {
		return tJournal;
	}

	public void setTJournal(TJournal tJournal) {
		this.tJournal = tJournal;
	}

	@XmlTransient
	public List<TJournalDetailItem> getTJournalDetailItemList() {
		return tJournalDetailItemList;
	}

	@Deprecated
	public void setTJournalDetailItemList(List<TJournalDetailItem> tJournalDetailItemList) {
		this.tJournalDetailItemList = tJournalDetailItemList;
	}

	public void addTJournalDetailItem(TJournalDetailItem tJournalDetailItem) {
		this.tJournalDetailItemList.add(tJournalDetailItem);
		tJournalDetailItem.setTJournalDetail(this);
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash + Objects.hashCode(this.tJournalDetailPK);
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
		final TJournalDetail other = (TJournalDetail) obj;
		if(!Objects.equals(this.tJournalDetailPK, other.tJournalDetailPK)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jp.co.kasan.db.entity.TJournalDetail[ tJournalDetailPK=" + tJournalDetailPK + " ]";
	}

}
