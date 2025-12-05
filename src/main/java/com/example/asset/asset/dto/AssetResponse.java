package com.example.asset.asset.dto;

import com.example.asset.asset.enums.AssetStatus;
import com.example.asset.asset.enums.AssetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetResponse {

    private String id;
    private String name;
    private String code;
    private AssetType type;
    private LocalDate purchaseDate;
    private BigDecimal price;
    private AssetStatus status;
    private LocalDateTime createdAt;
}
