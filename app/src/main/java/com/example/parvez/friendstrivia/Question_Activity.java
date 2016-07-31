package com.example.parvez.friendstrivia;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Question_Activity extends ActionBarActivity {
    private List<Questions> questionsList;
    private String[] ques;
    private String[] op1, op2, op3, op4, ans;
    private TextView view,tv;
    private Button button;
    private RadioGroup rg;
    private RadioButton rb1, rb2, rb3, rb4;
    private int count=1, i = 0;
    private int qno = 1;
    private int flag;
    public static int correct,wrong,marks;
    private Context context = this;
    private ProgressBar bar;
    private int total=0;
    private MediaPlayer logoMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity);
        logoMusic = MediaPlayer.create(Question_Activity.this,R.raw.question);
        logoMusic.start();
        view = (TextView) findViewById(R.id.textView1);
        button = (Button) findViewById(R.id.button1);
        rg = (RadioGroup) findViewById(R.id.radioGroup1);
        rb1 = (RadioButton) findViewById(R.id.radioButton1);
        rb2 = (RadioButton) findViewById(R.id.radioButton2);
        rb3 = (RadioButton) findViewById(R.id.radioButton3);
        rb4 = (RadioButton) findViewById(R.id.radioButton4);
        bar = (ProgressBar) findViewById(R.id.progressBar);
        tv = (TextView) findViewById(R.id.textView2);

        final CountDownTimer timer=new CountDownTimer(15000, 1000) {
            public void onTick(long millisUntilFinished) {
                /*total = total+ 7;
                bar.setProgress(total);*/
                tv.setText(""+millisUntilFinished / 1000);
            }
            public void onFinish() {
                //bar.setProgress(100);
                button.performClick();
            }
        };

        if (ImageAdapter.character != -1) {
            final ArrayList<Integer> number = new ArrayList<Integer>();
            if (ImageAdapter.character == 1) {
                for (int i = 0; i < 15; ++i)
                    number.add(i);
            }
            else if (ImageAdapter.character == 2) {
                for (int i = 15; i < 30; ++i)
                    number.add(i);
            }
            else if (ImageAdapter.character == 3) {
                for (int i = 30; i < 45; ++i)
                    number.add(i);
            }
            else if (ImageAdapter.character == 4) {
                for (int i = 45; i < 60; ++i)
                    number.add(i);
            }

            else if (ImageAdapter.character == 5) {
                for (int i = 60; i < 75; ++i)
                    number.add(i);
            }
            else if (ImageAdapter.character == 6) {
                for (int i = 75; i < 90; ++i)
                    number.add(i);
            }

            Collections.shuffle(number);
            flag = number.get(0);
            bar.setProgress(0);
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RadioButton uans = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
                    String ansText = "";
                    if (uans != null) {
                        ansText = uans.getText().toString();
                    }
                    if (ansText.equalsIgnoreCase(ans[flag])) {
                        correct++;
                    } else {
                        wrong++;
                    }
                    flag++;
                    if (i < count) {
                        i++;
                        total = total+10;
                        bar.setProgress(total);
                        flag = number.get(i);
                        rg.clearCheck();
                        timer.start();
                        view.setText(qno + "." + ques[flag]);
                        rb1.setText(op1[flag]);
                        rb2.setText(op2[flag]);
                        rb3.setText(op3[flag]);
                        rb4.setText(op4[flag]);
                        qno++;
                    } else {
                        marks = correct;
                        Intent myIntent = new Intent(Question_Activity.this, Result_Activity.class);
                        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        Question_Activity.this.startActivity(myIntent);
                    }
                }
            };
            MyDatabaseHelper db = new MyDatabaseHelper(context);
            try {
                db.createDataBase();
                db.openDataBase();
            } catch (IOException e) {
                e.printStackTrace();
            }
            DataSource ds = new DataSource(context);
            try {
                ds.open();
                questionsList = ds.getAllQuestions();
                Questions[] questions = new Questions[questionsList.size()];
                ques = new String[questionsList.size()];
                op1 = new String[questionsList.size()];
                op2 = new String[questionsList.size()];
                op3 = new String[questionsList.size()];
                op4 = new String[questionsList.size()];
                ans = new String[questionsList.size()];
                if (ImageAdapter.character == 1) {
                    for (int i = 0; i < 15; i++) {
                        setquestion(i,questions);
                    }
                }
                else if (ImageAdapter.character == 2) {
                    for (int i = 15; i < 30; i++) {
                        setquestion(i,questions);
                    }
                }
                else if (ImageAdapter.character == 3) {
                    for (int i = 30; i < 45; i++) {
                        setquestion(i,questions);
                    }
                }
                else if (ImageAdapter.character == 4) {
                    for (int i = 45; i < 60; i++) {
                        setquestion(i,questions);
                    }
                }
                else if (ImageAdapter.character == 5) {
                    for (int i = 60; i < 75; i++) {
                        setquestion(i,questions);
                    }
                }
                else if (ImageAdapter.character == 6) {
                    for (int i = 75; i < 90; i++) {
                        setquestion(i,questions);
                    }
                }
                count =  9;
                timer.start();
                total =0;
                bar.setProgress(total);
                view.setText(qno + "." + ques[flag]);
                rb1.setText(op1[flag]);
                rb2.setText(op2[flag]);
                rb3.setText(op3[flag]);
                rb4.setText(op4[flag]);
                qno++;
                button.setOnClickListener(listener);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ds.close();
            db.close();
        }
    }

    private void setquestion(int i,Questions[] questions) {
        questions[i] = questionsList.get(i);
        ques[i] = questions[i].getQues();
        op1[i] = questions[i].getOp1();
        op2[i] = questions[i].getOp2();
        op3[i] = questions[i].getOp3();
        op4[i] = questions[i].getOp4();
        ans[i] = questions[i].getAns();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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
