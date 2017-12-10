/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.db.entity;

import jp.co.kasan.db.entity.pk.TJournalDetailItemPK;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import jp.co.kasan.journal.type.DebitCreditType;

/**
 *
 * @author rued97
 */
@Entity
@Table(name = "t_journal_detail_item")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "TJournalDetailItem.findAll", query = "SELECT t FROM TJournalDetailItem t"),
	@NamedQuery(name = "TJournalDetailItem.findByAccountBookNo", query = "SELECT t FROM TJournalDetailItem t WHERE t.tJournalDetailItemPK.accountBookNo = :accountBookNo"),
	@NamedQuery(name = "TJournalDetailItem.findByJournalNo", query = "SELECT t FROM TJournalDetailItem t WHERE t.tJournalDetailItemPK.journalNo = :journalNo"),
	@NamedQuery(name = "TJournalDetailItem.findByJournalDetailType", query = "SELECT t FROM TJournalDetailItem t WHERE t.tJournalDetailItemPK.journalDetailType = :journalDetailType"),
	@NamedQuery(name = "TJournalDetailItem.findByJournalDetailNo", query = "SELECT t FROM TJournalDetailItem t WHERE t.tJournalDetailItemPK.journalDetailNo = :journalDetailNo"),
	@NamedQuery(name = "TJournalDetailItem.findByNo", query = "SELECT t FROM TJournalDetailItem t WHERE t.tJournalDetailItemPK.no = :no"),
	@NamedQuery(name = "TJournalDetailItem.findByAmount", query = "SELECT t FROM TJournalDetailItem t WHERE t.amount = :amount")})
public class TJournalDetailItem implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected TJournalDetailItemPK tJournalDetailItemPK;
	@Basic(optional = false)
    @NotNull
    @Column(name = "amount")
	private long amount;
	@JoinColumns({
    	@JoinColumn(name = "account_book_no", referencedColumnName = "account_book_no", insertable = false, updatable = false),
    	@JoinColumn(name = "account_title_code", referencedColumnName = "account_title_code"),
    	@JoinColumn(name = "account_title_item_no", referencedColumnName = "no")})
    @ManyToOne(optional = false)
	private MAccountTitleItem mAccountTitleItem;
	@JoinColumns({
    	@JoinColumn(name = "account_book_no", referencedColumnName = "account_book_no", insertable = false, updatable = false),
    	@JoinColumn(name = "journal_no", referencedColumnName = "journal_no", insertable = false, updatable = false),
    	@JoinColumn(name = "journal_detail_type", referencedColumnName = "type", insertable = false, updatable = false),
    	@JoinColumn(name = "journal_detail_no", referencedColumnName = "no", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
	private TJournalDetail tJournalDetail;

	public TJournalDetailItem() {
	}

	public TJournalDetailItem(TJournalDetail journalDetail, long no) {
		this(journalDetail.getAccountBookNo(), journalDetail.getNo(), journalDetail.getType(), journalDetail.getJournalNo(), no);
		this.tJournalDetail = journalDetail;
	}

	private TJournalDetailItem(long accountBookNo, long journalNo, DebitCreditType journalDetailType, long journalDetailNo, long no) {
		this.tJournalDetailItemPK = new TJournalDetailItemPK(accountBookNo, journalNo, journalDetailType, journalDetailNo, no);
	}

	public TJournalDetailItemPK getTJournalDetailItemPK() {
		return tJournalDetailItemPK;
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
		int hash = 0;
		hash += (tJournalDetailItemPK != null ? tJournalDetailItemPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof TJournalDetailItem)) {
			return false;
		}
		TJournalDetailItem other = (TJournalDetailItem) object;
		if ((this.tJournalDetailItemPK == null && other.tJournalDetailItemPK != null) || (this.tJournalDetailItemPK != null && !this.tJournalDetailItemPK.equals(other.tJournalDetailItemPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jp.co.kasan.db.entity.TJournalDetailItem[ tJournalDetailItemPK=" + tJournalDetailItemPK + " ]";
	}
	
}
