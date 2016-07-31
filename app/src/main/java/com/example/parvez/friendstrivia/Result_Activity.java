package com.example.parvez.friendstrivia;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



import java.util.List;

public class Result_Activity extends Activity {
    private MediaPlayer logoMusic;
    private TextView tv;
    private Button btnRestart, share, twitter_button, fb_button;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        logoMusic = MediaPlayer.create(Result_Activity.this, R.raw.result);
        logoMusic.start();
        
        tv = (TextView) findViewById(R.id.textView1);
        btnRestart = (Button) findViewById(R.id.button1);
        share = (Button) findViewById(R.id.button2);
        twitter_button = (Button) findViewById(R.id.twitter_button);
        fb_button = (Button) findViewById(R.id.fb_button);

        StringBuffer sb = new StringBuffer();
        sb.append(Question_Activity.marks);

        tv.setText(sb);
        if (Question_Activity.correct == 10) {
            Toast.makeText(getApplicationContext(), "Perfect! Could you be Anymore Awesome?",
                    Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(getApplicationContext(), "Correct: " + Question_Activity.correct + " Wrong: " + Question_Activity.wrong,
                    Toast.LENGTH_LONG).show();
        
        Question_Activity.correct = 0;
        Question_Activity.wrong = 0;

        btnRestart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent in = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(in);
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Check Out this Awesome Friends Trivia App!";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "How you doin'");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });


        fb_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String urlToShare = "http://play.google.com/store/apps/details?id=com.parvezdev.friendstrivia";
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                // intent.putExtra(Intent.EXTRA_SUBJECT, "Foo bar"); // NB: has no effect!
                intent.putExtra(Intent.EXTRA_TEXT, urlToShare);
                // See if official Facebook app is found
                boolean facebookAppFound = false;
                List<ResolveInfo> matches = getPackageManager().queryIntentActivities(intent, 0);
                for (ResolveInfo info : matches) {
                    if (info.activityInfo.packageName.toLowerCase().startsWith("com.facebook.katana")) {
                        intent.setPackage(info.activityInfo.packageName);
                        facebookAppFound = true;
                        break;
                    }
                }
                // As fallback, launch sharer.php in a browser
                if (!facebookAppFound) {
                    String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + urlToShare;
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
                }
                startActivity(intent);
            }
        });


        twitter_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String urlToShare = "http://play.google.com/store/apps/details?id=com.parvezdev.friendstrivia";
                Intent tweetIntent = new Intent(Intent.ACTION_SEND);
                tweetIntent.putExtra(Intent.EXTRA_TEXT, "Check Out This Awesome Friends Trivia App! " +urlToShare );
                tweetIntent.setType("text/plain");

                PackageManager packManager = getPackageManager();
                List<ResolveInfo> resolvedInfoList = packManager.queryIntentActivities(tweetIntent, PackageManager.MATCH_DEFAULT_ONLY);

                boolean resolved = false;
                for (ResolveInfo resolveInfo : resolvedInfoList) {
                    if (resolveInfo.activityInfo.packageName.startsWith("com.twitter.android")) {
                        tweetIntent.setClassName(
                                resolveInfo.activityInfo.packageName,
                                resolveInfo.activityInfo.name);
                        resolved = true;
                        break;
                    }
                }
                if (resolved) {
                    startActivity(tweetIntent);
                } else {
                    Intent i = new Intent();
                    i.putExtra(Intent.EXTRA_TEXT, "Check this out!" + urlToShare);
                    i.setAction(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("https://twitter.com/intent/tweet?text="+ urlToShare));
                    startActivity(i);
                }
            }
        });

    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        logoMusic.release();

    }

    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);
        }
    }
}