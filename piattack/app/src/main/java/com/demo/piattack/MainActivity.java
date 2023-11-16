package com.demo.piattack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 9527;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button Button1 = (Button) findViewById(R.id.button1);
        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isNLServiceEnabled()) {
                    Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
                    startActivityForResult(intent, REQUEST_CODE);
                } else {
                    showMsg("通知使用权已获取！");
                    toggleNotificationListenerService();
                }
            }
        });
    }

    public boolean isNLServiceEnabled() {
        Set<String> packageNames = NotificationManagerCompat.getEnabledListenerPackages(this);
        if (packageNames.contains(getPackageName())) {
            return true;
        }
        return false;
    }
    public void toggleNotificationListenerService() {
        PackageManager pm = getPackageManager();
        pm.setComponentEnabledSetting(new ComponentName(getApplicationContext(), NotifyService.class),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);

        pm.setComponentEnabledSetting(new ComponentName(getApplicationContext(), NotifyService.class),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (isNLServiceEnabled()) {
                showMsg("通知使用权已获取！");
                toggleNotificationListenerService();
            } else {
                showMsg("通知使用权未获取！");
            }
        }
    }

    private void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}