package com.example.contentproviderass4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.contentproviderass4.databinding.ActivitySplashBinding;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
ActivitySplashBinding binding;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        binding=ActivitySplashBinding.inflate( getLayoutInflater() );
        setContentView( binding.getRoot() );
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run() {
                startActivity(new Intent(getBaseContext(), SendSMSActivity.class));
                finish();
            }}, 4000);

        binding.imageView.setOnClickListener( view -> {
            startActivity(new Intent(getBaseContext(), SendSMSActivity.class));
            finish();
        } );
    }
}