package com.example.spot_seeker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    //SplashScreen timer to go to different screen
    private static final int SPLASH_SCREEN = 4000;
    //Variables Animation
    Animation topAnim, bottomAnim;
    ImageView logo,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //to make splashScreen Full screen and hide the taskbar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        initialize();
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