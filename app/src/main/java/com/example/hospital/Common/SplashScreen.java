package com.example.hospital.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hospital.PhoneAuth;
import com.example.hospital.R;
import com.example.hospital.Screens.HomePageActivity;

public class SplashScreen extends AppCompatActivity {

    ImageView logoImage  ;
    private static int SPLASH_SCREEN = 3000 ;



    SharedPreferences gettingStarted ;
    Animation fadeIn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN   ) ;
        setContentView(R.layout.splash_screen);


        logoImage = findViewById(R.id.logoImage) ;

        fadeIn = AnimationUtils.loadAnimation(this,R.anim.opacity_anim) ;



        logoImage.setAnimation(fadeIn);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                gettingStarted = getSharedPreferences("gettingStarted",MODE_PRIVATE) ;
                boolean firstTime = gettingStarted.getBoolean("firstTime",true) ;
                if(firstTime){

                    SharedPreferences.Editor editor = gettingStarted.edit() ;
                    editor.putBoolean("firstTime",false);
                    editor.commit() ;

                Intent gettingStartedIntent = new Intent(SplashScreen.this, GettingStarted.class) ;
                startActivity(gettingStartedIntent) ;
                finish();}
                else {
                    Intent gettingStartedIntent = new Intent(SplashScreen.this, HomePageActivity.class) ;
                    startActivity(gettingStartedIntent) ;
                    finish();
                }


            }
        },SPLASH_SCREEN) ;




    }
}