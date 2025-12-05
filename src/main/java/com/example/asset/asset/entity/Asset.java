package com.example.asset.asset.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("asset")
public class Asset {

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("name")
    private String name;

    @TableField("code")
    private String code;

    @TableField("type")
    private AssetType type;

    @TableField("purchase_date")
    private LocalDate purchaseDate;

    @TableField("price")
    private BigDecimal price;

    @TableField("status")
    private AssetStatus status;

    @TableField("created_at")
    private LocalDateTime createdAt;
}
