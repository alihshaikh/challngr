package com.projectaeries.core.communitypost;

import com.projectaeries.core.community.Community;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "community_posts")
public class CommunityPost {

    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ElementCollection
    @CollectionTable(name="community_post_images", joinColumns = @JoinColumn(name="post_id"))
    @Column(name = "image_url")
    private List<String> imageUrlList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id")
    private Community community;

    public void setCommunity(Community community) {
        this.community = community;
    }

    public Long getId() { return id;}
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public List<String> getImageUrlList() { return imageUrlList; }
    public void setImageUrlList(List<String> imageUrlList) { this.imageUrlList = imageUrlList; }
}
