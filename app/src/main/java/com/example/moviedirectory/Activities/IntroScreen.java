package com.example.moviedirectory.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviedirectory.R;

public class IntroScreen extends Activity {

    private TextView appName;
    private ImageView appLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screen);

        appLogo = findViewById(R.id.appLogo);
        appName = findViewById(R.id.appName);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startEnterAnimation();
            }
        }, 500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }, 3000);
    }

    private void startEnterAnimation() {
        appName.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
        appLogo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.topdown));

        appLogo.setVisibility(View.VISIBLE);
        appName.setVisibility(View.VISIBLE);
    }

}
