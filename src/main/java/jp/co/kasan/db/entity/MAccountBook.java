/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.db.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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

	@XmlTransient
	public List<MAccountTitle> getMAccountTitleList() {
		return mAccountTitleList;
	}

	public void setMAccountTitleList(List<MAccountTitle> mAccountTitleList) {
		this.mAccountTitleList = mAccountTitleList;
	}

	@XmlTransient
	public List<TJournal> getTJournalList() {
		return tJournalList;
	}

	public void setTJournalList(List<TJournal> tJournalList) {
		this.tJournalList = tJournalList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (no != null ? no.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof MAccountBook)) {
			return false;
		}
		MAccountBook other = (MAccountBook) object;
		if ((this.no == null && other.no != null) || (this.no != null && !this.no.equals(other.no))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jp.co.kasan.db.MAccountBook[ no=" + no + " ]";
	}
	
}
