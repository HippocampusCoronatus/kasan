/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.db.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 会計帳簿マスタ。
 *
 * @author rued97
 */
@Entity
@Table(name = "m_account_book")
public class MAccountBook implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Column(name = "no")
	private Long no;

	@NotNull
	@Size(min = 1, max = 30)
	@Column(name = "name")
	private String name;

	@JoinTable(name = "m_member_account_book_relation",
			joinColumns = {
				@JoinColumn(name = "account_book_no", referencedColumnName = "no")
			},
			inverseJoinColumns = {
				@JoinColumn(name = "member_no", referencedColumnName = "no")
			})
	@ManyToMany()
	private List<MMember> mMemberList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "mAccountBook")
	private List<MAccountTitle> mAccountTitleList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "mAccountBook")
	private List<TJournal> tJournalList;

	public MAccountBook() {
		this.mMemberList = new ArrayList<>();
		this.mAccountTitleList = new ArrayList<>();
		this.tJournalList = new ArrayList<>();
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlTransient
	public List<MMember> getMMemberList() {
		return mMemberList;
	}

	@Deprecated
	public void setMMemberList(List<MMember> mMemberList) {
		this.mMemberList = mMemberList;
	}

	@XmlTransient
	public List<MAccountTitle> getMAccountTitleList() {
		return mAccountTitleList;
	}

	@Deprecated
	public void setMAccountTitleList(List<MAccountTitle> mAccountTitleList) {
		this.mAccountTitleList = mAccountTitleList;
	}

	public void addMAccountTitle(MAccountTitle mAccountTitle) {
		this.mAccountTitleList.add(mAccountTitle);
		mAccountTitle.setMAccountBook(this);
	}

	@XmlTransient
	public List<TJournal> getTJournalList() {
		return tJournalList;
	}

	@Deprecated
	public void setTJournalList(List<TJournal> tJournalList) {
		this.tJournalList = tJournalList;
	}

	public void addTJournal(TJournal tJournal) {
		this.tJournalList.add(tJournal);
		tJournal.setMAccountBook(this);
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 37 * hash + Objects.hashCode(this.no);
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
		final MAccountBook other = (MAccountBook) obj;
		if(!Objects.equals(this.no, other.no)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jp.co.kasan.db.MAccountBook[ no=" + this.no + " ]";
	}

}
