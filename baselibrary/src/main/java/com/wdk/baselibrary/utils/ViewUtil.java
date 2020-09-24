package com.wdk.baselibrary.utils;

import android.text.TextPaint;
import android.text.TextUtils;
import android.widget.TextView;

/**
 * @Description :View 相关的工具类
 * @Author : wdk
 * @CretaTime : 2020/9/17 2:23 PM
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2020/9/17 2:23 PM
 * @LastCheckBy :wdk
 */
public class ViewUtil {

    /**
     * 设置textView字体加粗
     *
     * @param textView textView
     */
    public static void textViewBlod(TextView textView) {
        try {
            TextPaint tp = textView.getPaint();
            tp.setFakeBoldText(true);
            textView.postInvalidate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void textViewBlodFalse(TextView textView) {
        try {
            TextPaint tp = textView.getPaint();
            tp.setFakeBoldText(false);
            textView.postInvalidate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void textViewBlod(TextView textView, boolean isBold) {
        try {
            TextPaint tp = textView.getPaint();
            tp.setFakeBoldText(isBold);
            textView.postInvalidate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取文字在textview中显示的行数
     *
     * @param words    文字
     * @param width    textView的宽度
     * @param textView TextView
     * @return
     */
    public static float getTextViewLines(String words, int width, TextView textView) {
        if (TextUtils.isEmpty(words)) {
            return 0;
        }
        if (width <= 0) {
            return 0;
        }
        if (textView.getTextSize() <= 0) {
            return 0;
        }
        TextPaint paint = textView.getPaint();
        float textWidth = paint.measureText(words);
        return textWidth / width;
    }
}
