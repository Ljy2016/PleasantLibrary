package com.azadljy.pleasantlibrary.utils;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.qw.soul.permission.SoulPermission;
import com.qw.soul.permission.bean.Permission;
import com.qw.soul.permission.bean.Permissions;
import com.qw.soul.permission.callbcak.CheckRequestPermissionListener;
import com.qw.soul.permission.callbcak.CheckRequestPermissionsListener;
import com.qw.soul.permission.callbcak.GoAppDetailCallBack;


public class PermissionUtil {

    public interface PermissionListener {
        void onSuccess();

        void onFailure();
    }

    public static void callLocation(final Context context, final PermissionListener permissionListener) {
        SoulPermission.getInstance().checkAndRequestPermission(Manifest.permission.ACCESS_COARSE_LOCATION, new CheckRequestPermissionListener() {
            @Override
            public void onPermissionOk(Permission permission) {
                permissionListener.onSuccess();
            }

            @Override
            public void onPermissionDenied(Permission permission) {
                if (permission.shouldRationale()) {
                    new AlertDialog.Builder(context)
                            .setTitle("提示")
                            .setMessage("如果拒绝权限，应用将无法获取该设备的定位信息，请授予权限")
                            .setPositiveButton("授予", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    callLocation(context, permissionListener);
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    permissionListener.onFailure();
                                }
                            })
                            .create().show();
                } else {
                    String permissionDesc = permission.getPermissionNameDesc();
                    new AlertDialog.Builder(context)
                            .setTitle("提示")
                            .setMessage(permissionDesc + "异常，请前往设置－>权限管理，打开" + permissionDesc + "。")
                            .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //去设置页
                                    SoulPermission.getInstance().goApplicationSettings(new GoAppDetailCallBack() {
                                        @Override
                                        public void onBackFromAppDetail(Intent data) {
                                            if (checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                                                permissionListener.onSuccess();
                                            }
                                        }
                                    });
                                }
                            }).create().show();
                }
            }
        });
    }

    public static void callReadAndWrite(final Context context, final PermissionListener permissionListener) {
        SoulPermission.getInstance().checkAndRequestPermissions(Permissions.build(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE), new CheckRequestPermissionsListener() {
            @Override
            public void onAllPermissionOk(Permission[] allPermissions) {
                permissionListener.onSuccess();
            }

            @Override
            public void onPermissionDenied(Permission[] refusedPermissions) {

            }
        });
    }


    public static boolean checkPermission(@NonNull String permission) {
        Permission checkResult = SoulPermission.getInstance().checkSinglePermission(permission);
        return checkResult.isGranted();
    }


    public static void showAlertDialog(final Context context, final PermissionListener permissionListener, String message, DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(context)
                .setTitle("提示")
                .setMessage(message)
                .setPositiveButton("授予", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        callLocation(context, permissionListener);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        permissionListener.onFailure();
                    }
                })
                .create().show();
    }
}
