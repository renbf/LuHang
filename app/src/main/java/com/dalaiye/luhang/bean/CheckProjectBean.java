package com.dalaiye.luhang.bean;

/**
 * @author AnYu
 * @date 2019/4/23
 * @function 注释
 **/
public class CheckProjectBean {

    /**
     * checkTeamId : string,检查项目id
     * checkProjectId : string,检查项id
     * checkProjectName : string,检查项名称
     * isOk : string,检查结果1通过0未通过
     * inspectPlanId : string,检查计划ID
     */

    private String checkTeamId;
    private String checkProjectId;
    private String checkProjectName;
    private String isOk="0";
    private String inspectPlanId;

    public String getCheckTeamId() {
        return checkTeamId;
    }

    public void setCheckTeamId(String checkTeamId) {
        this.checkTeamId = checkTeamId;
    }

    public String getCheckProjectId() {
        return checkProjectId;
    }

    public void setCheckProjectId(String checkProjectId) {
        this.checkProjectId = checkProjectId;
    }

    public String getCheckProjectName() {
        return checkProjectName;
    }

    public void setCheckProjectName(String checkProjectName) {
        this.checkProjectName = checkProjectName;
    }

    public String getIsOk() {
        return isOk;
    }

    public void setIsOk(String isOk) {
        this.isOk = isOk;
    }

    public String getInspectPlanId() {
        return inspectPlanId;
    }

    public void setInspectPlanId(String inspectPlanId) {
        this.inspectPlanId = inspectPlanId;
    }
}
