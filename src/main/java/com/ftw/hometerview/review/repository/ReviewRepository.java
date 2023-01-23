package com.ftw.hometerview.review.repository;

import com.ftw.hometerview.review.domain.Review;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {

    List<Review> getReviewByBuildingId(String buildingId, Pageable pageable);

    List<Review> getReviewByCompanyId(String companyId, Pageable pageable);

    List<Review> getReviewByMemberId(String memberId, Pageable pageable);

}
