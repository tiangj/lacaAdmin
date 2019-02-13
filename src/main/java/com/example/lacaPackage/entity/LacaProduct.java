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
@TableName("laca_product")
public class LacaProduct extends Model<LacaProduct> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 产品类型id
     */
    @TableField("product_type_id")
    private Integer productTypeId;
    /**
     * 产品类型
     */
    @TableField("product_name")
    private String productName;
    /**
     * 大小(型号)
     */
    @TableField("product_size")
    private String productSize;
    /**
     * 颜色
     */
    @TableField("product_color")
    private Integer productColor;
    /**
     * 描述
     */
    private String remark;
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

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public Integer getProductColor() {
        return productColor;
    }

    public void setProductColor(Integer productColor) {
        this.productColor = productColor;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public static final String PRODUCT_TYPE_ID = "product_type_id";

    public static final String PRODUCT_NAME = "product_name";

    public static final String PRODUCT_SIZE = "product_size";

    public static final String PRODUCT_COLOR = "product_color";

    public static final String REMARK = "remark";

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
        return "LacaProduct{" +
        "id=" + id +
        ", productTypeId=" + productTypeId +
        ", productName=" + productName +
        ", productSize=" + productSize +
        ", productColor=" + productColor +
        ", remark=" + remark +
        ", createDate=" + createDate +
        ", createUser=" + createUser +
        ", updateDate=" + updateDate +
        ", updateUser=" + updateUser +
        ", deleteFlag=" + deleteFlag +
        "}";
    }
}
