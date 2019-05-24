package com.example.a16073.bombcountdown;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

     TextView timer;
     SeekBar timerSeekBar;
    Button startBtn;
    Boolean counterActivated=false;
    CountDownTimer countDownTimer;

    public void resetTimer(){
        startBtn.setText("Start");
        timerSeekBar.setProgress(30);
        timerSeekBar.setEnabled(true);
        timer.setText("00:30");
        counterActivated=false;
        countDownTimer.cancel();
    }

    public void buttonClicked(View view){
        if(counterActivated){
            resetTimer();

        }else {
            counterActivated = true;
            startBtn = findViewById(R.id.startBtn);
            startBtn.setText("Stop");
            timerSeekBar.setEnabled(false);
            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000+100, 1000) {
                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    timer.setText("Done");
                    MediaPlayer airhornSound = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    airhornSound.start();
                    resetTimer();
                }
            }.start();
        }
    }

    public void updateTimer(int timeLeft){
        int minute=timeLeft/60;
        int second=timeLeft%60;
        if (second<10){
            timer.setText(Integer.toString(minute)+":0"+Integer.toString(second));
        }
        else{
            timer.setText(Integer.toString(minute)+":"+Integer.toString(second));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer=findViewById(R.id.TimerTextView);
        timerSeekBar=findViewById(R.id.TimerSeekBar);
        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




    }
}
