package com.ss.lms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_publisher")
public class Publisher implements Serializable {

	private static final long serialVersionUID = 6242727467250111683L;

  @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((publisherId == null) ? 0 : publisherId.hashCode());
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
		Publisher other = (Publisher) obj;
		if (publisherId == null) {
			if (other.publisherId != null)
				return false;
		} else if (!publisherId.equals(other.publisherId))
			return false;
		return true;
	}

  @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "publisherId")
  private Integer publisherId;

  @Column(name = "publisherName")
  private String publisherName;

  @Column(name = "publisherAddress")
  private String publisherAddress;
  
  @Column(name = "publisherPhone")
	private String publisherPhone;
	
	@Override
	public String toString() {
		return publisherName;
	}

	public Integer getPublisherId() {
		return publisherId;
	}
	
	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}
	
	public String getPublisherName() {
		return publisherName;
	}
	
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getPublisherPhone() {
		return publisherPhone;
	}

	public void setPublisherPhone(String publisherPhone) {
		this.publisherPhone = publisherPhone;
	}

	public String getPublisherAddress() {
		return publisherAddress;
	}

	public void setPublisherAddress(String publisherAddress) {
		this.publisherAddress = publisherAddress;
	}
}