package com.dalaiye.luhang.bean;

/**
 * @author AnYu
 * @date 2019/4/26
 * @function 注释
 **/
public class AcceptanceBean {

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
     * riskResource : string,风险源
     * maybeResultName : string,可能后果
     * dangerUrl : string,图片地址，多个按,分割
     * remark : string,备注
     * dochangeStep : string,整改措施
     * dochangeCapital : string,整改资金
     * dochangePicture : string,整改照片 多个,分隔
     */

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
    private String riskResource;
    private String maybeResultName;
    private String dangerUrl;
    private String remark;
    private String dochangeStep;
    private String dochangeCapital;
    private String dochangePicture;

    public String getDangerName() {
        return dangerName;
    }

    public void setDangerName(String dangerName) {
        this.dangerName = dangerName;
    }

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

    public String getRiskResource() {
        return riskResource;
    }

    public void setRiskResource(String riskResource) {
        this.riskResource = riskResource;
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

    public String getDochangePicture() {
        return dochangePicture;
    }

    public void setDochangePicture(String dochangePicture) {
        this.dochangePicture = dochangePicture;
    }
}
