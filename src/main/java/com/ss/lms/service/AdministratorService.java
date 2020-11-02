package com.ss.lms.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.lms.entity.Book;
import com.ss.lms.entity.Borrower;
import com.ss.lms.entity.Branch;
import com.ss.lms.entity.Genre;
import com.ss.lms.entity.Loan;
import com.ss.lms.entity.Publisher;
import com.ss.lms.repo.AuthorRepo;
import com.ss.lms.repo.BookRepo;
import com.ss.lms.repo.BorrowerRepo;
import com.ss.lms.repo.BranchRepo;
import com.ss.lms.repo.GenreRepo;
import com.ss.lms.repo.PublisherRepo;

class OverrideDueDateRequest {
  private Branch branch;
  private Book book;
	private Integer cardNo;
	private Integer numDaysToExtend;

  public OverrideDueDateRequest(Branch branch, Book book, Integer cardNo, Integer numDaysToExtend) {
    this.branch = branch;
    this.book = book;
		this.cardNo = cardNo;
		this.numDaysToExtend = numDaysToExtend;
  }

  public Branch getBranch() {
    return branch;
  }

  public Book getBook() {
    return book;
  }

  public Integer getCardNo() {
    return cardNo;
	}
	
	public Integer getNumDaysToExtend() {
		return numDaysToExtend;
	}
}


@RestController
public class AdministratorService {

	@Autowired
	AuthorRepo arepo;
	
	@Autowired
	BookRepo brepo;
	
	@Autowired
	BorrowerRepo borrowerRepo;

	@Autowired
	BranchRepo branchRepo;

	@Autowired
	GenreRepo grepo;

	@Autowired
	PublisherRepo prepo;
	
