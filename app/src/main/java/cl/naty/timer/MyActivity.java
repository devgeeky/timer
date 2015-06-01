package cl.naty.timer;

import android.app.Activity;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;

import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.util.concurrent.TimeUnit;


public class MyActivity extends Activity{

    Button btnStart, btnStop, btnClear;
    TextView textViewTime, twoPoints, twoPoints2;
    EditText editTextTimeSec, editTextTimeMin, editTextTimeHou;
    CounterClass timer;
    boolean itsOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_my);

        //final MediaPlayer mp = MediaPlayer.create(this, R.raw.AudioFile1);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnClear = (Button) findViewById(R.id.btnClear);
        twoPoints = (TextView) findViewById(R.id.twoPoints);
        twoPoints2 = (TextView) findViewById(R.id.twoPoints2);
        textViewTime = (TextView) findViewById(R.id.textViewTime);
        editTextTimeSec = (EditText) findViewById(R.id.editTextTimeSec);
        editTextTimeMin = (EditText) findViewById(R.id.editTextTimeMin);
        editTextTimeHou = (EditText) findViewById(R.id.editTextTimeHou);
        textViewTime.setText("00:00:00");
        editTextTimeHou.setText("0");
        editTextTimeMin.setText("0");
        editTextTimeSec.setText("0");
        twoPoints.setText(":");
        twoPoints2.setText(":");
        itsOn = false;


        btnStart.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if(itsOn == true){
                timer.cancel();}
                int min,sec,hou,totalTime;
                if(!editTextTimeSec.getText().toString().equals("")){
                    sec = Integer.valueOf(editTextTimeSec.getText().toString());
                }
                else{
                    sec = 0;
                    editTextTimeSec.setText("0");
                }
                if(!editTextTimeMin.getText().toString().equals("")){
                    min = Integer.valueOf(editTextTimeMin.getText().toString());
                }
                else{
                    min = 0;
                    editTextTimeMin.setText("0");
                }
                if(!editTextTimeHou.getText().toString().equals(""))
                    hou = Integer.valueOf(editTextTimeHou.getText().toString());
                else{
                    hou = 0;
                    editTextTimeHou.setText("0");
                }
                totalTime = 1000 * (sec + 60 * (min + 60 * hou));
                if(sec>=60){
                    min=((int)(sec/60))+min;
                    sec=sec-60*((int)(sec/60));
                    editTextTimeMin.setText(min+"");
                    editTextTimeSec.setText(sec+"");
                    if(min>=60){
                        hou=((int)(min/60))+hou;
                        min=min-60*((int)min/60);
                        editTextTimeHou.setText(hou+"");
                        editTextTimeMin.setText(min+"");
                        editTextTimeSec.setText(sec+"");
                    }
                }
                timer = new CounterClass(totalTime, 1000);
                timer.start();
                itsOn = true;
            }
        });

        btnStop.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if(editTextTimeSec.getText().toString().equals("")) {
                    editTextTimeSec.setText("0");
                }
                if(editTextTimeMin.getText().toString().equals("")){
                    editTextTimeMin.setText("0");
                }
                if(editTextTimeHou.getText().toString().equals("")){
                    editTextTimeHou.setText("0");
                }
                timer.cancel();
                itsOn = false;
            }
        });

        btnClear.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if(editTextTimeSec.getText().toString().equals("")) {
                    editTextTimeSec.setText("0");
                }
                if(editTextTimeMin.getText().toString().equals("")){
                    editTextTimeMin.setText("0");
                }
                if(editTextTimeHou.getText().toString().equals("")){
                    editTextTimeHou.setText("0");
                }
                String timetext;
                if(itsOn == true){
                    timer.cancel();
                }
                int sec = Integer.valueOf(editTextTimeSec.getText().toString());
                int min = Integer.valueOf(editTextTimeMin.getText().toString());
                int hou = Integer.valueOf(editTextTimeHou.getText().toString());
                timetext = String.format("%02d:%02d:%02d",hou,min,sec);
                textViewTime.setText(timetext);
            }
        });

        editTextTimeHou.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if(itsOn==false) {
                    String timetext;
                    if (!hasFocus) {
                        if (editTextTimeHou.getText().toString().equals("")) {
                            editTextTimeHou.setText("0");
                        }
                        String text = (String) textViewTime.getText();
                        String[] times = text.split(":");
                        int h = Integer.valueOf(editTextTimeHou.getText().toString());
                        timetext = String.format("%02d:%02d:%02d", h, Integer.parseInt(times[1]), Integer.parseInt(times[2]));
                        if (h >= 10 || h == 0) {
                            editTextTimeHou.setText("" + h);
                        } else {
                            editTextTimeHou.setText("0" + h);
                        }

                        textViewTime.setText(timetext);
                    }
                }
            }
        });
        editTextTimeMin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if(itsOn==false){
                String timetext;
                if (!hasFocus) {
                    if (editTextTimeMin.getText().toString().equals("")) {
                        editTextTimeMin.setText("0");
                    }
                    String text = (String) textViewTime.getText();
                    String[] times = text.split(":");
                    int h = Integer.valueOf(editTextTimeHou.getText().toString());
                    int m = Integer.valueOf(editTextTimeMin.getText().toString());
                    if (m >= 60) {
                        editTextTimeHou.setText((int) (m / 60) + h + "");
                        times[0] = editTextTimeHou.getText().toString();
                        editTextTimeMin.setText(m - 60 * ((int) (m / 60)) + "");
                        m = m - 60 * ((int) (m / 60));
                    } else if (m >= 10 || m == 0) {
                        editTextTimeMin.setText("" + m);
                    } else {
                        editTextTimeMin.setText("0" + m);
                    }
                    timetext = String.format("%02d:%02d:%02d", Integer.parseInt(times[0]), m, Integer.parseInt(times[2]));
                    textViewTime.setText(timetext);
                }
                }
            }
        });
        editTextTimeSec.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (itsOn == false) {
                    String timetext;
                    if (!hasFocus) {
                        if (editTextTimeSec.getText().toString().equals("")) {
                            editTextTimeSec.setText("0");
                        }
                        String text = (String) textViewTime.getText();
                        String[] times = text.split(":");
                        int h = Integer.valueOf(editTextTimeHou.getText().toString());
                        int m = Integer.valueOf(editTextTimeMin.getText().toString());
                        int s = Integer.valueOf(editTextTimeSec.getText().toString());
                        if (s >= 60) {
                            m = ((int) (s / 60)) + m;
                            s = s - 60 * ((int) (s / 60));
                            editTextTimeMin.setText(m + "");
                            editTextTimeSec.setText(s + "");
                            times[1] = editTextTimeMin.getText().toString();
                            times[2] = editTextTimeSec.getText().toString();
                            if (m >= 60) {
                                h = ((int) (m / 60)) + h;
                                m = m - 60 * ((int) m / 60);
                                editTextTimeHou.setText(h + "");
                                editTextTimeMin.setText(m + "");
                                editTextTimeSec.setText(s + "");
                                times[0] = editTextTimeHou.getText().toString();
                                times[1] = editTextTimeMin.getText().toString();
                                times[2] = editTextTimeSec.getText().toString();
                            }
                        }

                        timetext = String.format("%02d:%02d:%02d", Integer.parseInt(times[0]), Integer.parseInt(times[1]), s);
                        textViewTime.setText(timetext);
                    }
                }
            }
        });
    }


    public class CounterClass extends CountDownTimer {

        final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.audiofile1);

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            String hms = String.format("%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
            System.out.println(hms);
            textViewTime.setText(hms);
        }

        @Override
        public void onFinish(){
            mp.start();
            textViewTime.setText("Time's up!");
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    mp.stop();
                }
            }, 1800);
        }



    }
}
