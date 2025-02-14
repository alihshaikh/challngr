//package com.projectaeries.core.controller;
//
//import com.projectaeries.core.community.Community;
//import com.projectaeries.core.communitypost.CommunityPost;
//import com.projectaeries.core.communitypost.CommunityPostService;
//import com.projectaeries.core.community.CommunityService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/posts")
//public class CommunityPostController {
//
//    @Autowired
//    private CommunityPostService communityPostService;
//    @Autowired
//    private CommunityService communityService;
//
//    @GetMapping("/{communityId}")
//    public ResponseEntity<List<CommunityPost>> getCommunityPostsByCommunityId(@PathVariable Long communityId) {
//        Community community = communityService.getCommunityById(communityId);
//        if (community == null) {
//            return ResponseEntity.notFound().build();
//        }
////        return communityPostService.find
//
//    }
//
//}
