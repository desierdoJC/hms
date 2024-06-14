package io.nichan.hms.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.nichan.hms.entity.Role;
import io.nichan.hms.entity.User;

public class CustomUserDetails implements UserDetails{

    private User user;
    

    public CustomUserDetails(User user){
        this.user = user;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roles = user.getRoles();
        Collection < ? extends GrantedAuthority> mapRoles = roles.stream()
        .map(role -> new SimpleGrantedAuthority(role.getName()))
        .collect(Collectors.toList());

        return mapRoles;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }
    
    public String getName(){
        return user.getName();
    }

    public String getFirstName(){
        String[] name = user.getName().split(" ");
        return name[0];
    }

    public String getLastName(){
        String[] name = user.getName().split(" ");
        return name[1];
    }

}
