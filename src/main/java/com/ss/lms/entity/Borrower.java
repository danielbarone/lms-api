package com.ss.lms.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tbl_borrower")
public class Borrower implements Serializable {

	private static final long serialVersionUID = 2368451049261606836L;
  
  @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cardNo")
  private Integer cardNo;

  @Column(name = "name")
  private String name;

  @Column(name = "address")
  private String address;

  @Column(name = "phone")
  private String phone;
  
  @ManyToMany(fetch = FetchType.LAZY,mappedBy = "borrowers")
	@JsonBackReference
  private List<Book> books;
  
  @Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
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
	Borrower other = (Borrower) obj;
	if (cardNo == null) {
		if (other.cardNo != null)
			return false;
	} else if (!cardNo.equals(other.cardNo))
		return false;
	return true;
}

@Override
	public String toString() {
    return name;
  }

  public Integer getCardNo() {
    return cardNo;
  }

  public void setCardNo(Integer cardNo) {
    this.cardNo = cardNo;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public List<Book> getBooks() {
    return books;
  }

  public void setBooks(List<Book> books) {
    this.books = books;
  }
}
