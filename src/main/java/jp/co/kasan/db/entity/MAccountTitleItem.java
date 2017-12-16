/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.db.entity;

import jp.co.kasan.db.entity.pk.MAccountTitleItemPK;
import java.io.Serializable;
import java.util.List;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rued97
 */
@Entity
@Table(name = "m_account_title_item")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "MAccountTitleItem.findAll", query = "SELECT m FROM MAccountTitleItem m"),
	@NamedQuery(name = "MAccountTitleItem.findByAccountBookNo", query = "SELECT m FROM MAccountTitleItem m WHERE m.mAccountTitleItemPK.accountBookNo = :accountBookNo"),
	@NamedQuery(name = "MAccountTitleItem.findByAccountTitleCode", query = "SELECT m FROM MAccountTitleItem m WHERE m.mAccountTitleItemPK.accountTitleCode = :accountTitleCode"),
	@NamedQuery(name = "MAccountTitleItem.findByNo", query = "SELECT m FROM MAccountTitleItem m WHERE m.mAccountTitleItemPK.no = :no"),
	@NamedQuery(name = "MAccountTitleItem.findByName", query = "SELECT m FROM MAccountTitleItem m WHERE m.name = :name")})
public class MAccountTitleItem implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected MAccountTitleItemPK mAccountTitleItemPK;
	@Size(max = 255)
    @Column(name = "name")
	private String name;
	@JoinColumns({
    	@JoinColumn(name = "account_book_no", referencedColumnName = "account_book_no", insertable = false, updatable = false),
    	@JoinColumn(name = "account_title_code", referencedColumnName = "code", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
	private MAccountTitle mAccountTitle;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "mAccountTitleItem")
	private List<TJournalDetailItem> tJournalDetailItemList;

	public MAccountTitleItem() {
	}

	public MAccountTitleItem(MAccountTitleItemPK mAccountTitleItemPK) {
		this.mAccountTitleItemPK = mAccountTitleItemPK;
	}

	public MAccountTitleItem(long accountBookNo, String accountTitleCode, long no) {
		this.mAccountTitleItemPK = new MAccountTitleItemPK(accountBookNo, accountTitleCode, no);
	}

	public MAccountTitleItemPK getMAccountTitleItemPK() {
		return mAccountTitleItemPK;
	}

	public void setMAccountTitleItemPK(MAccountTitleItemPK mAccountTitleItemPK) {
		this.mAccountTitleItemPK = mAccountTitleItemPK;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MAccountTitle getMAccountTitle() {
		return mAccountTitle;
	}

	public void setMAccountTitle(MAccountTitle mAccountTitle) {
		this.mAccountTitle = mAccountTitle;
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
		hash += (mAccountTitleItemPK != null ? mAccountTitleItemPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof MAccountTitleItem)) {
			return false;
		}
		MAccountTitleItem other = (MAccountTitleItem) object;
		if ((this.mAccountTitleItemPK == null && other.mAccountTitleItemPK != null) || (this.mAccountTitleItemPK != null && !this.mAccountTitleItemPK.equals(other.mAccountTitleItemPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jp.co.kasan.db.entity.MAccountTitleItem[ mAccountTitleItemPK=" + mAccountTitleItemPK + " ]";
	}

	/**
	 * 番号を取得します。
	 * @return 
	 */
	public long getNo() {
		return this.getMAccountTitleItemPK().getNo();
	}	
}
