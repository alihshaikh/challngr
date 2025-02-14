package com.projectaeries.core.user;

import com.projectaeries.core.community.Community;

import java.util.Optional;
import java.util.Set;

public class UserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private Set<Community> communities;

    public UserDTO() {}

    public UserDTO(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getEmail() { return email; }

    public Set<Community> getCommunities() { return communities; }

    void setFirstName(String firstName) { this.firstName = firstName; }

    void setLastName(String lastName) { this.lastName = lastName; }

    void setEmail(String email) { this.email = email; }

    void setCommunities(Set<Community> communities) { this.communities = communities; }
}
