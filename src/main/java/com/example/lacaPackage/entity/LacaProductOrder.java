package com.example.lacaPackage.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author generator-plus123
 * @since 2019-02-13
 */
@TableName("laca_product_order")
public class LacaProductOrder extends Model<LacaProductOrder> {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    /**
     * 订单总数
     */
    @TableField("total_num")
    private Integer totalNum;
    /**
     * 类型(1:进,2:出)
     */
    @TableField("sale_type")
    private Integer saleType;
    /**
     * 加盟商
     */
    @TableField("join_shop")
    private String joinShop;
    /**
     * 客户姓名
     */
    @TableField("customer_name")
    private String customerName;
    /**
     * 设计师
     */
    private String designer;
    /**
     * 用店费
     */
    @TableField("user_shop_fee")
    private String userShopFee;
    /**
     * 支付方式(1:现金,2:刷卡,3:微信,4:支付宝)
     */
    @TableField("post_way")
    private String postWay;
    /**
     * 订单总金额
     */
    @TableField("total_price")
    private String totalPrice;
    /**
     * 微信支付金额
     */
    @TableField("wx_pay_price")
    private String wxPayPrice;
    /**
     * 支付宝支付金额
     */
    @TableField("alipay_pay_price")
    private String alipayPayPrice;
    /**
     * 现金支付金额
     */
    @TableField("cash_pay_price")
    private String cashPayPrice;
    /**
     * 刷卡支付金额
     */
    @TableField("card_pay_price")
    private String cardPayPrice;
    /**
     * 状态
     */
    private String status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 其他
     */
    private String other;
    @TableField("create_date")
    private Date createDate;
    /**
     * 创建人
     */
    @TableField("create_user")
    private String createUser;
    /**
     * 更新时间
     */
    @TableField("update_date")
    private Date updateDate;
    /**
     * 更新人
     */
    @TableField("update_user")
    private String updateUser;
    private Integer deleteFlag;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getSaleType() {
        return saleType;
    }

    public void setSaleType(Integer saleType) {
        this.saleType = saleType;
    }

    public String getJoinShop() {
        return joinShop;
    }

    public void setJoinShop(String joinShop) {
        this.joinShop = joinShop;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDesigner() {
        return designer;
    }

    public void setDesigner(String designer) {
        this.designer = designer;
    }

    public String getUserShopFee() {
        return userShopFee;
    }

    public void setUserShopFee(String userShopFee) {
        this.userShopFee = userShopFee;
    }

    public String getPostWay() {
        return postWay;
    }

    public void setPostWay(String postWay) {
        this.postWay = postWay;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getWxPayPrice() {
        return wxPayPrice;
    }

    public void setWxPayPrice(String wxPayPrice) {
        this.wxPayPrice = wxPayPrice;
    }

    public String getAlipayPayPrice() {
        return alipayPayPrice;
    }

    public void setAlipayPayPrice(String alipayPayPrice) {
        this.alipayPayPrice = alipayPayPrice;
    }

    public String getCashPayPrice() {
        return cashPayPrice;
    }

    public void setCashPayPrice(String cashPayPrice) {
        this.cashPayPrice = cashPayPrice;
    }

    public String getCardPayPrice() {
        return cardPayPrice;
    }

    public void setCardPayPrice(String cardPayPrice) {
        this.cardPayPrice = cardPayPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
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

    public static final String TOTAL_NUM = "total_num";

    public static final String SALE_TYPE = "sale_type";

    public static final String JOIN_SHOP = "join_shop";

    public static final String CUSTOMER_NAME = "customer_name";

    public static final String DESIGNER = "designer";

    public static final String USER_SHOP_FEE = "user_shop_fee";

    public static final String POST_WAY = "post_way";

    public static final String TOTAL_PRICE = "total_price";

    public static final String WX_PAY_PRICE = "wx_pay_price";

    public static final String ALIPAY_PAY_PRICE = "alipay_pay_price";

    public static final String CASH_PAY_PRICE = "cash_pay_price";

    public static final String CARD_PAY_PRICE = "card_pay_price";

    public static final String STATUS = "status";

    public static final String REMARK = "remark";

    public static final String OTHER = "other";

    public static final String CREATE_DATE = "create_date";

    public static final String CREATE_USER = "create_user";

    public static final String UPDATE_DATE = "update_date";

    public static final String UPDATE_USER = "update_user";

    public static final String DELETEFLAG = "deleteFlag";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "LacaProductOrder{" +
        "id=" + id +
        ", totalNum=" + totalNum +
        ", saleType=" + saleType +
        ", joinShop=" + joinShop +
        ", customerName=" + customerName +
        ", designer=" + designer +
        ", userShopFee=" + userShopFee +
        ", postWay=" + postWay +
        ", totalPrice=" + totalPrice +
        ", wxPayPrice=" + wxPayPrice +
        ", alipayPayPrice=" + alipayPayPrice +
        ", cashPayPrice=" + cashPayPrice +
        ", cardPayPrice=" + cardPayPrice +
        ", status=" + status +
        ", remark=" + remark +
        ", other=" + other +
        ", createDate=" + createDate +
        ", createUser=" + createUser +
        ", updateDate=" + updateDate +
        ", updateUser=" + updateUser +
        ", deleteFlag=" + deleteFlag +
        "}";
    }
}
