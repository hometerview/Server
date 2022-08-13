package com.ftw.hometerview.review.repository;

import com.ftw.hometerview.review.domain.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String>, ReviewRepositoryCustom {

}
