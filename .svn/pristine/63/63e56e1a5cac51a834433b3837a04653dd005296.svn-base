package com.gfc.library.utils.watcher;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * @author admin
 * @date 2019/4/4
 * @function 注释
 **/
public class EnabledTextWatcher implements TextWatcher {
    
    private EnabledListener mEnabledListener;

    public EnabledTextWatcher(EnabledListener enabledListener) {
        mEnabledListener = enabledListener;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (mEnabledListener != null){
            mEnabledListener.checkEnable();
        }
    }
    
    public interface EnabledListener{
        void checkEnable();
    }
}
