package com.fluidsoft.fluidsoft.tgconnect.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fluidsoft.fluidsoft.tgconnect.NewsDetails;
import com.fluidsoft.fluidsoft.tgconnect.R;
import com.fluidsoft.fluidsoft.tgconnect.model.News;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> implements RecyclerView.OnItemTouchListener {

    private ArrayList<News> newsData;
    private Activity activity;
    Context ctx;
    Bitmap b; // your bitmap

    public NewsAdapter(ArrayList<News> data, Activity mactivity, Context ctx) {
        this.newsData = data;
        this.activity = mactivity;
        this.ctx = ctx;
    }

    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_news_cardview, parent, false);
        return new NewsHolder(view, ctx, newsData);
    }

    @Override
    public void onBindViewHolder(NewsHolder holder, int position) {

        News news = newsData.get(position);

        holder.setTxtnewTitle(news.getTitle());
//        holder.setTxtnewsDesc(news.getDescription());
//        holder.setTxtnewsUrl(news.getUrl());

        Glide.with(activity).load(news.getImageUrl()).into(holder.imageView);

    }


    @Override
    public int getItemCount() {
        if (newsData == null)
            return 0;
        return newsData.size();

    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {


    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public class NewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public ImageView imageView;
        public TextView txtnewTitle, txtnewsDesc, txtnewsUrl;
        ArrayList<News> news = new ArrayList<News>();
        Context ctx;

        public NewsHolder(View itemView, Context ctx, ArrayList<News> news) {
            super(itemView);
            this.news = news;
            this.ctx = ctx;
//            Context ctx=ctx;

            imageView = (ImageView) itemView.findViewById(R.id.imageview_news_image);
            txtnewTitle = (TextView) itemView.findViewById(R.id.textview_enws_title);
            imageView.setOnClickListener(this);
//            txtnewsDesc=(TextView)itemView.findViewById(R.id.textview_news_description);
//            txtnewsUrl=(TextView)itemView.findViewById(R.id.textView_news_url);


        }

        /*public void setImageView(String imageView) {
//            this.imageView = imageView;

            imageView.
        }*/

        public void setTxtnewTitle(String title) {
//            this.txtnewTitle = txtnewTitle;
            txtnewTitle.setText(title);
        }

        public void setTxtnewsDesc(String description) {
//            this.txtnewsDesc = txtnewsDesc;
            txtnewsDesc.setText(description);
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            News news = this.news.get(position);
            Intent intent = new Intent(this.ctx, NewsDetails.class);

            intent.putExtra("Article_Header", news.getTitle());
            intent.putExtra("Article_Body", news.getDescription());
            intent.putExtra("IMG_URL", news.getImageUrl());
            this.ctx.startActivity(intent);


        }

       /* public void setTxtnewsUrl(String url) {
            this.txtnewsUrl = txtnewsUrl;
            txtnewsUrl.setText(url);
        }*/
    }

}
