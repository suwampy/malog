package com.malog.member.infra.jwt.vo;

import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class RawToken {
    private final String subject;
    private final String jti;
    private final List<String> authorities;

    public RawToken(String subject, List<String> authorities) {
        this(subject, null, authorities);
    }

    public void verify(String typeName) {
        if (!isRefreshable(typeName)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isRefreshable(String typeName) {
        return authorities.stream().anyMatch(scope -> scope.equals(typeName));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RawToken rawToken = (RawToken) o;
        return Objects.equals(subject, rawToken.subject) && Objects.equals(jti,
            rawToken.jti) && Objects.equals(authorities, rawToken.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, jti, authorities);
    }
}
