package com.example.hospital.AdapterClass;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospital.Models.Appointment;
import com.example.hospital.PhoneAuth;
import com.example.hospital.R;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentrViewHolder> {
    List<Appointment> appointmentList ;
    private Appointment currentAppointment ;


    public AppointmentAdapter(List<Appointment> appointmentsList) {
        this.appointmentList = appointmentsList ;
    }


    @NonNull
    @Override
    public AppointmentrViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View LayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_appointment,parent,false) ;
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT) ;
        LayoutView.setLayoutParams(lp);

        AppointmentrViewHolder rcv = new AppointmentrViewHolder(LayoutView) ;

        return rcv;
    }



    @Override
    public void onBindViewHolder(@NonNull AppointmentrViewHolder holder, int position) {


        currentAppointment =appointmentList.get(position) ;
        holder.appointmentDoctorName.setText(currentAppointment.getDoctorName());
        holder.appointmentDoctorHospital.setText(currentAppointment.getDoctorHospital());
        holder.appointmentAdress.setText(currentAppointment.getAppointmentAddress());
        holder.appointmentTime.setText(currentAppointment.getAppointmentTime());
       // holder.appointmentDoctorHospital.setText(currentAppointment.getAppointmentDoctorHospital());
        holder.appointmentFees.setText(String.valueOf(currentAppointment.getAppointmentFees()));
        Picasso.get().load(currentAppointment.getAppointmentDoctorImage()).into(holder.appointmentDoctorImg);




    }



    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    public class AppointmentrViewHolder extends RecyclerView.ViewHolder {

        TextView appointmentDoctorName,appointmentDoctorHospital,appointmentAdress,appointmentTime ,appointmentFees;
        ImageView appointmentDoctorImg ;
        CardView cardHolder ;


        public AppointmentrViewHolder(@NonNull View view) {
            super(view);
            appointmentDoctorName = view.findViewById(R.id.appointment_doctor_name);
            appointmentDoctorHospital = view.findViewById(R.id.appointment_doctor_hospital);
            appointmentAdress = view.findViewById(R.id.appointment_address);
            appointmentTime = view.findViewById(R.id.appointment_time);
            appointmentFees= view.findViewById(R.id.appointment_fees);
            appointmentDoctorImg = view.findViewById(R.id.appointement_doctor_image) ;

            cardHolder = view.findViewById(R.id.item_appointment) ;

        }
    }
}

