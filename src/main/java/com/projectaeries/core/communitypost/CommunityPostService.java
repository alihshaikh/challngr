package com.projectaeries.core.communitypost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommunityPostService {

    @Autowired
    private CommunityPostRepository communityPostRepository;

    public Iterable<CommunityPost> findAll() {
        return communityPostRepository.findAll();
    }

    public CommunityPost findById(Long id) {
        return communityPostRepository.findById(id).orElse(null);
    }

    @Transactional
    public CommunityPost save(CommunityPost communityPost) {
        return communityPostRepository.save(communityPost);
    }

    @Transactional
    public void deleteById(Long id) {
        CommunityPost communityPost = findById(id);
        communityPostRepository.delete(communityPost);
    }

}
