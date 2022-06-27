package com.example.reviewohrid.service.serviceImpl;

import com.example.reviewohrid.model.User;
import com.example.reviewohrid.model.UserPrincipal;
import com.example.reviewohrid.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(email);
        user.orElseThrow(() -> new UsernameNotFoundException("User not present"));
        return UserPrincipal.create(user.get());
    }


}
