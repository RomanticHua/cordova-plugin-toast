package com.king.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId = Utils.getId("layout", "activity_splash");
        setContentView(layoutId);

        int btn = Utils.getId("id", "btn_splash");
        findViewById(btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SplashActivity.this, "帅哥！！", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
