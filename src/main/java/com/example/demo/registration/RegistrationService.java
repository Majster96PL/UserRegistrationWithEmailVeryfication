package com.example.demo.registration;

import com.example.demo.email.EmailValidation;
import com.example.demo.user.User;
import com.example.demo.user.UserRole;
import com.example.demo.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final EmailValidation emailValidation;
    private final UserService userService;

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
}
