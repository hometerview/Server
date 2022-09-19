package com.ftw.hometerview.review.controller.dto;

import static com.ftw.hometerview.core.interceptor.AuthUtil.getCurrentMemberId;

import com.ftw.hometerview.review.domain.Review;
import com.ftw.hometerview.review.domain.Review.Floor;
import com.ftw.hometerview.review.domain.Review.Price;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;


public class ReviewDto {

    @Getter
    public static class Create {

        @NotBlank
        private String buildingId;
        private String companyId;
        private String period;
        @NotNull
        private Integer rating;
        @Size(min = 30, max = 900)
        private String advantage;
        @Size(min = 30, max = 900)
        private String disadvantage;
        private Price price;
        private Floor floor;

        public Review toReview() {
            return Review.builder()
                .memberId(getCurrentMemberId())
                .buildingId(this.buildingId)
                .companyId(this.companyId)
                .rating(this.rating)
                .advantage(this.advantage)
                .disadvantage(this.disadvantage)
                .period(this.period)
                .price(this.price)
                .floor(this.floor)
                .likeCount(0)
                .bookmarkCount(0)
                .build();
        }

    }

    @Getter
    public static class Modify {

        @NotBlank
        private String reviewId;
        @NotBlank
        private String buildingId;
        private String companyId;
        private String period;
        @NotNull
        private Integer rating;
        @Size(min = 30, max = 900)
        private String advantage;
        @Size(min = 30, max = 900)
        private String disadvantage;
        private Price price;
    }

    @Getter
    @Builder
    public static class Meta {

        private String reviewId;
        private String buildingId;
        private Integer rating;
        private String advantage;
        private String disadvantage;

    }

    @Getter
    @Builder
    public static class Detail {

        private Review review;
        private Boolean isLiked;
        private Boolean isBookmarked;

    }

}
