package com.zybooks.clockapp;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Timer extends AppCompatActivity {
    private TextView countdownTimer;
    private CountDownTimer timer;
    private long timeLeftInSecond = 3600000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        countdownTimer = findViewById(R.id.timer); //this line shows the textview from the activity_timer.xml

        timer = new CountDownTimer(timeLeftInSecond, 1000) {
            @Override
            public void onTick(long secondUntilFinished) {
                timeLeftInSecond = secondUntilFinished;
                updateCountdownText();
            }

            @Override
            public void onFinish() {
                // Timer finishes, do something here
            }
        }.start();
    }

    private void updateCountdownText() {
        int hours = (int) (timeLeftInSecond / 1000) / 3600;
        int minutes = (int) ((timeLeftInSecond / 1000) % 3600) / 60;
        int seconds = (int) (timeLeftInSecond / 1000) % 60;



        String timeLeftFormatted = String.format("%02d:%02d:%02d", hours, minutes,seconds);
        countdownTimer.setText(timeLeftFormatted);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }
}
