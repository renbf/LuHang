package com.dalaiye.luhang.adapter;

import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;

import com.dalaiye.luhang.R;
import com.dalaiye.luhang.bean.log.CheckBean;
import com.dalaiye.luhang.constant.Constant;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

/**
 * @author admin
 * @date 2019/4/16
 * @function 选项
 **/
public class CheckOptionAdapter extends TagAdapter<CheckBean> {

    private List<CheckBean> mOptionBeans;

    public CheckOptionAdapter(List<CheckBean> datas) {
        super(datas);
        mOptionBeans = datas;
    }

    public List<CheckBean> getOptionBeans() {
        return mOptionBeans;
    }

    @Override
    public View getView(FlowLayout parent, int position, CheckBean checkOptionBean) {
        AppCompatTextView textView = (AppCompatTextView) LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.adapter_check_option_item, parent, false);
        textView.setText(checkOptionBean.getDictName());
        textView.setSelected(checkOptionBean.getStatus() == Constant.CHECK_OPTION_SELECTED);
        return textView;
    }

    @Override
    public void onSelected(int position, View view) {
        super.onSelected(position, view);
        getItem(position).setStatus(Constant.CHECK_OPTION_SELECTED);
    }

    @Override
    public void unSelected(int position, View view) {
        super.unSelected(position, view);
        getItem(position).setStatus(Constant.CHECK_OPTION_UNSELECTED);

    }
}
