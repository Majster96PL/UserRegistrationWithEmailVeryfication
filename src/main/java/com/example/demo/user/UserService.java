package com.example.demo.user;

import com.example.demo.security.PasswordEncoder;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private static final String MESSAGE = "User with email %s not found";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format(MESSAGE, email))
        );
    }

    public String recordUser(User user){
        boolean isUserExists = userRepository.findByEmail(user.getEmail()).isPresent();
        if (isUserExists){
            throw new IllegalStateException("Email already taken");
        }
        String passwordEncoded = passwordEncoder
                .bCryptPasswordEncoder()
                .encode(user.getPassword());
        user.setPassword(passwordEncoded);
        userRepository.save(user);
        return " ";
    }
}
