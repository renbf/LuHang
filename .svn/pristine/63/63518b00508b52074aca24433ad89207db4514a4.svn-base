package com.dalaiye.luhang.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author AnYu
 * @date 2019/4/12
 * @function 注释
 **/
public class CheckPlanBean {


    /**
     * total : string,总行数
     * pageNumber : string,string,integer,第几页
     * rows : [{"id":"string,检查计划的id","titleId":"string,检查标题id","checkTitle":"string,检查标题","checkName":"string,检查项目名称","checkTime":"string,检查时间","checkStatus":"string,检查状态 0未检出 1已完成 2上报了隐患","checkYear":"string,年度","checkTypeName":"string,检查类型","checkProjects":[{"checkTeam":"string,检查项目id","checkTeamName":"string,检查项目名称"}]}]
     */

    private String total;
    private String pageNumber;
    private List<RowsBean> rows;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean implements Serializable {
        /**
         * id : string,检查计划的id
         * titleId : string,检查标题id
         * checkTitle : string,检查标题
         * checkTime : string,检查时间
         * checkStatus : string,检查状态 0未检出 1已完成 2上报了隐患
         * checkYear : string,年度
         * checkTypeName : string,检查类型
         * checkProjects : [{"checkTeam":"string,检查项目id","checkTeamName":"string,检查项目名称"}]
         */

        private String id;
        private String titleId;
        private String checkTitle;
        private String checkTime;
        private String checkStatus;
        private String checkYear;
        private String checkTypeName;
        private String dangerId;
        private List<CheckProjectsBean> checkProjects;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitleId() {
            return titleId;
        }

        public void setTitleId(String titleId) {
            this.titleId = titleId;
        }

        public String getCheckTitle() {
            return checkTitle;
        }

        public void setCheckTitle(String checkTitle) {
            this.checkTitle = checkTitle;
        }

        public String getCheckTime() {
            return checkTime;
        }

        public void setCheckTime(String checkTime) {
            this.checkTime = checkTime;
        }

        public String getCheckStatus() {
            return checkStatus;
        }

        public void setCheckStatus(String checkStatus) {
            this.checkStatus = checkStatus;
        }

        public String getCheckYear() {
            return checkYear;
        }

        public String getDangerId() {
            return dangerId;
        }

        public void setDangerId(String dangerId) {
            this.dangerId = dangerId;
        }

        public void setCheckYear(String checkYear) {
            this.checkYear = checkYear;
        }

        public String getCheckTypeName() {
            return checkTypeName;
        }

        public void setCheckTypeName(String checkTypeName) {
            this.checkTypeName = checkTypeName;
        }

        public List<CheckProjectsBean> getCheckProjects() {
            return checkProjects;
        }

        public void setCheckProjects(List<CheckProjectsBean> checkProjects) {
            this.checkProjects = checkProjects;
        }

        public static class CheckProjectsBean implements Serializable{
            /**
             * checkTeam : string,检查项目id
             * checkTeamName : string,检查项目名称
             */

            private String checkTeam;
            private String checkTeamName;

            public String getCheckTeam() {
                return checkTeam;
            }

            public void setCheckTeam(String checkTeam) {
                this.checkTeam = checkTeam;
            }

            public String getCheckTeamName() {
                return checkTeamName;
            }

            public void setCheckTeamName(String checkTeamName) {
                this.checkTeamName = checkTeamName;
            }
        }
    }
}
