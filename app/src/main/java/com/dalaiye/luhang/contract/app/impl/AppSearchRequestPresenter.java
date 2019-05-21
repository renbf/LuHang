package com.dalaiye.luhang.contract.app.impl;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.blankj.utilcode.util.SPUtils;
import com.dalaiye.luhang.contract.app.AppSearchRequestContract;
import com.gfc.library.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @date 2019/4/12
 * @function 注释
 **/
public class AppSearchRequestPresenter extends BasePresenter<AppSearchRequestContract.IAppSearchRequestView>
        implements AppSearchRequestContract.IAppSearchRequestPresenter {
    @Override
    public void querySearchHistory(String userId) {
        String history = SPUtils.getInstance().getString("SEARCH_HISTORY");
        if (TextUtils.isEmpty(history)) {
            SPUtils.getInstance().put("SEARCH_HISTORY", JSON.toJSONString(new ArrayList<String>()));
            history = SPUtils.getInstance().getString("SEARCH_HISTORY");
        }
        List<String> list = JSON.parseObject(history, new TypeReference<List<String>>() {
        });
        if (list == null) {
            list = new ArrayList<>();
        }
        getView().setSearchHistory(list);
    }

    @Override
    public void addSearchHistory(String content) {
        String history = SPUtils.getInstance().getString("SEARCH_HISTORY");
        List<String> list = JSON.parseObject(history, new TypeReference<List<String>>() {
        });
        if (list == null) {
            list = new ArrayList<>();
        }
        if (list.contains(content)) {
            list.remove(content);
        }
        list.add(content);
        SPUtils.getInstance().put("SEARCH_HISTORY", JSON.toJSONString(list));
    }

    @Override
    public void deleteHistory(String userId) {
        SPUtils.getInstance().put("SEARCH_HISTORY", JSON.toJSONString(new ArrayList<String>()));
        getView().historyDeleteSuccess();
    }
}
