package com.ftw.hometerview.review.controller;

import com.ftw.hometerview.core.domain.ResponseEntity;
import com.ftw.hometerview.review.controller.dto.ReviewDto;
import com.ftw.hometerview.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Operation(summary = "리뷰 수정")
    @PatchMapping
    public ResponseEntity<Void> modifyReview(@Validated @RequestBody ReviewDto.Modify req) {
        this.reviewService.modifyReviewList(req);
        return ResponseEntity.successResponse();
    }

    @Operation(summary = "리뷰 삭제")
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable String reviewId) {
        this.reviewService.deleteReview(reviewId);
        return ResponseEntity.successResponse();
    }

}
