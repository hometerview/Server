package com.ftw.hometerview.place.controller;

import static com.ftw.hometerview.core.util.Constants.DEFAULT_PAGE_SIZE;

import com.ftw.hometerview.core.annotation.NonAuthorized;
import com.ftw.hometerview.core.domain.ResponseEntity;
import com.ftw.hometerview.place.controller.dto.CompanyDto;
import com.ftw.hometerview.place.controller.dto.PlaceDto;
import com.ftw.hometerview.place.domain.SearchType;
import com.ftw.hometerview.place.service.PlaceServiceFacade;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/place")
public class PlaceController {

    private final PlaceServiceFacade placeService;

    @Operation(summary = "소속 수동입력")
    @PostMapping("/company")
    public ResponseEntity<Void> registerCompany(
        @Validated @RequestBody CompanyDto.RegisterCompany req) {
        this.placeService.registerCompany(req);
        return ResponseEntity.successResponse();
    }

    @Operation(summary = "검색")
    @NonAuthorized
    @GetMapping
    public ResponseEntity<PlaceDto.SearchResult> search(
        @RequestParam SearchType searchType,
        @RequestParam String keyword,
        @PageableDefault(size = DEFAULT_PAGE_SIZE) Pageable pageable) {
        var searchResult = this.placeService.search(searchType, keyword, pageable);
        return ResponseEntity.successResponse(searchResult);
    }

}
