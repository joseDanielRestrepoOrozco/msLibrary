package com.mslibrary.mslibrary.Controllers;

import com.mslibrary.mslibrary.Models.Book;
import com.mslibrary.mslibrary.Models.Loan;
import com.mslibrary.mslibrary.Models.User;
import com.mslibrary.mslibrary.Repositories.BookRepository;
import com.mslibrary.mslibrary.Repositories.LoanRepository;
import com.mslibrary.mslibrary.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanRepository theLoanRepository;

    @Autowired
    private UserRepository theUserRepository;

    @Autowired
    private BookRepository theBookRepository;

    @GetMapping("")
    public List<Loan> index() {
        return this.theLoanRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("user/{user_id}/book/{book_id}")
    public Loan store(@PathVariable String user_id, @PathVariable String book_id) {
        User theUser = this.theUserRepository.findById(user_id).orElse(null);
        Book theBook = this.theBookRepository.findById(book_id).orElse(null);
        if (theUser != null && theBook != null) {
            Loan newLoan = new Loan();
            newLoan.setBook(theBook);
            newLoan.setUser(theUser);
            newLoan.setDate(LocalDate.now());
            return this.theLoanRepository.save(newLoan);
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void destroy(@PathVariable String id) {
        Loan theLoan = this.theLoanRepository
                .findById(id)
                .orElse(null);
        if (theLoan != null) {
            this.theLoanRepository.delete(theLoan);
        }
    }
}
