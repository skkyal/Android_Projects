package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar timerSeekBar;
    TextView timer;
    Button start;
    CountDownTimer countdown;
    boolean counter=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.timerButton);
        timer = (TextView) findViewById(R.id.timer);


        timerSeekBar = (SeekBar) findViewById(R.id.timerSeekBar);
        int max = 600;
        timerSeekBar.setMax(max);
        timerSeekBar.setProgress(30);
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                update(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                timerFunc();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                timerFunc();
            }
        });
    }

    public void update(int x){
        int minutes=x/60;
        int seconds=x%60;
        String s= Integer.toString(seconds);
        String m= Integer.toString(minutes);
        if(seconds<=9)
            s = "0"+s;
        if(minutes<=9)
            m= "0"+m;

        timer.setText(m+ ":" +s);
    }
    public void start(View view){
        timerFunc();
    }
    public void timerFunc(){
        if(counter) {
            counter=false;
           // timerSeekBar.setEnabled(true);
            start.setText("START");
            countdown.cancel();

        }
        else {
            counter = true;

            //timerSeekBar.setEnabled(false);
            start.setText("STOP");

            countdown=  new CountDownTimer(timerSeekBar.getProgress() * 1000 + 200, 1000) {
                @Override
                public void onTick(long l) {
                    update((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer audio = MediaPlayer.create(getApplicationContext(), R.raw.sound);
                    audio.start();
                    counter=false;
                    timerSeekBar.setEnabled(true);
                    start.setText("START");
                    timerSeekBar.setProgress(30);
                }
            }.start();
        }
    }

}
