package com.example.Service;

import com.example.Entity.OldPost;
import com.example.Repository.OldPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CRUDOldPostService {

    @Autowired
    private OldPostRepository oldPostRepository;

    /**
     * Save Old Post to DB
     *
     * @param oldPost get from request
     * @return saved Old Post info
     */
    public OldPost saveOldPost(OldPost oldPost) {
        return (oldPost == null) ? null : oldPostRepository.save(oldPost);
    }

    /**
     * Find Old post by old Post Id
     *
     * @param oldPostId get from URL
     * @return Old post
     */
    public OldPost find(String oldPostId) {
        return (oldPostId == null) ? null : oldPostRepository.findOne(oldPostId);
    }

    /**
     * Update an Old Post
     *
     * @param oldPost info get from request
     * @return updated OldPost
     */
    public OldPost updateOldPost(OldPost oldPost) {
        if (oldPost == null || oldPost.getOldPostId() == null) return null;
        OldPost updateOldPost = oldPostRepository.findOne(oldPost.getOldPostId());
        updateOldPost.setOldPostCode(oldPost.getOldPostCode());
        return oldPostRepository.save(updateOldPost);
    }

    /**
     * Delete an OldPost
     *
     * @param oldPostId to delete
     */
    public void deleteOldPost(String oldPostId) {
        oldPostRepository.delete(oldPostId);
    }
}
