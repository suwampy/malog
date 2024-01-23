package com.malog.member.domain;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class TestUserRepository implements UserRepository {

    private final Map<Long, User> data = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    @Override
    public boolean existsByEmail(String email) {
        return data.values().stream()
            .anyMatch(user -> user.getEmail().equals(email));
    }

    @Override
    public User findByEmail(String email) {
        return data.values().stream()
            .filter(user -> user.getEmail().equals(email))
            .findFirst()
            .get();
    }

    @Override
    public User save(User user) {
        return data.put(idGenerator.incrementAndGet(), user);
    }

    @Override
    public User findByUniqueKey(String uniqueKey) {
        return data.values().stream()
            .filter(user -> user.getUniqueKey().equals(uniqueKey))
            .findFirst()
            .get();
    }
}
