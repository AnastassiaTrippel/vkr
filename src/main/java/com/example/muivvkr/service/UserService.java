package com.example.muivvkr.service;

import com.example.muivvkr.entity.UserEntity;
import com.example.muivvkr.enums.Role;
import com.example.muivvkr.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void add(String name, String password) {
        UserEntity user = new UserEntity();
        user.setUsername(name);
        user.setPassword(password);
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
    }

    public List<UserEntity> getUsers() {
        List<UserEntity> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public boolean isAdmin() {
        String currentUserName = getCurrentUserName();
        UserEntity currentUser = userRepository.findByUsername(currentUserName);

        return currentUser.getRoles().contains(Role.ADMIN);
    }

    protected String getCurrentUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String currentUserName;
        if (principal instanceof UserDetails) {
            currentUserName = ((UserDetails)principal).getUsername();
        } else {
            currentUserName = principal.toString();
        }

        return currentUserName;
    }
}
