package com.aya.app.notivigation;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;

public class VideoPlayerList extends AppCompatActivity {

    private VideoView       videoView1,videoView2,videoView3,videoView4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videoplayerlist);

        videoView1=findViewById(R.id.videoview1);
        videoView2=findViewById(R.id.videoview2);
        videoView3=findViewById(R.id.videoview3);
        videoView4=findViewById(R.id.videoview4);

        String numVideo = getIntent().getStringExtra("num");
        String videoName = getIntent().getStringExtra("videourilist");
        Uri videouri = Uri.parse(videoName);

        switch (numVideo){
            case "0":
                videoView1.setVideoURI(videouri);
                videoView1.start();
                break;
            case "1":
                videoView2.setVideoURI(videouri);
                videoView2.start();
                break;
            case "2":
                videoView3.setVideoURI(videouri);
                videoView3.start();
                break;
            case "3":
                videoView4.setVideoURI(videouri);
                videoView4.start();
                break;

        }
    }
}
