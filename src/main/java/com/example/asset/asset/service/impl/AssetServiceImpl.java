package com.example.asset.asset.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.asset.asset.dto.AssetCreateRequest;
import com.example.asset.asset.dto.AssetQueryRequest;
import com.example.asset.asset.dto.AssetResponse;
import com.example.asset.asset.entity.Asset;
import com.example.asset.asset.enums.AssetStatus;
import com.example.asset.asset.mapper.AssetMapper;
import com.example.asset.asset.service.AssetService;
import com.example.asset.common.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssetServiceImpl extends ServiceImpl<AssetMapper, Asset> implements AssetService {

    @Override
    public AssetResponse createAsset(AssetCreateRequest request) {
        validateCodeUnique(request.getCode());

        Asset asset = Asset.builder()
                .name(request.getName())
                .code(request.getCode())
                .type(request.getType())
                .purchaseDate(request.getPurchaseDate())
                .price(request.getPrice())
                .status(AssetStatus.IDLE)
                .createdAt(LocalDateTime.now())
                .build();
        this.save(asset);
        return buildResponse(asset);
    }

    @Override
    public IPage<AssetResponse> pageAssets(AssetQueryRequest request) {
        Page<Asset> page = Page.of(request.getPage(), request.getSize());
        LambdaQueryWrapper<Asset> queryWrapper = new LambdaQueryWrapper<>();

        if (StringUtils.hasText(request.getKeyword())) {
            queryWrapper.and(wrapper -> wrapper.like(Asset::getName, request.getKeyword())
                    .or()
                    .like(Asset::getCode, request.getKeyword()));
        }
        if (request.getStatus() != null) {
            queryWrapper.eq(Asset::getStatus, request.getStatus());
        }
        queryWrapper.orderByDesc(Asset::getCreatedAt);

        IPage<Asset> resultPage = this.page(page, queryWrapper);
        List<AssetResponse> responses = resultPage.getRecords().stream()
                .map(this::buildResponse)
                .collect(Collectors.toList());

        Page<AssetResponse> responsePage = new Page<>();
        responsePage.setRecords(responses);
        responsePage.setTotal(resultPage.getTotal());
        responsePage.setCurrent(resultPage.getCurrent());
        responsePage.setSize(resultPage.getSize());

        return responsePage;
    }

    private void validateCodeUnique(String code) {
        long count = this.lambdaQuery().eq(Asset::getCode, code).count();
        if (count > 0) {
            throw new BusinessException(400, "资产编号已存在");
        }
    }

    private AssetResponse buildResponse(Asset asset) {
        return AssetResponse.builder()
                .id(asset.getId())
                .name(asset.getName())
                .code(asset.getCode())
                .type(asset.getType())
                .purchaseDate(asset.getPurchaseDate())
                .price(asset.getPrice())
                .status(asset.getStatus())
                .createdAt(asset.getCreatedAt())
                .build();
    }
}
