package com.example.service;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdministratorRepository administratorRepository;

    @Override
    public UserDetails loadUserByUsername(String mailAddress) throws UsernameNotFoundException {
        Administrator administrator = administratorRepository.findByMailAddress(mailAddress);
        if (administrator == null) {
            throw new UsernameNotFoundException("ユーザが見つかりません");
        }

        return new User(
                administrator.getMailAddress(),
                administrator.getPassword(), // ここがBCryptされたハッシュ
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
        );
    }
}
