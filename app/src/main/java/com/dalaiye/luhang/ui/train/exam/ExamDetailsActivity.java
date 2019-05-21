package com.dalaiye.luhang.ui.train.exam;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.ExamQuestionAdapter;
import com.dalaiye.luhang.bean.ExamDetailsBean;
import com.dalaiye.luhang.constant.Constant;
import com.dalaiye.luhang.contract.train.ExamDetailsContract;
import com.dalaiye.luhang.contract.train.impl.ExamDetailsPresenter;
import com.dalaiye.luhang.widget.dialog.ExamScoreDialog;
import com.dalaiye.luhang.widget.dialog.ExamStatDialog;
import com.dalaiye.luhang.widget.textview.CountDownTextView;
import com.gfc.library.mvp.BaseMvpActivity;
import com.gfc.library.utils.user.AccountHelper;
import com.gfc.library.widget.recycler.ExamLayoutManager;
import com.gfc.library.widget.recycler.IExamListener;

/**
 * @author admin
 * @date 2019/4/10
 * @function 考试
 **/
public class ExamDetailsActivity extends BaseMvpActivity<ExamDetailsContract.IExamDetailsPresenter>
        implements ExamDetailsContract.IExamDetailsView, View.OnClickListener, IExamListener
        , BaseQuickAdapter.OnItemChildClickListener, ExamStatDialog.IExamStatCallBack {
    private static final String TAG = "ExamDetailsActivity";
    public static final String EXAM_USER_PAPER_ID = "exam_user_paper_id";
    public static final String EXAM_USER_PAPER_URL = "exam_user_paper_URL";

    private AppCompatImageView mTopBarBack;
    private AppCompatTextView mTopBarTitle;
    private AppCompatTextView mTopBarText;
    private View mLoadingLayout;
    private CountDownTextView mTvTimer;
    private RecyclerView mRecyclerView;
    private AppCompatTextView mTvQuestionCollection;
    private AppCompatTextView mTvQuestionCorrectNum;
    private AppCompatTextView mTvQuestionWrongNum;
    private AppCompatTextView mTvQuestionSubmitNum;

    private String mUrl;
    private String mUserPaperId;
    private ExamDetailsBean mDetailsBean;
    private ExamLayoutManager mLayoutManager;
    private ExamQuestionAdapter mQuestionAdapter;
    private ExamStatDialog mStatDialog;

    @Override
    protected ExamDetailsContract.IExamDetailsPresenter createPresenter() {
        return new ExamDetailsPresenter();
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_train_exam_details;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        mUrl = getIntent().getStringExtra(EXAM_USER_PAPER_URL);
        mUserPaperId = getIntent().getStringExtra(EXAM_USER_PAPER_ID);

        mTopBarBack = findViewById(R.id.top_bar_back);
        mTopBarTitle = findViewById(R.id.top_bar_title);
        mTopBarText = findViewById(R.id.top_bar_text);
        mLoadingLayout = findViewById(R.id.loading_layout);
        mTvTimer = findViewById(R.id.tv_timer);
        mRecyclerView = findViewById(R.id.recycler_view);
        mTvQuestionCollection = findViewById(R.id.tv_question_collection);
        mTvQuestionCorrectNum = findViewById(R.id.tv_question_correct_num);
        mTvQuestionWrongNum = findViewById(R.id.tv_question_wrong_num);
        mTvQuestionSubmitNum = findViewById(R.id.tv_question_submit_num);

        mTopBarBack.setOnClickListener(this);
        mTopBarTitle.setText("考卷详情");

        mTvQuestionCollection.setOnClickListener(this);
        mTvQuestionSubmitNum.setOnClickListener(this);

        mLayoutManager = new ExamLayoutManager(this, LinearLayoutManager.HORIZONTAL);
        mLayoutManager.setExamListener(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mTvTimer.setNormalText("考试结束")
                .setCountDownText("", "")
                .setCloseKeepCountDown(false)//关闭页面保持倒计时开关
                .setCountDownClickable(false)//倒计时期间点击事件是否生效开关
                .setShowFormatTime(true)//是否格式化时间
                .setOnCountDownFinishListener(new CountDownTextView.OnCountDownFinishListener() {
                    @Override
                    public void onFinish() {
                        mDetailsBean.setStatus(Constant.EXAM_END);
                        //倒计时结束 提交考卷 
                        mPresenter.submitExam(mUserPaperId);
                    }
                });

        mPresenter.getExamDetails(mUrl, mUserPaperId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_bar_back:
                finish();
                break;
            case R.id.top_bar_text:
                mPresenter.submitExam(mUserPaperId);
                break;
            case R.id.tv_question_collection:
                onClickCollection();
                break;
            case R.id.tv_question_submit_num:
                if (mStatDialog != null && !mStatDialog.isShowing()) {
                    mStatDialog.show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void setExamDetails(ExamDetailsBean details) {
        mDetailsBean = details;
        //dialog
        mStatDialog = new ExamStatDialog(this, this);
        mStatDialog.setQuestionsBeans(details.getExamQuestions());
        //试卷详情
        mQuestionAdapter = new ExamQuestionAdapter(mDetailsBean.getExamQuestions());
        mQuestionAdapter.setOnItemChildClickListener(this);
        mRecyclerView.setAdapter(mQuestionAdapter);
        //答过的题统计
        questionsStatistics();

        if (details.getExamQuestions().size() > 0) {
            //设置默认第一题的收藏状态
            final ExamDetailsBean.QuestionsBean questionsBean = mDetailsBean.getExamQuestions().get(0);
            boolean isCollection = questionsBean.getIsCollect() == Constant.EXAM_QUESTION_COLLECTION;
            mTvQuestionCollection.setSelected(isCollection);
            mTvQuestionCollection.setText(isCollection ? "已收藏" : "收藏");
        }
        //判断是不是交卷状态
        if (mDetailsBean.getStatus() != Constant.EXAM_END) {
            mTopBarText.setText("交卷");
            mTopBarText.setOnClickListener(this);
            mTvTimer.startCountDown(mDetailsBean.getRemainingTime());
            for (int i = 0; i < mDetailsBean.getExamQuestions().size(); i++) {
                final ExamDetailsBean.QuestionsBean bean = mDetailsBean.getExamQuestions().get(i);
                if (bean.getItemType() == Constant.EXAM_QUESTION_TYPE_NOT_ANSWERED) {
                    //滑动到一个未答的问题
                    mLayoutManager.scrollToPosition(i);
                    //设置这个题的收藏状态
                    boolean isCollection = bean.getIsCollect() == Constant.EXAM_QUESTION_COLLECTION;

                    if (isCollection) {
                        mTvQuestionCollection.setSelected(true);
                        mTvQuestionCollection.setText("已收藏");
                    } else {
                        mTvQuestionCollection.setSelected(false);
                        mTvQuestionCollection.setText("收藏");
                    }
                    break;
                }
            }
        }

        mLoadingLayout.setVisibility(View.GONE);
    }

    @Override
    public void submitExamSuccess(String score) {
        //交卷成功
        new ExamScoreDialog(this, score).show();
    }

    @Override
    public void submitQuestionSuccess(String userAnswerId, String isTrue) {
        //提交问题成功
        final int position = getShowPosition();
        final ExamDetailsBean.QuestionsBean bean = mQuestionAdapter.getData().get(position);
        //设置是否正确
        bean.setIsTrue(Integer.valueOf(isTrue));
        //设置答案的id
        bean.setUserSubjectId(userAnswerId);
        // 刷新adapter
        mQuestionAdapter.notifyDataSetChanged();
        //答过的题统计
        questionsStatistics();
    }

    @Override
    public void collectionSuccess() {
        //收藏
        int position = getShowPosition();
        ExamDetailsBean.QuestionsBean bean = mQuestionAdapter.getData().get(position);

        boolean isCollection = bean.getIsCollect() == Constant.EXAM_QUESTION_COLLECTION;

        if (isCollection) {
            bean.setIsCollect(Constant.EXAM_QUESTION_NOT_COLLECTION);
            mTvQuestionCollection.setSelected(false);
            mTvQuestionCollection.setText("收藏");
        } else {
            bean.setIsCollect(Constant.EXAM_QUESTION_COLLECTION);
            mTvQuestionCollection.setSelected(true);
            mTvQuestionCollection.setText("已收藏");
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (mDetailsBean.getStatus() == Constant.EXAM_END) {
            return;
        }
        switch (view.getId()) {
            case R.id.a_layout:
                setUserAnswers(position, 0);
                break;
            case R.id.b_layout:
                setUserAnswers(position, 1);
                break;
            case R.id.c_layout:
                setUserAnswers(position, 2);
                break;
            case R.id.d_layout:
                setUserAnswers(position, 3);
                break;
            case R.id.tv_submit_answer:
                final ExamDetailsBean.QuestionsBean questionsBean = mQuestionAdapter.getData().get(position);
                mPresenter.submitQuestion(mUserPaperId, questionsBean.getSubjectId(), questionsBean.getUserAnswer()
                        , questionsBean.getUserSubjectId());
                break;
            default:
                break;
        }
    }

    @Override
    public void onInitComplete() {
        Log.d(TAG, "onInitComplete: ");
    }

    @Override
    public void onPageRelease(boolean isNext, int position) {
        Log.d(TAG, "onPageRelease: " + position + "  " + isNext);
    }

    @Override
    public void onPageSelected(int position, boolean isBottom) {
        Log.d(TAG, "onPageSelected: " + position + "   " + isBottom);
        final ExamDetailsBean.QuestionsBean questionBean
                = mQuestionAdapter.getData().get(position);
        boolean isCollection = questionBean.getIsCollect() == Constant.EXAM_QUESTION_COLLECTION;

        if (isCollection) {
            mTvQuestionCollection.setSelected(true);
            mTvQuestionCollection.setText("已收藏");
        } else {
            mTvQuestionCollection.setSelected(false);
            mTvQuestionCollection.setText("收藏");
        }
    }

    /**
     * 设置
     */
    private void setUserAnswers(int questionPosition, int optionPosition) {

        final ExamDetailsBean.QuestionsBean questionsBean
                = mQuestionAdapter.getData().get(questionPosition);

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < questionsBean.getOptionContents().size(); i++) {
            ExamDetailsBean.QuestionsBean.OptionBean optionBean = questionsBean.getOptionContents().get(i);
            if (i == optionPosition) {
                //当前点击的选项选中状态取反
                optionBean.setSelector(!optionBean.isSelector());
            } else if (questionsBean.getSubjectType() != Constant.EXAM_QUESTION_TYPE_MULTIPLE) {
                //如果是单选 需要将其他选项的选中状态改为不选中
                optionBean.setSelector(false);
            }

            if (optionBean.isSelector()) {
                builder.append(optionBean.getOptionValue()).append(",");
            }
        }

        questionsBean.setUserAnswer(TextUtils.isEmpty(builder.toString()) ? "" : builder.toString().substring(0, builder.length() - 1));

        questionsBean.setIsTrue(questionsBean.getTrueAnswer().equals(questionsBean.getUserAnswer()) ?
                Constant.EXAM_QUESTION_YES : Constant.EXAM_QUESTION_NO);

        mQuestionAdapter.notifyDataSetChanged();
    }

    /**
     * 问题统计
     */
    private void questionsStatistics() {
        int noQuestion = 0;
        int yesQuestion = 0;
        int totalQuestion = mQuestionAdapter.getData().size();
        for (int i = 0; i < mQuestionAdapter.getData().size(); i++) {
            final ExamDetailsBean.QuestionsBean bean = mQuestionAdapter.getData().get(i);
            if (bean.getItemType() == Constant.EXAM_QUESTION_TYPE_ANSWERED) {
                if (bean.getIsTrue() != null && bean.getIsTrue() == Constant.EXAM_QUESTION_NO) {
                    noQuestion++;
                } else if (bean.getIsTrue() != null && bean.getIsTrue() == Constant.EXAM_QUESTION_YES) {
                    yesQuestion++;
                }
            }
        }
        SpannableString ss = new SpannableString(String.valueOf(yesQuestion + noQuestion)
                + "/" + String.valueOf(totalQuestion));
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(
                ContextCompat.getColor(this, R.color.color_text_dark));
        ss.setSpan(colorSpan, ss.toString().indexOf("/")
                , ss.toString().indexOf("/") + 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mTvQuestionCorrectNum.setText(String.valueOf(yesQuestion));
        mTvQuestionWrongNum.setText(String.valueOf(noQuestion));
        mTvQuestionSubmitNum.setText(ss);
    }

    @Override
    public void onClickCollection() {
        final int position = getShowPosition();
        final ExamDetailsBean.QuestionsBean bean = mQuestionAdapter.getData().get(position);
        mPresenter.collection(AccountHelper.getInstance().getUserId(),
                bean.getSubjectId(), bean.getIsCollect() == Constant.EXAM_QUESTION_COLLECTION
                        ? Constant.EXAM_QUESTION_NOT_COLLECTION : Constant.EXAM_QUESTION_COLLECTION);
    }

    @Override
    public int getShowPosition() {
        return mLayoutManager.findFirstVisibleItemPosition();
    }

    @Override
    public void onScrollChange(int position) {
        if (mLayoutManager != null) {
            mLayoutManager.scrollToPosition(position);
        }
        //设置这个题的收藏状态
        final ExamDetailsBean.QuestionsBean questionBean
                = mQuestionAdapter.getData().get(position);
        boolean isCollection = questionBean.getIsCollect() == Constant.EXAM_QUESTION_COLLECTION;

        if (isCollection) {
            mTvQuestionCollection.setSelected(true);
            mTvQuestionCollection.setText("已收藏");
        } else {
            mTvQuestionCollection.setSelected(false);
            mTvQuestionCollection.setText("收藏");
        }
    }
}
