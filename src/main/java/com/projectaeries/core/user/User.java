package com.projectaeries.core.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.projectaeries.core.community.Community;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @CreationTimestamp
    private Date createdAt;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_communities",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "community_id", referencedColumnName = "id")}
    )
    @JsonBackReference
    private List<Community> userCommunities;


    public User() {
    }

    public User(Long id, String firstName, String lastName, Date createdAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() { return this.email; }

    public void setEmail(String email) { this.email = email; }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<Community> getUserCommunities() { return this.userCommunities; }
    public void setUserCommunities(List<Community> userCommunities) {}
}
