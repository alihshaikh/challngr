package com.projectaeries.core.service;

import com.projectaeries.core.community.Community;
import com.projectaeries.core.community.CommunityRepository;
import com.projectaeries.core.community.CommunityService;
import com.projectaeries.core.communitypost.CommunityPost;
import com.projectaeries.core.user.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.awaitility.Awaitility.given;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommunityServiceUnitTests {

  @Mock
  private CommunityRepository communityRepository;

  @InjectMocks
  private CommunityService communityService;

  private Community community;

  @BeforeEach
  public void setup() {
    community = new Community();
    community.setId(1L);
    community.setName("Test Community");
    community.setDescription("Test Community Description");
    community.setCategory(Community.CommunityCategory.ENTERTAINMENT);
    community.setMemberCount(22);
    community.setCreatedAt(new Date(2025,01,01));
    List<CommunityPost> communityPosts = new ArrayList<>();
    community.setPosts(communityPosts);
    List<User> users = new ArrayList<>();
    community.setUsers(users);
  }

  @Test
  @Order(1)
  public void getEmployeeById() {
    when(communityRepository.findById(1L)).thenReturn(Optional.of(community));
    Community community = communityService.getCommunityById(1L);
    assertNotNull(community, "Community should not be null");
    assertEquals("Test Community", community.getName(), "Community name should match");
    assertEquals(1L, community.getId(), "Community id should match");
    verify(communityRepository, times(1)).findById(1L);
  }

  @Test
  @Order(2)
  public void getEmployeeByNonExistentId() {
    Long nonExistentId = 2L;
    when(communityRepository.findById(nonExistentId)).thenReturn(Optional.empty());
    Community community = communityService.getCommunityById(nonExistentId);
    assertNull(community, "Community should not be null");
    verify(communityRepository, times(1)).findById(nonExistentId);
  }

}
