package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.*;

public class ReviewReqDTO {
    public record ReviewCreateReqDTO(
            @NotNull Long userId,
            @NotNull @DecimalMin("0.0") @DecimalMax("5.0") Float star,
            @NotBlank @Size(min = 10) String content,
            String pictureUrl
    ) {}
}