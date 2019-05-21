package com.dalaiye.luhang.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author AnYu
 * @date 2019/4/12
 * @function 注释
 **/
public class TipsBean implements Serializable {


    /**
     * total : string,integer,总行数
     * pageNumber : string,integer,第几页
     * rows : [{"id":"string,视频的id","courseUrl":"string,课程的图片","courseName":"string,课程的名称","introduction":"string,课程的简介","videoUrl":"string,视频的地址","fileUrl":"string,文件地址","userCourseStatus":"string,学习状态0未学习1学习中2已完成 ","progress":"string,已观看秒数","isCollect":"string,是否收藏1是0否"}]
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

    public static class RowsBean implements Serializable{
        /**
         * id : string,视频的id
         * courseUrl : string,课程的图片
         * courseName : string,课程的名称
         * introduction : string,课程的简介
         * videoUrl : string,视频的地址
         * fileUrl : string,文件地址
         * userCourseStatus : string,学习状态0未学习1学习中2已完成
         * progress : string,已观看秒数
         * isCollect : string,是否收藏1是0否
         * webUrl:String,简介地址
         */

        private String id;
        private String courseUrl;
        private String courseName;
        private String introduction;
        private String videoUrl;
        private String fileUrl;
        private String userCourseStatus;
        private String progress;
        private String isCollect;
        private String webUrl;

        public String getWebUrl() {
            return webUrl;
        }

        public void setWebUrl(String webUrl) {
            this.webUrl = webUrl;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCourseUrl() {
            return courseUrl;
        }

        public void setCourseUrl(String courseUrl) {
            this.courseUrl = courseUrl;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public String getFileUrl() {
            return fileUrl;
        }

        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
        }

        public String getUserCourseStatus() {
            return userCourseStatus;
        }

        public void setUserCourseStatus(String userCourseStatus) {
            this.userCourseStatus = userCourseStatus;
        }

        public String getProgress() {
            return progress;
        }

        public void setProgress(String progress) {
            this.progress = progress;
        }

        public String getIsCollect() {
            return isCollect;
        }

        public void setIsCollect(String isCollect) {
            this.isCollect = isCollect;
        }
    }
}
