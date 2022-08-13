package com.ftw.hometerview.place.controller;

import com.ftw.hometerview.core.domain.ResponseEntity;
import com.ftw.hometerview.place.controller.dto.PlaceDto;
import com.ftw.hometerview.place.service.PlaceServiceFacade;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/place")
public class PlaceController {

    private final PlaceServiceFacade placeService;

    @Operation(summary = "소속 수동입력")
    @PostMapping
    public ResponseEntity<Void> registerCompany(
        @Validated @RequestBody PlaceDto.RegisterCompany req) {
        this.placeService.registerCompany(req);
        return ResponseEntity.successResponse();
    }

}
