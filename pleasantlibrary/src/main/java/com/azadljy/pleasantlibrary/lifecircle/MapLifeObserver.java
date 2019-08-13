package com.azadljy.pleasantlibrary.lifecircle;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import com.amap.api.maps.MapView;

public class MapLifeObserver implements DefaultLifecycleObserver {

    public static final String TAG = "MapLifeObserver";
    protected MapView mMapView = null;

    public MapLifeObserver(@NonNull  MapView mMapView) {
        this.mMapView = mMapView;
    }
    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        mMapView.onResume();
        Log.e(TAG, "onResume: " );
    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {
        mMapView.onPause();
        Log.e(TAG, "onPause: ");
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {

    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {

    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {

    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        mMapView.onDestroy();
        Log.e(TAG, "onDestroy: ");
    }


}