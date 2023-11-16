package com.demo.pisys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Toast.makeText(this, "您已成功通过PendingIntent查看通知！", Toast.LENGTH_SHORT).show();
    }
}