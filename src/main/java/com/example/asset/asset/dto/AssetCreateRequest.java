package com.example.asset.asset.dto;

import com.example.asset.asset.enums.AssetType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetCreateRequest {

    @NotBlank(message = "资产名称不能为空")
    @Size(max = 100, message = "资产名称长度不能超过100字符")
    private String name;

    @NotBlank(message = "资产编号不能为空")
    private String code;

    @NotNull(message = "资产类型不能为空")
    private AssetType type;

    @NotNull(message = "采购日期不能为空")
    private LocalDate purchaseDate;

    @NotNull(message = "价格不能为空")
    @DecimalMin(value = "0", inclusive = true, message = "价格不能为负数")
    private BigDecimal price;
}
