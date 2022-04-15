package com.example.demo.email.token;

import org.springframework.stereotype.Service;

@Service
public class VerificationTokenService {

    private final VerificationTokenRepository verificationTokenRepository;

    public VerificationTokenService(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    public void saveVerificationToken(VerificationToken token){
        verificationTokenRepository.save(token);
    }
}
