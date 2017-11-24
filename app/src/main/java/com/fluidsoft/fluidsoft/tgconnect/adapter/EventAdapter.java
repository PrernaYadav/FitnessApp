package com.fluidsoft.fluidsoft.tgconnect.adapter;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fluidsoft.fluidsoft.tgconnect.Jsonparse;
import com.fluidsoft.fluidsoft.tgconnect.R;
import com.fluidsoft.fluidsoft.tgconnect.UserInformation;
import com.fluidsoft.fluidsoft.tgconnect.model.Event;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventsHolder> implements RecyclerView.OnItemTouchListener {
    private ArrayList<Event> eventData;
    private Activity mActivity;
    String join;
    String jj,out1;
    int o;
    private final Jsonparse jsonparse = new Jsonparse(mActivity);

    public EventAdapter(ArrayList<Event> data, Activity activity) {

        this.eventData = data;
        this.mActivity = activity;
    }

    @Override
    public EventsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_events, parent, false);
        return new EventsHolder(view);

    }

    @Override
    public void onBindViewHolder(EventsHolder holder, final int position) {
        final Event event = eventData.get(position);

        holder.setTxtEventDate(event.getDateEvent());
        holder.setTxtEventLocation(event.getLocationEvent());
        holder.setTxtEventTime(event.getTimeEvent());

        Glide.with(mActivity).load(event.getImageEvent()).into(holder.imageViewEvents);

        try {

            holder.btnJoinEvent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    jj=String.valueOf(position+1);
                    new upcomingEvents().execute();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (eventData == null)
            return 0;
        return eventData.size();

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

    public class EventsHolder extends RecyclerView.ViewHolder {


        public ImageView imageViewEvents;
        public TextView txtEventDate, txtEventLocation, txtEventTime;
        public Button btnJoinEvent;

        public EventsHolder(View itemView1) {
            super(itemView1);

            imageViewEvents = (ImageView) itemView1.findViewById(R.id.imageview_event);
            txtEventDate = (TextView) itemView1.findViewById(R.id.txt_event_date);
            txtEventLocation = (TextView) itemView1.findViewById(R.id.txt_event_location);
            txtEventTime = (TextView) itemView1.findViewById(R.id.txt_event_time);
            btnJoinEvent = (Button) itemView1.findViewById(R.id.btn_join_event);

        }

        public void setTxtEventDate(String date) {
//            this.txtEventDate = txtEventDate;
            txtEventDate.setText(date);
        }

        public void setTxtEventLocation(String loc) {
            this.txtEventLocation = txtEventLocation;

            txtEventLocation.setText(loc);
        }

        public void setTxtEventTime(String time) {
            this.txtEventTime = txtEventTime;
            txtEventTime.setText(time);
        }

        public void setBtnJoinEvent(String join) {
            btnJoinEvent.setText(join);
        }
    }


    private class upcomingEvents extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {

            ArrayList<NameValuePair> param = new ArrayList<>();
            param.add(new BasicNameValuePair("Event_Code", jj));

            Log.i("param", "param:" + param);

            try {

                //------------------>>
                HttpGet httppost = new HttpGet(UserInformation.joinEvent);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);


                JSONObject jobj = jsonparse.makeHttpRequest(UserInformation.joinEvent, "POST", param);


                // StatusLine stat = response.getStatusLine();
                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    mActivity.runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(mActivity, "Event Joined", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(mActivity,"Try again",Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;


        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            Log.i("tag", "" + result);

        }
    }

}
