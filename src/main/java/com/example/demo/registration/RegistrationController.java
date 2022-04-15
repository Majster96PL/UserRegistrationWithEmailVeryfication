package com.example.demo.registration;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "login/v1/registration")
public class RegistrationController {

    private static RegistrationService registrationService;

    public String getRegisterUser(@RequestBody RegistrationRequest request){
        return registrationService.getRegisterUser(request);
    }
}
