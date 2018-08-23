package com.inveno.opensdk.android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.inveno.opensdk.android.support.TranslucentStatusUtil;
import com.inveno.se.volley.DefaultRetryPolicy;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "INVENO_BACKSTAGE_CTRL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TranslucentStatusUtil.translucentStatus(this);
        launchCustomSettings(this);
    }

    public void accessNewsFlow(View view) {
        startActivity(new Intent(this,OpenNewFlowActivity.class));
        finish();
    }

    public void accessCardView(View view) {
        startActivity(new Intent(this,OpenCardActivity.class));
        finish();
    }
    private static void launchCustomSettings(MainActivity mainActivity){
        File cardConfigFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"inveno_card");
        Log.i(TAG,"CardConfigFile path:"+cardConfigFile.getAbsolutePath());
        File mainConfigFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"inveno_main");
        File flowConfigFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"inveno_flow");
        Log.i(TAG,"FlowConfigFile path:"+flowConfigFile.getAbsolutePath());
        File volleyTimeoutFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"inveno_volley_timeout");
        Log.i(TAG,"VolleyTimeoutFile path:"+volleyTimeoutFile.getAbsolutePath());
        if(volleyTimeoutFile.exists()){
            try {
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS = readStreamInt(new FileInputStream(volleyTimeoutFile));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(cardConfigFile.exists()){
            mainActivity.accessCardView(null);
        }else if(mainConfigFile.exists()){

        }else if(flowConfigFile.exists()){
            mainActivity.accessNewsFlow(null);
        }else{

        }
    }

    private static int readStreamInt(InputStream inputStream) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        byte[] data = new byte[dataInputStream.available()];
        dataInputStream.read(data);
        StringBuilder builder = new StringBuilder();
        for(byte item :data){
            if(item>='0' && item<='9'){
                builder.append((char) item);
            }else {
                break;
            }
        }
        int timeOut = Integer.parseInt(builder.toString());
        dataInputStream.close();
        return timeOut;
    }
}
