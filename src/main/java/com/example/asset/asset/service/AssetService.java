package com.example.asset.asset.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.asset.asset.dto.AssetCreateRequest;
import com.example.asset.asset.dto.AssetQueryRequest;
import com.example.asset.asset.dto.AssetResponse;
import com.example.asset.asset.entity.Asset;

public interface AssetService extends IService<Asset> {

    AssetResponse createAsset(AssetCreateRequest request);

    IPage<AssetResponse> pageAssets(AssetQueryRequest request);
}
