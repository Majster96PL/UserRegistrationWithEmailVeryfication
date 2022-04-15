package com.example.demo.email.token;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VerificationTokenService {

    private final VerificationTokenRepository verificationTokenRepository;

    public VerificationTokenService(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    public void saveVerificationToken(VerificationToken token){
        verificationTokenRepository.save(token);
    }

    public Optional<VerificationToken> getToken(String token){
        return verificationTokenRepository.findByToken(token);
    }

    public int setConfirmedToken(String token){
        return verificationTokenRepository.updateConfirmedToken(token, LocalDateTime.now());
    }

}
