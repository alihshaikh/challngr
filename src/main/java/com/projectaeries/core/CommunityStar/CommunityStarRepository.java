package com.projectaeries.core.CommunityStar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface CommunityStarRepository extends JpaRepository<CommunityStar, Long> {
  @Query("SELECT cs from CommunityStar cs where cs.communityStarPK.communityId = :communityId")
  List<CommunityStar> findByCommunityId(Long communityId);

  @Query("SELECT cs from CommunityStar cs where cs.communityStarPK.userId = :userId")
  List<CommunityStar> findByUserId(Long userId);

  @Query("SELECT cs from CommunityStar cs where cs.communityStarPK.communityId = :communityId and cs.communityStarPK.userId = :userId")
  CommunityStar findByCommunityIdAndUserId(Long communityId, Long userId);

  @Query("select count(cs) from CommunityStar cs where cs.communityStarPK.communityId = :communityId")
  Integer getCommunityStarCountByCommunityId(Long communityId);
}
