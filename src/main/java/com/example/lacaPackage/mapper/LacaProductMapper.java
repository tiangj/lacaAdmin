package com.example.lacaPackage.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.lacaPackage.DO.ProductDO;
import com.example.lacaPackage.entity.LacaProduct;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author generator-plus123
 * @since 2019-02-13
 */
@Component
public interface LacaProductMapper extends BaseMapper<LacaProduct> {

    List<ProductDO> getAllProductList(Page<ProductDO> page, ProductDO productDO);

    ProductDO getProductInfoById(String id);

}
