package com.fluidsoft.fluidsoft.tgconnect.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fluidsoft.fluidsoft.tgconnect.R;
import com.fluidsoft.fluidsoft.tgconnect.model.EventPrevious;

import java.util.ArrayList;

public class EventsPreviousAdapter extends RecyclerView.Adapter<EventsPreviousAdapter.EventPreviousHolder> implements RecyclerView.OnClickListener {
    private ArrayList<EventPrevious> eventPreviouses;
    private Activity activity;

    public EventsPreviousAdapter(ArrayList<EventPrevious> eventPreviouses, Activity activity) {
        this.eventPreviouses = eventPreviouses;
        this.activity = activity;
    }

    @Override
    public EventPreviousHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_previous_events, parent, false);
        return new EventPreviousHolder(view);
    }

    @Override
    public void onBindViewHolder(EventPreviousHolder holder, int position) {
        final EventPrevious event = eventPreviouses.get(position);

        holder.setTxtEventPreviousDate(event.getDateEventPrevious());
        holder.setTxtEventPreviousLocation(event.getLocationEventPrevious());
        holder.setTxtEventPreviousTime(event.getTimeEventPrevious());

        Glide.with(activity).load(event.getImageEventPrevious()).into(holder.imageViewEventsPrevious);

    }

    @Override
    public int getItemCount() {
        if (eventPreviouses == null)
            return 0;
        return eventPreviouses.size();
    }

    @Override
    public void onClick(View v) {

    }

    public class EventPreviousHolder extends RecyclerView.ViewHolder {


        public ImageView imageViewEventsPrevious;
        public TextView txtEventPreviousDate, txtEventPreviousLocation, txtEventPreviousTime;
//        public Button btnJoinEvent;

        public EventPreviousHolder(View itemView1) {
            super(itemView1);

            imageViewEventsPrevious = (ImageView) itemView1.findViewById(R.id.imageview_event_previous);
            txtEventPreviousDate = (TextView) itemView1.findViewById(R.id.txt_event_date_previous);
            txtEventPreviousLocation = (TextView) itemView1.findViewById(R.id.txt_event_location_previous);
            txtEventPreviousTime = (TextView) itemView1.findViewById(R.id.txt_event_time_previous);
//            btnJoinEvent = (Button) itemView1.findViewById(R.id.btn_join_event);

        }

        public void setTxtEventPreviousDate(String date) {
//            this.txtEventDate = txtEventDate;
            txtEventPreviousDate.setText(date);
        }

        public void setTxtEventPreviousLocation(String loc) {
//            this.txtEventPreviousLocation = txtEventPreviousLocation;

            txtEventPreviousLocation.setText(loc);
        }

        public void setTxtEventPreviousTime(String time) {
//            this.txtEventTime = txtEventTime;
            txtEventPreviousTime.setText(time);
        }

    }
}