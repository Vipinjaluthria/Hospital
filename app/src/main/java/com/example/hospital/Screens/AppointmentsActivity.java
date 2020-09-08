package com.example.hospital.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.hospital.AdapterClass.AppointmentAdapter;
//import com.example.hospital.AdapterClass.AppointmentAdapter;
import com.example.hospital.Models.Appointment;
import com.example.hospital.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppointmentsActivity extends AppCompatActivity {

    RecyclerView appointmentsRecyclerView ;
    RecyclerView.LayoutManager appointmentListManager ;
    AppointmentAdapter appointmentAdapter ;
    List<Appointment> appointmentsList ;
    LinearLayout NoAppointmentsView ;
    FirebaseFirestore db  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);
        db= FirebaseFirestore.getInstance() ;
        appointmentsList = new ArrayList<>() ;

        NoAppointmentsView = findViewById(R.id.no_appointments) ;
        NoAppointmentsView.setVisibility(View.VISIBLE);
        initializeAppointmentRecyclerView();
        getAppointMents() ;


    }

    private void getAppointMents() {

        db.collection("appointments").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Date date ;
                Appointment currappointment ;
                for(QueryDocumentSnapshot doc : task.getResult()){
                    System.out.println("data");
                    date = doc.getTimestamp("time").toDate() ;
                    currappointment = new Appointment(doc.getString("doctor"),doc.getString("hospital"),doc.getId(),date.toString(),doc.getString("address"),(int)Math.round(doc.getDouble("fees")),doc.getBoolean("is_complete"),false,
                            false,"https://media.gettyimages.com/photos/woman-lifts-her-arms-in-victory-mount-everest-national-park-picture-id507910624?s=2048x2048"
                    ) ;

                    appointmentsList.add(currappointment) ;

                    appointmentsList.add(currappointment) ;
                    appointmentsList.add(currappointment) ;
                    appointmentsList.add(currappointment) ;
                    appointmentsList.add(currappointment) ;

                    appointmentAdapter.notifyDataSetChanged();
                    System.out.println(appointmentsList);

                }

            }
        }) ;

        if(appointmentsList.size() ==0){
            NoAppointmentsView.setVisibility(View.INVISIBLE);
        }

        else NoAppointmentsView.setVisibility(View.VISIBLE);

    }

    private void initializeAppointmentRecyclerView() {
        appointmentsRecyclerView = findViewById(R.id.appointments_recycler_view) ;
        appointmentsRecyclerView.setNestedScrollingEnabled(false) ;//to scroll seemlessly
        appointmentsRecyclerView.setHasFixedSize(false);//to scroll
        appointmentListManager= new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL,false) ;
        //for divider
        appointmentsRecyclerView.setLayoutManager(appointmentListManager);
        //adapter controls where each card to list and adds it to certains postiion
        appointmentAdapter = new AppointmentAdapter(appointmentsList) ;
        //viewHolder gets data from xml and lets us use it
        appointmentsRecyclerView.setAdapter(appointmentAdapter);

    }
}