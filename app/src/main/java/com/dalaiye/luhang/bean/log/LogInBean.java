package com.dalaiye.luhang.bean.log;

import java.util.List;

/**
 * @author admin
 * @date 2019/4/18
 * @function 行车中数据
 **/
public class LogInBean {
    /** 行车日志ID */
    private String driverLogId;
    /** 填报日期 */
    private String writeTime;
    /** 检查事项 */
    private String carCheckProject;
    /** 换驾休息地点 */
    private String changeAddress;
    /** 停车时间 */
    private String stopCarTime;
    /** 休息地点 */
    private String restAddress;
    /** 发车时间 */
    private String startCarTime;
    /** 故障处理 */
    private String troubleShooting;
    /** 行车照片 */
    private String drivingPhoto;
    /** 签名照片 */
    private String autograph;
    /** 创建人id */
    private String addUserId;
    /** 检查事项 */
    private List<CheckBean> carCheckProjectList;
    /** 交接备注 */
    private String exchangeRemark;
    /** 交接时间 */
    private String updateDate;

    public String getDriverLogId() {
        return driverLogId;
    }

    public void setDriverLogId(String driverLogId) {
        this.driverLogId = driverLogId;
    }

    public String getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(String writeTime) {
        this.writeTime = writeTime;
    }

    public String getCarCheckProject() {
        return carCheckProject;
    }

    public void setCarCheckProject(String carCheckProject) {
        this.carCheckProject = carCheckProject;
    }

    public String getChangeAddress() {
        return changeAddress;
    }

    public void setChangeAddress(String changeAddress) {
        this.changeAddress = changeAddress;
    }

    public String getStopCarTime() {
        return stopCarTime;
    }

    public void setStopCarTime(String stopCarTime) {
        this.stopCarTime = stopCarTime;
    }

    public String getRestAddress() {
        return restAddress;
    }

    public void setRestAddress(String restAddress) {
        this.restAddress = restAddress;
    }

    public String getStartCarTime() {
        return startCarTime;
    }

    public void setStartCarTime(String startCarTime) {
        this.startCarTime = startCarTime;
    }

    public String getTroubleShooting() {
        return troubleShooting;
    }

    public void setTroubleShooting(String troubleShooting) {
        this.troubleShooting = troubleShooting;
    }

    public String getDrivingPhoto() {
        return drivingPhoto;
    }

    public void setDrivingPhoto(String drivingPhoto) {
        this.drivingPhoto = drivingPhoto;
    }

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph;
    }

    public String getAddUserId() {
        return addUserId;
    }

    public void setAddUserId(String addUserId) {
        this.addUserId = addUserId;
    }

    public List<CheckBean> getCarCheckProjectList() {
        return carCheckProjectList;
    }

    public void setCarCheckProjectList(List<CheckBean> carCheckProjectList) {
        this.carCheckProjectList = carCheckProjectList;
    }

    public String getExchangeRemark() {
        return exchangeRemark;
    }

    public void setExchangeRemark(String exchangeRemark) {
        this.exchangeRemark = exchangeRemark;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
