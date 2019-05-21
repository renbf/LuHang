package com.dalaiye.luhang.bean;

import java.util.List;

/**
 * @author AnYu
 * @date 2019/4/12
 * @function 注释
 **/
public class DangersRectificationBean {


    /**
     * total : string,string,总行数
     * pageNumber : string,,integer,第几页
     * rows : [{"id":"string,隐患id","dangerPosition":"string,整改的部位","dochangeTypeName":"string,整改的类型","dangerLevelName":"string,隐患等级","endDate":"string,截止日期","status":"string,整改状态 1待整改2已整改"}]
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
         * id : string,隐患id
         * dangerPosition : string,整改的部位
         * dochangeTypeName : string,整改的类型
         * dangerLevelName : string,隐患等级
         * endDate : string,截止日期
         * status : string,整改状态 1待整改2已整改
         */

        private String id;
        private String dangerPosition;
        private String dochangeTypeName;
        private String dangerLevelName;
        private String endDate;
        private String status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDangerPosition() {
            return dangerPosition;
        }

        public void setDangerPosition(String dangerPosition) {
            this.dangerPosition = dangerPosition;
        }

        public String getDochangeTypeName() {
            return dochangeTypeName;
        }

        public void setDochangeTypeName(String dochangeTypeName) {
            this.dochangeTypeName = dochangeTypeName;
        }

        public String getDangerLevelName() {
            return dangerLevelName;
        }

        public void setDangerLevelName(String dangerLevelName) {
            this.dangerLevelName = dangerLevelName;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
