package com.ftw.hometerview.place.controller.dto;

import com.ftw.hometerview.place.domain.Company;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

public class CompanyDto {

    @Getter
    public static class RegisterCompany {

        @NotBlank
        private String name;
        @NotBlank
        private String roadAddress;
        @NotNull
        private Double lat;
        @NotNull
        private Double lon;

        public Company toCompany() {
            return Company.builder()
                .name(this.name)
                .roadAddress(this.roadAddress)     // oo시 oo구 oo동 ~~
                .province(this.roadAddress.split(" ")[1])
                .lat(this.lat)
                .lon(this.lon)
                .build();
        }
    }

    @Getter
    @Builder
    public static class Meta {

        private String companyId;
        private String name;
        private String roadAddress;
        private String nearestStation;

        public void setStationName(String stationName) {
            this.nearestStation = stationName;
        }
    }

}
