package com.example.parvez.friendstrivia;


import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
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
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class question extends ActionBarActivity {
    List<Questions> questionsList;
    String[] ques;
    String[] op1, op2, op3, op4, ans;
    TextView view,tv;
    Button button;
    RadioGroup rg;
    RadioButton rb1, rb2, rb3, rb4;
    int count=1, i = 0;
    int qno = 1;
    int flag;
    public static int correct,wrong,marks;
    Context context = this;
    ProgressBar bar;
    int total=0;
    MediaPlayer logoMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity);
        logoMusic = MediaPlayer.create(question.this,R.raw.question);
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

        if (ImageAdapter.character == 1) {
            final ArrayList<Integer> number = new ArrayList<Integer>();
            for (int i = 0; i < 15; ++i)
                number.add(i);
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

                        Intent myIntent = new Intent(question.this, Result.class);
                        question.this.startActivity(myIntent);

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

                for (int i = 0; i < 15; i++) {
                    questions[i] = questionsList.get(i);
                    ques[i] = questions[i].getQues();
                    op1[i] = questions[i].getOp1();
                    op2[i] = questions[i].getOp2();
                    op3[i] = questions[i].getOp3();
                    op4[i] = questions[i].getOp4();
                    ans[i] = questions[i].getAns();
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

        else if (ImageAdapter.character==2){

            final ArrayList<Integer> number = new ArrayList<Integer>();
            for (int i = 15; i <30 ; ++i)
                number.add(i);
            Collections.shuffle(number);
            flag = number.get(0);


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
                        timer.start();
                        flag = number.get(i);
                        rg.clearCheck();
                        view.setText(qno + "." + ques[flag]);
                        rb1.setText(op1[flag]);
                        rb2.setText(op2[flag]);
                        rb3.setText(op3[flag]);
                        rb4.setText(op4[flag]);
                        qno++;


                    } else {

                        marks = correct;

                        Intent myIntent = new Intent(question.this, Result.class);
                        question.this.startActivity(myIntent);

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

                for (int i = 15; i < 30; i++) {
                    questions[i] = questionsList.get(i);
                    ques[i] = questions[i].getQues();
                    op1[i] = questions[i].getOp1();
                    op2[i] = questions[i].getOp2();
                    op3[i] = questions[i].getOp3();
                    op4[i] = questions[i].getOp4();
                    ans[i] = questions[i].getAns();
                }
                count = 9;
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
}
