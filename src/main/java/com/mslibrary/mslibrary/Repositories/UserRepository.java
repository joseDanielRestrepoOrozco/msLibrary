package com.mslibrary.mslibrary.Repositories;

import com.mslibrary.mslibrary.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
