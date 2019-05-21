package com.dalaiye.luhang.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.bean.ExamCollectionBean;
import com.dalaiye.luhang.bean.ExamDetailsBean;
import com.dalaiye.luhang.ui.user.QuestionDetailsActivity;

import java.util.List;

/**
 * @author AnYu
 * @date 2019/4/20
 * @function 考题收藏的adapter
 **/
public class ExamCollectionAdapter extends BaseQuickAdapter<ExamCollectionBean.RowsBean,BaseViewHolder> 
implements BaseQuickAdapter.OnItemClickListener{
    public ExamCollectionAdapter(  @Nullable List<ExamCollectionBean.RowsBean> data) {
        super(R.layout.adapter_user_exam_collection_item, data);
        setOnItemClickListener(this);
    }

    @Override
    protected void convert(BaseViewHolder helper, ExamCollectionBean.RowsBean item) {
        helper.setText(R.id.tv_question_name,item.getSubject());
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(mContext,QuestionDetailsActivity.class);
        intent.putExtra(QuestionDetailsActivity.QUESTION_BEAN,getData().get(position));
        mContext.startActivity(intent);
    }
}
