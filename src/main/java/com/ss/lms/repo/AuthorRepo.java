package com.ss.lms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss.lms.entity.Author;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Integer>{

}