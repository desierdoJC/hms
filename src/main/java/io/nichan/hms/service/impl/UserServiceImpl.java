package io.nichan.hms.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.nichan.hms.entity.Role;
import io.nichan.hms.entity.User;
import io.nichan.hms.repository.RoleRepository;
import io.nichan.hms.repository.UserRepository;
import io.nichan.hms.dto.UserDto;
import io.nichan.hms.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                            RoleRepository roleRepository,
                            PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //Creates a new User Entity then Save to DB
    @Override
    public void saveUser(UserDto userDto) {
    
        //Create new user object and set name/email
       User user = new User();
       user.setName(userDto.getFirstName() + " " + userDto.getLastName());
       user.setEmail(userDto.getEmail());

       //Encrypt Password
       user.setPassword(passwordEncoder.encode(userDto.getPassword()));

       //Set Role
       Role role = roleRepository.findByName("ROLE_ADMIN");
       if(role == null){
            role = checkRoleExist();
       }
       user.setRoles(Arrays.asList(role));
       userRepository.save(user);
    }

    //Finds a user entity from db using email
    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    //finds all user entities from db then maps each entity to a userdto object
    @Override
    public List<UserDto> findAllUsers() {
       List<User> users = userRepository.findAll();
       return users.stream().map((user) -> mapToUserDto(user)).collect(Collectors.toList());
    }

    private UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        String[] str = user.getName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
    
}
