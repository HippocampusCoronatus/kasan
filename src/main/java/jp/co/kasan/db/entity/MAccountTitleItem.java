/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.db.entity;

import jp.co.kasan.db.entity.pk.MAccountTitleItemPK;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * 勘定科目品目マスタ
 * @author rued97
 */
@Entity
@Table(name = "m_account_title_item")
public class MAccountTitleItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	protected MAccountTitleItemPK mAccountTitleItemPK;

	@Size(max = 255)
	@Column(name = "name")
	private String name;

	@JoinColumns({
		@JoinColumn(name = "account_book_no",
				referencedColumnName = "account_book_no", insertable = false, updatable = false),
    	@JoinColumn(name = "account_title_code",
				referencedColumnName = "code", insertable = false, updatable = false)})
	@ManyToOne(optional = false)
	private MAccountTitle mAccountTitle;

	public MAccountTitleItem() {
		this.mAccountTitleItemPK = new MAccountTitleItemPK();
	}

	public MAccountTitleItemPK getMAccountTitleItemPK() {
		return mAccountTitleItemPK;
	}

	public void setMAccountTitleItemPK(MAccountTitleItemPK mAccountTitleItemPK) {
		this.mAccountTitleItemPK = mAccountTitleItemPK;
	}

	public long getAccountBookNo() {
		return this.mAccountTitleItemPK.getAccountBookNo();
	}

	public void setAccountBookNo(long accountBookNo) {
		this.mAccountTitleItemPK.setAccountBookNo(accountBookNo);
	}

	public String getAccountTitleCode() {
		return this.mAccountTitleItemPK.getAccountTitleCode();
	}

	public void setAccountTitleCode(String accountTitleCode) {
		this.mAccountTitleItemPK.setAccountTitleCode(accountTitleCode);
	}

	public long getNo() {
		return this.mAccountTitleItemPK.getNo();
	}

	public void setNo(long no) {
		this.mAccountTitleItemPK.setNo(no);
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
		this.mAccountTitleItemPK.setAccountBookNo(mAccountTitle.getAccountBookNo());
		this.mAccountTitleItemPK.setAccountTitleCode(mAccountTitle.getCode());
		this.mAccountTitle = mAccountTitle;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 79 * hash + Objects.hashCode(this.mAccountTitleItemPK);
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
		final MAccountTitleItem other = (MAccountTitleItem) obj;
		if(!Objects.equals(this.mAccountTitleItemPK, other.mAccountTitleItemPK)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jp.co.kasan.db.entity.MAccountTitleItem[ mAccountTitleItemPK=" + mAccountTitleItemPK + " ]";
	}

}
