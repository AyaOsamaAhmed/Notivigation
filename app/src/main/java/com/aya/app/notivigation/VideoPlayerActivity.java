package com.aya.app.notivigation;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;

public class VideoPlayerActivity extends AppCompatActivity {

    private VideoView       videoView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videoplayer);
        videoView = findViewById(R.id.videoview);

        Uri videouri = Uri.parse(getIntent().getExtras().getString("videouri"));
        videoView.setVideoURI(videouri);
        videoView.start();
    }
}
