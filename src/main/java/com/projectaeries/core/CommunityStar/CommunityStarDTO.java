package com.projectaeries.core.CommunityStar;

public class CommunityStarDTO {

  private Long userId;
  private Long communityId;

  public CommunityStarDTO() {}

  public CommunityStarDTO(Long userId, Long communityId) {
    this.userId = userId;
    this.communityId = communityId;
  }

  public Long getUserId() {
    return userId;
  }
  public Long getCommunityId() {
    return communityId;
  }
}
