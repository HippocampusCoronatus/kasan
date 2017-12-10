/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.db.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import jp.co.kasan.db.entity.pk.TJournalPK;

/**
 *
 * @author rued97
 */
@Entity
@Table(name = "t_journal")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "TJournal.findAll", query = "SELECT t FROM TJournal t"),
	@NamedQuery(name = "TJournal.findByAccountBookNo", query = "SELECT t FROM TJournal t WHERE t.tJournalPK.accountBookNo = :accountBookNo"),
	@NamedQuery(name = "TJournal.findByNo", query = "SELECT t FROM TJournal t WHERE t.tJournalPK.no = :no"),
	@NamedQuery(name = "TJournal.findByTradingDate", query = "SELECT t FROM TJournal t WHERE t.tradingDate = :tradingDate")})
public class TJournal implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected TJournalPK tJournalPK;
	@Basic(optional = false)
    @NotNull
    @Column(name = "trading_date")
    @Temporal(TemporalType.DATE)
	private LocalDate tradingDate;
	@JoinColumn(name = "account_book_no", referencedColumnName = "no", insertable = false, updatable = false)
    @ManyToOne(optional = false)
	private MAccountBook mAccountBook;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tJournal")
	private List<TJournalDetail> tJournalDetailList;

	public TJournal() {
	}

	public TJournal(TJournalPK tJournalPK) {
		this.tJournalPK = tJournalPK;
	}

	public TJournal(MAccountBook accountBook, long no) {
		this(accountBook.getNo(), no);
		this.mAccountBook = accountBook;
	}

	private TJournal(long accountBookNo, long no) {
		this.tJournalPK = new TJournalPK(accountBookNo, no);
	}

	public TJournalPK getTJournalPK() {
		return tJournalPK;
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
		this.mAccountBook = mAccountBook;
	}

	@XmlTransient
	public List<TJournalDetail> getTJournalDetailList() {
		return tJournalDetailList;
	}

	public void setTJournalDetailList(List<TJournalDetail> tJournalDetailList) {
		this.tJournalDetailList = tJournalDetailList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (tJournalPK != null ? tJournalPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof TJournal)) {
			return false;
		}
		TJournal other = (TJournal) object;
		if ((this.tJournalPK == null && other.tJournalPK != null) || (this.tJournalPK != null && !this.tJournalPK.equals(other.tJournalPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jp.co.kasan.db.entity.TJournal[ tJournalPK=" + tJournalPK + " ]";
	}

	/**
	 * 会計帳簿番号を取得します。
	 * @return 会計帳簿番号
	 */
	public long getAccountBookNo() {
		return this.tJournalPK.getAccountBookNo();
	}

	/**
	 * 番号を取得します。
	 * @return 番号
	 */
	public long getNo() {
		return this.tJournalPK.getNo();
	}	
}
