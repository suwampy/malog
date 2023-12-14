package com.malog.member.domain;

import com.malog.common.KeyGenerator;
import com.malog.common.domain.AbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "user")
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class User extends AbstractAggregateRoot {
    private static final String PREFIX = "user_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "unique_key")
    private String uKey;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    private LocalDateTime joinedAt;

    @UpdateTimestamp
    private LocalDateTime lastModifiedAt;

    private boolean withdraw;

    private String emailCheckToken;

    private LocalDateTime emailCheckTokenGeneratedAt;

    private boolean emailVerified;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles = new HashSet<>();

    private User(String email, String password, String name) {
        this.uKey = KeyGenerator.generate(PREFIX);
        this.email = email;
        this.password = password;
        this.name = name;
        this.roles = Set.of(UserRole.USER);
    }

    public static User register(String email, String password, String username) {
        User user = new User(email, password, username);
        user.generateEmailCheckToken();
        return user;
    }

    private void generateEmailCheckToken() {
        this.emailCheckToken = UUID.randomUUID().toString();
        this.emailCheckTokenGeneratedAt = LocalDateTime.now();
        registerEvent(new RegisteredAccountEvent(this));
    }
    }
}
