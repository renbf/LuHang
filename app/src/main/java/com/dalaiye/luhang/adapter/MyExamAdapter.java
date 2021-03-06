package com.dalaiye.luhang.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.bean.ExamBean;

import java.util.List;

/**
 * @author AnYu
 * @date 2019/4/15
 * @function 个人中心我的考试adapter
 **/
public class MyExamAdapter extends BaseQuickAdapter<ExamBean.RowsBean, BaseViewHolder> {
    public MyExamAdapter(@Nullable List<ExamBean.RowsBean> data) {
        super(R.layout.adapter_user_exam_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ExamBean.RowsBean item) {

        AppCompatTextView tvExamScore = helper.getView(R.id.tv_exam_score);
        AppCompatTextView tvExamTitle = helper.getView(R.id.tv_exam_title);
        AppCompatTextView tvExamTime = helper.getView(R.id.tv_exam_time);

        tvExamScore.setText(item.getPaperScore() + "分");
        tvExamTitle.setText(item.getPaperName());
        String beginDate = item.getBeginDate().substring(0, 10);
        String endDate = item.getEndDate().substring(0, 10);
        SpannableString ss = new SpannableString("考试时间：" + beginDate + " - " + endDate);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.color_text_dark));
        ss.setSpan(colorSpan, 0, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tvExamTime.setText(ss);

    }
}
