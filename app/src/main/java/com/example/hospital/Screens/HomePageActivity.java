package com.example.hospital.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.hospital.Models.Specialist;
import com.example.hospital.R;
import com.example.hospital.Specialists.SpecialistAdapter;
import com.example.hospital.Specialists.onNoteClick;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener, onNoteClick {

    DrawerLayout drawerLayout ;
    NavigationView navigationView ;
    static final float END_SCALE =0.7f ;
    ImageView HamBurger ;
    LinearLayout contentView ;
    RecyclerView recyclerView;
    SpecialistAdapter specialistAdapter;
    List<Specialist> specialistList=new ArrayList<>();
    FirebaseFirestore firebaseFirestore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home_page);
        firebaseFirestore=FirebaseFirestore.getInstance();

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        contentView = findViewById(R.id.home_content);
        recyclerView = findViewById(R.id.specialistrecylerview);
        HamBurger = findViewById(R.id.ham_burger);
        //drawer button
        HamBurger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else drawerLayout.openDrawer(GravityCompat.START);
                animateDrawer();

            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);


        firebaseFirestore.collection("Specialist").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (QueryDocumentSnapshot doc:task.getResult()) {
                    Specialist specialist = new Specialist("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQcTa0HAi8nPpenLZemVehBMtO2az3PQ8UZkg&usqp=CAU", doc.getId());
                    specialistList.add(specialist);
                    specialistAdapter.notifyDataSetChanged();
                }
            }

        });
        specialistAdapter = new SpecialistAdapter(getApplicationContext(), specialistList,this);

        recyclerView.setAdapter(specialistAdapter);
        navigationView.bringToFront();

    }




    private void animateDrawer() {
        drawerLayout.setScrimColor(getResources().getColor(R.color.colorPrimary));

        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });

    }


    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else  super.onBackPressed();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {




        return true;
    }

    @Override
    public void OnNoteClick(int position) {

        startActivity(new Intent(getApplicationContext(),SearchDoctor.class));
    }
}