package com.hcc.services;

import com.hcc.entities.Authority;
import com.hcc.entities.User;
import com.hcc.repositories.UserRepository;
import com.hcc.utils.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
//    @Autowired
//    CustomPasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepo.findByUsername(username);
        return userOpt.orElseThrow(() -> new UsernameNotFoundException("Invalid Credentials"));
    }

    public User saveUser(User user) {
        return userRepo.saveAndFlush(user);
    }

//    public String getUserAuthorities(Long userId) {
//        Optional<User> user = userRepo.findById(userId);
//        List<Authority> authorities = (List<Authority>) user.get().getAuthorities();
//        return authorities.get(0);
//    }
}
