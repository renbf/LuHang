package com.dalaiye.luhang.ui.car.check;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.contract.car.CompleteCheckContract;
import com.dalaiye.luhang.contract.car.impl.CompleteCheckPresenter;
import com.dalaiye.luhang.ui.user.UserSignActivity;
import com.dalaiye.luhang.utils.EmojiFilter;
import com.gfc.library.event.EventKeys;
import com.gfc.library.event.EventMessage;
import com.gfc.library.mvp.BaseMvpActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.Objects;

/**
 * @author AnYu
 * @date 2019/4/17
 * @function 完成检查
 **/
public class CompleteCheckActivity extends BaseMvpActivity<CompleteCheckContract.ICompleteCheckPresenter>
        implements CompleteCheckContract.ICompleteCheckView, View.OnClickListener {

    private android.support.v7.widget.AppCompatImageView mTopBarBack;
    private android.support.v7.widget.AppCompatTextView mTopBarTitle;
    private android.support.v7.widget.AppCompatImageView mIvStartSign;
    private android.support.v7.widget.AppCompatTextView mTvSubmit;
    private String checkId;
    private android.support.v7.widget.AppCompatEditText mEdCheckObj;
    private String signImage;
    @Override
    protected CompleteCheckContract.ICompleteCheckPresenter createPresenter() {
        return new CompleteCheckPresenter();
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_car_complete_check;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        checkId = getIntent().getStringExtra("id");
        mTopBarBack = findViewById(R.id.top_bar_back);
        mTopBarTitle = findViewById(R.id.top_bar_title);
        mIvStartSign = findViewById(R.id.iv_start_sign);
        mTvSubmit = findViewById(R.id.tv_submit);
        mEdCheckObj = findViewById(R.id.ed_check_obj);
        mEdCheckObj.setFilters(new InputFilter[]{new EmojiFilter(this)});
        mTopBarTitle.setText("完成检查");

        mTopBarBack.setOnClickListener(this);
        mIvStartSign.setOnClickListener(this);
        mTvSubmit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_bar_back :
                finish();
                break;
            case R.id.iv_start_sign:
                startActivityForResult(new Intent(this, UserSignActivity.class),UserSignActivity.USER_SIGN_CODE);
                break;
            case R.id.tv_submit:
                String checkObj = Objects.requireNonNull(mEdCheckObj.getText()).toString();
                if(!TextUtils.isEmpty(signImage)) {
                    mPresenter.completeCheck(checkId,checkObj,signImage);
                }else {
                    Toast.makeText(CompleteCheckActivity.this, "请您签字！", Toast.LENGTH_SHORT).show();
                }

                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UserSignActivity.USER_SIGN_CODE && resultCode == UserSignActivity.USER_SIGN_CODE) {
            signImage = data.getStringExtra(UserSignActivity.USER_SIGN_PATH);
            Glide.with(this)
                    .load(signImage)
                    .into(mIvStartSign);
        }
    }

    @Override
    public void completeCheck(String message) {
        Toast.makeText(CompleteCheckActivity.this, message, Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new EventMessage(EventKeys.COMPLETE_CHECK,null));
        finish();
    }
}
