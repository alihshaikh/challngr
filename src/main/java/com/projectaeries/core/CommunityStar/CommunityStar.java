package com.projectaeries.core.CommunityStar;

import com.projectaeries.core.community.Community;
import com.projectaeries.core.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "community_stars")
public class CommunityStar {
  @EmbeddedId
  private CommunityStarPK communityStarPK;

  @ManyToOne
  @MapsId("communityId") // Matches field in CommunityStarPK
  @JoinColumn(name = "community_id", nullable = false)
  Community community;

  @ManyToOne
  @MapsId("userId") // Matches field in CommunityStarPK
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  private Date createdAt;

  public CommunityStar() {}

  public CommunityStar(CommunityStarPK communityStarPK, User user) {
    this.communityStarPK = communityStarPK;
    this.user = user;
  }

  public CommunityStarPK getCommunityStarPK() {
    return communityStarPK;
  }

  public void setCommunityStarPK(CommunityStarPK communityStarPK) {
    this.communityStarPK = communityStarPK;
  }
  public void setCommunity(Community community) {
    this.community = community;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
}


/*
COMMUNITY_STARS TABLE:
Long id primary key
Long user_id
Long community_id
Date createdAt
Foreign Key references (user_id) references user(id)
Foreign Key references (community_id) references community(id)

Community will have a list of stars
List<CommunityStar> communityStars;

A community can have multiple communityStars
A community star can only have one community and user_id instance


*/