package com.dalaiye.luhang.bean;

import java.util.List;

/**
 * @author AnYu
 * @date 2019/4/23
 * @function 注释
 **/
public class RankingBean {


    /**
     * userCourseVo : {"rank":"string,排名","classHourSum":"string,课时"}
     * userCourseVos : [{"userName":"string,名字","deptName":"string,部门名称","classHour":"string,课时"}]
     */

    private UserCourseVoBean userCourseVo;
    private List<UserCourseVosBean> userCourseVos;

    public UserCourseVoBean getUserCourseVo() {
        return userCourseVo;
    }

    public void setUserCourseVo(UserCourseVoBean userCourseVo) {
        this.userCourseVo = userCourseVo;
    }

    public List<UserCourseVosBean> getUserCourseVos() {
        return userCourseVos;
    }

    public void setUserCourseVos(List<UserCourseVosBean> userCourseVos) {
        this.userCourseVos = userCourseVos;
    }

    public static class UserCourseVoBean {
        /**
         * rank : string,排名
         * progress : string,课时
         */

        private String rank;
        private String classHourSum;

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getClassHourSum() {
            return classHourSum;
        }

        public void setClassHourSum(String classHourSum) {
            this.classHourSum = classHourSum;
        }
    }

    public static class UserCourseVosBean {
        /**
         * userName : string,名字
         * deptName : string,部门名称
         * progress : string,课时
         */

        private String userName;
        private String deptName;
        private String classHour;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public String getClassHour() {
            return classHour;
        }

        public void setClassHour(String classHour) {
            this.classHour = classHour;
        }
    }
}
