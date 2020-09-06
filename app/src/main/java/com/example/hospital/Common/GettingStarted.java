package com.example.hospital.Common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hospital.AdapterClass.SliderAdapter;
import com.example.hospital.PhoneAuth;
import com.example.hospital.R;
import com.example.hospital.Screens.HomePageActivity;

public class GettingStarted extends AppCompatActivity {

    ViewPager2 viewPager2;
    LinearLayout dots ;
    TextView[] dotsList ;
    SliderAdapter sliderAdapter ;
    Button getStartedButton,nextButton,skipButton;
    Animation bottomAnim ;
    int currentPosition ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN   ) ;



        setContentView(R.layout.activity_getting_started);
        viewPager2 = findViewById(R.id.slider) ;
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        getStartedButton = findViewById(R.id.get_started_button) ;
        skipButton = findViewById(R.id.skip_btn) ;
        nextButton = findViewById(R.id.next_button) ;






        dots = findViewById(R.id.dots) ;
        sliderAdapter = new SliderAdapter(this) ;

        viewPager2.setAdapter(sliderAdapter);

        addDots(0) ;


        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position ;
                if(position == dotsList.length-1) {
                    getStartedButton.setAnimation(bottomAnim);
                    getStartedButton.setVisibility(View.VISIBLE);

                }else{

                    bottomAnim = AnimationUtils.loadAnimation(GettingStarted.this,R.anim.bottom_anim) ;

                    getStartedButton.setVisibility(View.INVISIBLE);
                }
                addDots(position);
                dotsList[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                super.onPageSelected(position);
            }
        });






    }

    private void addDots(int position) {
        dots.removeAllViews();
        System.out.println("Adding dots");
        dotsList = new TextView[4] ;
        for(int i=0 ;i<4 ;i++){
            dotsList[i] = new TextView(this) ;
            dotsList[i].setText(Html.fromHtml(
                    "&#8226;"
            )) ;

            dotsList[i].setTextSize(35);
            if(i==position){
                dotsList[i].setTextSize(40);
                dotsList[i].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            }
            dotsList[i].setTextColor(getResources().getColor(R.color.colorWhite));
            dots.addView(dotsList[i]);




        }

    }




    public void skipfn(View v) {
        Intent intent = new Intent(v.getContext(), HomePageActivity.class);
        startActivity(intent) ;
        finish();


    }



    public void nextFn(View v){
        if(currentPosition<dotsList.length-1) {
            viewPager2.setCurrentItem(currentPosition + 1);
        }

    }
}