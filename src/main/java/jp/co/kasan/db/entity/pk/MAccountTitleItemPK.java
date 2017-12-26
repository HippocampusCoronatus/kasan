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
public class MAccountTitleItemPK implements Serializable {

	@NotNull
	@Column(name = "account_book_no")
	private long accountBookNo;

	@NotNull
	@Size(min = 1, max = 6)
	@Column(name = "account_title_code")
	private String accountTitleCode;

	@NotNull
	@Column(name = "no")
	private long no;

	public MAccountTitleItemPK() {
	}

	public MAccountTitleItemPK(long accountBookNo, String accountTitleCode, long no) {
		this.accountBookNo = accountBookNo;
		this.accountTitleCode = accountTitleCode;
		this.no = no;
	}

	public long getAccountBookNo() {
		return accountBookNo;
	}

	public void setAccountBookNo(long accountBookNo) {
		this.accountBookNo = accountBookNo;
	}

	public String getAccountTitleCode() {
		return accountTitleCode;
	}

	public void setAccountTitleCode(String accountTitleCode) {
		this.accountTitleCode = accountTitleCode;
	}

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 79 * hash + (int) (this.accountBookNo ^ (this.accountBookNo >>> 32));
		hash = 79 * hash + Objects.hashCode(this.accountTitleCode);
		hash = 79 * hash + (int) (this.no ^ (this.no >>> 32));
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
		final MAccountTitleItemPK other = (MAccountTitleItemPK) obj;
		if(this.accountBookNo != other.accountBookNo) {
			return false;
		}
		if(this.no != other.no) {
			return false;
		}
		if(!Objects.equals(this.accountTitleCode, other.accountTitleCode)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jp.co.kasan.db.entity.MAccountTitleItemPK[ accountBookNo=" + accountBookNo + ", accountTitleCode=" + accountTitleCode + ", no=" + no + " ]";
	}

}
