package com.ss.lms.repo;

import java.util.List;
import java.sql.Timestamp;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ss.lms.entity.BookCopies;
import com.ss.lms.entity.Branch;
import com.ss.lms.entity.Loan;


@Repository
public interface BranchRepo extends JpaRepository<Branch, Integer>{

  @Transactional
  @Modifying
  @Query("UPDATE BookCopies SET noOfCopies =:noOfCopies WHERE branchId =:branchId AND bookId =:bookId")
  public void updateNoOfCopies(@Param("branchId") Integer branchId, @Param("bookId") Integer bookId, @Param("noOfCopies") Integer noOfCopies);

  @Transactional
  @Modifying
  @Query("UPDATE Loan SET dueDate =:dueDate WHERE branchId =:branchId AND bookId =:bookId AND cardNo =:cardNo")
  public void overrideDueDate(@Param("branchId") Integer branchId, @Param("bookId") Integer bookId, @Param("cardNo") Integer cardNo, @Param("dueDate") Timestamp dueDate);

  @Query(" FROM BookCopies WHERE branchId =:branchId AND bookId =:bookId")
  public List<BookCopies> getNoOfCopies(@Param("branchId") Integer branchId, @Param("bookId") Integer bookId);

  @Query(" FROM Loan WHERE branchId =:branchId AND bookId =:bookId AND cardNo =:cardNo")
  public List<Loan> getDueDate(@Param("branchId") Integer branchId, @Param("bookId") Integer bookId, @Param("cardNo") Integer cardNo);
}