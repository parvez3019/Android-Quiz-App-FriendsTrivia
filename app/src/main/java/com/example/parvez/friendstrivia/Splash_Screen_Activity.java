package com.example.parvez.friendstrivia;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Splash_Screen_Activity extends Activity {
    private MediaPlayer logoMusic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logoMusic = MediaPlayer.create(Splash_Screen_Activity.this,R.raw.splash);
        logoMusic.start();
        Thread logoTimer = new Thread() {
            public void run(){
                try{
                    int logoTimer = 0;
                    while(logoTimer <3000){
                        sleep(100);
                        logoTimer = logoTimer +100;
                    };
                    Intent myIntent = new Intent(Splash_Screen_Activity.this, MainActivity.class);

                    Splash_Screen_Activity.this.startActivity(myIntent);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                finally{
                    finish();
                }
            }
        };
        logoTimer.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        logoMusic.release();
    }
}






