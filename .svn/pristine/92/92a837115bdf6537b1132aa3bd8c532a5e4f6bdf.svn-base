package com.dalaiye.luhang.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.bean.MessageBean;
import com.dalaiye.luhang.constant.Constant;
import com.gfc.library.widget.recycler.RecyclerViewHolder;

import java.util.List;

/**
 * @author admin
 * @date 2019/4/12
 * @function 注释
 **/
public class MessageAdapter extends BaseQuickAdapter<MessageBean.RowsBean, RecyclerViewHolder> {


    public MessageAdapter(@Nullable List<MessageBean.RowsBean> data) {
        super(R.layout.adapter_user_message_item, data);
    }

    @Override
    protected void convert(RecyclerViewHolder helper, MessageBean.RowsBean item) {
        AppCompatImageView ivMessageType = helper.getView(R.id.iv_message_type);

        helper.setText(R.id.tv_message_msg,item.getTitle());
        helper.setText(R.id.tv_msg_time,item.getCreateDate().substring(0,10));
        if("0".equals(item.getIsRead())) {
            helper.setVisible(R.id.view_msg,true);
        }else {
            helper.setVisible(R.id.view_msg,false);
        }
        switch (Integer.parseInt(item.getMessageType())) {
            case Constant.MESSAGE_STATE_COURSE:
                helper.setText(R.id.tv_message_type,"课程安排");
                ivMessageType.setImageDrawable(ContextCompat.getDrawable(mContext,R.mipmap.ic_message_course));
                break;
            case Constant.MESSAGE_STATE_EXAM:
                helper.setText(R.id.tv_message_type,"考试安排");
                ivMessageType.setImageDrawable(ContextCompat.getDrawable(mContext,R.mipmap.ic_message_exam));
                break;
            case Constant.MESSAGE_STATE_CHECK:
                helper.setText(R.id.tv_message_type,"检查计划");
                ivMessageType.setImageDrawable(ContextCompat.getDrawable(mContext,R.mipmap.ic_message_check));
                break;
            case Constant.MESSAGE_STATE_RECTIFICATION:
                helper.setText(R.id.tv_message_type,"隐患整改");
                ivMessageType.setImageDrawable(ContextCompat.getDrawable(mContext,R.mipmap.ic_message_rectification));
                break;
            case Constant.MESSAGE_STATE_ACCEPTANCE:
                helper.setText(R.id.tv_message_type,"隐患验收");
                ivMessageType.setImageDrawable(ContextCompat.getDrawable(mContext,R.mipmap.ic_message_acceptance));
                break;
            default:
                break;
        }
    }
}
