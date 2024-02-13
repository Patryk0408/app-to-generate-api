package com.krawczyk.APItest.payload.response;

import com.krawczyk.APItest.models.Role;
import com.krawczyk.APItest.models.User;
import java.util.Set;

public class UserDataResponse {
    private String username;
    private String email;
    private Set<Role> role;
    private Long id;

    public UserDataResponse(User user)
    {
        username = user.getUsername();
        email = user.getEmail();
        role = user.getRoles();
        id = user.getId();
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
