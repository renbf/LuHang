package com.dalaiye.luhang.adapter;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.bean.ExamDetailsBean;
import com.dalaiye.luhang.constant.Constant;
import com.gfc.library.widget.recycler.RecyclerViewHolder;

import java.util.List;

/**
 * @author admin
 * @date 2019/4/19
 * @function 注释
 **/
public class ExamQuestionAdapter extends BaseMultiItemQuickAdapter<ExamDetailsBean.QuestionsBean, RecyclerViewHolder> {


    public ExamQuestionAdapter(List<ExamDetailsBean.QuestionsBean> data) {
        super(data);
        addItemType(Constant.EXAM_QUESTION_TYPE_NOT_ANSWERED, R.layout.adapter_train_exam_question_not_answered_item);
        addItemType(Constant.EXAM_QUESTION_TYPE_ANSWERED, R.layout.adapter_train_exam_question_answered_item);
    }

    @Override
    protected void convert(RecyclerViewHolder helper, ExamDetailsBean.QuestionsBean item) {
        //最外层布局
        View[] layoutCompats = {helper.getView(R.id.a_layout), helper.getView(R.id.b_layout)
                , helper.getView(R.id.c_layout), helper.getView(R.id.d_layout)};
        //abcd
        View[] defImages = {helper.getView(R.id.iv_answer_def_a), helper.getView(R.id.iv_answer_def_b)
                , helper.getView(R.id.iv_answer_def_c), helper.getView(R.id.iv_answer_def_d)};
        //yes
        View[] yesImages = {helper.getView(R.id.iv_answer_yes_a), helper.getView(R.id.iv_answer_yes_b)
                , helper.getView(R.id.iv_answer_yes_c), helper.getView(R.id.iv_answer_yes_d)};
        //no
        View[] noImages = {helper.getView(R.id.iv_answer_no_a), helper.getView(R.id.iv_answer_no_b)
                , helper.getView(R.id.iv_answer_no_c), helper.getView(R.id.iv_answer_no_d)};
        //选项
        AppCompatTextView[] tvOptions = {helper.getView(R.id.tv_answer_a), helper.getView(R.id.tv_answer_b)
                , helper.getView(R.id.tv_answer_c), helper.getView(R.id.tv_answer_d)};
        //设置问题的标题
        initQuestionTitle(helper, item);

        //初始化选项
        initOption(item, tvOptions, layoutCompats, defImages, yesImages, noImages);

        switch (helper.getItemViewType()) {
            case Constant.EXAM_QUESTION_TYPE_NOT_ANSWERED:

                initUserAnswers(item, defImages, yesImages, noImages);
                initComment(helper, item);

                if (TextUtils.isEmpty(item.getUserAnswer())) {
                    helper.getView(R.id.tv_submit_answer).setEnabled(false);
                } else {
                    helper.getView(R.id.tv_submit_answer).setEnabled(true);
                }

                helper.addOnClickListener(R.id.a_layout);
                helper.addOnClickListener(R.id.b_layout);
                helper.addOnClickListener(R.id.c_layout);
                helper.addOnClickListener(R.id.d_layout);
                helper.addOnClickListener(R.id.tv_submit_answer);

                break;
            case Constant.EXAM_QUESTION_TYPE_ANSWERED:
                initAnswer(helper, item);
                initTrueAndUserAnswer(item, defImages, yesImages, noImages);
                break;
            default:
                break;
        }
    }

