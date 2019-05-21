package com.dalaiye.luhang.ui.user;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.bean.UserBean;
import com.dalaiye.luhang.contract.user.UserCenterContract;
import com.dalaiye.luhang.contract.user.impl.UserCenterPresenter;
import com.dalaiye.luhang.ui.app.AppSettingActivity;
import com.gfc.library.config.ECLibrary;
import com.gfc.library.mvp.BaseMvpFragment;
import com.gfc.library.utils.user.AccountHelper;

/**
 * @author admin
 * @date 2019/4/10
 * @function 个人中心
 **/
public class UserCenterFragment extends BaseMvpFragment<UserCenterContract.IUserCenterPresenter>
        implements UserCenterContract.IUserCenterView, View.OnClickListener {

    private View mViewMsg;
    private AppCompatImageView mIvUserAvatar;
    private AppCompatTextView mTvUserName;
    private AppCompatTextView mTvUserTeam;
    private UserBean mUserBean;
    @Override
    public UserCenterContract.IUserCenterPresenter createPresenter() {
        return new UserCenterPresenter();
    }

    @Override
    protected Object layoutRes() {
        return R.layout.fragment_user_center;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        mViewMsg = mRootView.findViewById(R.id.view_msg);
        mIvUserAvatar = mRootView.findViewById(R.id.iv_user_avatar);
        mTvUserName = mRootView.findViewById(R.id.tv_user_name);
        mTvUserTeam = mRootView.findViewById(R.id.tv_user_team);

        mRootView.findViewById(R.id.msg_layout).setOnClickListener(this);
        mRootView.findViewById(R.id.tv_exam).setOnClickListener(this);
        mRootView.findViewById(R.id.tv_course).setOnClickListener(this);
        mRootView.findViewById(R.id.tv_collection).setOnClickListener(this);
        mRootView.findViewById(R.id.tv_auth).setOnClickListener(this);
        mRootView.findViewById(R.id.tv_setting).setOnClickListener(this);
        mIvUserAvatar.setOnClickListener(this);
        mTvUserName.setOnClickListener(this);
        mTvUserTeam.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.queryUserInfo(AccountHelper.getInstance().getUserId());
        mPresenter.isHaveMessage(AccountHelper.getInstance().getUserId());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.msg_layout:
                startActivity(new Intent(getContext(), UserMessageActivity.class));
                break;
            case R.id.tv_exam:
                startActivity(new Intent(getContext(), UserExamActivity.class));
                break;
            case R.id.tv_course:
                startActivity(new Intent(getContext(), UserCourseActivity.class));
                break;
            case R.id.tv_collection:
                startActivity(new Intent(getContext(), UserCollectionActivity.class));
                break;
            case R.id.tv_auth:
                Intent authIntent = new Intent(getContext(), UserAuthActivity.class);
                authIntent.putExtra("authUrl",mUserBean.getAuthUrl());
                startActivity(authIntent);
                break;
            case R.id.tv_setting:
                startActivity(new Intent(getContext(), AppSettingActivity.class));
                break;
            case R.id.iv_user_avatar:
            case R.id.tv_user_name:
            case R.id.tv_user_team:
                Intent intent = new Intent(getContext(), UserProfileActivity.class);
                if(mUserBean!= null) {
                    intent.putExtra("userBean",mUserBean);
                }
                startActivity(intent);
                break;
            default:
                break;
        }
    }


    @Override
    public void setIsHaveMessage(String isHaveMsg) {
        if ("0".equals(isHaveMsg)) {
            mViewMsg.setVisibility(View.GONE);
        } else {
            mViewMsg.setVisibility(View.VISIBLE);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setUserInfo(UserBean userBean) {
        mUserBean = userBean;
        
        Glide.with(ECLibrary.getApplication())
                .load(userBean.getAvatar())
                .apply(new RequestOptions()
                        .centerCrop()
                        .circleCrop())
                .into(mIvUserAvatar);

        mTvUserName.setText(userBean.getUserName());
        mTvUserTeam.setText(userBean.getDeptName() + userBean.getPostName());
    }
}
