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
public class FixedOptionAdapter extends TagAdapter<CheckBean> {

    private List<CheckBean> mOptionBeans;

    public FixedOptionAdapter(List<CheckBean> datas) {
        super(datas);
        mOptionBeans = datas;
    }

    public List<CheckBean> getOptionBeans() {
        return mOptionBeans;
    }

    @Override
    public View getView(FlowLayout parent, int position, CheckBean checkOptionBean) {

        AppCompatTextView textView = null;
        boolean isSelected = checkOptionBean.getStatus() == Constant.CHECK_OPTION_SELECTED;

        if (isSelected) {
            textView = (AppCompatTextView) LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.adapter_fixed_option_sel_item, parent, false);
        } else {
            textView = (AppCompatTextView) LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.adapter_fixed_option_def_item, parent, false);
        }
        
        textView.setText(checkOptionBean.getDictName());
        return textView;
    }

}
