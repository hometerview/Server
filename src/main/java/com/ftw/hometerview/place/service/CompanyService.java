package com.ftw.hometerview.place.service;

import com.ftw.hometerview.core.domain.ResponseType;
import com.ftw.hometerview.core.exception.BadRequestException;
import com.ftw.hometerview.place.controller.dto.CompanyDto;
import com.ftw.hometerview.place.domain.Company;
import com.ftw.hometerview.place.repository.company.CompanyRepository;
import com.ftw.hometerview.place.util.NaverMapGeocodeAPI;
import com.ftw.hometerview.place.util.NaverMapGeocodeAPI.GeoPoint;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final NaverMapGeocodeAPI naverMapGeocodeAPI;

    public void register(CompanyDto.RegisterCompany req) {
        if (isDuplicated(req.getName(), req.getRoadAddress())) {
            throw new BadRequestException(ResponseType.DATA_DUPLICATED);
        }

        GeoPoint geoPoint = naverMapGeocodeAPI.convert(req.getRoadAddress());

        Company company = req.toCompany(geoPoint);
        // TODO: get nearliest station from naver API
        // TODO: get station id from db
        company.setNearliestStation("");
        this.companyRepository.save(company);
    }

    private boolean isDuplicated(String name, String roadAddress) {
        return this.companyRepository.existsByNameAndRoadAddress(name, roadAddress);
    }

    public List<CompanyDto.Meta> searchByKeyword(String keyword, Pageable pageable) {
        List<Company> companies = this.companyRepository.searchByKeyword(keyword, pageable);
        List<CompanyDto.Meta> response = companies.stream().map(Company::toMeta).toList();
        return response;
    }

}
