package com.shop.product.category.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.product.category.bean.entity.SpProductAttribute;
import com.shop.product.category.bean.req.SpProductAttributeReq;
import com.shop.product.category.bean.vo.SpProductAttributeVO;

import java.util.List;

public interface SpProductAttributeService extends IService<SpProductAttribute> {

    /**
     * 新增商品属性
     *
     * @param spProductAttributeReq
     * @return: void
     * @Date: 2021-05-31
     */
    void create(SpProductAttributeReq spProductAttributeReq);

    /**
     * 批量删除商品属性
     *
     * @param idList
     * @return: void
     * @Date: 2021-05-31
     */
    void deleteBatch(List<Long> idList);

    /**
     * 修改商品属性
     *
     * @param spProductAttributeReq
     * @return: void
     * @Date: 2021-05-31
     */
    void update(SpProductAttributeReq spProductAttributeReq);

    /**
     * 分页
     *
     * @param cid
     * @param type
     * @param current
     * @param size
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.shop.product.category.bean.vo.SpProductAttributeVO>
     * @Date: 2021-05-31
     */
    IPage<SpProductAttributeVO> page(Long productAttributeCategoryId,
                                     Integer type,
                                     Long current,
                                     Long size);

    /**
     * 根据ID查询商品属性
     *
     * @param id
     * @return: com.shop.product.category.bean.vo.SpProductAttributeVO
     * @Date: 2021-05-31
     */
    SpProductAttributeVO getById(Long id);


}