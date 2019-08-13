package com.azadljy.pleasantlibrary.widget;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.azadljy.pleasantlibrary.utils.SizeUtil;

public class MemoryUnitView extends AppCompatTextView {
    public static final String TAG = "MemoryUnitView";
    private Context context;




    public MemoryUnitView(Context context) {
        super(context);
        init(context);
    }

    public MemoryUnitView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MemoryUnitView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        this.context = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        heightSize = getHeight(heightSize);
        widthSize = getWidth(widthSize);
        super.onMeasure(MeasureSpec.makeMeasureSpec(widthSize, widthMode), MeasureSpec.makeMeasureSpec(heightSize, heightMode));
    }


    private int getWidth(int width) {
        width = width > SizeUtil.dp2px(context, 300) ? 300 : width;
        return width;
    }

    private int getHeight(int height) {
        height = height > SizeUtil.dp2px(context, 200) ? 200 : height;
        return height;
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e(TAG, "dispatchTouchEvent: " + event.getAction());


        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent: " + event.getAction());


        return super.onTouchEvent(event);
    }



}
