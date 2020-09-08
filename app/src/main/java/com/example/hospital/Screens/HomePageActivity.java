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

import com.example.hospital.AdapterClass.ArticleAdapter;
import com.example.hospital.Models.Article;
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
    RecyclerView recyclerView,articlesRecyclerView;
    SpecialistAdapter specialistAdapter;
    List<Specialist> specialistList=new ArrayList<>();
    FirebaseFirestore firebaseFirestore;
    List<Article> articlesList ;
    RecyclerView.Adapter articleAdapter ;

    RecyclerView.LayoutManager articleListManager;



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


        firebaseFirestore.collection("specialities").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (QueryDocumentSnapshot doc:task.getResult()) {
                    Specialist specialist = new Specialist(doc.getString("image"), doc.getId());
                    specialistList.add(specialist);
                    specialistAdapter.notifyDataSetChanged();
                }
            }

        });
        specialistAdapter = new SpecialistAdapter(getApplicationContext(), specialistList,this);

        recyclerView.setAdapter(specialistAdapter);
        navigationView.bringToFront();

        articlesList = new ArrayList<>() ;
        getArticles() ;

        initializeArticleRecyclerView();


    }

    private void getArticles() {
        Article demoArticle  = new Article("What You Need To Know About Asthma ?",
                "https://res-3.cloudinary.com/sharecare/image/upload/c_fill,dpr_1,f_auto,h_340,w_600/v1582741298/articles/what-you-need-to-know-about-asthma",
                "Your airways are responsible for carrying air into and out of your lungs, but what happens when they don’t function properly? ",
                "ajhawokasohasfaisufjujs"
                ) ;
        Article demoArticle2  = new Article("Can Your Makeup Really Give You Cancer?",
                "https://res-3.cloudinary.com/sharecare/image/upload/c_fill,dpr_1,f_auto,h_340,w_600/v1492100246/articles/can-makeup-give-you-cancer",
                "Ahh the end of a long day; time to relax, kick off your shoes and… clean the layers of caked-on makeup off your face. What’s even in that stuff? Could it be secretly hurting your health? We asked Gina Kirk, an expert esthetician specializing in dermatology at LewisGale Medical Center in Salem, Virginia, to discuss common cosmetic ingredients, and to weigh in on whether they could pose any long-term health risks.",
                "fawoieajjnfdiuahuaew"
                ) ;


        articlesList.add(demoArticle) ;
        articlesList.add(demoArticle2) ;
        articlesList.add(demoArticle) ;
        articlesList.add(demoArticle2) ;

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







    private void initializeArticleRecyclerView() {
        articlesRecyclerView = findViewById(R.id.article_list_recycler_view) ;
        articlesRecyclerView.setNestedScrollingEnabled(false) ;//to scroll seemlessly
        articlesRecyclerView.setHasFixedSize(false);//to scroll
        articleListManager= new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL,false) ;
        //for divider
        articlesRecyclerView.setLayoutManager(articleListManager);
        //adapter controls where each card to list and adds it to certains postiion
        articleAdapter = new ArticleAdapter(articlesList) ;
        //viewHolder gets data from xml and lets us use it
        articlesRecyclerView.setAdapter(articleAdapter);
    }
}