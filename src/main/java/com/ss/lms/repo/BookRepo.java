package com.ss.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ss.lms.entity.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer>{
	
	@Query(" FROM Book where title =:title")
	public List<Book> readBooksByTitle(@Param("title") String title);
}