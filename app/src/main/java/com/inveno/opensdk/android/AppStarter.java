package com.inveno.opensdk.android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RelativeLayout;

import com.inveno.opensdk.open.mvp.baseview.OpenBaseView;
import com.inveno.opensdk.open.mvp.param.AppParamsBuilder;

/**
 * 应用启动器，用来初始化接入英威诺信息流相关产品
 * 目前支持
 * Created by yunlnog.yang on 2018/6/9.
 */

public class AppStarter {
    private OpenBaseView workingView;
    private RelativeLayout lossPermissionLayout;
    private boolean userPermissionDenied = false;


    public void onActivityCreate(final Activity activity,OpenBaseView openBaseView){
        AppParamsBuilder.setProduct(  "openplatform","xoslauncher","bfa2bcd9fc9bb8ac905dd945a836a7a1","5e1d75af3ad0f99a08d71eb48645069bd96a3a3e");
        AppParamsBuilder.debug();
        workingView = openBaseView;
        lossPermissionLayout = activity.findViewById(R.id.permission_loss_layout);
        activity.findViewById(R.id.open_permission_setting_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPermissionSetting(v);
            }
        });
        AppPermissionUtil.checkPermission(activity, new Runnable() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        doWhenPermissionsGet();
                    }
                });
            }
        },false);
    }

    public void onActivityResume(final Activity activity){
        if(!userPermissionDenied) {
            if (!workingView.isInit()) {
                AppPermissionUtil.checkPermission(activity, new Runnable() {
                    @Override
                    public void run() {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                doWhenPermissionsGet();
                            }
                        });
                    }
                }, true);
            } else {
                workingView.onVisibilityChanged(true);
            }
        }else{
            lossPermissionLayout.setVisibility(View.VISIBLE);
            workingView.setVisibility(View.GONE);
        }
    }

    public void onActivityPause(Activity activity){
        if(workingView!=null) {
            workingView.onVisibilityChanged(false);
        }
    }

    public void onRequestPermissionsResult(Activity activity, int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        if(AppPermissionUtil.onRequestPermissionsResult(activity,requestCode,permissions,grantResults)){
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    doWhenPermissionsGet();
                }
            });
        }else{
            userPermissionDenied = true;
        }
    }

    private void doWhenPermissionsGet(){
        lossPermissionLayout.setVisibility(View.GONE);
        workingView.setVisibility(View.VISIBLE);
        workingView.init();
    }


    public void openPermissionSetting(View view) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", view.getContext().getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings","com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", view.getContext().getPackageName());
        }
        view.getContext().startActivity(localIntent);
        userPermissionDenied = false;
    }

    public void exitApp(){
        if(workingView!=null){
            workingView.onAppExit();
        }
    }
}
