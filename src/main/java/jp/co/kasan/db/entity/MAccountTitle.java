/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.db.entity;

import jp.co.kasan.db.entity.pk.MAccountTitlePK;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;
import jp.co.kasan.journal.type.AccountTitleType;

/**
 * 勘定科目マスタ
 *
 * @author rued97
 */
@Entity
@Table(name = "m_account_title")
public class MAccountTitle implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	protected MAccountTitlePK mAccountTitlePK;

	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "name")
	private String name;

	@NotNull
	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private AccountTitleType type;

	@Size(max = 255)
	@Column(name = "account_title_group")
	private String accountTitleGroup;

	@JoinColumn(name = "account_book_no",
			referencedColumnName = "no", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private MAccountBook mAccountBook;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "mAccountTitle")
	private List<MAccountTitleItem> mAccountTitleItemList;

	public MAccountTitle() {
		this.mAccountTitlePK = new MAccountTitlePK();
		this.mAccountTitleItemList = new ArrayList<>();
	}

	public MAccountTitlePK getMAccountTitlePK() {
		return mAccountTitlePK;
	}

	public void setMAccountTitlePK(MAccountTitlePK mAccountTitlePK) {
		this.mAccountTitlePK = mAccountTitlePK;
	}

	public long getAccountBookNo() {
		return this.mAccountTitlePK.getAccountBookNo();
	}

	public void setAccountBookNo(long accountBookNo) {
		this.mAccountTitlePK.setAccountBookNo(accountBookNo);
	}

	public String getCode() {
		return this.mAccountTitlePK.getCode();
	}

	public void setCode(String code) {
		this.mAccountTitlePK.setCode(code);
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
		this.setAccountBookNo(mAccountBook.getNo());
		this.mAccountBook = mAccountBook;
	}

	@XmlTransient
	public List<MAccountTitleItem> getMAccountTitleItemList() {
		return mAccountTitleItemList;
	}

	@Deprecated
	public void setMAccountTitleItemList(List<MAccountTitleItem> mAccountTitleItemList) {
		this.mAccountTitleItemList = mAccountTitleItemList;
	}

	/**
	 * 品目を追加します。
	 * @param mAccountTitleItem 品目
	 */
	public void addMAccountTitleItem(MAccountTitleItem mAccountTitleItem) {
		this.mAccountTitleItemList.add(mAccountTitleItem);
		mAccountTitleItem.setMAccountTitle(this);
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 71 * hash + Objects.hashCode(this.mAccountTitlePK);
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
		final MAccountTitle other = (MAccountTitle) obj;
		if(!Objects.equals(this.mAccountTitlePK, other.mAccountTitlePK)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jp.co.kasan.db.entity.MAccountTitle[ mAccountTitlePK=" + mAccountTitlePK + " ]";
	}

}
