package com.dalaiye.luhang.ui.user;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;

import com.dalaiye.luhang.R;
import com.dalaiye.luhang.bean.ExamCollectionBean;
import com.dalaiye.luhang.bean.ExamDetailsBean;
import com.dalaiye.luhang.constant.Constant;
import com.dalaiye.luhang.contract.user.QuestionDetailsContract;
import com.dalaiye.luhang.contract.user.impl.QuestionDetailsPresenter;
import com.gfc.library.base.BaseActivity;
import com.gfc.library.mvp.BaseMvpActivity;
import com.gfc.library.utils.user.AccountHelper;
import com.gfc.library.widget.recycler.RecyclerViewHolder;

/**
 * @author admin
 * @date 2019/4/30
 * @function 注释
 **/
public class QuestionDetailsActivity extends BaseMvpActivity<QuestionDetailsContract.IQuestionDetailsPresenter>
        implements QuestionDetailsContract.IQuestionDetailsView, View.OnClickListener {

    public static final String QUESTION_BEAN = "question_bean";
    private ExamCollectionBean.RowsBean mQuestionsBean = null;

    private AppCompatImageView mTopBarBack;
    private AppCompatTextView mTopBarTitle;
    private AppCompatTextView mTopBarText;
    private AppCompatTextView mTvQuestionTitle;
    private AppCompatTextView mTvQuestionComment;

    @Override
    protected QuestionDetailsContract.IQuestionDetailsPresenter createPresenter() {
        return new QuestionDetailsPresenter();
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_question_details;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mQuestionsBean = (ExamCollectionBean.RowsBean) getIntent().getSerializableExtra(QUESTION_BEAN);

        mTopBarBack = findViewById(R.id.top_bar_back);
        mTopBarTitle = findViewById(R.id.top_bar_title);
        mTopBarText = findViewById(R.id.top_bar_text);

        mTvQuestionTitle = findViewById(R.id.tv_question_title);

        AppCompatTextView[] tvOptions = {
                findViewById(R.id.tv_answer_a),
                findViewById(R.id.tv_answer_b),
                findViewById(R.id.tv_answer_c),
                findViewById(R.id.tv_answer_d)};

        View[] layout = {
                findViewById(R.id.a_layout),
                findViewById(R.id.b_layout),
                findViewById(R.id.c_layout),
                findViewById(R.id.d_layout)};

        View[] defImages = {
                findViewById(R.id.iv_answer_def_a),
                findViewById(R.id.iv_answer_def_b),
                findViewById(R.id.iv_answer_def_c),
                findViewById(R.id.iv_answer_def_d)};

        View[] yesImages = {
                findViewById(R.id.iv_answer_yes_a),
                findViewById(R.id.iv_answer_yes_b),
                findViewById(R.id.iv_answer_yes_c),
                findViewById(R.id.iv_answer_yes_d)};

        mTvQuestionComment = findViewById(R.id.tv_question_comment);

        mTopBarTitle.setText("考题详情");
        mTopBarText.setText("取消收藏");
        mTopBarBack.setOnClickListener(this);
        mTopBarText.setOnClickListener(this);

        initQuestionTitle(mQuestionsBean);
        initComment(mQuestionsBean);

        String[] answers = mQuestionsBean.getTrueAnswer().split(",");
        for (int i = 0; i < 4; i++) {
            if (i < mQuestionsBean.getOptionContents().size()) {
                final ExamCollectionBean.RowsBean.OptionContentsBean optionBean = mQuestionsBean.getOptionContents().get(i);
                tvOptions[i].setText(optionBean.getContent());
                layout[i].setVisibility(View.VISIBLE);
                defImages[i].setVisibility(View.VISIBLE);

                for (int j = 0; j < answers.length; j++) {
                    String answer = answers[j];
                    if (answer.equals(optionBean.getOptionValue())) {
                        defImages[i].setVisibility(View.GONE);
                        yesImages[i].setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    @Override
    public void collectionSuccess() { 
        boolean isCollection = mQuestionsBean.getIsCollect() == Constant.EXAM_QUESTION_COLLECTION;

        if (isCollection) {
            mQuestionsBean.setIsCollect(Constant.EXAM_QUESTION_NOT_COLLECTION);
            mTopBarText.setText("收藏");
        } else {
            mQuestionsBean.setIsCollect(Constant.EXAM_QUESTION_COLLECTION);
            mTopBarText.setText("取消收藏");
        }
    }
 
    /**
     * 初始化标题
     */
    private void initQuestionTitle(ExamCollectionBean.RowsBean item) {
        SpannableStringBuilder titleBuilder = new SpannableStringBuilder();
        if (item.getSubjectType() == Constant.EXAM_QUESTION_TYPE_SINGLE) {
            titleBuilder.append(" 单选题  ").append(item.getSubject());
        } else if (item.getSubjectType() == Constant.EXAM_QUESTION_TYPE_MULTIPLE) {
            titleBuilder.append(" 多选题  ").append(item.getSubject());
        } else if (item.getSubjectType() == Constant.EXAM_QUESTION_TYPE_JUDGE) {
            titleBuilder.append(" 判断题  ").append(item.getSubject());
        }
        BackgroundColorSpan bgColorSpan = new BackgroundColorSpan(ContextCompat.getColor(this, R.color.color_blue));
        ForegroundColorSpan fgColorSpan = new ForegroundColorSpan(Color.WHITE);
        titleBuilder.setSpan(bgColorSpan, 0, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        titleBuilder.setSpan(fgColorSpan, 1, 4, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mTvQuestionTitle.setText(titleBuilder);

    }

    /**
     * 初始化注释
     */
    private void initComment(ExamCollectionBean.RowsBean item) {
        SpannableStringBuilder commentBuilder = new SpannableStringBuilder();
        commentBuilder.append("*注释：").append(item.getAnnotation());
        ForegroundColorSpan commentColorSpan = new ForegroundColorSpan(Color.RED);
        commentBuilder.setSpan(commentColorSpan, 0, 4, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mTvQuestionComment.setText(commentBuilder);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_bar_back:
                finish();
                break;
            case R.id.top_bar_text:
                mPresenter.collection(AccountHelper.getInstance().getUserId(),
                        mQuestionsBean.getId(), mQuestionsBean.getIsCollect() == Constant.EXAM_QUESTION_COLLECTION
                                ? Constant.EXAM_QUESTION_NOT_COLLECTION : Constant.EXAM_QUESTION_COLLECTION);
                break;
            default:
                break;
        }
    }
}
