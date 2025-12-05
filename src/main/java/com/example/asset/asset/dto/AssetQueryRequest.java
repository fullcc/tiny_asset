package com.example.asset.asset.dto;

import com.example.asset.asset.enums.AssetStatus;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetQueryRequest {

    @Builder.Default
    @Min(value = 1, message = "page最小为1")
    private long page = 1;

    @Builder.Default
    @Min(value = 1, message = "size最小为1")
    private long size = 20;

    private String keyword;

    private AssetStatus status;
}
