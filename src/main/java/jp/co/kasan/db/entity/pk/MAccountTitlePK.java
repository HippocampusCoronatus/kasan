/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.db.entity.pk;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author rued97
 */
@Embeddable
public class MAccountTitlePK implements Serializable {

	@NotNull
	@Column(name = "account_book_no")
	private long accountBookNo;

	@NotNull
	@Size(min = 1, max = 6)
	@Column(name = "code")
	private String code;

	public MAccountTitlePK() {
	}

	public MAccountTitlePK(long accountBookNo, String code) {
		this.accountBookNo = accountBookNo;
		this.code = code;
	}

	public long getAccountBookNo() {
		return accountBookNo;
	}

	public void setAccountBookNo(long accountBookNo) {
		this.accountBookNo = accountBookNo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 89 * hash + (int) (this.accountBookNo ^ (this.accountBookNo >>> 32));
		hash = 89 * hash + Objects.hashCode(this.code);
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
		final MAccountTitlePK other = (MAccountTitlePK) obj;
		if(this.accountBookNo != other.accountBookNo) {
			return false;
		}
		if(!Objects.equals(this.code, other.code)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jp.co.kasan.db.entity.MAccountTitlePK[ accountBookNo=" + accountBookNo + ", code=" + code + " ]";
	}

}
