package com.stephaniescr.jpa.controller;

import com.stephaniescr.jpa.exception.ResourceNotFoundException;
import com.stephaniescr.jpa.model.User;
import com.stephaniescr.jpa.repository.UserRepository;
import com.stephaniescr.jpa.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CountryRepository countryRepository;

    //lista todos os usuários
    @GetMapping("/user")
    public List<User> index() {
        return userRepository.findAll();
    }
/*
    //lista usuário por id
    @GetMapping("/user/{userId}")
    public User show(@PathVariable (value = "userId") Long userId) {
        //Long userId = Long.parseLong(userId);
        return userRepository.findById(userId);
    }*/

    //lista todos os usuários por país
    @GetMapping("/country/{countryId}/user")
    public Page<User> getAllUsersByCountryId(@PathVariable (value = "countryId") Long countryId,
                                             Pageable pageable) {
        return userRepository.findByCountryId(countryId, pageable);
    }

    //cria novo usuário em um país
    @PostMapping("/country/{countryId}/user")
    public User createUser(@PathVariable (value = "countryId") Long countryId,
                           @Valid @RequestBody User user) {
        return countryRepository.findById(countryId).map(country -> {
            user.setCountry(country);
            return userRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("CountryId " + countryId + " not found"));
    }

    //atualiza usuário em um país
    @PutMapping("/country/{countryId}/user/{userId}")
    public User updateUser(@PathVariable (value = "countryId") Long countryId,
                           @PathVariable (value = "userId") Long userId,
                           @Valid @RequestBody User userRequest) {
        if(!countryRepository.existsById(countryId)) {
            throw new ResourceNotFoundException("CountryId " + countryId + " not found");
        }

        return userRepository.findById(userId).map(user -> {
            user.setName(userRequest.getName());
            return userRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + "not found"));
    }

    //deleta usuário em um país
    @DeleteMapping("/country/{countryId}/user/{userId}")
    public ResponseEntity<?> deleteComment(@PathVariable (value = "countryId") Long countryId,
                                           @PathVariable (value = "userId") Long userId) {
        if(!countryRepository.existsById(countryId)) {
            throw new ResourceNotFoundException("CountryId " + countryId + " not found");
        }

        return userRepository.findById(userId).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }
}