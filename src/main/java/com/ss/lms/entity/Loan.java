package com.ss.lms.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

class LoanKey implements Serializable {
  private Integer bookId;
  private Integer branchId;
  private Integer cardNo;
}

@Entity
@IdClass(LoanKey.class)
@Table(name = "tbl_book_loans")
public class Loan implements Serializable {

	private static final long serialVersionUID = 694222258778469655L;
  
  @Id
  private Integer bookId;
  
  @Id
  private Integer branchId;
  
  @Id
  private Integer cardNo;
  
  @Column(name = "dateOut")
  private Timestamp dateOut;
  
  @Column(name = "dueDate")
  private Timestamp dueDate;
  
  @Column(name = "dateIn")
  private Timestamp dateIn;
  
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
    result = prime * result + ((branchId == null) ? 0 : branchId.hashCode());
    result = prime * result + ((cardNo == null) ? 0 : cardNo.hashCode());
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
    Loan other = (Loan) obj;
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
    if (cardNo == null) {
      if (other.cardNo != null)
        return false;
    } else if (!cardNo.equals(other.cardNo))
      return false;
    return true;
  }

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

	public Integer getCardNo() {
		return cardNo;
	}

	public void setCardNo(Integer cardNo) {
		this.cardNo = cardNo;
	}

	public Timestamp getDateOut() {
		return dateOut;
	}

	public void setDateOut(Timestamp dateOut) {
		this.dateOut = dateOut;
	}

	public Timestamp getDueDate() {
		return dueDate;
	}

	public void setDueDate(Timestamp dueDate) {
		this.dueDate = dueDate;
	}

	public Timestamp getDateIn() {
		return dateIn;
	}

	public void setDateIn(Timestamp dateIn) {
		this.dateIn = dateIn;
	}

}
