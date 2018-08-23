package com.inveno.opensdk.android;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.inveno.opensdk.android.support.TranslucentStatusUtil;
import com.inveno.opensdk.open.mvp.cardview.CardStyleOperator;
import com.inveno.opensdk.open.mvp.cardview.OpenCardView;

public class OpenCardActivity extends AppCompatActivity {
    private AppStarter appStarter;
    private ObservableScrollView mCardContainerScrollView;
    private OpenCardView openCardFlowView;
    private CardStyleOperator mCardStyleOperator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        TranslucentStatusUtil.translucentStatus(this);
        mCardContainerScrollView = findViewById(R.id.card_container_scroll_view);
        openCardFlowView = findViewById(R.id.open_card_view);
        mCardStyleOperator = openCardFlowView.getStyleOperator();
        // 设置卡片标题栏背景色和标题字颜色
        mCardStyleOperator.setCardTitleColor(0x85FFFFFF,0xFFFFFFFF);
        // 设置卡片背景颜色
        mCardStyleOperator.setCardBackground(0x55FFFFFF);
        // 设置卡片标题
        mCardStyleOperator.setCardTitle("资讯速报");
        // 设置卡片刷新图标
        mCardStyleOperator.setRefreshIcon(R.mipmap.card_icon_refresh);
        // 设置资讯项颜色，资讯标题颜色，资讯来源颜色和资讯时间颜色
        mCardStyleOperator.setNewsColor(0xFF000000,0xFFb3b3b3,0xFFb3b3b3);
        appStarter = new AppStarter();
        appStarter.onActivityCreate(this,openCardFlowView);
        // 当包含卡片的组件发生滑动时，需要通知卡片View
        mCardContainerScrollView.setScrollViewListener(new ObservableScrollView.OnScrollViewChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                openCardFlowView.onScroll();
            }
        });
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
