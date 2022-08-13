package com.ftw.hometerview.review.controller;

import com.ftw.hometerview.core.domain.ResponseEntity;
import com.ftw.hometerview.review.controller.dto.ReviewDto;
import com.ftw.hometerview.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/review")
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "리뷰 등록")
    @PostMapping
    public ResponseEntity<Void> registerReview(@Validated @RequestBody ReviewDto.Create request) {
        this.reviewService.registerReview(request);
        return ResponseEntity.successResponse();
    }

}
