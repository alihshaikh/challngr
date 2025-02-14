package com.projectaeries.core.dto;

public class CommunityRequestDTO {

    private Long userId;
    private Long communityId;

    public CommunityRequestDTO() {}

    public CommunityRequestDTO(Long userId, Long communityId) {
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
