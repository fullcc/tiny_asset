package com.example.asset.asset.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.asset.asset.dto.AssetCreateRequest;
import com.example.asset.asset.dto.AssetQueryRequest;
import com.example.asset.asset.dto.AssetResponse;
import com.example.asset.asset.service.AssetService;
import com.example.asset.common.PageResponse;
import com.example.asset.common.Result;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/assets")
@Validated
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @PostMapping
    public Result<AssetResponse> createAsset(@Valid @RequestBody AssetCreateRequest request) {
        return Result.success(assetService.createAsset(request));
    }

    @GetMapping
    public Result<PageResponse<AssetResponse>> listAssets(
            @RequestParam(name = "page", defaultValue = "1") long page,
            @RequestParam(name = "page_size", defaultValue = "20") long size,
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "status", required = false) com.example.asset.asset.enums.AssetStatus status
    ) {
        AssetQueryRequest query = AssetQueryRequest.builder()
                .page(page)
                .size(size)
                .keyword(keyword)
                .status(status)
                .build();
        IPage<AssetResponse> responsePage = assetService.pageAssets(query);
        PageResponse<AssetResponse> payload = PageResponse.<AssetResponse>builder()
                .list(responsePage.getRecords())
                .total(responsePage.getTotal())
                .page(responsePage.getCurrent())
                .size(responsePage.getSize())
                .build();
        return Result.success(payload);
    }
}
