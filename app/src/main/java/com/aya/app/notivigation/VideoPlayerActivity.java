package com.aya.app.notivigation;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoPlayerActivity extends AppCompatActivity {

    private VideoView       videoView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videoplayer);
        videoView = findViewById(R.id.videoview);

        Uri videouri = Uri.parse(getIntent().getExtras().getString("videouri"));
        Toast.makeText(this, videouri+"", Toast.LENGTH_SHORT).show();

        final MediaController mediacontroller = new MediaController(this);

        mediacontroller.setAnchorView(videoView);
        videoView.setMediaController(mediacontroller);

        videoView.setVideoURI(videouri);
        videoView.requestFocus();
        videoView.start();
    }
}
