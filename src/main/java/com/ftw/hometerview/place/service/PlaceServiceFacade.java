package com.ftw.hometerview.place.service;

import com.ftw.hometerview.place.controller.dto.CompanyDto;
import com.ftw.hometerview.place.controller.dto.PlaceDto;
import com.ftw.hometerview.place.controller.dto.BuildingDto;
import com.ftw.hometerview.place.controller.dto.PlaceDto.SearchResult;
import com.ftw.hometerview.place.controller.dto.StationDto;
import com.ftw.hometerview.place.domain.SearchType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PlaceServiceFacade {

    private final CompanyService companyService;
    private final BuildingService buildingService;
    private final StationService stationService;

    public void registerCompany(CompanyDto.RegisterCompany req) {
        this.companyService.register(req);
    }

    public PlaceDto.SearchResult search(SearchType searchType, String keyword, Pageable pageable) {
        List<CompanyDto.Meta> companies = this.companyService.searchByKeyword(keyword, pageable);
        if (searchType.equals(SearchType.COMPANY)) {
            return new SearchResult(companies);
        }
        List<BuildingDto.Meta> buildings = this.buildingService.searchByKeyword(keyword, pageable);
        List<StationDto.Meta> stations = this.stationService.searchByKeyword(keyword, pageable);
        return SearchResult.builder().companies(companies).buildings(buildings).stations(stations)
            .build();
        // TODO:: Async 처리 적용 가능 여부(메서드 분리 필요 확인)
    }

}
