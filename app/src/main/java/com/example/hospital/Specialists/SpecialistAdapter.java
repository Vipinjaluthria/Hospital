package com.example.hospital.Specialists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospital.Models.Specialist;
import com.example.hospital.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SpecialistAdapter extends RecyclerView.Adapter<SpecialistViewholder> {
    Context context;
    List<Specialist> specialistList;
    onNoteClick onNoteClick;

    public SpecialistAdapter(Context context, List<Specialist> specialistList,onNoteClick onNoteClick) {
        this.context = context;
        this.specialistList = specialistList;
        this.onNoteClick=onNoteClick;
    }

    @NonNull
    @Override
    public SpecialistViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.specialist_itmes,parent,false);
        return new SpecialistViewholder(view,onNoteClick);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialistViewholder holder, int position) {
        holder.textView.setText(specialistList.get(position).getText());
        Picasso.get().load(specialistList.get(position).getImg()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return specialistList.size();
    }
}
