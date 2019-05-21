package com.dalaiye.luhang.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.bean.ExamDetailsBean;
import com.dalaiye.luhang.constant.Constant;
import com.gfc.library.widget.recycler.RecyclerViewHolder;

import java.util.List;

/**
 * @author admin
 * @date 2019/4/28
 * @function 注释
 **/
public class QuestionGridAdapter extends BaseQuickAdapter<ExamDetailsBean.QuestionsBean, RecyclerViewHolder> {

    public QuestionGridAdapter(@Nullable List<ExamDetailsBean.QuestionsBean> data) {
        super(R.layout.adapter_dialog_question_item, data);
    }

    @Override
    protected void convert(RecyclerViewHolder helper, ExamDetailsBean.QuestionsBean item) {
        AppCompatTextView textView = helper.getView(R.id.tv_question_state);

        textView.setText(String.valueOf(helper.getAdapterPosition() + 1));

        if (item.getIsTrue() != null && item.getIsTrue() == Constant.EXAM_QUESTION_NO) {
            textView.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.shape_question_state_wrong));
        } else if (item.getIsTrue() != null && item.getIsTrue() == Constant.EXAM_QUESTION_YES) {
            textView.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.shape_question_state_answer));
        } else if (item.getIsTrue() == null) {
            textView.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.shape_question_state_not));
        }

    }
}
