package com.example.demo.user;

import com.example.demo.email.token.VerificationToken;
import com.example.demo.email.token.VerificationTokenService;
import com.example.demo.security.PasswordEncoder;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private static final String MESSAGE = "User with email %s not found";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenService verificationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format(MESSAGE, email))
        );
    }

    public String recordUser(User user){
        boolean isUserExists = userRepository
                .findByEmail(user.getEmail()).isPresent();

        if (isUserExists){
            throw new IllegalStateException("Email taken already");
        }

        String passwordEncoded = passwordEncoder
                .bCryptPasswordEncoder()
                .encode(user.getPassword());
        user.setPassword(passwordEncoded);
        userRepository.save(user);
        String token = UUID.randomUUID().toString();
        var verificationToken = new VerificationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(5),
                user
        );
        verificationTokenService.saveVerificationToken(verificationToken);
        return token;
    }

    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }
}
