package com.krawczyk.APItest.controllers;

import com.krawczyk.APItest.payload.request.CreateUserRequest;
import com.krawczyk.APItest.payload.response.MessageResponse;
import com.krawczyk.APItest.payload.response.UserDataResponse;
import com.krawczyk.APItest.models.ERole;
import com.krawczyk.APItest.models.User;
import com.krawczyk.APItest.repository.RoleRepository;
import com.krawczyk.APItest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Objects;


@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/admin/getAllUsers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllUsers() {
        var list = new ArrayList<UserDataResponse>();
        for (var user:
                userRepository.findAll()) {
            list.add(new UserDataResponse(user));
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/admin/getUser/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getUserById(@PathVariable("id") int id) {
        var foundedUser = userRepository.findById((long) id);
        if(foundedUser.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: User not found!"));
        }
        return ResponseEntity.ok(new UserDataResponse(foundedUser.get()));
    }

    @PostMapping("/admin/createUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createUser(@RequestBody() CreateUserRequest createUserRequest) {
        var userRole = roleRepository.findByName(ERole.ROLE_USER);
        User newUser = new User(createUserRequest.getUsername(), createUserRequest.getPassword(), createUserRequest.getEmail(), userRole.get());
        if(userRepository.findByUsername(newUser.getUsername()).isPresent())
        {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Username is already taken!"));
        }
        if(userRepository.existsByEmail(newUser.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Email is already taken!"));
        }
        userRepository.save(newUser);
        return ResponseEntity.ok(new UserDataResponse(newUser));
    }

    @DeleteMapping("/admin/deleteUser/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {

        if(!userRepository.existsById(id))
        {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: User not found!"));
        }

        userRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("User deleted successfully!"));
    }

    @GetMapping("/admin/addAdminRole/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addAdminRole(@PathVariable("id") Long id) {
        var adminRole = roleRepository.findByName(ERole.ROLE_ADMIN);
        var user = userRepository.findById(id);

        if(user.isEmpty())
        {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: User not found!"));
        }

        if(user.get().getRoles().contains(adminRole.get()))
        {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: User already have admin role!"));
        }
        user.get().addRole(adminRole.get());

        userRepository.save(user.get());

        return ResponseEntity.ok(new MessageResponse("Admin role added successfully!"));
    }

    @GetMapping("/admin/removeAdminRole/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> removeAdminRole(@PathVariable("id") Long id, Authentication authentication) {
        var adminRole = roleRepository.findByName(ERole.ROLE_ADMIN);
        var user = userRepository.findById(id);
        var ownUser = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new RuntimeException("No user with name " + authentication.getName()));


        if(user.isEmpty())
        {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: User not found!"));
        }

        if(Objects.equals(user.get().getId(), ownUser.getId()))
        {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: You can't remove admin role from yourself!"));
        }

        if(!user.get().getRoles().contains(adminRole.get()))
        {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: User don't have admin role!"));
        }
        user.get().removeRole(adminRole.get());

        userRepository.save(user.get());

        return ResponseEntity.ok(new MessageResponse("Admin role remove successfully!"));
    }
}
