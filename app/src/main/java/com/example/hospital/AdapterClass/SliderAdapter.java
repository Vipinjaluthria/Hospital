package com.example.hospital.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.hospital.R;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder>{


    Context ctx ;
    LayoutInflater layoutInflater ;
    int images[] = {
            R.drawable.splash_1,
            R.drawable.splash_2,
            R.drawable.splash_3,
            R.drawable.splash_4

    } ;
    final int  headings[] = {

            R.string.splash_string_2,
            R.string.splash_string_1,
            R.string.splash_string_3,
            R.string.splash_string_4
    } ;

    int descs[] = {

            R.string.splash_desc_2,
            R.string.splash_desc_1,
            R.string.splash_desc_3,
            R.string.splash_desc_4
    } ;

    public SliderAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public SliderAdapter.SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.slide_layout, parent, false);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderAdapter.SliderViewHolder holder, int position) {
        holder.sliderImage.setImageResource(images[position]);
        holder.sliderHeading.setText(headings[position]);
        holder.sliderDesc.setText(descs[position]);


    }

    @Override
    public int getItemCount() {
        return 4;
    }


    public class SliderViewHolder extends RecyclerView.ViewHolder{
        ImageView sliderImage ;
        TextView sliderHeading,sliderDesc ;

        public SliderViewHolder(@NonNull View view) {
            super(view);
            sliderImage = view.findViewById(R.id.slider_image) ;
        sliderHeading = view.findViewById(R.id.slider_heading) ;
         sliderDesc= view.findViewById(R.id.slider_desc) ;

        }
    }


}
