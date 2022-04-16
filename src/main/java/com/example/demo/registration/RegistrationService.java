package com.example.demo.registration;

import com.example.demo.email.EmailValidation;
import com.example.demo.email.token.VerificationToken;
import com.example.demo.email.token.VerificationTokenService;
import com.example.demo.user.User;
import com.example.demo.user.UserRole;
import com.example.demo.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final EmailValidation emailValidation;
    private final UserService userService;
    private final VerificationTokenService verificationTokenService;

    public String getRegisterUser(RegistrationRequest request) {
        boolean isEmailValidation = emailValidation.test(request.getEmail());
        if(!isEmailValidation){
            throw new IllegalStateException("Email is not validation.");
        }
        return userService.recordUser(
                new User(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        UserRole.USER
                )
        );
    }

    public String confirmToken(String token){
        VerificationToken verificationToken = verificationTokenService
                .getToken(token)
                .orElseThrow(
                        () -> new IllegalStateException("token not found"));

        if (verificationToken.getConfirmedToken() != null){
            throw new IllegalStateException("Email already confirmed");
        }

        LocalDateTime expiredToken = verificationToken.getExpiredToken();

        if(expiredToken.isBefore(LocalDateTime.now())){
            throw  new IllegalStateException("Token expired");
        }

        verificationTokenService.setConfirmedToken(token);
        userService.enableUser(verificationToken.getUser().getEmail());
        return "token confirmed";
    }
}
