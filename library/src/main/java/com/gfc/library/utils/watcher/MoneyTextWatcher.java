package com.gfc.library.utils.watcher;

import android.text.Editable;
import android.text.TextWatcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author admin
 * @date 2019/4/8
 * @function 注释
 **/
public class MoneyTextWatcher  implements TextWatcher {
    /**
     * 判断小数点后2位的数字的正则表达式
     */
    private static final Pattern PATTEN = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$");

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String point = ".";
        String zero = "0";

        //如果"."在起始位置,则起始位置自动补0
        if (point.equals(s.toString().trim())) {
            s.insert(0, zero);
        }

        int size = s.length();
        //如果“0”在首位置，第二位不是“.”就把“0”删除
        if (s.toString().startsWith(zero) && size > 1) {
            String second = String.valueOf(s.toString().charAt(1));
            if (!point.equals(second)) {
                s.delete(0, 1);
            }
        }

        Matcher matcher = PATTEN.matcher(s.toString());
        if (!matcher.matches() && s.length() > 0) {
            s.delete(s.length() - 1, s.length());
        }

    }
}

