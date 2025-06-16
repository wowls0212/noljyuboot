package com.example.noljyu.Service;

import com.example.noljyu.Entity.UserEntity;
import com.example.noljyu.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findOneById(id);

        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found: " + id);
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        if ("admin".equalsIgnoreCase(userEntity.getAdmin())) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        log.info("로그인 권한 확인 - id: {}, admin: {}, roles: {}", id, userEntity.getAdmin(), authorities);

        return new org.springframework.security.core.userdetails.User(
                userEntity.getId(),
                userEntity.getPw(),
                authorities
        );
    }
}

