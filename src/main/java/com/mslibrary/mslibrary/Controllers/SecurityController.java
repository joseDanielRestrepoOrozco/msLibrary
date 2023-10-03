package com.mslibrary.mslibrary.Controllers;

import com.mslibrary.mslibrary.Models.User;
import com.mslibrary.mslibrary.Repositories.UserRepository;
import com.mslibrary.mslibrary.Services.EncryptionService;
import com.mslibrary.mslibrary.Services.JwtService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("security")
public class SecurityController {

    @Autowired
    private UserRepository theUserRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private EncryptionService encryptionService;

    @PostMapping("login")
    public String login(@RequestBody User theUser, final HttpServletResponse response) throws IOException {
        String token = "";
        User actualUser = this.theUserRepository.getUserByEmail(theUser.getEmail());
        if (actualUser != null && actualUser.getPassword().equals(encryptionService.convertirSHA256(theUser.getPassword()))){
            token = jwtService.generateToken(actualUser);
        }else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
        return token;
    }
}
