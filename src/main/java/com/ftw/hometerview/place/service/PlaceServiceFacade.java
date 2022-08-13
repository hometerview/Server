package com.ftw.hometerview.place.service;

import com.ftw.hometerview.place.controller.dto.PlaceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PlaceServiceFacade {

    private final CompanyService companyService;

    public void registerCompany(PlaceDto.RegisterCompany req) {
        this.companyService.register(req);
    }

}
