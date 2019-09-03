package com.example.Repository;

import com.example.Entity.City;
import com.example.Entity.OldPost;
import org.springframework.data.repository.CrudRepository;

/**
 * OldPost Repository
 */
public interface OldPostRepository extends CrudRepository<OldPost, String> {
}
