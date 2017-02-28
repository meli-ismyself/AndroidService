package com.meliismyself.androidservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartedServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_started_service);
    }

    public void startStartedService(View view) {
        Intent intent = new Intent(StartedServiceActivity.this, MyStartedService.class);
        startActivity(intent);
    }

    public void stopStartedService(View view) {
        Intent intent = new Intent(StartedServiceActivity.this, MyStartedService.class);
        stopService(intent);
    }
}
