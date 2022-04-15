package com.example.demo.email.token;


import com.example.demo.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String token;
    private LocalDateTime createdToken;
    private LocalDateTime expiredToken;
    private LocalDateTime confirmedToken;
    @ManyToOne
    @JoinColumn(nullable = false, name = "userId")
    private User user;

    public VerificationToken(
            String token,
            LocalDateTime createdToken,
            LocalDateTime expiredToken,
            User user) {
        this.token = token;
        this.createdToken = createdToken;
        this.expiredToken = expiredToken;
        this.user = user;
    }
}
