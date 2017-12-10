/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.db.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rued97
 */
@Entity
@Table(name = "m_member")
public class MMember implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @NotNull
    @Column(name = "no")
	private Long no;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
	private String email;

    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "name")
	private String name;

    @Column(name = "password")
	private byte[] password;

	@ManyToMany(mappedBy = "mMemberList", cascade = {CascadeType.PERSIST})
	private List<MAccountBook> mAccountBookList;

	public MMember() {
		this.mAccountBookList = new ArrayList<>();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	@XmlTransient
	public List<MAccountBook> getMAccountBookList() {
		return mAccountBookList;
	}

	/**
	 * 会計帳簿を追加します。
	 * @param book 会計帳簿
	 */
	public void addMAccountBook(MAccountBook book) {
		this.mAccountBookList.add(book);
		book.getMMemberList().add(this);
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
		if (!(object instanceof MMember)) {
			return false;
		}
		MMember other = (MMember) object;
		if ((this.no == null && other.no != null) || (this.no != null && !this.no.equals(other.no))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jp.co.kasan.db.MMember[ no=" + no + " ]";
	}
	
}
