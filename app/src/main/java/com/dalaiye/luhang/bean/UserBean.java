package com.dalaiye.luhang.bean;

import java.io.Serializable;

/**
 * @author admin
 * @date 2019/4/12
 * @function 用户信息
 **/
public class UserBean implements Serializable {


    /**
     * avatar : string,用户头像
     * sexName : string,性别
     * phonenumber : string,手机号
     * deptName : string,部门
     * postName : string,岗位
     * userName : string,用户姓名
     * authUrl : 用户认证照片
     */

    private String avatar;
    private String sexName;
    private String phonenumber;
    private String deptName;
    private String postName;
    private String userName;
    private String authUrl;

    public String getAuthUrl() {
        return authUrl;
    }

    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
