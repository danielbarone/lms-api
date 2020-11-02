package com.ss.lms.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "tbl_book")
public class Book implements Serializable {

	private static final long serialVersionUID = 1338085671779511268L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bookId")
	private Integer bookId;

	@Column(name = "title")
	@NonNull
	private String title;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "tbl_book_authors", joinColumns = {@JoinColumn(name="bookId") }, 
		inverseJoinColumns = {@JoinColumn(name="authorId") } )
	private List<Author> authors;

	@ManyToMany
	@JoinTable(name = "tbl_book_genres", joinColumns = {@JoinColumn(name="bookId") }, 
		inverseJoinColumns = {@JoinColumn(name="genre_id") } )
	private List<Genre> genres;

	@ManyToMany
	@JoinTable(name = "tbl_book_copies", joinColumns = {@JoinColumn(name="bookId") }, 
		inverseJoinColumns = {@JoinColumn(name="branchId") } )
	private List<Branch> branches;

	@ManyToMany
	@JoinTable(name = "tbl_book_loans", joinColumns = {@JoinColumn(name="bookId") }, 
		inverseJoinColumns = {@JoinColumn(name="cardNo") } )
	private List<Borrower> borrowers;

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
	
}