package com.aya.app.notivigation;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoPlayerList extends AppCompatActivity {

    private VideoView       videoView1,videoView2,videoView3,videoView4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videoplayerlist);

        videoView1=findViewById(R.id.videoview1);
        videoView2=findViewById(R.id.videoview2);

        String numVideo = getNumber()+"";

        String [] videoName = new String[5];
            videoName =getVideoList();
        Uri videouri  ;

        Toast.makeText(this, videoName[0] + "--" +videoName[1], Toast.LENGTH_SHORT).show();


        videouri = Uri.parse(videoName[1]);

        final MediaController mediacontroller = new MediaController(this);
        mediacontroller.setAnchorView(videoView1);


        videoView1.setMediaController(mediacontroller);

        videoView1.setVideoURI(videouri);
        videoView1.start();


    }

    private int getNumber () {
        SharedPreferences sh = getSharedPreferences("video", MODE_PRIVATE);
        int len = sh.getAll().size();

        return len-1 ;
    }
    private String [] getVideoList () {
        String [] video = new String[5];
        SharedPreferences sh_video = getSharedPreferences("videoList", MODE_PRIVATE);
        int len = sh_video.getAll().size();

        for(int i = 1 ; i< len ; i++) {
            String   videoShared = "videoUri" + i ;
            video [i] =  sh_video.getString(videoShared,"");
        }
        return video;
    }

}
