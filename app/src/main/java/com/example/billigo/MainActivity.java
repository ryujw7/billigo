package com.example.billigo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class MainActivity extends BaseActivity {
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(getApplication(), HomeActivity.class));
                        MainActivity.this.finish();
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    }
                });

            }
        }).start();
    }
}
