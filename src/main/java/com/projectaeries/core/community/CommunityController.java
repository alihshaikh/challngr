package com.projectaeries.core.community;

import com.projectaeries.core.CommunityStar.CommunityStar;
import com.projectaeries.core.CommunityStar.CommunityStarService;
import com.projectaeries.core.communitypost.CommunityPost;
import com.projectaeries.core.communitypost.CommunityPostService;
import com.projectaeries.core.dto.CommunityRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/communities")
public class CommunityController {

    private final CommunityService communityService;
    private final CommunityPostService communityPostService;
    private final CommunityStarService communityStarService;

    @Autowired
    public CommunityController(CommunityService communityService, CommunityPostService communityPostService, CommunityStarService communityStarService) {
        this.communityService = communityService;
        this.communityPostService = communityPostService;
        this.communityStarService = communityStarService;
    }

    @GetMapping("/")
    public Iterable<Community> getAllCommunities() {
        return communityService.getAllCommunities();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Community> getCommunityById(@PathVariable Long id) {
        Community community = communityService.getCommunityById(id);
        if (community == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(community);
    }

    @GetMapping("/{community_id}/get_posts")
    public ResponseEntity<List<CommunityPost>> getCommunityPosts(@PathVariable Long community_id) {
     Community community = communityService.getCommunityById(community_id);
     if (community == null) {
         return ResponseEntity.notFound().build();
     }
     return ResponseEntity.ok(community.getPosts());
    }

    @PostMapping("/{community_id}/post")
    public ResponseEntity<CommunityPost> addCommunityPost(@PathVariable Long community_id, @RequestBody CommunityPost post) {
        Community community = communityService.getCommunityById(community_id);
        if (community == null) {
            return ResponseEntity.notFound().build();
        }
        post.setCommunity(community);
        community.getPosts().add(post);
        communityPostService.save(post);
        return ResponseEntity.status(200).body(post);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCommunity(@RequestBody Community community) {
        communityService.createCommunity(community);
        return ResponseEntity.ok("Community created");
    }

    @PostMapping("/join_community")
    public ResponseEntity<String> joinCommunity(@RequestBody CommunityRequestDTO communityRequestDTO) {
        Community updatedCommunity = communityService.addUserToCommunity(
                communityRequestDTO.getUserId(),
                communityRequestDTO.getCommunityId());
        if (updatedCommunity == null)
            return ResponseEntity.status(404).body(communityRequestDTO.getUserId() + " is not a valid community");
        return ResponseEntity.ok("UserID: " + communityRequestDTO.getUserId() + " joined community id : " + updatedCommunity.getId());
    }

    @PostMapping("/star")
    public ResponseEntity<String> starCommunity(@RequestBody CommunityRequestDTO communityRequestDTO) {
        communityStarService.postCommunityStar(communityRequestDTO.getUserId(), communityRequestDTO.getCommunityId());
        return ResponseEntity.ok("Community star stared");
    }

    @GetMapping("/{communityId}/get_community_stars")
    public List<CommunityStar> getStarsByCommunityId(@PathVariable Long communityId) {
        return communityStarService.findByCommunityId(communityId);
    }

    // go to users controller
    @GetMapping("/{userId}/get_user_stars")
    public List<CommunityStar> getStarsByUserId(@PathVariable Long userId) {
        return communityStarService.findByUserId(userId);
    }
}
