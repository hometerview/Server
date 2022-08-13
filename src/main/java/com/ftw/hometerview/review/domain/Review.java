package com.ftw.hometerview.review.domain;

import com.ftw.hometerview.core.domain.AbstractDocument;
import com.ftw.hometerview.review.controller.dto.ReviewDto;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import nonapi.io.github.classgraph.json.Id;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "review")
public class Review extends AbstractDocument {

    @Id
    ObjectId id;

    String memberId;
    String buildingId;
    String companyId;
    Integer rating;

    String advantage;
    String disadvantage;

    String period;
    Price price;
    List<String> certification;
    Integer like_count;
    Integer bookmark_count;

    @Getter
    public static class Price {

        String monthly;     // 월세
        String deposit;     //전세
        String maintainFee; // 관리비
    }

    public Review modify(ReviewDto.Modify req) {
        this.companyId = req.getCompanyId();
        this.rating = req.getRating();
        this.advantage = req.getAdvantage();
        this.disadvantage = req.getDisadvantage();
        this.period = req.getPeriod();
        this.price = req.getPrice();
        return this;

    }

    public Review setCertification(List<String> cert) {
        this.certification = cert;
        return this;
    }

    public static ReviewDto.Detail toDetail(Review review) {
        return ReviewDto.Detail.builder()
            .review(review)
            .isBookmarked(null)
            .isLiked(null)
            .build();
    }

    public static ReviewDto.Meta toMeta(Review review) {
        return ReviewDto.Meta.builder()
            .reviewId(String.valueOf(review.getId()))
            .buildingId(review.getBuildingId())
            .rating(review.getRating())
            .advantage(review.getAdvantage())
            .disadvantage(review.getDisadvantage())
            .build();
    }

}
