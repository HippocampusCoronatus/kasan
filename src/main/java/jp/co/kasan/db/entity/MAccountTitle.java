/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.db.entity;

import jp.co.kasan.db.entity.pk.MAccountTitlePK;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import jp.co.kasan.journal.type.AccountTitleType;

/**
 *
 * @author rued97
 */
@Entity
@Table(name = "m_account_title")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "MAccountTitle.findAll", query = "SELECT m FROM MAccountTitle m"),
	@NamedQuery(name = "MAccountTitle.findByAccountBookNo", query = "SELECT m FROM MAccountTitle m WHERE m.mAccountTitlePK.accountBookNo = :accountBookNo"),
	@NamedQuery(name = "MAccountTitle.findByCode", query = "SELECT m FROM MAccountTitle m WHERE m.mAccountTitlePK.code = :code"),
	@NamedQuery(name = "MAccountTitle.findByName", query = "SELECT m FROM MAccountTitle m WHERE m.name = :name"),
	@NamedQuery(name = "MAccountTitle.findByType", query = "SELECT m FROM MAccountTitle m WHERE m.type = :type"),
	@NamedQuery(name = "MAccountTitle.findByGroup", query = "SELECT m FROM MAccountTitle m WHERE m.accountTitleGroup = :accountTitleGroup")})
public class MAccountTitle implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected MAccountTitlePK mAccountTitlePK;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
	private String name;
	@Basic(optional = false)
    @NotNull
    @Column(name = "type")
	@Enumerated(EnumType.STRING)
	private AccountTitleType type;
	@Size(max = 255)
    @Column(name = "account_title_group")
	private String accountTitleGroup;
	@JoinColumn(name = "account_book_no", referencedColumnName = "no", insertable = false, updatable = false)
    @ManyToOne(optional = false)
	private MAccountBook mAccountBook;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "mAccountTitle")
	private List<MAccountTitleItem> mAccountTitleItemList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "mAccountTitle")
	private List<TJournalDetail> tJournalDetailList;

	public MAccountTitle() {
	}

	public MAccountTitle(MAccountTitlePK mAccountTitlePK) {
		this.mAccountTitlePK = mAccountTitlePK;
	}

	public MAccountTitle(MAccountTitlePK mAccountTitlePK, String name, AccountTitleType type) {
		this.mAccountTitlePK = mAccountTitlePK;
		this.name = name;
		this.type = type;
	}

	public MAccountTitle(long accountBookNo, String code) {
		this.mAccountTitlePK = new MAccountTitlePK(accountBookNo, code);
	}

	public MAccountTitle(MAccountBook accountBook, String code) {
		this.mAccountBook = accountBook;
		this.mAccountTitlePK = new MAccountTitlePK(accountBook.getNo(), code);
	}

	public MAccountTitlePK getMAccountTitlePK() {
		return mAccountTitlePK;
	}

	public void setMAccountTitlePK(MAccountTitlePK mAccountTitlePK) {
		this.mAccountTitlePK = mAccountTitlePK;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AccountTitleType getType() {
		return type;
	}

	public void setType(AccountTitleType type) {
		this.type = type;
	}

	public String getAccountTitleGroup() {
		return accountTitleGroup;
	}

	public void setAccountTitleGroup(String accountTitleGroup) {
		this.accountTitleGroup = accountTitleGroup;
	}

	public MAccountBook getMAccountBook() {
		return mAccountBook;
	}

	public void setMAccountBook(MAccountBook mAccountBook) {
		this.mAccountBook = mAccountBook;
	}

	@XmlTransient
	public List<MAccountTitleItem> getMAccountTitleItemList() {
		return mAccountTitleItemList;
	}

	public void setMAccountTitleItemList(List<MAccountTitleItem> mAccountTitleItemList) {
		this.mAccountTitleItemList = mAccountTitleItemList;
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
		hash += (mAccountTitlePK != null ? mAccountTitlePK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof MAccountTitle)) {
			return false;
		}
		MAccountTitle other = (MAccountTitle) object;
		if ((this.mAccountTitlePK == null && other.mAccountTitlePK != null) || (this.mAccountTitlePK != null && !this.mAccountTitlePK.equals(other.mAccountTitlePK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jp.co.kasan.db.entity.MAccountTitle[ mAccountTitlePK=" + mAccountTitlePK + " ]";
	}

	/**
	 * コードを取得します。
	 * @return コード
	 */
	public String getCode() {
		return this.getMAccountTitlePK().getCode();
	}
}
