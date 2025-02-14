package com.projectaeries.core.CommunityStar;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/community-stars")
public class CommunityStarController {

  private final CommunityStarService communityStarService;

  public CommunityStarController(CommunityStarService communityStarService) {
    this.communityStarService = communityStarService;
  }

  @PostMapping("/post-star")
  public ResponseEntity<String> starCommunity(@RequestBody CommunityStarDTO communityStarDTO) {
    CommunityStarStatus starPostingStatus = communityStarService.postCommunityStar(communityStarDTO.getUserId(), communityStarDTO.getCommunityId());
    if (starPostingStatus == CommunityStarStatus.FAILED) {
      return ResponseEntity.status(400).body("Something went wrong. Could be invalid data for user, community.");
    }
    return ResponseEntity.status(200).body("Community star posted for community: " + communityStarDTO.getCommunityId());
  }

  @GetMapping("/get-community-stars-count/{community_id}")
  public ResponseEntity<Integer> getCommunityStarsCount(@PathVariable Long community_id) {
    return ResponseEntity.ok(communityStarService.getCommunityStarCountByCommunityId(community_id
    ));
  }

  @DeleteMapping("/remove-community-star")
  public ResponseEntity<String> removeCommunityStar(@RequestBody CommunityStarDTO communityStarDTO) {
    CommunityStarStatus starRemovalStatus = communityStarService.removeCommunityStar(communityStarDTO.getUserId(), communityStarDTO.getCommunityId());
    if (starRemovalStatus== CommunityStarStatus.NOT_FOUND) {
      return ResponseEntity.status(404).body("Could not find star for community " + communityStarDTO.getCommunityId() + " user " + communityStarDTO.getUserId());
    }
    return ResponseEntity.ok("deleted star with userId: " + communityStarDTO.getUserId() + " and communityId: " + communityStarDTO.getCommunityId());
  }

}

