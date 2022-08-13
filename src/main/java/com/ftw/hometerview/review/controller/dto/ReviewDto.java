package com.ftw.hometerview.review.controller.dto;

import com.ftw.hometerview.review.domain.Review.Price;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;


public class ReviewDto {

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

}
