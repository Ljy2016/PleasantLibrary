package com.azadljy.pleasantlibrary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class ProportionateLayout extends RelativeLayout {

    double  proportion=1;

    public ProportionateLayout(Context context) {
        super(context);
    }

    public ProportionateLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProportionateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ProportionateLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec),
                getDefaultSize(0, heightMeasureSpec));

        int width = getMeasuredWidth();
        int height= (int) (width*proportion);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(
                height, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
