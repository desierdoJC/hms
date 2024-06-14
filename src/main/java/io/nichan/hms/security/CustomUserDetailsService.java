package io.nichan.hms.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.nichan.hms.entity.User;
import io.nichan.hms.repository.UserRepository;



@Service
public class CustomUserDetailsService implements UserDetailsService{
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       User user = userRepository.findByEmail(email);

       if(user != null){
        return new CustomUserDetails(user);
       }else{
        throw new UsernameNotFoundException("Invalid username and/or password.");
       }
    }



}
