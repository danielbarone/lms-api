package com.ss.lms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

class BookCopiesKey implements Serializable {
  private Integer bookId;
  private Integer branchId;
}

@Entity
@IdClass(BookCopiesKey.class)
@Table(name = "tbl_book_copies")
public class BookCopies implements Serializable {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
		result = prime * result + ((branchId == null) ? 0 : branchId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookCopies other = (BookCopies) obj;
		if (bookId == null) {
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		if (branchId == null) {
			if (other.branchId != null)
				return false;
		} else if (!branchId.equals(other.branchId))
			return false;
		return true;
	}

	private static final long serialVersionUID = 3317999906286516526L;

  @Id
  private Integer bookId;

  @Id
  private Integer branchId;

  @Column(name = "noOfCopies")
  private Integer noOfCopies;


  public Integer getBookId() {
		return bookId;
	}
	
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
  }
  
  public Integer getBranchId() {
		return branchId;
	}
	
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
  }

  public Integer getNoOfCopies() {
		return noOfCopies;
	}
	
	public void setNoOfCopies(Integer noOfCopies) {
		this.noOfCopies = noOfCopies;
  }

}
