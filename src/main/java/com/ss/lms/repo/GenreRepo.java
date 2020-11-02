package com.ss.lms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss.lms.entity.Genre;

@Repository
public interface GenreRepo extends JpaRepository<Genre, Integer>{

}