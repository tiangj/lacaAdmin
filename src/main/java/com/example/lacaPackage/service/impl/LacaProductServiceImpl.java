package com.example.lacaPackage.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.lacaPackage.DO.ProductDO;
import com.example.lacaPackage.entity.LacaProduct;
import com.example.lacaPackage.entity.LacaProductStock;
import com.example.lacaPackage.mapper.LacaProductMapper;
import com.example.lacaPackage.mapper.LacaProductStockMapper;
import com.example.lacaPackage.service.ILacaProductService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author generator-plus123
 * @since 2019-02-13
 */
@Service
public class LacaProductServiceImpl extends ServiceImpl<LacaProductMapper, LacaProduct> implements ILacaProductService {

    @Autowired
    private LacaProductMapper lacaProductMapper;

    @Autowired
    private LacaProductStockMapper lacaProductStockMapper;

    @Override
    public Page<ProductDO> getAllProductList(Page<ProductDO> page, ProductDO productDO) {
        page.setRecords(lacaProductMapper.getAllProductList(page,productDO));
        return page;
    }

    @Override
    public ProductDO getProductInfoById(String id) {
        return lacaProductMapper.getProductInfoById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> saveProduct(ProductDO productDO,String userId) throws Exception  {
        Map<String,Object> result=new HashMap<>();
        try{
            // 保存商品信息
            LacaProduct lacaProduct=new LacaProduct();
            lacaProduct.setProductTypeId(productDO.getProductTypeId());
            lacaProduct.setProductName(productDO.getProductName());
            lacaProduct.setProductColor(productDO.getProductColor());
            lacaProduct.setProductSize(productDO.getProductSize());
            lacaProduct.setRemark(productDO.getRemark());
            lacaProduct.setUpdateUser(userId);
            lacaProduct.setUpdateDate(new Date());
            if(productDO.getProductId()!=null){
                lacaProduct.setId(productDO.getProductId());
                lacaProductMapper.updateById(lacaProduct);
            }else{
                lacaProduct.setCreateUser(userId);
                lacaProduct.setCreateDate(new Date());
                lacaProductMapper.insert(lacaProduct);
            }
            //保存商品库存
            LacaProductStock lacaProductStock=new LacaProductStock();
            lacaProductStock.setProductId(productDO.getProductId()+"");
            lacaProductStock.setIncomeNum(productDO.getIncomeNum());
            lacaProductStock.setOutNum(productDO.getOutNum());
            lacaProductStock.setExsitNum(productDO.getExsitNum());
            lacaProductStock.setUpdateUser(userId);
            lacaProductStock.setUpdateDate(new Date());
            if(productDO.getProductStockId()!=null){
                lacaProductStock.setId(productDO.getProductStockId());
                lacaProductStockMapper.updateById(lacaProductStock);
            }else{
                lacaProductStock.setCreateUser(userId);
                lacaProductStock.setCreateDate(new Date());
                lacaProductStockMapper.insert(lacaProductStock);
            }
            result.put("code",1);
            result.put("msg","操作成功");
        }catch (Exception e){
            result.put("code",0);
            result.put("msg","操作失败");
            throw new Exception();
        }
        return result;
    }
}
