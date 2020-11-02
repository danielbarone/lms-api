package com.ss.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookCopies;
import com.ss.lms.entity.Branch;
import com.ss.lms.repo.BranchRepo;
import com.ss.lms.repo.BookCopiesRepo;
import com.ss.lms.repo.BookRepo;

class UpdateBookCopiesRequest {
  private Branch branch;
  private Book book;
  private Integer noOfCopies;

  public UpdateBookCopiesRequest(Branch branch, Book book, Integer noOfCopies) {
    this.branch = branch;
    this.book = book;
    this.noOfCopies = noOfCopies;
  }

  public Branch getBranch() {
    return branch;
  }

  public Book getBook() {
    return book;
  }

  public Integer getNoOfCopies() {
    return noOfCopies;
  }
}

@RestController
public class LibrarianService {
  
  @Autowired
  BookCopiesRepo bookCopiesRepo;

  @Autowired
  BranchRepo branchRepo;

  @Autowired
  BookRepo bookRepo;
  
  @RequestMapping(value = "/getBooksByBranch", method = RequestMethod.GET, produces = "application/json")
	public List<Book> getBooksByBranch(@RequestParam Integer branchId) {
		List<Book> books = new ArrayList<>();
    Branch branch = branchRepo.getOne(branchId);
    books = branch.getBooks();
		return books;
	}
  
  @RequestMapping(value = "/updateLib", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public Branch updateLib(@RequestBody Branch branch) {

		if (branchRepo.existsById(branch.getBranchId())) {
			Branch updatedBranch = branchRepo.getOne(branch.getBranchId());
			if (branch.getBranchName() != null) {
				updatedBranch.setBranchName(branch.getBranchName());
			}
			if (branch.getBranchAddress() != null) {
				updatedBranch.setBranchAddress(branch.getBranchAddress());
			}
			return branchRepo.save(updatedBranch);
		}
		return null;
  }

  @RequestMapping(value = "/updateNoOfCopies", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
  public Branch updateNoOfCopies(@RequestBody UpdateBookCopiesRequest updateBookCopiesRequest) {
    
    Branch branch = updateBookCopiesRequest.getBranch();
    Book book = updateBookCopiesRequest.getBook();
    Integer noOfCopies = updateBookCopiesRequest.getNoOfCopies();

    try {
      Book currBook = bookRepo.getOne(book.getBookId());
      Branch currBranch = branchRepo.getOne(branch.getBranchId());
      List<Book> branchBooks = currBranch.getBooks();
      
      // See if book exists in branch, update it
      if (branchBooks.contains(currBook)) {
        branchRepo.updateNoOfCopies(branch.getBranchId(), book.getBookId(), noOfCopies);
      
      // Else, add new book with copies to branch
      } else {
        BookCopies bookCopies = new BookCopies();
        bookCopies.setBookId(book.getBookId());
        bookCopies.setBranchId(branch.getBranchId());
        bookCopies.setNoOfCopies(noOfCopies);
        bookCopiesRepo.save(bookCopies);
      }
      return branch;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @RequestMapping(value = "/getNoOfCopies", method = RequestMethod.GET, produces = "application/json")
  public BookCopies getNoOfCopies(@RequestParam Integer branchId, @RequestParam Integer bookId) {
    List<BookCopies> bookCopies = branchRepo.getNoOfCopies(branchId, bookId);
    return bookCopies.get(0);
  }
  
}
