package com.azadljy.pleasantlibrary.utils;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

public class AnimatorUtil extends BaseUtil {


    public static void backGroundColorGradient(View view, long duration, int... colors) {
        if(colors.length==0){
            throw  new NullPointerException("颜色值不能为空");
        }
        ValueAnimator animator = ObjectAnimator.ofInt(view, "backgroundColor", colors);//对背景色颜色进行改变，操作的属性为"backgroundColor",此处必须这样写，不能全小写,后面的颜色为在对应颜色间进行渐变
        animator.setDuration(duration);
        animator.setEvaluator(new ArgbEvaluator());//如果要颜色渐变必须要ArgbEvaluator，来实现颜色之间的平滑变化，否则会出现颜色不规则跳动
        animator.start();
    }
}
