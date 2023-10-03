package com.mslibrary.mslibrary.Repositories;

import com.mslibrary.mslibrary.Models.Loan;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoanRepository extends MongoRepository<Loan, String>  {
}
