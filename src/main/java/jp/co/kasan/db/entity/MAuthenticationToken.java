/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.db.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author rued97
 */
@Entity
@Table(name = "m_authentication_token")
public class MAuthenticationToken implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Column(name = "member_no")
	private Long memberNo;

	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "token")
	private String token;

	@JoinColumn(name = "member_no", referencedColumnName = "no", insertable = false, updatable = false)
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	private MMember mMember;

	public MAuthenticationToken() {
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
		int hash = 5;
		hash = 31 * hash + Objects.hashCode(this.memberNo);
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
		final MAuthenticationToken other = (MAuthenticationToken) obj;
		if(!Objects.equals(this.memberNo, other.memberNo)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jp.co.kasan.db.MAuthenticationToken[ memberNo=" + this.memberNo + " ]";
	}

}
