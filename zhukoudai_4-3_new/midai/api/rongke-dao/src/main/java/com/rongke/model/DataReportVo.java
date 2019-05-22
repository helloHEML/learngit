package com.rongke.model;/** * Created by bin on 2018/3/12. *//** * 统计报表Vo视图类 */public class DataReportVo {    /**     * 日期     */    private String gmtTime;    /**     * 日评估价格     */    private String price;    /**     * 日订单量、审核总数量     */    private String number;    /**     * 审核拒绝数量     */    private String numberNo;    /**     * 日评估费     */    private String assessMoney;    /**     * 实际放款金额     * @return     */    private String finalMoney;    /**     * 实际放款金额(总额)     * @return     */    private String allFinalMoney;    /**     * 逾期收款金额     * @return     */    private String repayRecordMoneyOverDue;    /**     * 收款总金额     * @return     */    private String repayRecordMoney;    /**     * 收款金额(总额)     * @return     */    private String allRepayRecordMoney;    /**     * 管理员姓名（放款员，审核员。。。）     * @return     */    private String adminName;    /**     * 管理员登录名称     * @return     */    private String adminLoginName;    /**     * 还款总单数     * @return     */    private String repayRecordNumber;    public String getRepayRecordNumber() {        return repayRecordNumber;    }    public void setRepayRecordNumber(String repayRecordNumber) {        this.repayRecordNumber = repayRecordNumber;    }    public String getGmtTime() {        return gmtTime;    }    public void setGmtTime(String gmtTime) {        this.gmtTime = gmtTime;    }    public String getPrice() {        return price;    }    public void setPrice(String price) {        this.price = price;    }    public String getNumber() {        return number;    }    public void setNumber(String number) {        this.number = number;    }    public String getAssessMoney() {        return assessMoney;    }    public void setAssessMoney(String assessMoney) {        this.assessMoney = assessMoney;    }    public String getFinalMoney() {        return finalMoney;    }    public void setFinalMoney(String finalMoney) {        this.finalMoney = finalMoney;    }    public String getRepayRecordMoney() {        return repayRecordMoney;    }    public void setRepayRecordMoney(String repayRecordMoney) {        this.repayRecordMoney = repayRecordMoney;    }    public String getAllFinalMoney() {        return allFinalMoney;    }    public void setAllFinalMoney(String allFinalMoney) {        this.allFinalMoney = allFinalMoney;    }    public String getAllRepayRecordMoney() {        return allRepayRecordMoney;    }    public void setAllRepayRecordMoney(String allRepayRecordMoney) {        this.allRepayRecordMoney = allRepayRecordMoney;    }    public String getAdminName() {        return adminName;    }    public void setAdminName(String adminName) {        this.adminName = adminName;    }    public String getAdminLoginName() {        return adminLoginName;    }    public void setAdminLoginName(String adminLoginName) {        this.adminLoginName = adminLoginName;    }    public String getNumberNo() {        return numberNo;    }    public void setNumberNo(String numberNo) {        this.numberNo = numberNo;    }    public String getRepayRecordMoneyOverDue() {        return repayRecordMoneyOverDue;    }    public void setRepayRecordMoneyOverDue(String repayRecordMoneyOverDue) {        this.repayRecordMoneyOverDue = repayRecordMoneyOverDue;    }}