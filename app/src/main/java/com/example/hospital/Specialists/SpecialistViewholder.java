package com.example.hospital.Specialists;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospital.R;

public class SpecialistViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ImageView imageView;
    TextView textView;
    onNoteClick onNoteClick;
    public SpecialistViewholder(@NonNull View itemView,onNoteClick onNoteClick) {
        super(itemView);
        imageView=itemView.findViewById(R.id.specialist_items_image);
        textView=itemView.findViewById(R.id.specialist_items_text);
        this.onNoteClick=onNoteClick;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    onNoteClick.OnNoteClick(getAdapterPosition());
    }
}
