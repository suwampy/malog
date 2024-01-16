package com.malog.common.security;

import com.malog.member.domain.UserRepository;
import com.malog.member.domain.UserRole;
import com.malog.member.infra.jwt.TokenExtractor;
import com.malog.member.infra.jwt.TokenParser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;


@RequiredArgsConstructor
public final class JwtAuthFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private final UserRepository userRepository;
    private final TokenExtractor tokenExtractor;
    private final TokenParser tokenParser;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {
        tokenExtractor.extract(request.getHeader(AUTHORIZATION_HEADER)).ifPresent(rawToken -> {
            var tokenMap = tokenParser.parse(rawToken);
            String email = tokenMap.getSubject();
            var account = userRepository.findByEmail(email);

            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                var context = new UsernamePasswordAuthenticationToken(
                    new AuthenticatedUser(account.getUniqueKey(), account.getRoles()), null,
                    authorities(account.getRoles()));
                SecurityContextHolder.getContext().setAuthentication(context);
            }
        });

        filterChain.doFilter(request, response);
    }

    private Collection<? extends GrantedAuthority> authorities(Set<UserRole> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.name())).toList();
    }
}
