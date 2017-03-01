package com.meliismyself.androidservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class StartedServiceActivity extends AppCompatActivity {
    String TAG = StartedServiceActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_started_service);
    }

    public void startStartedService(View view) {
        try {
            Intent intent = new Intent(StartedServiceActivity.this, MyStartedService.class);
            startService(intent);
        }catch (Exception e){
            Log.d(TAG, " ERROR SERVICE " + e.getMessage());
        }

    }

    public void stopStartedService(View view) {
        Intent intent = new Intent(StartedServiceActivity.this, MyStartedService.class);
        stopService(intent);
    }
}
