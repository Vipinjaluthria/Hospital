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

import com.example.hospital.Models.Article;
import com.example.hospital.PhoneAuth;
import com.example.hospital.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticlerViewHolder> {
    List<Article> articleList ;
    private Article currentArticle ;


    public ArticleAdapter(List<Article> articlesList) {
        this.articleList = articlesList ;
    }


    @NonNull
    @Override
    public ArticlerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View LayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article,null,false) ;
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT) ;
        LayoutView.setLayoutParams(lp);

        ArticlerViewHolder rcv = new ArticlerViewHolder(LayoutView) ;

        return rcv;
    }



    @Override
    public void onBindViewHolder(@NonNull ArticlerViewHolder holder, int position) {
        currentArticle =articleList.get(position) ;
        holder.articleDesc.setText(currentArticle.getArticleDescription());
        holder.articleHeading.setText(currentArticle.getArticleHeading());
        Picasso.get().load(currentArticle.getArticleImage()).into(holder.articleImg);


        holder.cardHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PhoneAuth.class) ;
                v.getContext().startActivity(intent);

            }
        });


    }



    @Override
    public int getItemCount() {
        return 4;
    }

    public class ArticlerViewHolder extends RecyclerView.ViewHolder {

        TextView articleHeading,articleDesc ;
        ImageView articleImg ;
        CardView cardHolder ;


        public ArticlerViewHolder(@NonNull View view) {
            super(view);
            articleHeading = view.findViewById(R.id.article_title_list);
            articleDesc = view.findViewById(R.id.article_desc_list);
            articleImg = view.findViewById(R.id.article_image_list);

            cardHolder = view.findViewById(R.id.article_card) ;

        }
    }
}
