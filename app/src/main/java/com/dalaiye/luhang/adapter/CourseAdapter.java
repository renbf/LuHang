package com.dalaiye.luhang.adapter;

import android.content.Intent;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.bean.CourseBean;
import com.dalaiye.luhang.ui.train.course.BrowseFileActivity;
import com.dalaiye.luhang.ui.train.course.CourseDetailsActivity;

import java.util.List;

/**
 * @author AnYu
 * @date 2019/4/11
 * @function 课程安排的adapter
 **/
public class CourseAdapter extends BaseMultiItemQuickAdapter<CourseBean.RowsBean, BaseViewHolder>
        implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {
    /**
     * type 0是从培训列表进来 1是从我的课程进来
     */
    private int type;

    public CourseAdapter(List<CourseBean.RowsBean> data, int type) {
        super(data);
        this.type = type;
        addItemType(0, R.layout.adapter_train_course_video_item);
        addItemType(1, R.layout.adapter_train_course_file_item);
        addItemType(2, R.layout.adapter_train_course_file_video_item);

        this.setOnItemClickListener(this);
        this.setOnItemChildClickListener(this);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseBean.RowsBean item) {
        switch (helper.getItemViewType()) {
            case 0:
                helper.setText(R.id.tv_course_name, item.getCourseName());
                helper.setText(R.id.tv_time, item.getStartDate());
                AppCompatImageView imageView = helper.getView(R.id.tv_img);
                Glide.with(mContext).load(item.getCourseUrl()).into(imageView);
                helper.setText(R.id.tv_introduce, item.getIntroduction());
                ProgressBar progressBar = helper.getView(R.id.progress);
                if (type == 0) {
                    progressBar.setProgress(Integer.valueOf(item.getPercentage()));
                    helper.setText(R.id.tv_progress, item.getPercentage() + "%");
                } else {
                    progressBar.setProgress(100);
                    helper.setText(R.id.tv_progress, 100 + "%");
                }
                break;
            case 1:
                AppCompatImageView imageView1 = helper.getView(R.id.iv_img1);
                Glide.with(mContext).load(item.getCourseUrl()).into(imageView1);
                helper.setText(R.id.tv_file_name, item.getCourseName());
                helper.setText(R.id.tv_time, item.getStartDate());
                break;
            case 2:
                helper.setText(R.id.tv_course_name, item.getCourseName());
                helper.setText(R.id.tv_time, item.getStartDate());
                AppCompatImageView imageView2 = helper.getView(R.id.tv_img);
                Glide.with(mContext).load(item.getCourseUrl()).into(imageView2);
                helper.setText(R.id.tv_introduce, item.getIntroduction());
                helper.addOnClickListener(R.id.tv_borwse_file);
                ProgressBar progressBar1 = helper.getView(R.id.progress);
                if (type == 0) {
                    helper.setText(R.id.tv_progress, item.getPercentage() + "%");
                    progressBar1.setProgress(Integer.valueOf(item.getPercentage()));
                } else {
                    progressBar1.setProgress(100);
                    helper.setText(R.id.tv_progress, 100 + "%");
                }

                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        int itemViewType = adapter.getItemViewType(position);
        CourseBean.RowsBean rowsBean = (CourseBean.RowsBean) adapter.getData().get(position);

        Intent intentCourse = new Intent(mContext, CourseDetailsActivity.class);
        Intent intentFile = new Intent(mContext, BrowseFileActivity.class);

        intentCourse.putExtra("type", 0);
        intentCourse.putExtra("rows", rowsBean);
        intentFile.putExtra("rows", rowsBean);

        switch (itemViewType) {
            case 0:
                mContext.startActivity(intentCourse);
                break;
            case 1:
                mContext.startActivity(intentFile);
                break;
            case 2:
                mContext.startActivity(intentCourse);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        CourseBean.RowsBean rowsBean = (CourseBean.RowsBean) adapter.getData().get(position);
        Intent intent = new Intent(mContext, BrowseFileActivity.class);
        intent.putExtra("rows", rowsBean);
        mContext.startActivity(intent);
    }

}
