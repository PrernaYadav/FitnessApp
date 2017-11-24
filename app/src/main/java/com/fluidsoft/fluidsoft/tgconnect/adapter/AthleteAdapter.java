package com.fluidsoft.fluidsoft.tgconnect.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fluidsoft.fluidsoft.tgconnect.R;
import com.fluidsoft.fluidsoft.tgconnect.model.Athlete;

import java.util.ArrayList;

public class AthleteAdapter extends RecyclerView.Adapter<AthleteAdapter.AthleteHolder> implements RecyclerView.OnItemTouchListener {
    private ArrayList<Athlete> athleteData;
    private Activity activity;

    public AthleteAdapter(ArrayList<Athlete> athleteData, Activity activity) {
        this.athleteData = athleteData;
        this.activity = activity;
    }

    @Override
    public AthleteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_athlete, parent, false);
        return new AthleteHolder(view);
    }

    @Override
    public void onBindViewHolder(final AthleteHolder holder, int position) {

        final Athlete athlete = athleteData.get(position);
        holder.setTxtAthleteName(athlete.getAthleteName());
        Glide.with(activity).load(athlete.getAthletePhoto()).into(holder.imageViewAthletePhoto);
//        Glide.with(activity).load(athlete.getAthleteLike()).into(holder.imageViewAthleteLike);
        holder.imageViewathleteShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = athlete.getAthletePhoto();
                String shareBody1 = athlete.getAthleteName();
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Photo Share by TG CONNECT :  "+"\n"+shareBody+"\n"+"Athlete Name"+"\n"+shareBody1+"\n"+"By TG CONNECT ");
                activity.startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

        holder.imageViewAthleteLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.imageViewAthleteLike.setImageResource(R.drawable.liked);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (athleteData == null)
            return 0;
        return athleteData.size();
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

    public class AthleteHolder extends RecyclerView.ViewHolder {


        public ImageView imageViewAthletePhoto, imageViewAthleteLike, imageViewathleteShare;
        public TextView textAthleteName;

        public AthleteHolder(View itemView1) {
            super(itemView1);


            imageViewAthletePhoto = (ImageView) itemView1.findViewById(R.id.imageview_athlete_photo);
            imageViewAthleteLike = (ImageView) itemView1.findViewById(R.id.imagebutton_athlete_like);
            imageViewathleteShare = (ImageView) itemView1.findViewById(R.id.imagebutton_athlete_share);
            textAthleteName = (TextView) itemView1.findViewById(R.id.textView_athlete_name);


        }

        public void setTxtAthleteName(String txtAthleteName) {

            textAthleteName.setText(txtAthleteName);
        }
    }

}