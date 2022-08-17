package com.ftw.hometerview.review.domain;

import com.ftw.hometerview.core.domain.AbstractDocument;
import com.ftw.hometerview.review.controller.dto.ReviewDto;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
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
    Integer likeCount;
    Integer bookmarkCount;

    @Getter
    public static class Price {

        String monthly;     // 월세
        String deposit;     //전세
        String maintainFee; // 관리비
    }

    public void modify(ReviewDto.Modify req) {
        this.companyId = req.getCompanyId();
        this.rating = req.getRating();
        this.advantage = req.getAdvantage();
        this.disadvantage = req.getDisadvantage();
        this.period = req.getPeriod();
        this.price = req.getPrice();

    }

    public void setCertification(List<String> cert) {
        this.certification = cert;
    }

    public ReviewDto.Detail toDetail() {
        return ReviewDto.Detail.builder()
            .review(this)
            .isBookmarked(null)
            .isLiked(null)
            .build();
    }


    public ReviewDto.Meta toMeta() {
        return ReviewDto.Meta.builder()
            .reviewId(String.valueOf(this.getId()))
            .buildingId(this.getBuildingId())
            .rating(this.getRating())
            .advantage(this.getAdvantage())
            .disadvantage(this.getDisadvantage())
            .build();
    }

}
