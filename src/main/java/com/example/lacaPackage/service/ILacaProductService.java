package com.example.lacaPackage.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.lacaPackage.DO.ProductDO;
import com.example.lacaPackage.entity.LacaProduct;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author generator-plus123
 * @since 2019-02-13
 */
public interface ILacaProductService extends IService<LacaProduct> {

    Page<ProductDO> getAllProductList(Page<ProductDO> page, ProductDO productDO);

    ProductDO getProductInfoById(String id);

    Map<String,Object> saveProduct(ProductDO productDO,String userId) throws Exception;

}
