package com.mslibrary.mslibrary.Controllers;

import com.mslibrary.mslibrary.Models.User;
import com.mslibrary.mslibrary.Repositories.UserRepository;
import com.mslibrary.mslibrary.Services.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository theUserRepository;

    @Autowired
    private EncryptionService encryptionService;

    @GetMapping("")
    public List<User> index() {
        return this.theUserRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public User store(@RequestBody User newUser) {
        newUser.setPassword(encryptionService.convertirSHA256(newUser.getPassword()));
        return this.theUserRepository.save(newUser);
    }

    @GetMapping("{id}")
    public User show(@PathVariable String id) {
        return this.theUserRepository.findById(id).orElse(null);
    }

    @PutMapping("{id}")
    public User update(@PathVariable String id, @RequestBody User theNewUser) {
        User theActualUser = this.theUserRepository.findById(id).orElse(null);
        if (theActualUser != null) {
            theActualUser.setName(theNewUser.getName());
            theActualUser.setEmail(theNewUser.getEmail());
            theActualUser.setPassword(encryptionService.convertirSHA256(theNewUser.getPassword()));
            return this.theUserRepository.save(theActualUser);
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void destroy(@PathVariable String id) {
        User theUser = this.theUserRepository.findById(id).orElse(null);
        if (theUser != null) {
            this.theUserRepository.delete(theUser);
        }
    }

}
