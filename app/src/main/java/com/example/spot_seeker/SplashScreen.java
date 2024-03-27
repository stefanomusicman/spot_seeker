package com.example.spot_seeker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    //SplashScreen timer to go to different screen
    private static final int SPLASH_SCREEN_DURATION = 4000;
    //Variables Animation
    Animation topAnim, bottomAnim;
    ImageView logo,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        initialize();

        //Go to another activity with animation
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, LoginScreen.class);
                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View, String>(logo, "logo_image");
                pairs[1] = new Pair<View, String>(name, "logo_name");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashScreen.this, pairs);
                startActivity(intent, options.toBundle());
            }
        }, SPLASH_SCREEN_DURATION);
    }

    private void initialize(){

        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        //Location of resources
        logo = findViewById(R.id.logoView);
        name = findViewById(R.id.nameView);
        //Set animation to elements
        logo.setAnimation(topAnim);
        name.setAnimation(bottomAnim);

    }

}