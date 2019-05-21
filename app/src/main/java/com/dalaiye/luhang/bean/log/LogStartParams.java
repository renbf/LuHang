package com.dalaiye.luhang.bean.log;

import java.util.List;

/**
 * @author admin
 * @date 2019/4/15
 * @function 行车前参数
 **/
public class LogStartParams { 

    
    public static class LogStartDriveParams{
        /** 检查事项 */
        private List<CheckBean> carCheckProjectList;
        /** 不符合项 */
        private String noAccord;
        /** 确认评论 */
        private String sureComment;
        /** 备注 */
        private String remark;
        /** 创建人id */
        private String addUserId;

        public LogStartDriveParams(String addUserId) {
            this.addUserId = addUserId;
        }

        public List<CheckBean> getCarCheckProjectList() {
            return carCheckProjectList;
        }

        public void setCarCheckProjectList(List<CheckBean> carCheckProjectList) {
            this.carCheckProjectList = carCheckProjectList;
        }

        public String getNoAccord() {
            return noAccord;
        }

        public void setNoAccord(String noAccord) {
            this.noAccord = noAccord;
        }

        public String getSureComment() {
            return sureComment;
        }

        public void setSureComment(String sureComment) {
            this.sureComment = sureComment;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getAddUserId() {
            return addUserId;
        }

        public void setAddUserId(String addUserId) {
            this.addUserId = addUserId;
        }
    }
    
    
    
    public static class LogStartBasisParams{
        /** 填报日期 */
        private String writeTime;
        /** 车牌号 */
        private String carNumber;
        /** 核载吨位 */
        private String idealTonnage;
        /** 实载吨位 */
        private String actualTonnage;
        /** 货物名称 */
        private String goodsName;
        /** 类项 */
        private String type;
        /** 启运地 */
        private String transporAddress;
        /** 目的地 */
        private String goalAddress;
        /** 启运日期 */
        private String transportDate;
        /** 到达日期 */
        private String arriveDate;
        /** 里程 */
        private String km;
        /** 车辆状态0行车前1行车中2行车后 */
        private String status;
        /** 驾驶员1 */
        private String firstDriverId;
        /** 驾驶员2 */
        private String secondDriverId;
        /** 押运员 */
        private String escortId;
        /** 气温 */
        private String temperature;
        /** 天气 */
        private String weather;
        /** 创建人id */
        private String addUserId;

        public LogStartBasisParams(String addUserId) {
            this.addUserId = addUserId;
        }

        public String getWriteTime() {
            return writeTime;
        }

        public void setWriteTime(String writeTime) {
            this.writeTime = writeTime;
        }

        public String getCarNumber() {
            return carNumber;
        }

        public void setCarNumber(String carNumber) {
            this.carNumber = carNumber;
        }

        public String getIdealTonnage() {
            return idealTonnage;
        }

        public void setIdealTonnage(String idealTonnage) {
            this.idealTonnage = idealTonnage;
        }

        public String getActualTonnage() {
            return actualTonnage;
        }

        public void setActualTonnage(String actualTonnage) {
            this.actualTonnage = actualTonnage;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTransporAddress() {
            return transporAddress;
        }

        public void setTransporAddress(String transporAddress) {
            this.transporAddress = transporAddress;
        }

        public String getGoalAddress() {
            return goalAddress;
        }

        public void setGoalAddress(String goalAddress) {
            this.goalAddress = goalAddress;
        }

        public String getTransportDate() {
            return transportDate;
        }

        public void setTransportDate(String transportDate) {
            this.transportDate = transportDate;
        }

        public String getArriveDate() {
            return arriveDate;
        }

        public void setArriveDate(String arriveDate) {
            this.arriveDate = arriveDate;
        }

        public String getKm() {
            return km;
        }

        public void setKm(String km) {
            this.km = km;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getFirstDriverId() {
            return firstDriverId;
        }

        public void setFirstDriverId(String firstDriverId) {
            this.firstDriverId = firstDriverId;
        }

        public String getSecondDriverId() {
            return secondDriverId;
        }

        public void setSecondDriverId(String secondDriverId) {
            this.secondDriverId = secondDriverId;
        }

        public String getEscortId() {
            return escortId;
        }

        public void setEscortId(String escortId) {
            this.escortId = escortId;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getAddUserId() {
            return addUserId;
        }

        public void setAddUserId(String addUserId) {
            this.addUserId = addUserId;
        }
    }  
}
