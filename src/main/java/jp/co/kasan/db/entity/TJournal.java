/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.db.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;
import jp.co.kasan.db.entity.pk.TJournalPK;

/**
 * 仕訳
 * @author rued97
 */
@Entity
@Table(name = "t_journal")
public class TJournal implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	protected TJournalPK tJournalPK;

	@NotNull
	@Column(name = "trading_date")
	@Temporal(TemporalType.DATE)
	private LocalDate tradingDate;

	@JoinColumn(name = "account_book_no",
			referencedColumnName = "no", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private MAccountBook mAccountBook;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tJournal")
	private List<TJournalDetail> tJournalDetailList;

	public TJournal() {
		this.tJournalPK = new TJournalPK();
		this.tJournalDetailList = new ArrayList<>();
	}

	public TJournalPK getTJournalPK() {
		return tJournalPK;
	}

	public void settJournalPK(TJournalPK tJournalPK) {
		this.tJournalPK = tJournalPK;
	}

	public long getAccountBookNo() {
		return this.tJournalPK.getAccountBookNo();
	}

	public long getNo() {
		return this.tJournalPK.getNo();
	}

	public void setNo(long no) {
		this.tJournalPK.setNo(no);
	}

	public LocalDate getTradingDate() {
		return tradingDate;
	}

	public void setTradingDate(LocalDate tradingDate) {
		this.tradingDate = tradingDate;
	}

	public MAccountBook getMAccountBook() {
		return mAccountBook;
	}

	public void setMAccountBook(MAccountBook mAccountBook) {
		this.tJournalPK.setAccountBookNo(mAccountBook.getNo());
		this.mAccountBook = mAccountBook;
	}

	@XmlTransient
	public List<TJournalDetail> getTJournalDetailList() {
		return tJournalDetailList;
	}

	@Deprecated
	public void setTJournalDetailList(List<TJournalDetail> tJournalDetailList) {
		this.tJournalDetailList = tJournalDetailList;
	}

	public void addTJournalDetail(TJournalDetail tJournalDetail) {
		this.tJournalDetailList.add(tJournalDetail);
		tJournalDetail.setTJournal(this);
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 59 * hash + Objects.hashCode(this.tJournalPK);
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
		final TJournal other = (TJournal) obj;
		if(!Objects.equals(this.tJournalPK, other.tJournalPK)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jp.co.kasan.db.entity.TJournal[ tJournalPK=" + tJournalPK + " ]";
	}

}
