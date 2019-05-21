package com.dalaiye.luhang.widget.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.QuestionGridAdapter;
import com.dalaiye.luhang.bean.ExamDetailsBean;
import com.dalaiye.luhang.constant.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @date 2019/4/28
 * @function 注释
 **/
public class ExamStatDialog extends AppCompatDialog implements BaseQuickAdapter.OnItemClickListener
        , View.OnClickListener {
    private AppCompatTextView mTvQuestionCollection;
    private AppCompatTextView mTvQuestionCorrectNum;
    private AppCompatTextView mTvQuestionWrongNum;
    private AppCompatTextView mTvQuestionSubmitNum;
    private RecyclerView mRecyclerView;

    private IExamStatCallBack mCallBack;
    private QuestionGridAdapter mGridAdapter;
    private List<ExamDetailsBean.QuestionsBean> mQuestionsBeans = new ArrayList<>();

    private int mYesQuestion = 0;
    private int mNoQuestion = 0;

    public ExamStatDialog(Context context, IExamStatCallBack callBack) {
        this(context, R.style.Up_Down_dialog_Animation);
        mCallBack = callBack; 
    }

    private ExamStatDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_exam_stat);
        initView();
        initWindow();
    }

    private void initView() {
        mTvQuestionCollection = findViewById(R.id.tv_question_collection);
        mTvQuestionCorrectNum = findViewById(R.id.tv_question_correct_num);
        mTvQuestionWrongNum = findViewById(R.id.tv_question_wrong_num);
        mTvQuestionSubmitNum = findViewById(R.id.tv_question_submit_num);
        mRecyclerView = findViewById(R.id.recycler_view);

        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),
                6, LinearLayoutManager.VERTICAL, false));
        mGridAdapter = new QuestionGridAdapter(mQuestionsBeans);
        mGridAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mGridAdapter);

        mTvQuestionCorrectNum.setText(String.valueOf(mYesQuestion));
        mTvQuestionWrongNum.setText(String.valueOf(mNoQuestion));
        
        mTvQuestionCollection.setOnClickListener(this);
        mTvQuestionSubmitNum.setOnClickListener(this);
    }

    public void setQuestionsBeans(List<ExamDetailsBean.QuestionsBean> data) {
        mQuestionsBeans.clear();
        mQuestionsBeans.addAll(data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_question_collection:
                if (mCallBack != null){
                    mCallBack.onClickCollection();
                }
                cancel();
                break;
            case R.id.tv_question_submit_num:
                cancel(); 
                break;
            default:
                break;
        }
    }

    @Override
    public void show() {
        super.show();
        mGridAdapter.notifyDataSetChanged();
        setCollection();
        questionsStatistics();
    }

    private void setCollection() {
        if (mCallBack != null) {
            //收藏
            final ExamDetailsBean.QuestionsBean questionBean =
                    mGridAdapter.getData().get(mCallBack.getShowPosition());

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

    private void questionsStatistics() {
        int noQuestion = 0;
        int yesQuestion = 0;
        int totalQuestion = mGridAdapter.getData().size();
        for (int i = 0; i < mGridAdapter.getData().size(); i++) {
            final ExamDetailsBean.QuestionsBean bean = mGridAdapter.getData().get(i);
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
                ContextCompat.getColor(getContext(), R.color.color_text_dark));
        ss.setSpan(colorSpan, ss.toString().indexOf("/")
                , ss.toString().indexOf("/") + 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mTvQuestionCorrectNum.setText(String.valueOf(yesQuestion));
        mTvQuestionWrongNum.setText(String.valueOf(noQuestion));
        mTvQuestionSubmitNum.setText(ss);
    }

    private void initWindow() {
        Window dialogWindow = getWindow();
        if (dialogWindow != null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = getContext().getResources().getDisplayMetrics().widthPixels;
            lp.gravity = Gravity.BOTTOM;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (mCallBack != null) {
            mCallBack.onScrollChange(position);
        }
        cancel();
    }


    public interface IExamStatCallBack {
        void onClickCollection();

        int getShowPosition();

        void onScrollChange(int position);
    }
}