    /**
     * 初始化标题
     */
    private void initQuestionTitle(RecyclerViewHolder helper, ExamDetailsBean.QuestionsBean item) {
        SpannableStringBuilder titleBuilder = new SpannableStringBuilder();
        if (item.getSubjectType() == Constant.EXAM_QUESTION_TYPE_SINGLE) {
            titleBuilder.append(" 单选题  ").append(item.getSubject());
        } else if (item.getSubjectType() == Constant.EXAM_QUESTION_TYPE_MULTIPLE) {
            titleBuilder.append(" 多选题  ").append(item.getSubject());
        } else if (item.getSubjectType() == Constant.EXAM_QUESTION_TYPE_JUDGE) {
            titleBuilder.append(" 判断题  ").append(item.getSubject());
        }
        BackgroundColorSpan bgColorSpan = new BackgroundColorSpan(ContextCompat.getColor(mContext, R.color.color_blue));
        ForegroundColorSpan fgColorSpan = new ForegroundColorSpan(Color.WHITE);
        titleBuilder.setSpan(bgColorSpan, 0, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        titleBuilder.setSpan(fgColorSpan, 1, 4, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        helper.setText(R.id.tv_question_title, titleBuilder);

    }

    /**
     * 初始化选项
     */
    private void initOption(ExamDetailsBean.QuestionsBean item, AppCompatTextView[] tvOptions
            , View[] layoutCompats, View[] defImages, View[] yesImages, View[] noImages) {

        for (int i = 0; i < 4; i++) {
            if (i < item.getOptionContents().size()) {
                final ExamDetailsBean.QuestionsBean.OptionBean optionBean = item.getOptionContents().get(i);
                tvOptions[i].setText(optionBean.getContent());
                layoutCompats[i].setVisibility(View.VISIBLE);
                defImages[i].setVisibility(View.VISIBLE);
                yesImages[i].setVisibility(View.GONE);
                noImages[i].setVisibility(View.GONE);
            }
        }
    }

    /**
     * 初始化注释
     */
    private void initComment(RecyclerViewHolder helper, ExamDetailsBean.QuestionsBean item) {
        SpannableStringBuilder commentBuilder = new SpannableStringBuilder();
        commentBuilder.append("*注释：").append(item.getAnnotation());
        ForegroundColorSpan commentColorSpan = new ForegroundColorSpan(Color.RED);
        commentBuilder.setSpan(commentColorSpan, 0, 4, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        helper.setText(R.id.tv_question_comment, commentBuilder);
    }

    /**
     * 初始化正确答案
     */
    private void initAnswer(RecyclerViewHolder helper, ExamDetailsBean.QuestionsBean item) {
        SpannableStringBuilder answerBuilder = new SpannableStringBuilder();
        answerBuilder.append("正确答案：").append(item.getTrueAnswer());
        ForegroundColorSpan answerColorSpan = new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.color_blue));
        answerBuilder.setSpan(answerColorSpan, 5, answerBuilder.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        helper.setText(R.id.tv_question_answer, answerBuilder);
    }

    /**
     * 用户选择
     */
    private void initUserAnswers(ExamDetailsBean.QuestionsBean item, View[] defImages, View[] yesImages, View[] noImages) {
        for (int i = 0; i < item.getOptionContents().size(); i++) {
            ExamDetailsBean.QuestionsBean.OptionBean optionBean = item.getOptionContents().get(i);
            if (optionBean.isSelector()) {
                defImages[i].setVisibility(View.GONE);
                yesImages[i].setVisibility(View.VISIBLE);
                noImages[i].setVisibility(View.GONE);
            }
        }
    }

    /**
     * 考过的
     */
    private void initTrueAndUserAnswer(ExamDetailsBean.QuestionsBean item, View[] defImages, View[] yesImages, View[] noImages) {


        String[] trueAnswers = item.getTrueAnswer().split(",");
        if (!TextUtils.isEmpty(item.getUserAnswer())) {

            String[] userAnswers = item.getUserAnswer().split(",");

            for (int i = 0; i < userAnswers.length; i++) {

                String userAnswer = userAnswers[i];

                if ("A".equals(userAnswer)) {
                    defImages[0].setVisibility(View.GONE);
                    yesImages[0].setVisibility(View.GONE);
                    noImages[0].setVisibility(View.VISIBLE);
                } else if ("B".equals(userAnswer)) {
                    defImages[1].setVisibility(View.GONE);
                    yesImages[1].setVisibility(View.GONE);
                    noImages[1].setVisibility(View.VISIBLE);
                } else if ("C".equals(userAnswer)) {
                    defImages[2].setVisibility(View.GONE);
                    yesImages[2].setVisibility(View.GONE);
                    noImages[2].setVisibility(View.VISIBLE);
                } else if ("D".equals(userAnswer)) {
                    defImages[3].setVisibility(View.GONE);
                    yesImages[3].setVisibility(View.GONE);
                    noImages[3].setVisibility(View.VISIBLE);
                }
            }
        }


        for (int i = 0; i < trueAnswers.length; i++) {

            String trueAnswer = trueAnswers[i];

            if ("A".equals(trueAnswer)) {
                defImages[0].setVisibility(View.GONE);
                yesImages[0].setVisibility(View.VISIBLE);
                noImages[0].setVisibility(View.GONE);
            } else if ("B".equals(trueAnswer)) {
                defImages[1].setVisibility(View.GONE);
                yesImages[1].setVisibility(View.VISIBLE);
                noImages[1].setVisibility(View.GONE);
            } else if ("C".equals(trueAnswer)) {
                defImages[2].setVisibility(View.GONE);
                yesImages[2].setVisibility(View.VISIBLE);
                noImages[2].setVisibility(View.GONE);
            } else if ("D".equals(trueAnswer)) {
                defImages[3].setVisibility(View.GONE);
                yesImages[3].setVisibility(View.VISIBLE);
                noImages[3].setVisibility(View.GONE);
            }
        }
    }
}
