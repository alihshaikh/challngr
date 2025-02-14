package com.projectaeries.core.communitycomment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunityCommentService {
    @Autowired
    private CommunityCommentRepository communityCommentRepository;

}
