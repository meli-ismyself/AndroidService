package com.meliismyself.androidservices;

import android.content.Intent;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class StartedServiceActivity extends AppCompatActivity {
    String TAG = StartedServiceActivity.class.getSimpleName();
    private TextView tvStartedServiceResult, tvIntentServiceResult;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_started_service);

        tvStartedServiceResult = (TextView) findViewById(R.id.tvStartedServiceResult);
        tvIntentServiceResult = (TextView) findViewById(R.id.tvIntentServiceResult);
    }

    public void startStartedService(View view) {
        try {
            Intent intent = new Intent(StartedServiceActivity.this, MyStartedService.class);
            intent.putExtra("sleepTime", 10);
            startService(intent);
        }catch (Exception e){
            Log.d(TAG, " ERROR SERVICE " + e.getMessage());
        }

    }

    public void stopStartedService(View view) {
        Intent intent = new Intent(StartedServiceActivity.this, MyStartedService.class);
        stopService(intent);
    }

    public void startIntentService(View view) {
        MyResultReceiver resultReceiver = new MyResultReceiver(null);

        Intent intent = new Intent(StartedServiceActivity.this, MyIntentService.class);
        intent.putExtra("sleepTime", 10);
        intent.putExtra("receiver", resultReceiver);
        startService(intent);
    }

    public class MyResultReceiver extends android.os.ResultReceiver{

        /**
         * Create a new ResultReceive to receive results.  Your
         * {@link #onReceiveResult} method will be called from the thread running
         * <var>handler</var> if given, or from an arbitrary thread if null.
         *
         * @param handler
         */
        public MyResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);

            Log.i("MyResultReceiver", Thread.currentThread().getName());

            if (resultCode == 18 && resultData != null){
                final String result = resultData.getString("resultIntentService");

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("MyHandler", Thread.currentThread().getName());

                        tvIntentServiceResult.setText(result);

                    }
                });
            }
        }
    }
}
