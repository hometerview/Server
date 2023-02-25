package com.ftw.hometerview.place.controller.dto;

import com.ftw.hometerview.place.controller.dto.CompanyDto.Meta;
import com.ftw.hometerview.place.domain.Company;
import com.ftw.hometerview.place.domain.Station;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PlaceDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class SearchResult {   // 검색창 자동완성 데이터

        List<CompanyDto.Meta> companies;
        List<BuildingDto.Meta> buildings;
        List<StationDto.Meta> stations;

        public SearchResult(List<Meta> companies) {
            this.companies = companies;
            this.buildings = Collections.EMPTY_LIST;
            this.stations = Collections.EMPTY_LIST;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class CompanyDetail {

        String companyId;
        String companyName;
        String roadAddress;
        String province;
        String stationId;
        String stationName;

        public CompanyDetail(Company company, Station station) {
            this.companyId = company.getId();
            this.companyName = company.getName();
            this.roadAddress = company.getRoadAddress();
            this.province = company.getProvince();
            this.stationId = station.getId();
            this.stationName = station.getName();
        }

    }

}
