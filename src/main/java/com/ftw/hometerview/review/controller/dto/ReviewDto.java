package com.ftw.hometerview.review.controller.dto;

import static com.ftw.hometerview.core.interceptor.AuthUtil.getCurrentMemberId;

import com.ftw.hometerview.review.domain.Review;
import com.ftw.hometerview.review.domain.Review.Price;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
                .like_count(0)
                .bookmark_count(0)
                .build();
        }

    }

}
