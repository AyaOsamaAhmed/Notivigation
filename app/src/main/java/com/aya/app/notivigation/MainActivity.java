package com.aya.app.notivigation;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Arrays;

//https://developer.android.com/training/notify-user/build-notification
//https://ocr.space/ocrapi
//https://a9t9.com/kantu/docs/visual-ui-testing?ref=ocrspace
public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "1";
    private int notificationID = 0;

    private static int VIDEO_REQUEST = 101;
    private         Uri  videoUri = null ;
    private String [] videoList ;
    private Uri [] videoListUri ;
    private int numVideos = 1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //make chanel notification if sdk 8.0.0
        createNotificationChannel();
        //create notification
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this , CHANNEL_ID);
        mBuilder.setSmallIcon(R.drawable.notivication);
        mBuilder.setContentTitle("Notification Alert, Click Me!");
        mBuilder.setContentText("Hi, This is Android Notification Detail!");

        //make intent when click on it
        Intent resultIntent = new Intent(this, MainActivity.class);  // move to
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);

        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        // To be notification appear
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(notificationID, mBuilder.build());

        // video list
        videoList = new String[5];
        videoListUri = new Uri[5];

    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    public void captureVideo(View view) {

        Intent  videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (videoIntent.resolveActivity(getPackageManager()) != null){

            startActivityForResult(videoIntent,VIDEO_REQUEST);
        }

    }

    public void playVideo(View view) {
        Intent  playvideo = new Intent(this , VideoPlayerActivity.class);
        playvideo.putExtra("videouri",videoUri.toString());
        startActivity(playvideo);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == VIDEO_REQUEST && resultCode == RESULT_OK){
            videoUri = data.getData();
            videoList[numVideos]=videoUri.toString(); // String
            videoListUri[numVideos]=videoUri;         // Uri
            // Record Number
            recordNumber(numVideos);

            // Video List
            SharedPreferences sh2 = getSharedPreferences("videoList", MODE_PRIVATE);
            SharedPreferences.Editor edit_video = sh2.edit();
            String   videoShared = "videoUri" + numVideos ;
            edit_video.putString( videoShared , videoUri.toString() );
            edit_video.apply();

            numVideos ++;
        }


    }

    private void recordNumber (int num){
        // Video List number
        SharedPreferences sh = getSharedPreferences("video", MODE_PRIVATE);
        SharedPreferences.Editor edit = sh.edit();
        String   videonum = "num" + num ;
        edit.putString(videonum,num + "");
        edit.apply();

    }

    private int getNumber () {
        SharedPreferences sh = getSharedPreferences("video", MODE_PRIVATE);
        int len = sh.getAll().size();

    return len-1 ;
    }
    public void playVideoList(View view) {

        videoList = new String[5];
        Toast.makeText(this,""+(numVideos-1),Toast.LENGTH_LONG).show();

        Intent  playvideo = new Intent(this , VideoPlayerList.class);
        playvideo.putExtra("num",(numVideos-1)+"");
        playvideo.putExtra("videourilist",videoList);
        startActivity(playvideo);


    }
}
