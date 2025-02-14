package com.projectaeries.core.CommunityStar;

import com.projectaeries.core.community.Community;
import com.projectaeries.core.community.CommunityRepository;
import com.projectaeries.core.user.User;
import com.projectaeries.core.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class CommunityStarService {

  Logger logger = LoggerFactory.getLogger(CommunityStarService.class);

  private final CommunityStarRepository communityStarRepository;
  private final CommunityRepository communityRepository;
  private final UserRepository userRepository;

  public CommunityStarService(CommunityStarRepository communityStarRepository, CommunityRepository communityRepository, UserRepository userRepository) {
    this.communityStarRepository = communityStarRepository;
    this.communityRepository = communityRepository;
    this.userRepository = userRepository;
  }

  public Integer getCommunityStarCountByCommunityId(Long community_id) {
    return communityStarRepository.getCommunityStarCountByCommunityId(community_id);
  }

  public List<CommunityStar> findByCommunityId(Long community_id) {
    return communityStarRepository.findByCommunityId(community_id);
  }

  public List<CommunityStar> findByUserId(Long user_id) {
    return communityStarRepository.findByUserId(user_id);
  }

  public CommunityStarStatus postCommunityStar(Long user_id, Long community_id) {
    User user = userRepository.findById(user_id).orElse(null);
    Community community = communityRepository.findById(community_id).orElse(null);
    if (user == null || community == null) {
      return CommunityStarStatus.FAILED;
    }

    CommunityStar foundStar = communityStarRepository.findByCommunityIdAndUserId(community_id, user_id);
    if (foundStar != null) {
      return CommunityStarStatus.FAILED;
    }
    CommunityStarPK communityStarPK = new CommunityStarPK(community_id, user_id);
    CommunityStar communityStar = new CommunityStar();
    communityStar.setCommunityStarPK(communityStarPK);
    communityStar.setUser(user);
    communityStar.setCommunity(community);
    communityStarRepository.save(communityStar);
    return CommunityStarStatus.POSTED;
  }

  public CommunityStarStatus removeCommunityStar(Long user_id, Long community_id) {
    CommunityStar foundStar = communityStarRepository.findByCommunityIdAndUserId(community_id, user_id);
    if (foundStar == null) {
      return CommunityStarStatus.NOT_FOUND;
    }
    communityStarRepository.delete(foundStar);
    return CommunityStarStatus.DELETED;
  }
}
