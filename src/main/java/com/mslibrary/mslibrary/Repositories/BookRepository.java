package com.mslibrary.mslibrary.Repositories;

import com.mslibrary.mslibrary.Models.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
}
