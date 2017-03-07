/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.db.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rued97
 */
@Entity
@Table(name = "m_authentication_token")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "MAuthenticationToken.findAll", query = "SELECT m FROM MAuthenticationToken m")})
public class MAuthenticationToken implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "member_no")
	private Long memberNo;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "token")
	private String token;
	@JoinColumn(name = "member_no", referencedColumnName = "no", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
	private MMember mMember;

	public MAuthenticationToken() {
	}

	public MAuthenticationToken(Long memberNo) {
		this.memberNo = memberNo;
	}

	public MAuthenticationToken(Long memberNo, String token) {
		this.memberNo = memberNo;
		this.token = token;
	}

	public Long getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public MMember getMMember() {
		return mMember;
	}

	public void setMMember(MMember mMember) {
		this.mMember = mMember;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (memberNo != null ? memberNo.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof MAuthenticationToken)) {
			return false;
		}
		MAuthenticationToken other = (MAuthenticationToken) object;
		if ((this.memberNo == null && other.memberNo != null) || (this.memberNo != null && !this.memberNo.equals(other.memberNo))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jp.co.kasan.db.MAuthenticationToken[ memberNo=" + memberNo + " ]";
	}
	
}