	@RequestMapping(value = "/addBook", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> addBook(@RequestBody Book book) {
		try {
			brepo.save(book);
			return new ResponseEntity<>(book, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to add book", HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/updateBook", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> updateBook(@RequestBody Book book) {
		try {
			if (brepo.existsById(book.getBookId())) {
				brepo.save(book);
				return new ResponseEntity<>(book, HttpStatus.OK);
			}
			return new ResponseEntity<>("Could not locate book", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to update book.", HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/deleteBook", method = RequestMethod.DELETE, consumes = "application/json")
	public ResponseEntity<?> deleteBook(@RequestBody Book book) {
		try {
			brepo.delete(book);
			return new ResponseEntity<>(book, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to delete book", HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/getBooksByQuery", method = RequestMethod.GET, produces = "application/json")
	public List<Book> getBooksByQuery(@RequestParam String searchString) {
		List<Book> books = new ArrayList<>();
		if (searchString != null && searchString.length() > 0) {
				books = brepo.readBooksByTitle(searchString);
		} else {
				books = brepo.findAll();
		}
		return books;
	}

	@RequestMapping(value = "/addGenre", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> addGenre(@RequestBody Genre genre) {
		try {
			grepo.save(genre);
			return new ResponseEntity<>(genre, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to add genre", HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/updateGenre", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> updateGenre(@RequestBody Genre genre) {
		try {
			if (grepo.existsById(genre.getGenreId())) {
				grepo.save(genre);
				return new ResponseEntity<>(genre, HttpStatus.OK);
			}
			return new ResponseEntity<>("Could not locate genre", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to update genre", HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/deleteGenre", method = RequestMethod.DELETE, consumes = "application/json")
	public ResponseEntity<?> deleteGenre(@RequestBody Genre genre) {
		try {
			grepo.delete(genre);
			return new ResponseEntity<>(genre, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to delete genre", HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/getGenres", method = RequestMethod.GET, produces = "application/json")
	public List<Genre> getGenres() {
		List<Genre> genres = new ArrayList<>();
		genres = grepo.findAll();
		return genres;
	}

	@RequestMapping(value = "/addPublisher", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> addPublisher(@RequestBody Publisher publisher) {
		try {
			prepo.save(publisher);
			return new ResponseEntity<>(publisher, HttpStatus.OK); 
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to add publisher", HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/updatePublisher", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> updatePublisher(@RequestBody Publisher publisher) {
		try {
			if (prepo.existsById(publisher.getPublisherId())) {
				Publisher updatedPublisher = prepo.findById(publisher.getPublisherId()).get();
				if (publisher.getPublisherName() != null) {
					updatedPublisher.setPublisherName(publisher.getPublisherName());
				}
	
				if (publisher.getPublisherAddress() != null) {
					updatedPublisher.setPublisherAddress(publisher.getPublisherAddress());
				}
	
				if (publisher.getPublisherPhone() != null) {
					updatedPublisher.setPublisherPhone(publisher.getPublisherPhone());
				}
				
				prepo.save(updatedPublisher);
				return new ResponseEntity<>(updatedPublisher, HttpStatus.OK);
			}
			return new ResponseEntity<>("Could not locate publisher.", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to update publisher", HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/deletePublisher", method = RequestMethod.DELETE, consumes = "application/json")
	public ResponseEntity<?> deletePublisher(@RequestBody Publisher publisher) {
		try {
			prepo.delete(publisher);
			return new ResponseEntity<>(publisher, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to delete publisher", HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/getPublishers", method = RequestMethod.GET, produces = "application/json")
	public List<Publisher> getPublishers() {
		List<Publisher> publishers = new ArrayList<>();
		publishers = prepo.findAll();
		return publishers;
	}

	@RequestMapping(value = "/addBranch", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> addBranch(@RequestBody Branch branch) {
		try {
			branchRepo.save(branch);
			return new ResponseEntity<>(branch, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Could not add branch.", HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/updateBranch", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> updateBranch(@RequestBody Branch branch) {
		try {
			if (branchRepo.existsById(branch.getBranchId())) {
				Branch updatedBranch = branchRepo.getOne(branch.getBranchId());
				if (branch.getBranchName() != null) {
					updatedBranch.setBranchName(branch.getBranchName());
				}
	
				if (branch.getBranchAddress() != null) {
					updatedBranch.setBranchAddress(branch.getBranchAddress());
				}
				branchRepo.save(updatedBranch);
				return new ResponseEntity<>(branch, HttpStatus.OK);
			}
			return new ResponseEntity<>("Could not locate branch.", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to update branch", HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/deleteBranch", method = RequestMethod.DELETE, consumes = "application/json")
	public ResponseEntity<?> deleteBranch(@RequestBody Branch branch) {
		try {
			branchRepo.delete(branch);
			return new ResponseEntity<>(branch, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to delete branch.", HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/getBranches", method = RequestMethod.GET, produces = "application/json")
	public List<Branch> getBranches() {
		List<Branch> branches = new ArrayList<>();
		branches = branchRepo.findAll();
		return branches;
	}

	@RequestMapping(value = "/addBorrower", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> addBorrower(@RequestBody Borrower borrower) {
		try {
			borrowerRepo.save(borrower);
			return new ResponseEntity<>(borrower, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to add borrower", HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/updateBorrower", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> updateBorrower(@RequestBody Borrower borrower) {
		try {
			if (borrowerRepo.existsById(borrower.getCardNo())) {
				Borrower updatedBorrower = borrowerRepo.findById(borrower.getCardNo()).get();
				if (borrower.getName() != null) {
					updatedBorrower.setName(borrower.getName());
				}
				if (borrower.getAddress() != null) {
					updatedBorrower.setAddress(borrower.getAddress());
				}
				if (borrower.getPhone() != null) {
					updatedBorrower.setPhone(borrower.getPhone());
				}
				borrowerRepo.save(updatedBorrower);
				return new ResponseEntity<>(updatedBorrower, HttpStatus.OK);
			}
			return new ResponseEntity<>("Could not locate borrower", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error: unable to update borrower", HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/deleteBorrower", method = RequestMethod.DELETE, consumes = "application/json")
	public ResponseEntity<?> deleteBorrower(@RequestBody Borrower borrower) {
		try {
			borrowerRepo.delete(borrower);
			return new ResponseEntity<>(borrower, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to delete borrower.", HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/getBorrowers", method = RequestMethod.GET, produces = "application/json")
	public List<Borrower> getBorrowers() {
		List<Borrower> borrowers = new ArrayList<>();
		borrowers = borrowerRepo.findAll();
		return borrowers;
	}	

	@RequestMapping(value = "/overrideDueDate", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> overrideDueDate(@RequestBody OverrideDueDateRequest overrideDueDateRequest) {
		Branch branch = overrideDueDateRequest.getBranch();
		Book book = overrideDueDateRequest.getBook();
		Integer cardNo = overrideDueDateRequest.getCardNo();
		Integer numDaysToExtend = overrideDueDateRequest.getNumDaysToExtend();

		try {
			List<Loan> loans = branchRepo.getDueDate(branch.getBranchId(), book.getBookId(), cardNo);
			Loan loan = loans.get(0);
	
			Date date = new Date(loan.getDueDate().getTime());
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DATE, numDaysToExtend);
			Date due = c.getTime();
	
			Timestamp dueDate = new Timestamp(due.getTime());
			loan.setDueDate(dueDate);
			branchRepo.overrideDueDate(branch.getBranchId(), book.getBookId(), cardNo, dueDate);
			return new ResponseEntity<>(loan, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to override due date", HttpStatus.BAD_REQUEST);
		}
	}
	
}