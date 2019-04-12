package com.ouyangjia.mybatis;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 借款申请 信息
 * 
 * @author liuwhb 2016年12月7日下午5:01:33
 */
@SuppressWarnings("serial")
@Data
public class LoanApply implements Serializable {
    /**
     * 主键ID
     */
    private Long fatpLoanApplyId;
    /**
     * 流水号（借据号)
     */
    private String serialNo;
    /**
     * 合同流水号
     */
    private String contractSerialNo;
    /**
     * 客户代码
     */
    private String customerId;
    /**
     * 客户名称
     */
    private String customerName;
    /**
     * 业务品种(代码:BusinessType)
     */
    private String businessType;
    /**
     * 业务币种(代码:Currency)
     */
    private String currency;
    /**
     * 入账机构
     */
    private String orgId;
    /**
     * 还款方式(代码:ReturnMethod)
     */
    private String repayType;
    /**
     * 还款周期(代码:PayCyc)
     */
    private String repayPeriod;
    /**
     * 还款日
     */
    private Integer repayDate;
    /**
     * 利率类型(代码:RateCode)
     */
    private String rateCode;
    /**
     * 利率模式(代码:RateMode)
     */
    private String rateMode;
    /**
     * 利率浮动方式(代码:RateFloatType)
     */
    private String rateFloatType;
    /**
     * 基准利率类型(代码:RateType)
     */
    private String baseRateType;
    /**
     * 基准利率
     */
    private Double baseRate;
    /**
     * 浮动利率
     */
    private Double rateFloat;
    /**
     * 利率
     */
    private Double businessRate;
    /**
     * 金额
     */
    private Double businessSum;
    /**
     * 出账状态 ：0(放款状态-未记帐)  1(放款状态-已记账) 9(放款状态-已取消)  01(手动发标)  02(发标完成)  03(获取融资担保)
     * 04(融资担保金额确定)  05(已满标) 06(放款清单待导出)\r\n 001(待发送还款计划)  99(发标失败)
     */
    private String putOutStatus;
    /**
     * 主还款方式(代码:MainReturnType)
     */
    private String mainRepayType;
    /**
     * 贷款期限
     */
    private Integer loanTerm;
    /**
     * 利率浮动周期(代码:RateAdjustType)
     */
    private String rateAdjustType;
    /**
     * 贷款期限单位(代码:TermUnit)()
     */
    private String loanTermFlag;
    /**
     * 经办机构
     */
    private String inputOrgId;
    /**
     * 出账类型(代码:PutOutType)
     */
    private String putOutType;
    /**
     * 用途
     */
    private String purpose;
    /**
     * 担保方式(代码:VouchType)
     */
    private String vouchType;
    /**
     * 出账申请日期
     */
    private String applyDate;
    /**
     * 影像条形码
     */
    private String imageNo;
    /**
     * 其他用途说明
     */
    private String otherPurpose;
    /**
     * 业务类型
     */
    private String loanType;
    /**
     * 客户类型（代码：CustomerType）
     */
    private String customerType;
    /**
     * 还款卡号
     */
    private String repayCardId;
    /**
     * 还款银行
     */
    private String repayBank;
    /**
     * 担保费
     */
    private Double guaranteeFee;
    /**
     * 管理费
     */
    private Double managementFee;
    /**
     * 成交费
     */
    private Double fee;
    /**
     * 逾期账户管理费(万分比)
     */
    private Double overManagementFee;
    /**
     * 大华交易密码
     */
    private String dahuaTradePassword;
    /**
     * 大华交易账号
     */
    private String dahuaTradeAcco;
    /**
     * 放款卡号
     */
    private String loanCardId;
    /**
     * 放款银行
     */
    private String loanBank;
    /**
     * 手续费%,
     */
    private Double handlingFee;
    /**
     * 还款银行支行
     */
    private String repayBankBranch;
    /**
     * 放款银行支行
     */
    private String loanBankBranch;
    /**
     * 融资担保金额
     */
    private Double financeGMoney;
    /**
     * 融资担保费率
     */
    private Double financeGRate;
    /**
     * 融资管理费率
     */
    private Double financeMRate;
    /**
     * 融资担保比例
     */
    private Double rzRatio;
    /**
     * 提还违约金费率
     */
    private Double penaltyRate;
    /**
     * 提还手续费率
     */
    private Double prePoundageRate;
    /**
     * 借款服务费率
     */
    private Double consultingRate;
    /**
     * 标的还款标识（区分还款逻辑）
     */
    private String newRepayFlag;
    /**
     * 畅捷支付协议编号
     */
    private String crpsprotocolNo;
    /**
     * 是否需融资性担保公司担保（代码：YESNO）
     */
    private String isFinanceGuarantee;
    /**
     * 放款人id
     */
    private String lenderId;
    /**
     * 还款人id
     */
    private String repaymentId;
    /**
     * 是否预发布
     */
    private String preStatus;

    /**
     * 预发布日期
     */
    private String preDate;
    /**
     * 发标对象（只用于发标后）
     */
    private String sendTarget;
    /**
     * 推荐费率',
     */
    private Double recommendedRate;
    /**
     * 咨询费率
     */
    private Double advisoryRate;
    /**
     * 系统运营费率
     */
    private Double optionFeeRate;
    /**
     * 暂缓放款状态 :0(未暂缓)  1(已暂缓) 2(已终止)
     */
    private String deferPayStatus;
    /**
     * 资产管理费费率
     */
    private Double assetmanageRate;
    /**
     * 非融资担保费率
     */
    private Double nonFinanceGRate;
    /**
     * 申请属性
     */
    private String loanProperties;

    /**
     * 消费类型
     */
    private String loanPurpose;
    /**
     * 安心投保费率
     */
    private Double premiumRate;
    /**
     * 营业执照统一社会信用代码
     */
    private String creditCode;
    /**
     * 一次性保费费率
     */
    private Double oncePremiumRate;
    /**
     * 普惠官网签章批次号
     */
    private String batchNo;
    /**
     * 普惠官网签章状态 "01"：签章中;"02"：签章完成;"03":已导出
     */
    private String signStatus;
    /**
     * 创建人
     */
    private String createUser;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     * 更新人
     */
    private String updateUser;
    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 录入日期
     */
    private String inputDate;

    /**
     * 放款日期
     */
    private String putOutDate;

    /**
     * 客户经理编码，临时
     */
    private String sellerNo;

    /**
     * 申请属性信息
     */
    private String applyProperties;

    /**
     * 临时 申请流水号
     */
    private String applySerialNo;

    /**
     * 临时 关联借据号
     */
    private String relevanceLoanNo;

    /**
     * 临时 高分客户标志
     */
    boolean highMarksCustomer = false;
    /**
     * 实际放款金额（平台）
     *
     */
    private Double actualLoanAmount;

    /**
     * 官网合同生成状态
     */
    private String yylengingContractStatus;

    public LoanApply(){}

    public LoanApply(String serialNo,String loanCardId,String loanBank,String loanBankBranch,String repayCardId,String repayBank,String repayBankBranch){
        this.serialNo=serialNo;
        this.repayBank=repayBank;
        this.repayCardId=repayCardId;
        this.repayBankBranch=repayBankBranch;
        this.loanBank=loanBank;
        this.loanCardId=loanCardId;
        this.loanBankBranch=loanBankBranch;
        this.updateTime=new Date();
    }


}
