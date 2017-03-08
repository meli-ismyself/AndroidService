package com.meliismyself.androidservices;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by meli.oktavia on 01/03/2017.
 */

public class MyStartedService extends Service {
    private static final String TAG = MyStartedService.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate, Thread name " + Thread.currentThread().getName());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand, Thread name " + Thread.currentThread().getName());

        int sleepTime = intent.getIntExtra("sleepTime", 1);

        new MyAsyncTask().execute(sleepTime);

        return START_STICKY;

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind, Thread name " + Thread.currentThread().getName());

        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.i(TAG, "onDestroy, Thread name " + Thread.currentThread().getName());
    }

    class MyAsyncTask extends AsyncTask<Integer, String, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Log.i(TAG, "onPreExecute , Thread name : " + Thread.currentThread().getName());
        }

        @Override
        protected Void doInBackground(Integer... params) {
            Log.i(TAG, "doInBackground , Thread name : " + Thread.currentThread().getName());
            int sleepTime = params[0];
            int ctr =1;

            while (ctr <= sleepTime){
                publishProgress("Counter is now " + ctr);

                try {
                    Thread.sleep(sleepTime * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                ctr++;
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

            Toast.makeText(MyStartedService.this, values[0], Toast.LENGTH_LONG).show();
            Log.i(TAG, "onProgressUpdate , Thread name : " + Thread.currentThread().getName());

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            stopSelf(); // Destroy the Service from within the Service class itself
            Log.i(TAG, "onPostExecute , Thread name : " + Thread.currentThread().getName());

        }
    }
}
