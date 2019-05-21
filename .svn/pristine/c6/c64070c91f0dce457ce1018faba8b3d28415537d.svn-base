package com.dalaiye.luhang.utils;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Administrator
 * @date 2018/12/5
 * @function 功能
 */
public class EmojiFilter implements InputFilter {
    private Context mContext;

    public EmojiFilter(Context context) {
        mContext = context;
    }

    //关键的正则表达式
    Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\ud83e\udd00-\ud83e\udfff]|[\u2600-\u27ff]", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        Matcher emojiMatcher = emoji.matcher(source);
        if (emojiMatcher.find()) {
            Toast.makeText(mContext, "不支持表情输入", Toast.LENGTH_SHORT).show();
            return "";
        }
        return null;
    }
}
