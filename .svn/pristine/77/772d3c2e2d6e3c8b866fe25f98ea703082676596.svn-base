package com.dalaiye.luhang.ui.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.bean.UserBean;
import com.gfc.library.base.BaseActivity;
import com.gfc.library.config.ECLibrary;

/**
 * @author admin
 * @date 2019/4/10
 * @function 个人资料
 **/
public class UserProfileActivity extends BaseActivity implements View.OnClickListener {
    private AppCompatImageView mTopBarBack;
    private AppCompatTextView mTopBarTitle;
    private AppCompatTextView mTvUserPhone;
    private AppCompatTextView mTvUserTeam;
    private AppCompatTextView mTvUserIdentity;
    private AppCompatImageView mTvUserImg;
    private AppCompatTextView mTvUserSex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        initView();
    }

    private void initView() {
        UserBean userBean = (UserBean) getIntent().getSerializableExtra("userBean");

        mTopBarBack = findViewById(R.id.top_bar_back);
        mTopBarTitle = findViewById(R.id.top_bar_title);
        mTvUserPhone = findViewById(R.id.tv_user_phone);
        mTvUserTeam = findViewById(R.id.tv_user_team);
        mTvUserIdentity = findViewById(R.id.tv_user_identity);
        mTvUserImg = findViewById(R.id.tv_user_img);
        mTvUserSex = findViewById(R.id.tv_user_sex);
        mTopBarBack.setOnClickListener(this);
        mTopBarTitle.setText("个人资料");

        mTvUserPhone.setText(userBean.getPhonenumber());
        mTvUserTeam.setText(userBean.getDeptName());
        mTvUserIdentity.setText(userBean.getPostName());
        mTvUserSex.setText(userBean.getSexName());

        Glide.with(ECLibrary.getApplication())
                .load(userBean.getAvatar())
                .apply(new RequestOptions()
                        .centerCrop()
                        .circleCrop())
                .into(mTvUserImg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_bar_back :
                finish();
                break;
            default:
                break;
        }
    }
}
