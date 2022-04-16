package com.example.demo.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/login/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping
    public String getRegisterUser(@RequestBody RegistrationRequest request){
        return registrationService.getRegisterUser(request);
    }

    @GetMapping(path = "confirm")
    public String confirmToken(@RequestParam("token")String token){
        return registrationService.confirmToken(token);
    }
}
