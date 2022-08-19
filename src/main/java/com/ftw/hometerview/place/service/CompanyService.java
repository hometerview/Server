package com.ftw.hometerview.place.service;

import com.ftw.hometerview.core.domain.ResponseType;
import com.ftw.hometerview.core.exception.BadRequestException;
import com.ftw.hometerview.place.controller.dto.CompanyDto;
import com.ftw.hometerview.place.domain.Company;
import com.ftw.hometerview.place.repository.company.CompanyRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public void register(CompanyDto.RegisterCompany req) {
        if (isDuplicated(req.getName(), req.getLoadName())) {
            throw new BadRequestException(ResponseType.DATA_DUPLICATED);
        }
        Company company = req.toCompany();
        // TODO: get nearliest station from naver API
        // TODO: get station id from db
        company.setNearliestStation("");
        this.companyRepository.save(company);
    }

    private boolean isDuplicated(String name, String loadName) {
        return this.companyRepository.existsByNameAndLoadName(name, loadName);
    }

}
