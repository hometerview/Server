package com.ftw.hometerview.place.controller.dto;

import com.ftw.hometerview.place.domain.Building.ResidenceType;
import lombok.Builder;
import lombok.Getter;

public class BuildingDto {

    @Getter
    @Builder
    public static class Meta {

        private String buildingId;
        private String name;
        private String loadName;
    }

    @Getter
    @Builder
    public static class Brief {

        private String buildingId;
        private String name;
        private String loadName;
        private ResidenceType residenceType;
        private Integer totalRating;

        private Integer totalReviewCount;
        private Boolean isFavorite;
    }

}
