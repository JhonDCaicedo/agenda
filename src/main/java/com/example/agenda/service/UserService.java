package com.example.agenda.service;

import com.example.agenda.domain.User;
import com.example.agenda.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private UserRepository repository;

    public User saveUser(User user){
        return repository.save(user);
    }

    public boolean loadUserByUserName(String email, String password){
        Optional<User> user  = repository.findByEmail(email);
        return user.map(value -> value.getPassword().equals(password)).orElse(false);
    }

}
