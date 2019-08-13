package com.azadljy.pleasantlibrary.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;

import androidx.appcompat.widget.AppCompatTextView;

public class PrinterTextView extends AppCompatTextView {


    private StringBuffer stringBuffer = new StringBuffer();
    private Rect textRect = new Rect();
    private String[] arr;

    private int textCount;

    private int currentIndex = -1;

    /**
     * 每个字出现的时间
     */
    private int duration = 300;
    private ValueAnimator textAnimation;
    private TextAnimationListener textAnimationListener;

    //    private TextAnimationListener textAnimationListener;
    public PrinterTextView(Context context) {
        super(context);
    }

    public PrinterTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PrinterTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        if (stringBuffer != null) {
//            drawText(canvas, stringBuffer.toString());
//        }
    }


    private void drawText(Canvas canvas, String textString) {
        textRect.left = getPaddingLeft();
        textRect.top = getPaddingTop();
        textRect.right = getWidth() - getPaddingRight();
        textRect.bottom = getHeight() - getPaddingBottom();
        Paint.FontMetricsInt fontMetrics = getPaint().getFontMetricsInt();
        int baseLine = (textRect.bottom + textRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
        canvas.drawText(textString, getPaddingLeft(), baseLine, getPaint());
    }


    private void initAnimation() {
        textAnimation = ValueAnimator.ofInt(0, textCount - 1);
        textAnimation.setDuration(textCount * duration);
        textAnimation.setInterpolator(new LinearInterpolator());

        textAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int index = (int) animation.getAnimatedValue();
                if (currentIndex != index) {
                    stringBuffer.append(arr[index]);
                    currentIndex = index;
                    if (currentIndex == (textCount - 1)) {
                        if (textAnimationListener != null) {
                            textAnimationListener.animationFinish();
                        }
                    }

                    setText(stringBuffer.toString());

                }
            }
        });

    }

    public PrinterTextView setTextString(String textString) {

        if (textString != null) {
            textCount = textString.length();
            arr = new String[textCount];
            for (int i = 0; i < textCount; i++) {
                arr[i] = textString.substring(i, i + 1);
            }
            initAnimation();
        }
        return this;
    }


    public PrinterTextView stopAnimation() {
        if (textAnimation != null) {
            textAnimation.end();
        }
        return this;
    }



    public PrinterTextView startAnimation() {
        if (textAnimation != null) {
            stringBuffer.setLength(0);
            currentIndex = -1;
            textAnimation.start();
        }
        return this;
    }


    /**
     * 回调接口
     */
    public interface TextAnimationListener {
        void animationFinish();
    }

    public PrinterTextView setTextAnimationListener(TextAnimationListener textAnimationListener) {
        this.textAnimationListener = textAnimationListener;
        return this;
    }
}
