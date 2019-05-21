package com.dalaiye.luhang.ui.user;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.dalaiye.luhang.R;
import com.gfc.library.base.BaseActivity;

/**
 * @author admin
 * @date 2019/4/10
 * @function 我的收藏
 **/
public class UserCollectionActivity extends BaseActivity implements View.OnClickListener {
    private AppCompatTextView mTvCourseCollection;
    private AppCompatTextView mTvExamCollection;

    private CourseCollectionFragment mCourseCollectionFragment;
    private ExamCollectionFragment mExamCollectionFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_collection);
        initView();
        setSelect(0);
    }

    private void initView() {
        AppCompatImageView topBarBack = findViewById(R.id.top_bar_back);
        mTvCourseCollection = findViewById(R.id.tv_course_collection);
        mTvExamCollection = findViewById(R.id.tv_exam_collection);

        topBarBack.setOnClickListener(this);
        mTvCourseCollection.setOnClickListener(this);
        mTvExamCollection.setOnClickListener(this);
        mTvCourseCollection.setSelected(true);
        mTvCourseCollection.setTextColor(Color.parseColor("#FFFFFF"));
    }

    private void setSelect(int checkPostion) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hindFragment(transaction);
        switch (checkPostion) {
            case 0:
                if(mCourseCollectionFragment == null) {
                    mCourseCollectionFragment = new CourseCollectionFragment();
                    transaction.add(R.id.fl_content_collection,mCourseCollectionFragment);
                }else {
                    transaction.show(mCourseCollectionFragment);
                }
                break;
            case 1:
                if(mExamCollectionFragment == null) {
                    mExamCollectionFragment = new ExamCollectionFragment();
                    transaction.add(R.id.fl_content_collection,mExamCollectionFragment);
                }else {
                    transaction.show(mExamCollectionFragment);
                }
                break;
            default:
                break;
        }
        transaction.commit();
    }

    private void hindFragment(FragmentTransaction transaction) {
        if (mCourseCollectionFragment != null) {
            transaction.hide(mCourseCollectionFragment);
        }
        if (mExamCollectionFragment != null) {
            transaction.hide(mExamCollectionFragment);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_bar_back:
                finish();
                break;
            case R.id.tv_course_collection:
                mTvCourseCollection.setSelected(true);
                mTvCourseCollection.setTextColor(Color.parseColor("#FFFFFF"));

                mTvExamCollection.setSelected(false);
                mTvExamCollection.setTextColor(Color.parseColor("#000000"));

                setSelect(0);
                break;
            case R.id.tv_exam_collection:
                mTvExamCollection.setSelected(true);
                mTvExamCollection.setTextColor(Color.parseColor("#FFFFFF"));

                mTvCourseCollection.setSelected(false);
                mTvCourseCollection.setTextColor(Color.parseColor("#000000"));

                setSelect(1);

                break;
            default:
                break;
        }
    }
}
