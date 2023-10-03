package com.mslibrary.mslibrary.Repositories;

import com.mslibrary.mslibrary.Models.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author, String> {
}
