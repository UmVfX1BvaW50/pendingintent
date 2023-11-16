package com.demo.pisys;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button Button2 = (Button) findViewById(R.id.button2);
        Button2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
                Intent intent = new Intent("com.demo.action.pengdingIntent_test");
                PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_MUTABLE);
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification notification = new NotificationCompat.Builder(MainActivity.this,"channelid1")
                        .setContentTitle("天气预报通知")
                        .setContentText("请点击再查看通知详情~")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setContentIntent(pi)
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setAutoCancel(true)
                        .build();
                NotificationChannel notificationChannel = new NotificationChannel("channelid1","channelname",NotificationManager.IMPORTANCE_HIGH);
                manager.createNotificationChannel(notificationChannel);
                manager.notify(1, notification);
            }
        });
    }
}