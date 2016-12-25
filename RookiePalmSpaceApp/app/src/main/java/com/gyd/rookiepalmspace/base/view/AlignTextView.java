package com.gyd.rookiepalmspace.base.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

/**
 * Created by guoyading on 2016-01-18.
 */
public class AlignTextView extends AppCompatTextView {
    private boolean first = true;

    public AlignTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                initTextInfo();
                return true;
            }
        });
    }

    public AlignTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AlignTextView(Context context) {
        this(context, null, 0);
    }

    private float textSize;
    private float textLineHeight;
    private int top;
    private int y;
    private int lines;
    private int bottom;
    private int right;
    private int left;
    private int lineDrawWords;
    private char[] textCharArray;
    private float singleWordWidth;

    private float lineSpacingExtra;

    public void initTextInfo() {
        textSize = getTextSize();
        textLineHeight = getLineHeight();
        left = 0;
        right = getRight();
        y = getTop();
        // 要画的宽度
        int drawTotalWidth = right - left;
        String text = getText().toString();
        if (!TextUtils.isEmpty(text) && first) {
            textCharArray = text.toCharArray();
            TextPaint mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
            mTextPaint.density = getResources().getDisplayMetrics().density;
            mTextPaint.setTextSize(textSize);
            // 一个单词的的宽度
            singleWordWidth = mTextPaint.measureText("一") + lineSpacingExtra;
            // 一行可以放多少个字符
            lineDrawWords = (int) (drawTotalWidth / singleWordWidth);
            int length = textCharArray.length;
            lines = length / lineDrawWords;
            if ((length % lineDrawWords) > 0) {
                lines = lines + 1;
            }
            first = false;
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
            int totalHeight = (int) (lines * textLineHeight + textLineHeight * 2 + getPaddingBottom() + getPaddingTop() + layoutParams.bottomMargin + layoutParams.topMargin);
            setHeight(totalHeight);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        bottom = getBottom();
        int drawTotalLine = lines;

        if (maxLine != 0 && drawTotalLine > maxLine) {
            drawTotalLine = maxLine;
        }

        for (int i = 0; i < drawTotalLine; i++) {
            try {
                int length = textCharArray.length;
                int mLeft = left;
                // 第i+1行开始的字符index
                int startIndex = (i * 1) * lineDrawWords;
                // 第i+1行结束的字符index
                int endTextIndex = startIndex + lineDrawWords;
                if (endTextIndex > length) {
                    endTextIndex = length;
                    y += textLineHeight;
                } else {
                    y += textLineHeight;
                }
                for (; startIndex < endTextIndex; startIndex++) {
                    char c = textCharArray[startIndex];
//                  if (c == ' ') {
//                      c = '/u3000';
//                  } else if (c < '/177') {
//                      c = (char) (c + 65248);
//                  }
                    canvas.drawText(String.valueOf(c), mLeft, y, getPaint());
                    mLeft += singleWordWidth;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    int maxLine;

    public void setMaxLines(int max) {
        this.maxLine = max;
    }

    public void setLineSpacingExtra(int lineSpacingExtra) {
        this.lineSpacingExtra = lineSpacingExtra;
    }

    /**
     * 判断是否为中文
     *
     * @return
     */
    public static boolean containChinese(String string) {
        boolean flag = false;
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if ((c >= 0x4e00) && (c <= 0x9FA5)) {
                flag = true;
            }
        }
        return flag;
    }


    // 功能：字符串半角转换为全角
// 说明：半角空格为32,全角空格为12288.
// 		 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
// 输入参数：input -- 需要转换的字符串
// 输出参数：无：
// 返回值: 转换后的字符串
    public static String halfToFull(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 32) //半角空格
            {
                c[i] = (char) 12288;
                continue;
            }

            //根据实际情况，过滤不需要转换的符号
            //if (c[i] == 46) //半角点号，不转换
            // continue;

            if (c[i] > 32 && c[i] < 127)    //其他符号都转换为全角
                c[i] = (char) (c[i] + 65248);
        }
        return new String(c);
    }


    // 功能：字符串全角转换为半角
// 说明：全角空格为12288，半角空格为32
// 		 其他字符全角(65281-65374)与半角(33-126)的对应关系是：均相差65248
// 输入参数：input -- 需要转换的字符串
// 输出参数：无：
// 返回值: 转换后的字符串
    public static String fullToHalf(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) //全角空格
            {
                c[i] = (char) 32;
                continue;
            }

            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }
}
