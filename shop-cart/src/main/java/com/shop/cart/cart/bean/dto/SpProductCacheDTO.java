package com.shop.cart.cart.bean.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 商品缓存
 *
 * Author: wang Y
 * Date: 2021-06-19
 */
@Accessors(chain = true)
@Data
public class SpProductCacheDTO {

    /**
     * 商品Id
     */
    private Long productId;
    /**
     * 商品code
     */
    private String productCode;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品分类
     */
    private String classCode;
    /**
     * 计量单位
     */
    private String unit;
    /**
     * 厂家
     */
    private String manufacturer;
    /**
     * 商品code
     */
    private String packing;
    /**
     * 市场价(划线价)
     */
    private BigDecimal marketPrice;
    /**
     * 现价
     */
    private BigDecimal ourPrice;
    /**
     * 商品图片
     */
    private String img;
    /**
     * 商家ID
     */
    private String merchantManageCode;
    /**
     * 商家名称
     */
    private String merchantName;
    /**
     * 限购数量
     */
    private Integer maxNum;
    /**
     * 商品数量
     */
    private Integer productNum;
    /**
     * 商品选中状态
     */
    private Integer selectStatus;
    /**
     * 是否下架
     */
    private Integer productStatusOrderBy;


}
