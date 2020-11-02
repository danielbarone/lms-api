package com.ss.lms.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public Book addBook(@RequestBody Book book) {
			return brepo.save(book);
	}

	@RequestMapping(value = "/updateBook", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public Book updateBook(@RequestBody Book book) {
		if (brepo.existsById(book.getBookId())) {
			return brepo.save(book);
		}
		return null;
	}

	@RequestMapping(value = "/deleteBook", method = RequestMethod.DELETE, consumes = "application/json")
	public Book deleteBook(@RequestBody Book book) {
		try {
			brepo.delete(book);
			return book;
		} catch (Exception e) {
			e.printStackTrace();
			return book;
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
	public Genre addGenre(@RequestBody Genre genre) {
			return grepo.save(genre);
	}

	@RequestMapping(value = "/updateGenre", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public Genre updateGenre(@RequestBody Genre genre) {
		if (grepo.existsById(genre.getGenreId())) {
			return grepo.save(genre);
		}
		return null;
	}

	@RequestMapping(value = "/deleteGenre", method = RequestMethod.DELETE, consumes = "application/json")
	public Genre deleteGenre(@RequestBody Genre genre) {
		try {
			grepo.delete(genre);
			return genre;
		} catch (Exception e) {
			e.printStackTrace();
			return genre;
		}
	}

	@RequestMapping(value = "/getGenres", method = RequestMethod.GET, produces = "application/json")
	public List<Genre> getGenres() {
		List<Genre> genres = new ArrayList<>();
		genres = grepo.findAll();
		return genres;
	}

	@RequestMapping(value = "/addPublisher", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public Publisher addPublisher(@RequestBody Publisher publisher) {
			return prepo.save(publisher);
	}

	@RequestMapping(value = "/updatePublisher", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public Publisher updatePublisher(@RequestBody Publisher publisher) {
		if (prepo.existsById(publisher.getPublisherId())) {
			Publisher updatedPublisher = prepo.getOne(publisher.getPublisherId());
			if (publisher.getPublisherName() != null) {
				updatedPublisher.setPublisherName(publisher.getPublisherName());
			}

			if (publisher.getPublisherAddress() != null) {
				updatedPublisher.setPublisherAddress(publisher.getPublisherAddress());
			}

			if (publisher.getPublisherPhone() != null) {
				updatedPublisher.setPublisherPhone(publisher.getPublisherPhone());
			}
			
			return prepo.save(updatedPublisher);
		}
		return null;
	}

	@RequestMapping(value = "/deletePublisher", method = RequestMethod.DELETE, consumes = "application/json")
	public Publisher deletePublisher(@RequestBody Publisher publisher) {
		try {
			prepo.delete(publisher);
			return publisher;
		} catch (Exception e) {
			e.printStackTrace();
			return publisher;
		}
	}

	@RequestMapping(value = "/getPublishers", method = RequestMethod.GET, produces = "application/json")
	public List<Publisher> getPublishers() {
		List<Publisher> publishers = new ArrayList<>();
		publishers = prepo.findAll();
		return publishers;
	}

	@RequestMapping(value = "/addBranch", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public Branch addBranch(@RequestBody Branch branch) {
			return branchRepo.save(branch);
	}

	@RequestMapping(value = "/updateBranch", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public Branch updateBranch(@RequestBody Branch branch) {
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

	@RequestMapping(value = "/deleteBranch", method = RequestMethod.DELETE, consumes = "application/json")
	public Branch deleteBranch(@RequestBody Branch branch) {
		try {
			branchRepo.delete(branch);
			return branch;
		} catch (Exception e) {
			e.printStackTrace();
			return branch;
		}
	}

	@RequestMapping(value = "/getBranches", method = RequestMethod.GET, produces = "application/json")
	public List<Branch> getBranches() {
		List<Branch> branches = new ArrayList<>();
		branches = branchRepo.findAll();
		return branches;
	}

	@RequestMapping(value = "/addBorrower", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public Borrower addBorrower(@RequestBody Borrower borrower) {
			return borrowerRepo.save(borrower);
	}

	@RequestMapping(value = "/updateBorrower", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public Borrower updateBorrower(@RequestBody Borrower borrower) {
		if (borrowerRepo.existsById(borrower.getCardNo())) {
			Borrower updatedBorrower = borrowerRepo.getOne(borrower.getCardNo());
			if (borrower.getName() != null) {
				updatedBorrower.setName(borrower.getName());
			}
	
			if (borrower.getAddress() != null) {
				updatedBorrower.setAddress(borrower.getAddress());
			}

			if (borrower.getPhone() != null) {
				updatedBorrower.setPhone(borrower.getPhone());
			}
			
			return borrowerRepo.save(updatedBorrower);
		}
		return null;
	}

	@RequestMapping(value = "/deleteBorrower", method = RequestMethod.DELETE, consumes = "application/json")
	public Borrower deleteBorrower(@RequestBody Borrower borrower) {
		try {
			borrowerRepo.delete(borrower);
			return borrower;
		} catch (Exception e) {
			e.printStackTrace();
			return borrower;
		}
	}

	@RequestMapping(value = "/getBorrowers", method = RequestMethod.GET, produces = "application/json")
	public List<Borrower> getBorrowers() {
		List<Borrower> borrowers = new ArrayList<>();
		borrowers = borrowerRepo.findAll();
		return borrowers;
	}	


	@RequestMapping(value = "/overrideDueDate", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public Loan overrideDueDate(@RequestBody OverrideDueDateRequest overrideDueDateRequest) {
		Branch branch = overrideDueDateRequest.getBranch();
		Book book = overrideDueDateRequest.getBook();
		Integer cardNo = overrideDueDateRequest.getCardNo();
		Integer numDaysToExtend = overrideDueDateRequest.getNumDaysToExtend();

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

		return loan;
	}
	
}