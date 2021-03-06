/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cassandra.cassandra.controller;

import com.example.cassandra.cassandra.service.implementation.UserManagementService;
import dto.UserDTO;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LENOVO
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserManagementService userService;
    
    @GetMapping("/users")
    public List<UserDTO> getAllUsers(){
        System.out.println("This is a change");
        return userService.getAllUsers();
    }
    
    @PostMapping("users/create")
    public ResponseEntity<UserDTO> postUser(@RequestBody UserDTO userDTO){
        System.out.println("Create user: " + userDTO.getName() + "...");
        UserDTO user1 = userService.postUser(userDTO);
        return new ResponseEntity<>(user1, HttpStatus.OK);
    }
    
    @PutMapping("/users/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") UUID id, @RequestBody UserDTO userDTO){
        System.out.println("Update User with ID = " + id + "...");
        UserDTO user1 = userService.updateUser(id, userDTO);
        return new ResponseEntity<>(user1, HttpStatus.OK);
    }
    
    @DeleteMapping("users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") UUID id){
        System.out.println("Delete user with ID: " + id + "..." );
        userService.deleteUser(id);
        return new ResponseEntity<>("User has been deleted", HttpStatus.OK);
    }
    
    @GetMapping("users/active/{active}")
    public List<UserDTO> findByActive(@PathVariable boolean active){
        System.out.println("Listing active users");
        return userService.findByActive(active);
    }
    
    @GetMapping("users/category/{category}")
    public List<UserDTO> findByCategory(@PathVariable String category){
        return userService.findByCategory(category);
    }
   
    @PostMapping("users/login")
    public ResponseEntity logIn (@RequestBody UserDTO userDTO){
        UserDTO user1 = userService.logIn(userDTO);
        if(user1 != null){
            return new ResponseEntity<>(user1, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(user1, HttpStatus.UNAUTHORIZED);
    }
    
    @PostMapping("users/logout")
    public ResponseEntity logOut (@RequestBody UserDTO userDTO){
        UserDTO user1 = userService.logOut(userDTO);
        return new ResponseEntity<>(user1, HttpStatus.ACCEPTED);
    }
}
