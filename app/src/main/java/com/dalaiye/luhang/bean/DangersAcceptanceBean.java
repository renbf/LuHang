package com.dalaiye.luhang.bean;

import java.util.List;

/**
 * @author AnYu
 * @date 2019/4/12
 * @function 注释
 **/
public class DangersAcceptanceBean {

    /**
     * total : string
     * pageNumber : string
     * rows : [{"id":"string,验收的id","dochangeDeptName":"string,整改部门","dochangeTypeName":"string,整改类型","dochangeUserName":"string,整改人姓名","endDate":"string,截止日期"}]
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

    public static class RowsBean {
        /**
         * id : string,验收的id
         * dochangeDeptName : string,整改部门
         * dochangeTypeName : string,整改类型
         * dochangeUserName : string,整改人姓名
         * endDate : string,截止日期
         */

        private String id;
        private String dochangeDeptName;
        private String dochangeTypeName;
        private String dochangeUserName;
        private String endDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDochangeDeptName() {
            return dochangeDeptName;
        }

        public void setDochangeDeptName(String dochangeDeptName) {
            this.dochangeDeptName = dochangeDeptName;
        }

        public String getDochangeTypeName() {
            return dochangeTypeName;
        }

        public void setDochangeTypeName(String dochangeTypeName) {
            this.dochangeTypeName = dochangeTypeName;
        }

        public String getDochangeUserName() {
            return dochangeUserName;
        }

        public void setDochangeUserName(String dochangeUserName) {
            this.dochangeUserName = dochangeUserName;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }
    }
}
