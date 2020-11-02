package com.ss.lms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss.lms.entity.Borrower;

@Repository
public interface BorrowerRepo extends JpaRepository<Borrower, Integer>{

}