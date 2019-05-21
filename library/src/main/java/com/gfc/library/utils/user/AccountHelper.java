package com.gfc.library.utils.user;

import com.blankj.utilcode.util.SPUtils;
import com.gfc.library.event.EventKeys;
import com.gfc.library.event.EventMessage;

import org.greenrobot.eventbus.EventBus;

/**
 * @author admin
 * @date 2019/3/4
 * @function 用户相关操作
 **/
public class AccountHelper {
    private static final String USER_INFO = "USER_INFO";
    private static final String USER_ID = "USER_ID";
    private static final String USER_AUTH = "USER_AUTH";
    private static final String USER_LOGIN = "USER_LOGIN";
    private static final String PHONE_CID = "phone_cid";
    private static final String USER_BUSINESS_ID = "USER_BUSINESS_ID";

    private AccountHelper() {
    }

    private static class Holder {
        private static final AccountHelper INSTANCES = new AccountHelper();
    }

    public static AccountHelper getInstance() {
        return Holder.INSTANCES;
    }

    private SPUtils getSPUtils() {
        return SPUtils.getInstance(USER_INFO);
    }

    public String getUserId() {
        return getSPUtils().getString(USER_ID, "");
    }

    public String getCId() {
        return getSPUtils().getString(PHONE_CID, "");
    }

    public void setCID(String cid) {
        getSPUtils().put(PHONE_CID, cid);
    }

    public boolean getUserAuth() {
        return getSPUtils().getBoolean(USER_AUTH, false);
    }

    public boolean isLogin() {
        return getSPUtils().getBoolean(USER_LOGIN, false);
    }

    public String getBusinessId() {
        return getSPUtils().getString(USER_BUSINESS_ID, "");
    }

    public void setUserId(String userId, boolean isAuth, String businessId) {
        EventBus.getDefault().post(new EventMessage(EventKeys.LOGIN_SUCCESS, null));
        getSPUtils().put(USER_ID, userId);
        getSPUtils().put(USER_AUTH, isAuth);
        getSPUtils().put(USER_LOGIN, true);
        getSPUtils().put(USER_BUSINESS_ID, businessId);
    }

    public void clearUser() {
        getSPUtils().put(USER_ID, "");
        getSPUtils().put(USER_LOGIN, false);
        getSPUtils().put(USER_AUTH, false);
        getSPUtils().put(PHONE_CID,"");
        getSPUtils().put(USER_BUSINESS_ID, "");
    }

}
