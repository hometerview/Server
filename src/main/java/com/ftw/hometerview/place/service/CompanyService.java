package com.ftw.hometerview.place.service;

import com.ftw.hometerview.place.controller.dto.PlaceDto;
import com.ftw.hometerview.place.domain.Company;
import com.ftw.hometerview.place.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public void register(PlaceDto.RegisterCompany req) {
        Company company = req.toCompany();
        company.setNearliestStation("");  // TODO: get nearliest station from naver API
        this.companyRepository.save(company);
    }

}
