package com.ftw.hometerview.place.controller.dto;

import com.ftw.hometerview.place.domain.Company;
import com.ftw.hometerview.place.util.NaverMapGeocodeAPI.GeoPoint;
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

        public Company toCompany(GeoPoint geoPoint) {
            return Company.builder()
                .name(this.name)
                .roadAddress(this.roadAddress)     // oo시 oo구 oo동 ~~
                .province(this.roadAddress.split(" ")[1])
                .lat(geoPoint.getLat())
                .lon(geoPoint.getLon())
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
