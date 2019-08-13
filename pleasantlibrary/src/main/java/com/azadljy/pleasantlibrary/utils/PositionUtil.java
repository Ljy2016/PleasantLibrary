package com.azadljy.pleasantlibrary.utils;

public class PositionUtil {


    public static int getXOnCircle(int cX, int r, int angel) {
        int x = (int) (cX + r * Math.cos(angel * (3.14 / 180)));
        return x;
    }

    public static int getYOnCircle(int cY, int r, int angel) {
        int x = (int) (cY + r * Math.sin(angel * (3.14 / 180)));
        return x;
    }

}
