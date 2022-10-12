package com.ftw.hometerview.place.controller.dto;

import com.ftw.hometerview.place.controller.dto.CompanyDto.Meta;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

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

}
