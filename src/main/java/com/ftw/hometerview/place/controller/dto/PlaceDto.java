package com.ftw.hometerview.place.controller.dto;

import com.ftw.hometerview.place.domain.Company;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Getter;

public class PlaceDto {

    private static final String LOAD_PATTERN =
        "(([가-힣A-Za-z·\\d~\\-\\.]{2,}(로|길).[\\d]+)|([가-힣A-Za-z·\\d~\\-\\.]+(읍|동)\\s)[\\d]+)";

    @Getter
    public static class RegisterCompany {

        @NotBlank
        private String name;
        @NotBlank
        @Pattern(regexp = LOAD_PATTERN)
        private String loadName;
        @NotBlank
        private String lat;
        @NotBlank
        private String lon;

        public Company toCompany() {
            return Company.builder()
                .name(this.name)
                .loadName(this.loadName)     // oo시 oo구 oo동 ~~
                .city(this.loadName.split(" ")[1])
                .lat(this.lat)
                .lon(this.lon)
                .build();
        }
    }

}
