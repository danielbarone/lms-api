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
@Table(name = "tbl_genre")
public class Genre implements Serializable {

	private static final long serialVersionUID = 3015376066944790977L;

  @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "genre_id")
  private Integer genre_id;
  
  @Column(name = "genre_name")
  private String genre_name;
  
  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "genres")
	@JsonBackReference
	private List<Book> books;

	@Override
	public String toString() {
		return genre_name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genre_id == null) ? 0 : genre_id.hashCode());
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
		Genre other = (Genre) obj;
		if (genre_id == null) {
			if (other.genre_id != null)
				return false;
		} else if (!genre_id.equals(other.genre_id))
			return false;
		return true;
	}

	public Integer getGenreId() {
		return genre_id;
	}
	
	public void setGenreId(Integer genre_id) {
		this.genre_id = genre_id;
	}
	
	public String getGenreName() {
		return genre_name;
	}
	
	public void setGenreName(String genre_name) {
		this.genre_name = genre_name;
	}

}
