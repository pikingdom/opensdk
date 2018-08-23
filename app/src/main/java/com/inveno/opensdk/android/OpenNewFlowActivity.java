package com.inveno.opensdk.android;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.inveno.opensdk.android.support.TranslucentStatusUtil;
import com.inveno.opensdk.open.mvp.view.FlowStyleOperator;
import com.inveno.opensdk.open.mvp.view.OpenNewFlowView;

public class OpenNewFlowActivity extends AppCompatActivity {
    private AppStarter appStarter;
    private OpenNewFlowView openNewFlowView;
    private FlowStyleOperator styleOperator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_new_flow);
        TranslucentStatusUtil.translucentStatus(this);
        openNewFlowView = findViewById(R.id.open_new_flow_view);
        styleOperator = openNewFlowView.getStyleOperator();
        // 设置频道条颜色，频道条背景颜色、频道文字正常颜色、频道文字选中颜色
        styleOperator.setChannelColor(0xFFFFFFFF,
                0xFF1A1A1A,0xFFF9622F);
        // 设置信息流背景颜色
        styleOperator.setFlowBackground(0xFFFFFFFF);
        // 设置资讯项颜色，资讯标题颜色，资讯来源颜色和资讯时间颜色
        styleOperator.setNewsColor(0xFF000000,0xFFb3b3b3,0xFFb3b3b3);
        appStarter = new AppStarter();
        appStarter.onActivityCreate(this, openNewFlowView);

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void exitApp(){
        appStarter.exitApp();
        System.exit(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        exitApp();
    }

    @Override
    protected void onResume() {
        super.onResume();
        appStarter.onActivityResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        appStarter.onActivityPause(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        appStarter.onRequestPermissionsResult(this,requestCode,permissions,grantResults);
    }
}
