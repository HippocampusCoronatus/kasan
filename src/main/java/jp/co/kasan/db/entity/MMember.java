/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.db.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rued97
 */
@Entity
@Table(name = "m_member")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "MMember.findAll", query = "SELECT m FROM MMember m"),
	@NamedQuery(name = "MMember.findByNo", query = "SELECT m FROM MMember m WHERE m.no = :no"),
	@NamedQuery(name = "MMember.findByName", query = "SELECT m FROM MMember m WHERE m.name = :name"),
	@NamedQuery(name = "MMember.findByEmail", query = "SELECT m FROM MMember m WHERE m.email = :email")})
public class MMember implements Serializable {

	@Lob
    @Column(name = "password")
	private byte[] password;

	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "no")
	private Long no;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "name")
	private String name;
	// @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="無効な電子メール")//if the field contains email address consider using this annotation to enforce field validation
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
	private String email;
	@ManyToMany(mappedBy = "mMemberList", cascade = CascadeType.ALL)
	private List<MAccountBook> mAccountBookList;

	public MMember() {
	}

	public MMember(Long no) {
		this.no = no;
	}

	public MMember(Long no, String name, String email) {
		this.no = no;
		this.name = name;
		this.email = email;
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


	@XmlTransient
	public List<MAccountBook> getMAccountBookList() {
		return mAccountBookList;
	}

	public void setMAccountBookList(List<MAccountBook> mAccountBookList) {
		this.mAccountBookList = mAccountBookList;
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

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}
	
}
