package com.dalaiye.luhang.bean;

/**
 * @author AnYu
 * @date 2019/4/25
 * @function 注释
 **/
public class RectificationBean {


    /**
     * inspectDeptName : string,受检部门
     * inspectObj : string,受检对象
     * checkUserId : string,检查人
     * checkDate : string,检查日期
     * checkTypeName : string,检查类型
     * dangerPosition : string,隐患部位
     * dangerDeptName : string,隐患部门
     * dangerTypeName : string,隐患类型
     * dangerLevelName : string,隐患等级
     * riskResourceName : string,风险源
     * maybeResultName : string,可能后果
     * dangerUrl : string,图片地址，多个按,分割
     * remark : string,备注
     * dochangeTypeName : string,整改类型
     * endDate : string,整改截止时间
     * checkAcceptDeptName : string,验收部门
     * checkAcceptUserName : string,验收责任人
     */
    private String id;
    private String inspectDeptName;
    private String inspectObj;
    private String checkUserId;
    private String checkDate;
    private String checkTypeName;
    private String dangerName;
    private String dangerPosition;
    private String dangerDeptName;
    private String dangerTypeName;
    private String dangerLevelName;
    private String riskResourceName;
    private String maybeResultName;
    private String dangerUrl;
    private String remark;
    private String dochangeTypeName;
    private String endDate;
    private String checkAcceptDeptName;
    private String checkAcceptUserName;
    /**
     * 整改措施
     */
    private String dochangeStep;

    public String getDangerName() {
        return dangerName;
    }

    public void setDangerName(String dangerName) {
        this.dangerName = dangerName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDochangeStep() {
        return dochangeStep;
    }

    public void setDochangeStep(String dochangeStep) {
        this.dochangeStep = dochangeStep;
    }

    public String getDochangeCapital() {
        return dochangeCapital;
    }

    public void setDochangeCapital(String dochangeCapital) {
        this.dochangeCapital = dochangeCapital;
    }

    /**
     * 整改资金
     */
    private String dochangeCapital;

    public String getInspectDeptName() {
        return inspectDeptName;
    }

    public void setInspectDeptName(String inspectDeptName) {
        this.inspectDeptName = inspectDeptName;
    }

    public String getInspectObj() {
        return inspectObj;
    }

    public void setInspectObj(String inspectObj) {
        this.inspectObj = inspectObj;
    }

    public String getCheckUserId() {
        return checkUserId;
    }

    public void setCheckUserId(String checkUserId) {
        this.checkUserId = checkUserId;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getCheckTypeName() {
        return checkTypeName;
    }

    public void setCheckTypeName(String checkTypeName) {
        this.checkTypeName = checkTypeName;
    }

    public String getDangerPosition() {
        return dangerPosition;
    }

    public void setDangerPosition(String dangerPosition) {
        this.dangerPosition = dangerPosition;
    }

    public String getDangerDeptName() {
        return dangerDeptName;
    }

    public void setDangerDeptName(String dangerDeptName) {
        this.dangerDeptName = dangerDeptName;
    }

    public String getDangerTypeName() {
        return dangerTypeName;
    }

    public void setDangerTypeName(String dangerTypeName) {
        this.dangerTypeName = dangerTypeName;
    }

    public String getDangerLevelName() {
        return dangerLevelName;
    }

    public void setDangerLevelName(String dangerLevelName) {
        this.dangerLevelName = dangerLevelName;
    }

    public String getRiskResourceName() {
        return riskResourceName;
    }

    public void setRiskResourceName(String riskResourceName) {
        this.riskResourceName = riskResourceName;
    }

    public String getMaybeResultName() {
        return maybeResultName;
    }

    public void setMaybeResultName(String maybeResultName) {
        this.maybeResultName = maybeResultName;
    }

    public String getDangerUrl() {
        return dangerUrl;
    }

    public void setDangerUrl(String dangerUrl) {
        this.dangerUrl = dangerUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDochangeTypeName() {
        return dochangeTypeName;
    }

    public void setDochangeTypeName(String dochangeTypeName) {
        this.dochangeTypeName = dochangeTypeName;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCheckAcceptDeptName() {
        return checkAcceptDeptName;
    }

    public void setCheckAcceptDeptName(String checkAcceptDeptName) {
        this.checkAcceptDeptName = checkAcceptDeptName;
    }

    public String getCheckAcceptUserName() {
        return checkAcceptUserName;
    }

    public void setCheckAcceptUserName(String checkAcceptUserName) {
        this.checkAcceptUserName = checkAcceptUserName;
    }
}
