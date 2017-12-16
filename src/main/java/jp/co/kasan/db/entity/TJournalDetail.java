/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.db.entity;

import jp.co.kasan.db.entity.pk.TJournalDetailPK;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import jp.co.kasan.journal.type.DebitCreditType;

/**
 *
 * @author rued97
 */
@Entity
@Table(name = "t_journal_detail")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "TJournalDetail.findAll", query = "SELECT t FROM TJournalDetail t"),
	@NamedQuery(name = "TJournalDetail.findByAccountBookNo", query = "SELECT t FROM TJournalDetail t WHERE t.tJournalDetailPK.accountBookNo = :accountBookNo"),
	@NamedQuery(name = "TJournalDetail.findByJournalNo", query = "SELECT t FROM TJournalDetail t WHERE t.tJournalDetailPK.journalNo = :journalNo"),
	@NamedQuery(name = "TJournalDetail.findByType", query = "SELECT t FROM TJournalDetail t WHERE t.tJournalDetailPK.type = :type"),
	@NamedQuery(name = "TJournalDetail.findByNo", query = "SELECT t FROM TJournalDetail t WHERE t.tJournalDetailPK.no = :no"),
	@NamedQuery(name = "TJournalDetail.findByAmount", query = "SELECT t FROM TJournalDetail t WHERE t.amount = :amount")})
public class TJournalDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected TJournalDetailPK tJournalDetailPK;
	@Basic(optional = false)
    @NotNull
    @Column(name = "amount")
	private long amount;
	@JoinColumns({
    	@JoinColumn(name = "account_book_no", referencedColumnName = "account_book_no", insertable = false, updatable = false),
    	@JoinColumn(name = "account_title_code", referencedColumnName = "code")})
    @ManyToOne(optional = false)
	private MAccountTitle mAccountTitle;
	@JoinColumns({
    	@JoinColumn(name = "account_book_no", referencedColumnName = "account_book_no", insertable = false, updatable = false),
    	@JoinColumn(name = "journal_no", referencedColumnName = "no", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
	private TJournal tJournal;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tJournalDetail")
	private List<TJournalDetailItem> tJournalDetailItemList;

	public TJournalDetail() {
	}

	public TJournalDetail(TJournal journal, DebitCreditType type, long no) {
		this(journal.getAccountBookNo(), journal.getNo(), type, no);
		this.tJournal = journal;
	}

	public TJournalDetail(long accountBookNo, long journalNo, DebitCreditType type, long no) {
		this.tJournalDetailPK = new TJournalDetailPK(accountBookNo, journalNo, type, no);
	}

	public TJournalDetailPK getTJournalDetailPK() {
		return tJournalDetailPK;
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

	public void setTJournalDetailItemList(List<TJournalDetailItem> tJournalDetailItemList) {
		this.tJournalDetailItemList = tJournalDetailItemList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (tJournalDetailPK != null ? tJournalDetailPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof TJournalDetail)) {
			return false;
		}
		TJournalDetail other = (TJournalDetail) object;
		if ((this.tJournalDetailPK == null && other.tJournalDetailPK != null) || (this.tJournalDetailPK != null && !this.tJournalDetailPK.equals(other.tJournalDetailPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jp.co.kasan.db.entity.TJournalDetail[ tJournalDetailPK=" + tJournalDetailPK + " ]";
	}

	/**
	 * 会計帳簿番号を取得します。
	 * @return 会計帳簿番号
	 */
	public long getAccountBookNo() {
		return this.tJournalDetailPK.getAccountBookNo();
	}

	/**
	 * 仕訳番号を取得します。
	 * @return 仕訳番号
	 */
	public long getJournalNo() {
		return this.tJournalDetailPK.getJournalNo();
	}

	/**
	 * 番号を取得します。
	 * @return 番号
	 */
	public long getNo() {
		return this.tJournalDetailPK.getNo();
	}

	/**
	 * 種別を取得します。
	 * @return 種別
	 */
	public DebitCreditType getType() {
		return this.tJournalDetailPK.getType();
	}

}
