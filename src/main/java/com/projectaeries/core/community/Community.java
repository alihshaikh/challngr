package com.projectaeries.core.community;

import com.projectaeries.core.CommunityStar.CommunityStar;
import com.projectaeries.core.communitypost.CommunityPost;
import com.projectaeries.core.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table (name="communities")
public class Community {

    public enum CommunityCategory {
        FITNESS,
        PRODUCTIVITY,
        TECHNOLOGY,
        LEARNING,
        HEALTH,
        ENTERTAINMENT,
        SOCIAL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private CommunityCategory category;

    private Integer memberCount;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @ManyToMany(mappedBy = "userCommunities" , fetch = FetchType.LAZY)
    private List<User> users;

    @OneToMany(mappedBy="community", fetch = FetchType.LAZY)
    private List<CommunityPost> posts;

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommunityStar> stars;

    public Community() {}

    public Community(String name, String description, CommunityCategory category, Integer memberCount, Date createdAt) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.memberCount = 1;
        this.createdAt = new Date();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public CommunityCategory getCategory() {
        return category;
    }
    public void setCategory(CommunityCategory category) {
        this.category = category;
    }

    public Integer getMemberCount() {
        return memberCount;
    }
    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<CommunityPost> getPosts() { return posts; }
    public void setPosts(List<CommunityPost> posts) { this.posts = posts; }

    public List<User> getUsers() { return users; }
    public void setUsers(List<User> users) { this.users = users; }

    public List<CommunityStar> getStars() { return stars; }
    public void setStars(List<CommunityStar> stars) { this.stars = stars; }
}
