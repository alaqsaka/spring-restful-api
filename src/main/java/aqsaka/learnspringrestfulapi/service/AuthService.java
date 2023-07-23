package aqsaka.learnspringrestfulapi.service;

import aqsaka.learnspringrestfulapi.entity.User;
import aqsaka.learnspringrestfulapi.model.LoginUserRequest;
import aqsaka.learnspringrestfulapi.model.TokenResponse;
import aqsaka.learnspringrestfulapi.repository.UserRepository;
import aqsaka.learnspringrestfulapi.security.BCrypt;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    // Transactional -> because there is some database data manipulation
    @Transactional
    public TokenResponse login(LoginUserRequest request) {
        // Request validation using validationService
        validationService.validate(request);

        // Check user if exist in DB
        User user = userRepository.findById(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));

        // Check password
        if (BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            // Login success
            user.setToken(UUID.randomUUID().toString());
            user.setTokenExpiredAt(next30Days());

            userRepository.save(user);

            return TokenResponse.builder()
                    .token(user.getToken())
                    .expiredAt(user.getTokenExpiredAt())
                    .build();

        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }
    }

    // For creating token expired at in milliseconds
    private Long next30Days() {
        return System.currentTimeMillis() + (1000 * 60 * 24 * 30);
    }
}
