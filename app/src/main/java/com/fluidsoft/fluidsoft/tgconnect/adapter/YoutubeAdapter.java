package com.fluidsoft.fluidsoft.tgconnect.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.fluidsoft.fluidsoft.tgconnect.R;

import java.util.List;

public class YoutubeAdapter extends RecyclerView.Adapter<YoutubeAdapter.VideoViewHolder> {

    List<YoutubeVideo> youtubeVideosList;

    public YoutubeAdapter(List<YoutubeVideo> youtubeVideosList) {
        this.youtubeVideosList = youtubeVideosList;
    }

    public YoutubeAdapter() {
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_video, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {

     holder.webView.loadData(youtubeVideosList.get(position).getVideoUrl(),"text/html","utf-8");

    }




    @Override
    public int getItemCount() {

        return youtubeVideosList.size();

    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {

        WebView webView;

        public VideoViewHolder(View itemView) {
            super(itemView);

            webView = (WebView) itemView.findViewById(R.id.webview);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebChromeClient(new WebChromeClient() {


            });
        }
    }


}
