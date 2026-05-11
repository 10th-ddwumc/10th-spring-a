package com.example.umc10th.domain.review.dto;

import java.math.BigDecimal;
import java.util.List;

public class ReviewRequestDTO {
    public record CreateDto(
            Long storeId,
            Long userId,
            String content,
            BigDecimal star,
            List<String> photoUrls
    ) {}

}
