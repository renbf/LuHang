package com.dalaiye.luhang.adapter;

import android.support.annotation.Nullable;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.bean.CheckProjectBean;

import java.util.List;

/**
 * @author AnYu
 * @date 2019/4/17
 * @function 检查计划表的adapter
 **/
public class CheckPlanFormAdapter extends BaseQuickAdapter<CheckProjectBean,BaseViewHolder> {

    public CheckPlanFormAdapter(int layoutResId, @Nullable List<CheckProjectBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CheckProjectBean item) {
        helper.setText(R.id.tv_check_project_name,item.getCheckProjectName());
        if("0".equals(item.getIsOk())) {
            helper.setChecked(R.id.rb_no_pass,true);
        }else {
            helper.setChecked(R.id.rb_pass_through,true);
        }
        RadioButton rbPassThrough = helper.getView(R.id.rb_pass_through);
        rbPassThrough.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    item.setIsOk("1");
                }else {
                    item.setIsOk("0");
                }
            }
        });
    }
}
