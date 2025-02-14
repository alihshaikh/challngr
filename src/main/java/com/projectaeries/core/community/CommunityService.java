package com.projectaeries.core.community;

import com.projectaeries.core.user.User;
import com.projectaeries.core.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CommunityService {
    private final CommunityRepository communityRepository;
    private final UserRepository userRepository;

    public CommunityService(CommunityRepository communityRepository, UserRepository userRepository) {
        this.communityRepository = communityRepository;
        this.userRepository = userRepository;
    }

    public Iterable<Community> getAllCommunities() {
        return this.communityRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Community getCommunityById(Long id) {
        return this.communityRepository.findById(id).orElse(null);
    }

    @Transactional
    public Community createCommunity(Community community) {
        return this.communityRepository.save(community);
    }

    @Transactional
    public void deleteCommunityById(Long id) {
        this.communityRepository.deleteById(id);
    }

    public Community addUserToCommunity(Long user_id, Long community_id) {
        User user = userRepository.findById(user_id).orElse(null);
        Community community = communityRepository.findById(community_id).orElse(null);
        if (user == null || community == null) {
          return null;
        }
        user.getUserCommunities().add(community);
        userRepository.save(user);
        community.getUsers().add(user);
        community.setMemberCount(community.getMemberCount() + 1);
        return this.communityRepository.save(community);
    }

}
