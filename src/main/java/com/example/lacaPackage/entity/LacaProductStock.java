package com.example.lacaPackage.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author generator-plus123
 * @since 2019-02-13
 */
@TableName("laca_product_stock")
public class LacaProductStock extends Model<LacaProductStock> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 产品id
     */
    @TableField("product_id")
    private String productId;
    /**
     * 进
     */
    @TableField("income_num")
    private Integer incomeNum;
    /**
     * 销
     */
    @TableField("out_num")
    private Integer outNum;
    /**
     * 存
     */
    @TableField("exsit_num")
    private Integer exsitNum;
    @TableField("create_date")
    private Date createDate;
    @TableField("create_user")
    private String createUser;
    @TableField("update_date")
    private Date updateDate;
    @TableField("update_user")
    private String updateUser;
    @TableField("delete_flag")
    private Integer deleteFlag;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getIncomeNum() {
        return incomeNum;
    }

    public void setIncomeNum(Integer incomeNum) {
        this.incomeNum = incomeNum;
    }

    public Integer getOutNum() {
        return outNum;
    }

    public void setOutNum(Integer outNum) {
        this.outNum = outNum;
    }

    public Integer getExsitNum() {
        return exsitNum;
    }

    public void setExsitNum(Integer exsitNum) {
        this.exsitNum = exsitNum;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public static final String ID = "id";

    public static final String PRODUCT_ID = "product_id";

    public static final String INCOME_NUM = "income_num";

    public static final String OUT_NUM = "out_num";

    public static final String EXSIT_NUM = "exsit_num";

    public static final String CREATE_DATE = "create_date";

    public static final String CREATE_USER = "create_user";

    public static final String UPDATE_DATE = "update_date";

    public static final String UPDATE_USER = "update_user";

    public static final String DELETE_FLAG = "delete_flag";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "LacaProductStock{" +
        "id=" + id +
        ", productId=" + productId +
        ", incomeNum=" + incomeNum +
        ", outNum=" + outNum +
        ", exsitNum=" + exsitNum +
        ", createDate=" + createDate +
        ", createUser=" + createUser +
        ", updateDate=" + updateDate +
        ", updateUser=" + updateUser +
        ", deleteFlag=" + deleteFlag +
        "}";
    }
}
