package com.dalaiye.luhang.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author AnYu
 * @date 2019/4/11
 * @function 注释
 **/
public class CourseBean implements Serializable{


    /**
     * total : string,integer,总行数
     * pageNumber : string,integer,第几页
     * rows : [{"id":"string,string,视频的id或文件id","courseUrl":"string,课程的图片","trainingType":"string,用来判断是视频还是文件0视频1文件2有视频也有文件","courseName":"string,课程的名称","introduction":"string,课程的简介","videoUrl":"string,视频的地址","fileUrl":"string,文件的地址","isCollect":"string,是否收藏0否1是","progress":"long,进度条秒数","watchTimes":"long,文件观看时长","userCourse":"string,学习状态0未学习1学习中2已完成","startDate":"string,开始时间","percentage":"integer,观看百分比","totalTimes":"long,视频总时长"}]
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

    public static class RowsBean implements Serializable,MultiItemEntity {
        /**
         * id : string,string,视频的id或文件id
         * courseUrl : string,课程的图片
         * trainingType : string,用来判断是视频还是文件0视频1文件2有视频也有文件
         * courseName : string,课程的名称
         * introduction : string,课程的简介
         * videoUrl : string,视频的地址
         * fileUrl : string,文件的地址
         * isCollect : string,是否收藏0否1是
         * progress : long,进度条秒数
         * watchTimes : long,文件观看时长
         * userCourse : string,学习状态0未学习1学习中2已完成
         * startDate : string,开始时间
         * percentage : integer,观看百分比
         * totalTimes : long,视频总时长
         * userCourseId: 用户课程id
         */

        private String id;
        private String courseUrl;
        private String trainingType;
        private String courseName;
        private String introduction;
        private String videoUrl;
        private String fileUrl;
        private String isCollect;
        private String progress;
        private String watchTimes;
        private String userCourse;
        private String startDate;
        private String percentage;
        private String totalTimes;
        private String userCourseId;
        private String webUrl;

        public String getWebUrl() {
            return webUrl;
        }

        public void setWebUrl(String webUrl) {
            this.webUrl = webUrl;
        }

        public String getUserCourseId() {
            return userCourseId;
        }

        public void setUserCourseId(String userCourseId) {
            this.userCourseId = userCourseId;
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

        public String getTrainingType() {
            return trainingType;
        }

        public void setTrainingType(String trainingType) {
            this.trainingType = trainingType;
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

        public String getIsCollect() {
            return isCollect;
        }

        public void setIsCollect(String isCollect) {
            this.isCollect = isCollect;
        }

        public String getProgress() {
            return progress;
        }

        public void setProgress(String progress) {
            this.progress = progress;
        }

        public String getWatchTimes() {
            return watchTimes;
        }

        public void setWatchTimes(String watchTimes) {
            this.watchTimes = watchTimes;
        }

        public String getUserCourse() {
            return userCourse;
        }

        public void setUserCourse(String userCourse) {
            this.userCourse = userCourse;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getPercentage() {
            return percentage;
        }

        public void setPercentage(String percentage) {
            this.percentage = percentage;
        }

        public String getTotalTimes() {
            return totalTimes;
        }

        public void setTotalTimes(String totalTimes) {
            this.totalTimes = totalTimes;
        }

        @Override
        public int getItemType() {
            return Integer.valueOf(trainingType);
        }
    }
}
